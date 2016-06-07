package controller;

import java.util.ArrayList;

import model.Akt;
import model.TKorisnik;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import businessLogic.BeanManager;
import common.DatabaseConnection;
import dto.AktDto;

@Controller
@RequestMapping(value = "/noviAkt")
public class AktContoller {
	
	@RequestMapping(method = RequestMethod.GET)//AKT_DOC_ID
	 public String naFormu(Model model) {
	  AktDto aktDto = new AktDto();
	  model.addAttribute("akt", aktDto);
	  
	  /*BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
	  StringBuilder query = new StringBuilder();
      query.append("fn:collection(\"");
      query.append(DatabaseConnection.AKT_COL_ID);
      query.append("\")");
      ArrayList<Akt> akti=bm.executeQuery(query.toString());
	  
      System.out.println(akti.size());
	  */
	  return "noviAkt";
	 }
	
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String dodajAkt(AktDto akt, BindingResult bindingResult, Model model){
		if(!bindingResult.hasErrors()){
		System.out.println(akt.getNaziv());
		System.out.println(akt.getPreambula());
		
		}
		//TODO dodati u bazu i preuzeti za izvšne odredbe korisnika i datum 
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

}
