package rest.controllers;

import java.io.File;
import java.security.PrivateKey;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import model.Akt;
import model.Amandman;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import securityPackage.DecryptKEK;
import businessLogic.BeanManager;
import common.DatabaseConnection;
import common.JaxbXmlConverter;

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
		
		System.out.println(akt.getId());
		akt.setId("11111111111111111111");
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
		
		
		//provera zbog provg puta kad se digne server
		if(lastSavedAmandman!=null)
		{
			if(lastSavedAmandman.getId().equals((amandman.getId())) || lastSavedAmandman.getTimestamp().equals(amandman.getTimestamp()))
			{
				System.out.println("Replay!");
				return;
			}
		}
		bm.writeDocumentToArchive(amandman, DatabaseConnection.ARHIV_AKT_USVOJEN_COL_ID);
		lastSavedAmandman = amandman;
	}
	
}
