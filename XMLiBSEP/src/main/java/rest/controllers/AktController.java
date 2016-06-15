package rest.controllers;

import java.io.File;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import businessLogic.BeanManager;
import common.DatabaseConnection;
import common.JaxbXmlConverter;
import dto.AktDto;
import model.Akt;



@RestController
@RequestMapping(value = "/akt/")
public class AktController {
	
	@RequestMapping(value = "/addAkt/", method = RequestMethod.POST)
	public ResponseEntity dodajAkt(@RequestBody Akt akt){//kad se preuzme ceo xml text se upise u preamublu to je tipa string, da ne pravim novi dto
		
		
		System.out.println(akt.getPrelazneIZavrsneOdredbe().getDatum());
		ResponseEntity retVal = new ResponseEntity(akt,HttpStatus.OK);
		
		/*JaxbXmlConverter converter = new JaxbXmlConverter();
		converter.writeStringToFile(akt.getPreambula());
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(new File("Schema/Akt.xsd"));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Akt akt2= (Akt) converter.convertFromXml(new File("tmp.xml"), schema);
		
		*/
		
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		bm.writeDocument(akt, DatabaseConnection.AKT_PREDLOZEN_COL_ID, true);
		
		//System.out.println(akt.getPreambula());
		
		
		//TODO validirati xml i dodati u bazu 
	
		return retVal;
	}

}
