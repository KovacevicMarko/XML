package rest.controllers;

import model.Akt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import common.DatabaseConnection;

import businessLogic.BeanManager;

@RestController
@RequestMapping(value = "/IstorijskiArhiv/")
public class ArhivController 
{
	private Akt lastSavedAkt;
	
	@RequestMapping(value = "/saveAkt/", method = RequestMethod.POST)
	public void saveAkt(Akt akt)
	{
		//provera zbog provg puta kad se digne server
		if(lastSavedAkt!=null)
		{
			if(lastSavedAkt.getId().equals((akt.getId())) || lastSavedAkt.getTimeStamp().equals(akt.getTimeStamp()))
			{
				System.out.println("HEREEEEEEEEEEEEEEEEEEEEEE");
				return;
			}
		}
		BeanManager<Akt> bm = new BeanManager<Akt>();
		//Akt akt2 = bm.read("1136059015000972529.xml", true);
		bm.writeDocumentToArchive(akt, DatabaseConnection.ARHIV_AKT_USVOJEN_COL_ID);
		//Document doc = bm.readDocumentFromArchive("16488116637029458099.xml");
		//System.out.println(doc.toString());
		lastSavedAkt = akt;
	}
}
