package rest.controllers;

import java.security.PrivateKey;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import businessLogic.BeanManager;
import common.DatabaseConnection;
import model.Akt;
import model.Amandman;
import securityPackage.DecryptKEK;

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
		Document dock = null;
		try{
			dock = dec.decrypt(doc, pk);
		}catch(Exception e)
		{
			
		}
		if(dock == null)
		{
			System.out.println("Poruka nije sifrovana!");
			return;
		}
		
		Akt akt = bm.getBeanByDocument(dock);
		
		if(akt != null && akt.getSignature()==null)
		{
			System.out.println("Poruka nije potpisana!");
			return;
		}
		
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
}
