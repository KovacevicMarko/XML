package controller;

import java.io.File;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import model.Akt;
import model.Amandman;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import businessLogic.BeanManager;
import common.CommonQueries;
import common.DatabaseConnection;
import common.JaxbXmlConverter;
import dto.AmandmanDto;

@Controller
public class AmandmanController {
	
	@RequestMapping(value="/noviAmandman", method = RequestMethod.GET)
	 public String naFormu(Model model) 
	{
		/*eanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
	    
	    ArrayList<Amandman> amandmani =bm.executeQuery(CommonQueries.getAllProposedAmandmans());
		  
	    System.out.println(" ****************************************** " + amandmani.size());
		*/
		AmandmanDto amandmanDto = new AmandmanDto();
		model.addAttribute("amandman", amandmanDto);
		return "noviAmandman";
	 }
	
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String dodajAmandman(AmandmanDto amandman, BindingResult bindingResult, Model model){
		if(!bindingResult.hasErrors()){
			/*System.out.println(amandman.getAkt());
			System.out.println(amandman.getPravniOsnov());
			System.out.println(amandman.getPredmetIzmene());*/
			
			
			System.out.println(amandman.getCiljIzmene());
			
			JaxbXmlConverter converter = new JaxbXmlConverter();
			converter.writeStringToFile(amandman.getCiljIzmene());
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = null;
			try {
				schema = schemaFactory.newSchema(new File("Schema/Amandman.xsd"));
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Amandman amandman2= (Amandman) converter.convertFromXml(new File("tmp.xml"), schema);
			
			BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
			bm.writeDocument(amandman2, DatabaseConnection.AMANDMAN_PREDLOZEN_COL_ID, true);
			
		
		}
		//TODO dodati u bazu i preuzeti za izvšne odredbe korisnika i datum 
		else
		{
			System.out.println("error");
			
		}
		return "test";
	}
	
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel(Model model) {;
		return "redirect:homePage";
	}

	@RequestMapping(value="/amandmaniPage",method = RequestMethod.GET)
	 public String pogledajAmandmane(Model model) 
	{
		BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
	    StringBuilder query = new StringBuilder();
	    query.append("fn:collection(\"");
	    query.append(DatabaseConnection.AMANDMAN_PREDLOZEN_COL_ID);
	    query.append("\")");
	    ArrayList<Amandman> amandmani =bm.executeQuery(query.toString());
		  
	    System.out.println(" ****************************************** " + amandmani.size());
	    
	    AmandmanDto amandmanId= new AmandmanDto();
		model.addAttribute("amandmanId", amandmanId);
		model.addAttribute("amandmani", amandmani);
		return "amandmaniPage";
	 }
	
	
	

}
