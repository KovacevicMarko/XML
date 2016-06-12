package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import model.Akt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import businessLogic.BeanManager;

import common.CommonQueries;
import common.DatabaseConnection;

import dto.AktDto;

@Controller
@RequestMapping(value = "/akt")
public class AktContoller {
	
	 
	
	@RequestMapping(method = RequestMethod.GET)//AKT_DOC_ID
	 public String naFormu(Model model) {
	  AktDto aktDto = new AktDto();
	  model.addAttribute("akt", aktDto);
	  
	  BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
	  
      ArrayList<Akt> aktiPredlozeni=bm.executeQuery(CommonQueries.getAllProposedActs());
      ArrayList<Akt> aktiUsvojeni = bm.executeQuery(CommonQueries.getAllApprovedActs());	
      
      System.out.println(" ****************************************** " + aktiPredlozeni.size());
	  
	  return "noviAkt";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchAkt() {
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		String value = "tacke";
    	HashMap<String,ArrayList<String>> predlozeni = bm.searchByContent(value, DatabaseConnection.AKT_PREDLOZEN_COL_ID);
    	
    	Iterator it = predlozeni.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    	
		return "homePage";
	}
	
	@RequestMapping(value = "/searchTag", method = RequestMethod.GET)
	public String searchAktTag() {
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		String value = "Sadrzaj tacke";
		String tag = "Tacka";
    	HashMap<String,ArrayList<String>> predlozeni = bm.searchByContentAndTag(value, DatabaseConnection.AKT_PREDLOZEN_COL_ID, tag);
    	
    	Iterator it = predlozeni.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    	
		return "homePage";
	}
	
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String dodajAkt(AktDto akt, BindingResult bindingResult, Model model){//kad se preuzme ceo xml text se upise u preamublu to je tipa string, da ne pravim novi dto
		if(!bindingResult.hasErrors()){
		
		System.out.println(akt.getPreambula());
		
		}
		//TODO validirati xml i dodati u bazu 
		else
		{
			System.out.println("error  konjino glupa");
			System.out.println(akt.getNaziv());
			System.out.println(akt.getPreambula());
			
		}
		return "test";
	}
	
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel(Model model) {;
		return "redirect:homePage";
	}
	
	
	@RequestMapping(params = "opsirnije", method = RequestMethod.POST)
	public String opsirnijeAkt(AktDto akt, BindingResult bindingResult, Model model) throws ParserConfigurationException, SAXException, IOException{
		if(!bindingResult.hasErrors()){
			//System.out.println(akt.getId()); //TODO ispisati ceo xml sa id-em na stranicu(opsirnije)
			BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		    StringBuilder query = new StringBuilder();
		    query.append("fn:collection(\"");
		    query.append(DatabaseConnection.AKT_PREDLOZEN_COL_ID);
		    query.append("\")");
		    ArrayList<Akt> akti =bm.executeQuery(query.toString());
		    String aktXML="";
		    
		    for(int i=0; i<akti.size(); i++){
		    	if(akti.get(i).getID().equals(akt.getId())){
		    		if(bm.convertToXml((akti.get(i)))){
		    			System.out.println("uspeo");
		    		}
		    		
		    		break;
		    		
		    	}
		    }
			
		}
		//TODO dodati u bazu i preuzeti za izvšne odredbe korisnika i datum 
		else
		{
			System.out.println("error  konjino glupa");
			System.out.println(akt.getNaziv());
			System.out.println(akt.getPreambula());
			
		}
		String s=readFile("E:\\eclipse\\tmp.xml");
		
		model.addAttribute("aktText", s);
		return "test";
	}
	
	
	public String readFile(String path) throws IOException{
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
        	String sCurrentLine;
        	while ((sCurrentLine = br.readLine()) != null) {
        		sb.append(sCurrentLine);
        	}

        	}

      return sb.toString();
	}
}
