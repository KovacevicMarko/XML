package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.AktDto;



@Controller
public class DeoController {

	@RequestMapping(value = "/deo", method = RequestMethod.POST)
	public String deoPage(Model model, AktDto akt){
		    System.out.println(akt.getId());
			
		return "homePage";
	}
	
}
