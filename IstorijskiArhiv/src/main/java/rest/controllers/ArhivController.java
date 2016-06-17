package rest.controllers;

import java.security.PrivateKey;
import java.util.Random;

import model.Akt;
import model.Amandman;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import common.DatabaseConnection;

import securityPackage.DecryptKEK;
import businessLogic.BeanManager;
import dto.ArhivDto;

@RestController
public class ArhivController 
{
	private Akt lastSavedAkt;
	private Amandman lastSavedAmandman;
	private static String INPUT_OUTPUT_FILE = "tmp.xml";
	private static String IAGNS = "iagns";
	private static String SGNS = "sgns";
	private Document document;
	
	@RequestMapping(value = "/storeAkt/", method = RequestMethod.POST)
	public void storeAkt(@RequestBody ArhivDto arhiv)
	{
		
		System.out.println(arhiv);
		this.document = arhiv.getDoc();
		BeanManager<Akt> bm = new BeanManager<Akt>();
		//Document doc = bm.read(false, )
		DecryptKEK dec = new DecryptKEK();
		PrivateKey pk = dec.readPrivateKey(IAGNS);
		Document dock = null;
		try{
			dock = dec.decrypt(document, pk);
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
		}else{
			akt.setId(akt.getId()+".xml");
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
		lastSavedAkt = akt;
		
		//bm.writeDocument(akt, DatabaseConnection.ARHIV_AKT_USVOJEN_COL_ID, false, null);
		bm.write(akt, akt.getId(), DatabaseConnection.ARHIV_AKT_USVOJEN_COL_ID, false, null);
	}
}
