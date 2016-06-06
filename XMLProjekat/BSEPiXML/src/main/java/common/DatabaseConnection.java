package common;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;

/**
 * Singleton patern za konekciju ka bazi. 
 * @author marko
 *
 */
public class DatabaseConnection
{
	
	public static String USERS_DOC_ID="/Korisnici.xml";
	public static String USERS_COL_ID="ColKorisnici";
	
	public static String AKT_DOC_ID="/Akt.xml";
	public static String AKT_COL_ID="/ColAkt";

    private static DatabaseClient client = null;

    public static DatabaseClient getDbClient() {
        if (client == null){
            client = initializeClient();
        }

        return client;
    }

    /**
     * Inicijalizacija konekcije kroz parametre.
     * @return
     */
    private static DatabaseClient initializeClient(){
        Util.ConnectionProperties props = null;
        DatabaseClient client = null;
        try {
            props = Util.loadProperties();
            client = DatabaseClientFactory.newClient(props.host, props.port, props.database, props.user, props.password, props.authType);
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            return client;
        }

    }

}
