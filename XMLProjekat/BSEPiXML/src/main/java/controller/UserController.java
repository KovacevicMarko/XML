package controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.Korisnici;
import model.TKorisnik;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import password.PasswordStorage;
import securityPackage.SignEnveloped;
import businessLogic.BeanManager;
import common.DatabaseConnection;
import common.HelperClass;
import common.Role;
import dto.LoginUserDto;
import dto.UserDto;

@Controller
@RequestMapping(value = "/logIn")
public class UserController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String registerUser(Model model) 
	{
		SignEnveloped sgn = new SignEnveloped();
	
		LoginUserDto userDto = new LoginUserDto();
		model.addAttribute("user", userDto);
		return "logIn";
	}
	
	@RequestMapping(params = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, LoginUserDto user, BindingResult bindingResult, Model model)
	{
		String retVal = "homePage";
		
		
		
		if(!bindingResult.hasErrors())
		{
			HelperClass helpClass = new HelperClass();
			HelperClass.HelperObject helpObj = helpClass.new HelperObject();
			
			BeanManager<Korisnici> bm = new BeanManager<>("Schema/Korisnici.xsd");
	
	        /*citamo sve korisnike iz baze*/
			Korisnici users = bm.read(DatabaseConnection.USERS_DOC_ID);
	        for(TKorisnik tuser : users.getKorisnik())
	        {
	            if(user.getUsername().equals(tuser.getKorisnickoIme()) && PasswordStorage.checkPassword(user.getPassword(), tuser.getLozinka(), tuser.getSalt()) )
	            {
	            	//provera da li je sifra istekla
	            	if(!HelperClass.CheckPasswordDate(tuser, helpObj))
	            	{
	            		System.out.println("HERE MONTH");
	            		//TODO REDIRECT ON PAGE TO CHANGE PASSWORD
	            	}
	            	//provera da li je ostalo dovoljno dana do upozorenja
	            	if(helpObj.getFlag())
	            	{
	            		System.out.println("HERE DAY");
	            		//TODO SET WARNING MESSAGE + helpObj.getNumberOfExpiredDays(); 
	            	}
	            	
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
	            	break;
	            }
	
	        }

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
