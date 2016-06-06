package businessLogic;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentMetadataPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;

import common.JaxbXmlConverter;
import common.DatabaseConnection;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;


/**
 * Klasa za crud operacije nad beanovima.
 * @author marko
 *
 * @param <T>
 */
public class BeanManager<T> 
{
	
    private final String DOC_ID_DEFAULT = "default/document";

    private final String COL_ID_DEFAULT = "default/collection";

    private DatabaseClient client;

    private XMLDocumentManager xmlManager;

    private SchemaFactory schemaFactory;

    private Schema schema;

    private DatabaseManager<T> databaseManager;

    private JaxbXmlConverter<T> converter;

    private QueryManager queryManager;
    
    public BeanManager() {
        try {
            client = DatabaseConnection.getDbClient();
            xmlManager = client.newXMLDocumentManager();
            schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = schemaFactory.newSchema(new File("Schema/Akt.xsd"));
            converter = new JaxbXmlConverter<>();
            databaseManager = new DatabaseManager<>(client,xmlManager,schemaFactory,schema, converter);
            
            queryManager = new QueryManager(client, schema,converter);

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
        } catch (Exception e){
        	e.printStackTrace();
            System.out.println("Can't initialize Bean manager.");
        }
    }

    /**
     * Upis fajla u bazu.
     */
    public boolean write(FileInputStream inputStream, String docId, String colId) {
        return  databaseManager.writeFile(inputStream,docId,colId);
    }


    /**
     * Upis beana u bazu.
     */
    public boolean write(T bean, String docId, String colId) {
        return databaseManager.writeBean(bean,docId,colId);
    }

    /**
     * Citanje xml dokumnta iz baze po DocId-u.
     */
    public T read(String docId){
        return databaseManager.read(docId);
    }

    /**
     * Konvertovanje fajla u jaxb bean.
     */
    public T convertFromXml(File file){
        return converter.convertFromXml(file,schema);
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

}