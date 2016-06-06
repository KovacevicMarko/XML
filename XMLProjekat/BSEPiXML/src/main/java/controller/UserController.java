package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Korisnici;
import model.TKorisnik;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import businessLogic.BeanManager;
import common.DatabaseConnection;
import common.Role;
import dto.LoginUserDto;
import dto.UserDto;

@Controller
@RequestMapping(value = "/logIn")
public class UserController {

	@RequestMapping(method = RequestMethod.GET)
	public String registerUser(Model model) 
	{
	
		LoginUserDto userDto = new LoginUserDto();
		model.addAttribute("user", userDto);
		return "logIn";
	}
	
	@RequestMapping(params = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, LoginUserDto user, BindingResult bindingResult, Model model)
	{
		String retVal = "";
		
		if(!bindingResult.hasErrors())
		{

			BeanManager<Korisnici> bm = new BeanManager<>("Schema/Korisnici.xsd");
	
	        /*citamo sve korisnike iz baze*/
			Korisnici users = bm.read(DatabaseConnection.USERS_DOC_ID);
	        for(TKorisnik tuser : users.getKorisnik())
	        {
	            if(user.getUsername().equals(tuser.getKorisnickoIme()) && user.getPassword().equals(tuser.getLozinka()) )
	            {
	            	UserDto userD=new UserDto(tuser);
            		request.getSession().setAttribute("user",userD);
	            	if(tuser.getUloga().equals(Role.ULOGA_ODBORNIK)){
	            		System.out.println(tuser.getUloga());
	            		retVal = "homePage";
	            	}else if(tuser.getUloga().equals(Role.ULOGA_PREDSEDNIK))
	            	{	
	            		retVal = "homePage";  // ako bude trebalo za nesto, za sad nek radi isto
	            	}
	            	else{ 
	            		retVal = "homePage";
	            	}
	            }else{
	            	retVal = "homePage";
	            }
	
	        }

		}
		
		else
		{
			retVal = "homePage";
			System.out.println("error");
		}

		return retVal;
	}
	
	
	@RequestMapping(params = "logOut", method = RequestMethod.POST)
	public String logOut(HttpServletRequest request, LoginUserDto user, BindingResult bindingResult, Model model)
	{
		String retVal = "";
		
		if(!bindingResult.hasErrors())
		{

			request.getSession().invalidate();
	            	
			retVal = "homePage";
		}    		
		else
		{
			retVal = "homePage";
			System.out.println("error");
		}

		return retVal;
	}
	
}
