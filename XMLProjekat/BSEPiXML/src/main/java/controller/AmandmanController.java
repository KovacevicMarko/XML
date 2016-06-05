package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.AktDto;
import dto.AmandmanDto;

@Controller
@RequestMapping(value = "/noviAmandman")
public class AmandmanController {
	
	@RequestMapping(method = RequestMethod.GET)
	 public String naFormu(Model model) {
	  AmandmanDto amandmanDto = new AmandmanDto();
	  model.addAttribute("amandman", amandmanDto);
	  return "noviAmandman";
	 }
	
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String dodajAkt(AmandmanDto amandman, BindingResult bindingResult, Model model){
		if(!bindingResult.hasErrors()){
		System.out.println(amandman.getAkt());
		System.out.println(amandman.getPravniOsnov());
		System.out.println(amandman.getPredmetIzmene());
		System.out.println(amandman.getCiljIzmene());
		
		}
		//TODO dodati u bazu i preuzeti za izv�ne odredbe korisnika i datum 
		else
		{
			System.out.println("error  konjino glupa");
			
		}
		return "test";
	}
	
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel(Model model) {;
		return "redirect:homePage";
	}

}