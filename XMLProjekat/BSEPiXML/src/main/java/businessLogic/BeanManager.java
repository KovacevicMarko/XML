package businessLogic;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentDescriptor;
import com.marklogic.client.document.DocumentMetadataPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;

import common.DatabaseConnection;
import common.JaxbXmlConverter;


/**
 * Klasa za crud operacije nad beanovima.
 * @author marko
 *
 * @param <T>
 */
public class BeanManager<T> 
{

    private DatabaseClient client;

    private XMLDocumentManager xmlManager;

    private SchemaFactory schemaFactory;

    private Schema schema;

    private DatabaseManager<T> databaseManager;

    private JaxbXmlConverter<T> converter;

    private QueryManager queryManager;
    
    private SearchManager searchManager;
    
    public BeanManager() {
        try {
            client = DatabaseConnection.getDbClient();
            xmlManager = client.newXMLDocumentManager();
            schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = schemaFactory.newSchema(new File("Schema/Akt.xsd"));
            converter = new JaxbXmlConverter<>();
            databaseManager = new DatabaseManager<>(client,xmlManager,schemaFactory,schema, converter);
            
            queryManager = new QueryManager(client, schema,converter);
            searchManager = new SearchManager();

        } catch (Exception e){
            System.out.println("Can't initialize Bean manager.");
            e.printStackTrace();
        }
    }

    public BeanManager(String schemaFilePath) {
        try {
            client = DatabaseConnection.getDbClient();
            xmlManager = client.newXMLDocumentManager();
            schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = schemaFactory.newSchema(new File(schemaFilePath));
            converter = new JaxbXmlConverter<>();
            databaseManager = new DatabaseManager<>(client,xmlManager,schemaFactory,schema, converter);

            queryManager = new QueryManager(client,schema, converter);
            searchManager = new SearchManager();
        } catch (Exception e){
        	e.printStackTrace();
            System.out.println("Can't initialize Bean manager.");
        }
    }

    /**
     * Upis fajla u bazu.
     */
    public boolean write(T bean, FileInputStream inputStream, String docId, String colId, boolean signFlag, String username) {
        return  databaseManager.writeFile(bean, inputStream,docId,colId, signFlag, username);
    }
    
    /**
     * Upis fajla u bazu po document template-u.
     */
    public DocumentDescriptor writeDocument(FileInputStream inputStream, String colId, boolean signFlag, String username) {
        return databaseManager.writeDocument(inputStream,colId, signFlag, username);
    }
    
    /**
     * Upis bean-a u bazu po document template-u.
     */
    public DocumentDescriptor writeDocument(T bean,String colId, boolean signFlag, String username) {
    	return databaseManager.write(bean,colId , signFlag, username);
    }


    /**
     * Upis beana u bazu.
     */
    public boolean write(T bean, String docId, String colId, boolean signFlag, String username) {
        return databaseManager.writeBean(bean,docId,colId, signFlag, username);
    }
    
    public boolean writeDocumentToArchive(T bean, String colId)
    {
    	return databaseManager.writeDocumentToArchive(bean, colId);
    }

    /**
     * Citanje xml dokumnta iz baze po DocId-u.
     */
    public T read(String docId, boolean signatureFlag){
        return databaseManager.read(docId, signatureFlag);
    }
    
    public Document read(boolean signatureFlag, String docId){
        return databaseManager.read(signatureFlag, docId);
    }
    
    public Document readDocumentFromArchive(String docId)
    {
    	return databaseManager.readDocumentFromArchive(docId);
    }

    /**
     * Konvertovanje fajla u jaxb bean.
     */
    public T convertFromXml(File file){
        return converter.convertFromXml(file,schema);
    }
    
    /**
     * Funkcija za vracanje bean-a u odnosu na dokument.
     * @param document
     * @return
     */
    public T getBeanByDocument(Document document)
    {
    	return databaseManager.getBeanByDocument(document);
    }

    /**
     * Konvertovanje jaxb beana u xml na tmp lokaciju.
     */
    public boolean convertToXml(T  bean){
       return converter.ConvertJaxbToXml(bean);
    }

    /**
     * Brisanje dokumentra za zadati docID.
     */
    public boolean deleteDocument(String docId){
        return  databaseManager.deleteDocument(docId);
    }

    /**
     * Azuriranje dokumentra za zadati docID i patchHandler.
     */
    public boolean updateDocument(String docId, DocumentMetadataPatchBuilder.PatchHandle patchHandle){
       return databaseManager.updateDocument(docId,patchHandle);
    }

    /**
     * Validacija Jaxb beanova preko seme.
     */
    public boolean validateBeanBySchema(T bean){
        return databaseManager.validateBeanBySchema(bean);
    }

    /**
     * Validacija xml dokumenata preko seme.
     */
    public boolean validateXmlBySchema(String filePath){
        return databaseManager.validateXmlBySchema(filePath);
    }

    /**
     * Validacija potpisanog xml dokumentra iz tmp.xml.
     */
    public boolean validateXMLBySignature(String filepath){
        return databaseManager.validateXMLBySignature(filepath);
    }

    /**
	 * Izvrsavanje XQuery-a.
     */
    public ArrayList<T> executeQuery(String query){
        return queryManager.executeQuery(query);
    }
    
    public HashMap<String,ArrayList<String>> searchByContent(String content, String uriOfCollection)
    {
    	return searchManager.documentsMatchedWithParametar(content, uriOfCollection, databaseManager);
    }
    
    public HashMap<String,ArrayList<String>> searchByContentAndTag(String content, String uriOfCollection, String tag)
    {
    	return searchManager.documentsMatchedWithParametarAndTag(tag, content, uriOfCollection, databaseManager);
    }
}
