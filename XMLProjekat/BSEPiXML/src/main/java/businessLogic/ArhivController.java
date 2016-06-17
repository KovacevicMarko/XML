package businessLogic;

import java.security.PrivateKey;
import java.util.Random;

import model.Akt;
import model.Amandman;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import securityPackage.DecryptKEK;

import common.DatabaseConnection;

@RestController
@RequestMapping(value = "/IstorijskiArhiv/")
public class ArhivController 
{
	private Akt lastSavedAkt;
	private Amandman lastSavedAmandman;
	private static String INPUT_OUTPUT_FILE = "tmp.xml";
	private static String IAGNS = "iagns";
	private static String SGNS = "sgns";
	
	@RequestMapping(value = "/saveAkt/", method = RequestMethod.GET)
	public void saveAkt(Document doc)
	{
		
		BeanManager<Akt> bm = new BeanManager<Akt>();
		//Document doc = bm.read(false, )
		DecryptKEK dec = new DecryptKEK();
		PrivateKey pk = dec.readPrivateKey(IAGNS);
		Document dock = dec.decrypt(doc, pk);
		Akt akt = bm.getBeanByDocument(dock);
		
		if(akt.getId().isEmpty() || akt.getId() == null)
		{
			Random rand = new Random();
			akt.setId(Integer.toString(rand.nextInt(Integer.MAX_VALUE)));
		}
		//provera zbog provg puta kad se digne server
		if(lastSavedAkt!=null)
		{
			if(lastSavedAkt.getId().equals((akt.getId())) || lastSavedAkt.getTimeStamp().equals(akt.getTimeStamp()))
			{
				System.out.println("Replay!");
				return;
			}
		}
		bm.writeDocumentToArchive(akt, DatabaseConnection.ARHIV_AKT_USVOJEN_COL_ID);
		lastSavedAkt = akt;
	}
	
	@RequestMapping(value = "/saveAmandman", method = RequestMethod.POST)
	public void saveAmandman(Document doc)
	{
		BeanManager<Amandman> bm = new BeanManager<Amandman>();
		DecryptKEK dec = new DecryptKEK();
		PrivateKey pk = dec.readPrivateKey(IAGNS);
		Document dock = dec.decrypt(doc, pk);
		Amandman amandman = bm.getBeanByDocument(dock);
		
		if(amandman.getId().isEmpty() || amandman.getId() == null)
		{
			Random rand = new Random();
			amandman.setId(Integer.toString(rand.nextInt(Integer.MAX_VALUE)));
		}
		
		//provera zbog provg puta kad se digne server
		if(lastSavedAmandman!=null)
		{
			if(lastSavedAmandman.getId().equals((amandman.getId())) || lastSavedAmandman.getTimestamp().equals(amandman.getTimestamp()))
			{
				System.out.println("Replay!");
				return;
			}
		}
		bm.writeDocumentToArchive(amandman, DatabaseConnection.ARHIV_AMANDMAN_USVOJEN_COL_ID);
		lastSavedAmandman = amandman;
	}
	
}
