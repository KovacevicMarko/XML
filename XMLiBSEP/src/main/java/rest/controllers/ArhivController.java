package rest.controllers;

import java.io.File;
import java.security.PrivateKey;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import model.Akt;

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
	private static String INPUT_OUTPUT_FILE = "tmp.xml";
	
	@RequestMapping(value = "/saveAkt/", method = RequestMethod.POST)
	public void saveAkt(Document doc)
	{
		BeanManager<Akt> bm = new BeanManager<Akt>();
		DecryptKEK dec = new DecryptKEK();
		PrivateKey pk = dec.readPrivateKey();
		Document dock = dec.decrypt(doc, pk);
		Akt akt = bm.getBeanByDocument(dock);
		
		
		//provera zbog provg puta kad se digne server
		if(lastSavedAkt!=null)
		{
			if(lastSavedAkt.getId().equals((akt.getId())) || lastSavedAkt.getTimeStamp().equals(akt.getTimeStamp()))
			{
				System.out.println("HEREEEEEEEEEEEEEEEEEEEEEE");
				return;
			}
		}
		//Akt akt2 = bm.read("1136059015000972529.xml", true);
		bm.writeDocumentToArchive(akt, DatabaseConnection.ARHIV_AKT_USVOJEN_COL_ID);
		//Document doc = bm.readDocumentFromArchive("16488116637029458099.xml");
		//System.out.println(doc.toString());
		lastSavedAkt = akt;
	}
	
}
