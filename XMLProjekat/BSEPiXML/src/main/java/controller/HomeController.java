package controller;

import java.util.ArrayList;

import model.Akt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import businessLogic.BeanManager;

import common.DatabaseConnection;

import dto.AktDto;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public String homePage(Model model){
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
	    StringBuilder query = new StringBuilder();
	    query.append("fn:collection(\"");
	    query.append(DatabaseConnection.AKT_PREDLOZEN_COL_ID);
	    query.append("\")");
	    ArrayList<Akt> akti =bm.executeQuery(query.toString());
		  
	    System.out.println(" ****************************************** " + akti.size());
	    
	    AktDto aktId= new AktDto();
	    
	    model.addAttribute("akti", akti);	    
	    model.addAttribute("aktId", aktId);
		
			
		return "homePage";
	}

}
