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
	public static String USERS_COL_ID="/ColKorisnici";
	
	public static String AKT_PREDLOZEN_DOC_ID="/AktPredlozen.xml";
	public static String AKT_PREDLOZEN_COL_ID="/ColAktPredlozen";
	
	public static String AKT_USVOJEN_POJEDINOSTI_COL_ID="/ColAktUsvojenPojedinosti";
	public static String AKT_USVOJEN_CELOSTI_COL_ID="/ColAktUsvojenCelosti";
	public static String AKT_USVOJEN_NACELO_COL_ID="/ColAktUsvojenNacelu";
	
	public static String AKT_BACKUP_COL_ID = "/ColAktBackup";
	
	public static String AKT_USVOJEN_DOC_ID="/AktUsvojen.xml";
	public static String AKT_USVOJEN_COL_ID="/ColAktUsvojen";
	
	public static String AKT_ENCRYPT_DOC_ID="/AktEncrypt.xml";
	public static String AKT_ENCRYPT_COL_ID="/ColEncryptAkt";
	
	public static String AMANDMAN_PREDLOZEN_DOC_ID="/AmandmanPredlozen.xml";
	public static String AMANDMAN_PREDLOZEN_COL_ID="/ColAmandmanPredlozen";
	
	public static String AMANDMAN_USVOJEN_DOC_ID="/AmandmanUsvojen.xml";
	public static String AMANDMAN_USVOJEN_COL_ID="/ColAmandmanUsvojen";
	
	public static String ARHIV_AKT_USVOJEN_COL_ID="/IAGNSAkt";
	public static String ARHIV_AMANDMAN_USVOJEN_COL_ID="/IAGNSAmandman";

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
