package businessLogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
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
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentMetadataPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;
import com.marklogic.client.io.JAXBHandle;
import common.JaxbXmlConverter;
import common.ValidationXmlSchema;

/**
 * Klasa za rad sa osnovnim operacijama baze podataka, kao i za validaciju.
 * @author marko
 *
 */
public class DatabaseManager<T> {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);

    private XMLDocumentManager xmlManager;

    private SchemaFactory schemaFactory;

    private Schema schema;

    private DatabaseClient client;

    private JaxbXmlConverter<T> converter;
    
    private static TransformerFactory transformerFactory;
    
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
    public boolean writeFile(FileInputStream inputStream, String docId, String colId) {
        boolean ret = false;
        try{
            //if (!singXml(null)) {
            //    throw  new Exception("Could not sign xml, check tmp.xml.");
            //}
            InputStreamHandle handle = new InputStreamHandle(inputStream);
            DocumentMetadataHandle metadata = new DocumentMetadataHandle();
            metadata.getCollections().add(colId);
            xmlManager.write(docId,metadata,handle);
            ret = true;
        }
        catch (Exception e){
            logger.info("Unexpected error: " + e.getMessage());
            //System.out.println("Unexpected error: " + e.getMessage());
        } finally{
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
    public boolean writeBean(T bean, String docId, String colId) {
        boolean ret = false;
        try {
            // Try to convert to xml on default location.
            if (converter.ConvertJaxbToXml(bean)){
                FileInputStream inputStream = new FileInputStream(new File("tmp.xml"));
                ret = writeFile(inputStream,docId,colId);
            } else {
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
    
    /*
     * Operacija za citanje iz baze.
     */
    
    /**
     * Citanje XML dokumenta iz baze za zadati docID. 
     * @param docId
     * @return
     */
    public T read(String docId){
        T ret = null;
        try{
            JAXBContext jc = JAXBContext.newInstance("model");
            JAXBHandle<T> handle = new JAXBHandle<>(jc);

            // A metadata handle for metadata retrieval
            DocumentMetadataHandle metadata = new DocumentMetadataHandle();
            // Read real data when it is validated.
            xmlManager.read(docId,metadata,handle);

            ret = handle.get();
            // Convert bean to tmp.xml so that you can validateBeanBySchema it.
            if (!converter.ConvertJaxbToXml(ret)){
                ret = null;
                throw  new Exception("Could not convert bean to xml!");
            }
        }
        catch (Exception e) {
            //System.out.println("Unexpected error: " + e.getMessage());
            logger.info("ERROR: Unexpected error: " + e.getMessage());
        } finally {
            return ret;
        }
    }

    /**
     * Validacija potipsanog tmp.xml dokumenta.  
     * @param filepath
     * @return
     */
    public boolean validateXMLBySignature(String filepath){
        boolean ret = false;

        try{
        	//TODO
            /*VerifySignatureEnveloped verifySignatureEnveloped = new VerifySignatureEnveloped();
            Document document = verifySignatureEnveloped.loadDocument(filepath);
            ret =  verifySignatureEnveloped.verifySignature(document);*/
        } catch(Exception e){
            logger.info("ERROR: Unexpected error: " + e.getMessage());
        } finally {
            return  ret;
        }
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
            FileOutputStream fileOutputStream = new FileOutputStream("tmp.xml");
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
            T tmpAkt = (T) JAXBIntrospector.getValue(unmarshaller.unmarshal(new File("tmp.xml")));
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

	
}
