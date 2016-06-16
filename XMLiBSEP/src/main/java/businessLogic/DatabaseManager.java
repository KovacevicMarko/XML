package businessLogic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import model.Akt;
import model.Amandman;
import model.Korisnici;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.Database;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import rest.controllers.ArhivController;
import securityPackage.DecryptKEK;
import securityPackage.EncryptKEK;
import securityPackage.SignEnveloped;
import securityPackage.VerifySignatureEnveloped;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentDescriptor;
import com.marklogic.client.document.DocumentMetadataPatchBuilder;
import com.marklogic.client.document.DocumentUriTemplate;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.StringQueryDefinition;

import common.DatabaseConnection;
import common.JaxbXmlConverter;
import common.ValidationXmlSchema;

/**
 * Klasa za rad sa osnovnim operacijama baze podataka, kao i za validaciju.
 * @author marko
 *
 */
public class DatabaseManager<T> {
	
	@Autowired
	private ArhivController arhivController;

	private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);

    private XMLDocumentManager xmlManager;

    private SchemaFactory schemaFactory;

    private Schema schema;

    private DatabaseClient client;

    private JaxbXmlConverter<T> converter;
    
    private static TransformerFactory transformerFactory;
        
    private static String INPUT_OUTPUT_TMP_FILE = "tmp.xml";
    
    static {
        transformerFactory = TransformerFactory.newInstance();
    }

    public DatabaseManager(DatabaseClient client, XMLDocumentManager xmlManager, SchemaFactory schemaFactory, Schema schema, JaxbXmlConverter converter)
    {
        this.client = client;
        this.xmlManager = xmlManager;
        this.schemaFactory = schemaFactory;
        this.schema = schema;
        this.converter = converter;
    }
  
    /*
     * Operacije za snimanje u bazu.
     */
    /**
     * Snimanje fajla u bazu.
     * @param inputStream
     * @param docId
     * @param colId
     * @return
     */
    public boolean writeFile(T bean, FileInputStream inputStream, String docId, String colId, boolean signFlag, String username) {
        boolean ret = false;
        try{
        	
    		if (signFlag &&!singXml(null, username)) 
    		{
                throw  new Exception("Could not sign xml, check tmp.xml.");
            }
        	
    		String tmpColId = null;
    		
    		if(bean instanceof Akt)
    		{
    			tmpColId = DatabaseConnection.AKT_USVOJEN_COL_ID;
    		}
    		else if(bean instanceof Amandman)
    		{
    			tmpColId = DatabaseConnection.AMANDMAN_USVOJEN_COL_ID;
    		}
            /*if (colId.equals(tmpColId) && !encriptContent())
            {
                throw  new Exception("Could not encrypt xml, check tmp.xml.");
            }*/
            
            InputStreamHandle handle = new InputStreamHandle(inputStream);
            DocumentMetadataHandle metadata = new DocumentMetadataHandle();
            metadata.getCollections().add(colId);
            xmlManager.write(docId,metadata,handle);
            ret = true;
        }
        catch (Exception e){
            logger.info("Unexpected error: " + e.getMessage());
            //System.out.println("Unexpected error: " + e.getMessage());
        }finally{
        	return ret;
        }
    }

    /**
     * Snimanje bean-a u bazu.
     * @param bean
     * @param docId
     * @param colId
     * @return
     */
    public boolean writeBean(T bean, String docId, String colId, boolean signFlag, String username) {
        boolean ret = false;
        try
        {
        	if(signFlag && !writeTimeStamp(bean))
        	{
        		System.out.println("Can`t to write timestamp!");
        		return false;
        	}
        	
        	if(!validateBeanBySchema(bean)){
        		logger.info("Can`t to validate bean by schema!");
        		System.out.println("Can`t to validate bean by schema!");
        		return false;
        	}
            // Try to convert to xml on default location.
            if (converter.ConvertJaxbToXml(bean))
            {
                FileInputStream inputStream = new FileInputStream(new File(INPUT_OUTPUT_TMP_FILE));
                ret = writeFile(bean,inputStream,docId,colId, signFlag, username);
            } 
            else 
            {
                throw new Exception("Can't convert JAXB bean " + bean.toString() + " to XML.");
            }
        }
        catch (Exception e) {
            logger.info("ERROR: Unexpected error: " + e.getMessage());
        }
        finally{
            return ret;
        }
    }
    
    /**
     * Upisivanje bean-a u bazu podataka sa template docId-em.
     * @param
     * @param
     * @return
     */
    public DocumentDescriptor write(T bean,String colId, boolean signFlag, String username) {
        DocumentDescriptor ret = null;
        try {
        	//ako su korisnici u pitanju, ne treba timestamp 
        	if(signFlag && !writeTimeStamp(bean))
        	{
        		System.out.println("Can`t to write id and timestamp!");
        		return null;
        	}
        	if(!validateBeanBySchema(bean)){
        		logger.info("Can`t to validate bean by schema!");
        		System.out.println("Can`t to validate bean by schema!");
        		return null;
        	}
            // Try to convert to xml on default location.
            if (converter.ConvertJaxbToXml(bean)){
                FileInputStream inputStream = new FileInputStream(new File(INPUT_OUTPUT_TMP_FILE));
                ret = writeDocument(inputStream,colId, signFlag, username);
                boolean flagSet = setIdToBean(bean, ret.getUri(), username, colId);
            } else {
                throw new Exception(" Can't convert JAXB bean to XML.");
            }
        }
        catch (Exception e) {

        }
        finally{
            return ret;
        }
    }
   
    
    /**
     * Upisivanje fajla-a u bazu podataka sa template docId-em.
     * @param
     * @param
     * @return
     */
    public DocumentDescriptor writeDocument(FileInputStream inputStream, String colId, boolean signFlag, String username) {
        DocumentDescriptor ret = null;
        try{       
            
            DocumentUriTemplate template = xmlManager.newDocumentUriTemplate("xml");
            DocumentMetadataHandle metadata = new DocumentMetadataHandle();
            metadata.getCollections().add(colId);
            InputStreamHandle handle = new InputStreamHandle(inputStream);
            ret = xmlManager.create(template,metadata, handle);
        }
        catch (Exception e){
            logger.info("Could not write xml bean.");
        } finally{
            return ret;
        }
    }
    
    public boolean writeDocumentToArchive(T bean, String colId)
    {
    	if (converter.ConvertJaxbToXml(bean))
    	{
    		FileInputStream inputStream = null;
			try {
				inputStream = new FileInputStream(new File(INPUT_OUTPUT_TMP_FILE));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    		if (!encriptContent())
            {
    			return false;
            }
    		
    		InputStreamHandle handle = new InputStreamHandle(inputStream);
            DocumentMetadataHandle metadata = new DocumentMetadataHandle();
            metadata.getCollections().add(colId);
            String docId = "";
            if(bean instanceof Akt){
            	docId = ((Akt) bean).getId();
            }else if(bean instanceof Amandman){
            	docId = ((Amandman) bean).getId();
            }else{
            	System.out.println("Ne validan bean!");
            	return false;
            }
            xmlManager.write(docId,metadata,handle);
    		
    		/*DocumentUriTemplate template = xmlManager.newDocumentUriTemplate("xml");
            DocumentMetadataHandle metadata = new DocumentMetadataHandle();
            metadata.getCollections().add(colId);
            InputStreamHandle handle = new InputStreamHandle(inputStream);
            xmlManager.create(template,metadata, handle);*/
            
            return true;
    	}
    	return false;
    }
    
    /**
     * Funkcija za vracanje bean-a u odnosu na document.
     * @param docId
     * @return
     */
    
    public T getBeanByDocument(Document document)
    {
    	saveDocument(document);
		T retValue = converter.convertFromXml(new File(INPUT_OUTPUT_TMP_FILE), schema);
		
		return retValue;
    }
    
    /*
     * Operacija za citanje iz baze.
     */
    
    public Document readDocumentFromArchive(String docId)
    {
    	Document ret = null;
        // A metadata handle for metadata retrieval
        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        // A handle to receive the document's content.
        DOMHandle content = new DOMHandle();
        xmlManager.read(docId, metadata, content);

        ret = content.get();
        	
    	Document decrypredDoc = decryptContenc(ret); 
    	System.out.println("**********************" + decrypredDoc.toString());
        VerifySignatureEnveloped verifySignatureEnveloped = new VerifySignatureEnveloped();
        if (!verifySignatureEnveloped.verifySignature(decrypredDoc))
        {
            ret = null;
        }
     
        return ret;
    }
    
    /**
     * Citanje XML dokumenta iz baze za zadati docID. 
     * @param docId
     * @return
     */
    public T read(String docId, boolean signatureFlag){
        T ret = null;
        try{
            JAXBContext jc = JAXBContext.newInstance("model");
            JAXBHandle<T> handle = new JAXBHandle<>(jc);
            
            if(signatureFlag && !validateXMLBySignature(docId)){
            	ret = null;
            	throw new Exception("Could not validate xml by signature.");
            }
            System.out.println("Uspesno validiran xml po potpisu");

            // A metadata handle for metadata retrieval
            DocumentMetadataHandle metadata = new DocumentMetadataHandle();
            // Read real data when it is validated.
            xmlManager.read(docId,metadata,handle);

            ret = handle.get();

            if(!validateBeanBySchema(ret))
            {
            	System.out.println("Ne uspesna validacija beana po semi");
            	throw  new Exception("Could not bean bean by schema!");
            }
            if(ret!= null)
            {
            	 System.out.println("Uspesno validiran bean po semi!");
            }
           
        }
        catch (Exception e) {

        } finally {
            return ret;
        }
    }
        
    public Document read(boolean signatureFlag, String docId)
    {
        Document ret = null;
        // A metadata handle for metadata retrieval
        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        // A handle to receive the document's content.
        DOMHandle content = new DOMHandle();
        xmlManager.read(docId, metadata, content);

        ret = content.get();
        if (signatureFlag)
        {	
        	
        	Document decrypredDoc = decryptContenc(ret); 
        	System.out.println("**********************" + decrypredDoc.toString());
            VerifySignatureEnveloped verifySignatureEnveloped = new VerifySignatureEnveloped();
            if (!verifySignatureEnveloped.verifySignature(decrypredDoc))
            {
                ret = null;
            }
        }
        return ret;
    }
        
    /**
     * Citanje dokumenta iz baze i smestanje u tmp file radi njegove validacije.
     * @param docId
     * @return
     */
    private boolean convertInputToTmp(String docId){
        boolean ret = false;
        try{
            // A metadata handle for metadata retrieval
            DocumentMetadataHandle metadata = new DocumentMetadataHandle();
            // A handle to receive the document's content.
            DOMHandle content = new DOMHandle();
            xmlManager.read(docId, metadata, content);

            Document doc = content.get();
            FileOutputStream fileOutputStream = new FileOutputStream(INPUT_OUTPUT_TMP_FILE);
            transform(doc, fileOutputStream);
            ret = true;

        } catch (Exception e){
            logger.info("ERROR: Unexpected error: " + e.getMessage());

        } finally {
            return ret;
        }
    }

    /**
     * Serijalizacija DOM stabla u OutputStream.
     * @param node
     * @param out
     */
    private static void transform(Node node, OutputStream out) {
        try {

            // Kreiranje instance objekta zaduzenog za serijalizaciju DOM modela
            Transformer transformer = transformerFactory.newTransformer();

            // Indentacija serijalizovanog izlaza
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Nad "source" objektom (DOM stablo) vrši se transformacija
            DOMSource source = new DOMSource(node);

            // Rezultujući stream (argument metode)
            StreamResult result = new StreamResult(out);

            // Poziv metode koja vrši opisanu transformaciju
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Operacija za brisanje iz baze.
     */
    
    /**
     * Brisanje dokumenta za zadati DocId.
     */
    public boolean deleteDocument(String docId){
        boolean ret = false;
        try {
            xmlManager.delete(docId);
            ret = true;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            return  ret;
        }
    }

    /*
     * Operacije za azuriranje dokumenta u bazi.
     */
    
    /**
     * Azuriranje dokumenta sa zadatim docId i patchHandlom.
     * @param
     * @param 
     * @return
     */
    public boolean updateDocument(String docId, DocumentMetadataPatchBuilder.PatchHandle patchHandle){
        boolean ret = false;
        try{
            xmlManager.patch(docId,patchHandle);
            ret = true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return  ret;
        }
    }

    /*
     * Operacije za validaciju.
     */
    
    /**
     * Validacija beanova preko seme.
     * @param
     * @return
     */
    public boolean validateBeanBySchema(T bean){
        boolean ret = false;

        try{
            if (!(bean instanceof Akt) && !(bean instanceof Amandman) && !(bean instanceof Korisnici)){
                throw  new Exception("Can't validateBeanBySchema element that is not Akt, Amandman or Korisnik!");
            }
            JAXBContext context = JAXBContext.newInstance("model");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            converter.ConvertJaxbToXml(bean);
            // Podešavanje unmarshaller-a za XML schema validaciju
            unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new ValidationXmlSchema());
            T tmpAkt = (T) JAXBIntrospector.getValue(unmarshaller.unmarshal(new File(INPUT_OUTPUT_TMP_FILE)));
            ret = true;
        } catch (Exception e){
            logger.debug("Unexpected error: " +e.getMessage());
            e.printStackTrace();
        }finally {
            return ret;
        }
    }

    /**
     * Validacija xml dokumenta putem seme.
     * @param
     * @return
     */
    public boolean validateXmlBySchema(String filePath){
        boolean ret = false;

        try{
            JAXBContext context = JAXBContext.newInstance("model");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // Podešavanje unmarshaller-a za XML schema validaciju
            unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new ValidationXmlSchema());
            T tmpAkt = (T) JAXBIntrospector.getValue(unmarshaller.unmarshal(new File(filePath)));
            ret = true;
        } catch (Exception e){
            logger.info("ERROR: Unexpceted error: " + e.getMessage());
        }finally {
            return ret;
        }
    }
    
    
    /*
     * Pomocne metode.
     */
    private boolean writeTimeStamp(T bean)
    {
    	Date date = new Date();
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		
    	if(bean instanceof Akt)
    	{    		
    		try {
    			((Akt) bean).setTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
    		} catch (DatatypeConfigurationException e) {
    			e.printStackTrace();
    		}
    		
    		return true;
    	}
    	else if(bean instanceof Amandman)
    	{    		
    		try {
    			((Amandman) bean).setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
    		} catch (DatatypeConfigurationException e) {
    			e.printStackTrace();
    		}
    		
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	
    }
    
    /**
     * Upisi id u bean ako je u pitanju akt ili amandman.
     * @param bean
     * @param id
     * @return
     */
    private boolean setIdToBean(T bean, String idDoc, String username, String colId)
    {
    	String id = idDoc;
    	try
    	{
    		id = idDoc.split("\\.")[0];
    	}catch(Exception ex){
    		
    	}

    	if(bean instanceof Akt)
    	{
    		((Akt) bean).setId(id);
    		writeBean(bean, idDoc, colId, true, username);
    		return true;
    	}
    	else if(bean instanceof Amandman)
    	{
    		((Amandman) bean).setId(id);
    		writeBean(bean, idDoc, colId, true, username);
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /**
     * 
     * @param filePath
     * @return
     */
    private boolean singXml(String filePath, String username){

        boolean ret = false;

        try{

            SignEnveloped signEnveloped = new SignEnveloped();
            signEnveloped.setUsername(username);

            Document document;
            if (filePath == null) 
            {
                document  =signEnveloped.loadDocument(INPUT_OUTPUT_TMP_FILE);
            } 
            else 
            {
                document = signEnveloped.loadDocument(filePath);
            }
            
            PrivateKey pk = signEnveloped.readPrivateKey();
            Certificate cert = signEnveloped.readCertificate();
            document = signEnveloped.signDocument(document,pk,cert);
            signEnveloped.saveDocument(document, INPUT_OUTPUT_TMP_FILE);
            ret = true;

        } catch (Exception e){
            System.out.println("[DatabaseManager] Unexpected error: " +e.getMessage());
        } finally {
            return  ret;
        }
    }
    
    private boolean encriptContent()
    {
    	boolean ret = false;
    	try{
    		
    		EncryptKEK encryptKek = new EncryptKEK();
    		Document document;
    		document  = encryptKek.loadDocument(INPUT_OUTPUT_TMP_FILE);

            System.out.println("Generating secret key ....");
            SecretKey secretKey = encryptKek.generateDataEncryptionKey();
            
            Certificate cert = encryptKek.readCertificate();
            System.out.println("Encrypting....");
            document = encryptKek.encrypt(document ,secretKey, cert);
            encryptKek.saveDocument(document, INPUT_OUTPUT_TMP_FILE);
            arhivController.saveAkt(document);
            return true;
    		
    	} catch(Exception e){
    		System.out.println("[DatabaseManager] Unexpected error: " +e.getMessage());
    		return false;
    	}
    }
    
    private Document decryptContenc(Document doc)
    {
    	Document retValue = null;
    	
    	try
    	{
    		DecryptKEK decript = new DecryptKEK();
    		PrivateKey privateKey = decript.readPrivateKey();
    		Document document = decript.decrypt(doc, privateKey);
    		decript.saveDocument(document, INPUT_OUTPUT_TMP_FILE);
    		retValue = document;
    	}
    	catch(Exception ex)
    	{
    		System.out.println("Ne uspesna dekripcija!");
    		retValue = null;
    	}
    	
    	return retValue;
    }
    
    /**
     * Validacija potipsanog tmp.xml dokumenta.  
     * @param filepath
     * @return
     */
    public boolean validateXMLBySignature(String docId){
        boolean ret = false;

        try{
        	// A metadata handle for metadata retrieval
            DocumentMetadataHandle metadata = new DocumentMetadataHandle();
            // A handle to receive the document's content.
            DOMHandle content = new DOMHandle();
            xmlManager.read(docId, metadata, content);

            Document doc = content.get();
        	
            VerifySignatureEnveloped verifySignatureEnveloped = new VerifySignatureEnveloped();
            ret =  verifySignatureEnveloped.verifySignature(doc);
        } catch(Exception e){
            System.out.println("Ne validan format!");
        } finally {
            return  ret;
        }
    }
    
    /*
     * Operacije za pretragu.
     */
    
    /**
     * Pretraga kolekcije po vrednosti sadrzaja.
     * */
    public MatchDocumentSummary[] searchByField(String parameterOfSearch, String uriOfCollection){

        // Initialize query manager
        com.marklogic.client.query.QueryManager queryManager = client.newQueryManager();

        // Query definition is used to specify Google-style query string
        StringQueryDefinition queryDefinition = queryManager.newStringDefinition();

        // Set the criteria
        queryDefinition.setCriteria(parameterOfSearch);

        // Search within a specific collection
        queryDefinition.setCollections(uriOfCollection);

        // Perform search
        SearchHandle results = queryManager.search(queryDefinition, new SearchHandle());

        // Serialize search results to the standard output
        MatchDocumentSummary matches[] = results.getMatchResults();

        return matches;
    }
    
    /**
	 * Kreira DOM od XML dokumenta
	 */
	private Document loadDocument() {
		try {
			String file = INPUT_OUTPUT_TMP_FILE;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File(file));

			return document;
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Snima DOM u XML fajl 
	 */
	private void saveDocument(Document doc) 
	{
		try {
			String fileName = INPUT_OUTPUT_TMP_FILE;
			File outFile = new File(fileName);
			FileOutputStream f = new FileOutputStream(outFile);

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f);
			
			transformer.transform(source, result);

			f.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
