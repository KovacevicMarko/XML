package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.UserDto;

@Controller
@RequestMapping(value = "/logIn")
public class UserController {

	@RequestMapping(method = RequestMethod.GET)
	 public String registerUser(Model model) {
	  UserDto userDto = new UserDto();
	  model.addAttribute("user", userDto);
	  return "logIn";
	 }
	
	@RequestMapping(params = "login", method = RequestMethod.POST)
	public String login(UserDto user, BindingResult bindingResult, Model model){
		if(!bindingResult.hasErrors()){
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		}
		//TODO proveriti u bazi
		else
		{
			System.out.println("error");
		}
		return "test";
	}
	
}
