package controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
	            	/*if(!HelperClass.CheckPasswordDate(tuser, helpObj))
	            	{
	            		System.out.println("HERE MONTH");
	            		//TODO REDIRECT ON PAGE TO CHANGE PASSWORD
	            		
	            	}
	            	
	            	//provera da li je ostalo dovoljno dana do upozorenja
	            	if(helpObj.getFlag())
	            	{
	            		System.out.println("HERE DAY");
	            		//TODO SET WARNING MESSAGE + helpObj.getNumberOfExpiredDays(); 
	            		request.setAttribute("porukaOIsteku", "Vazenje lozinke vam istice za "+helpObj.getNumberOfExpiredDays()+" dana. Promenite lozinku!");
	            	}*/
	            	
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
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerForm(Model model) 
	{
	
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "registerPage";
	}
	
	@RequestMapping(params = "register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, UserDto user, BindingResult bindingResult, Model model)
	{
		String retVal = "";
		
		if(!bindingResult.hasErrors())
		{
			/*System.out.println(user.getIme());
			System.out.println(user.getPrezime());
			System.out.println(user.getKorisnickoIme());
			System.out.println(user.getLozinka());
			System.out.println(user.getUloga());
			System.out.println(user.getEmail());*/
			BeanManager<Korisnici> bm = new BeanManager<>("Schema/Korisnici.xsd");
			
			boolean postojiSaTimImenom=false;
			
	        /*citamo sve korisnike iz baze*/
			Korisnici users = bm.read(DatabaseConnection.USERS_DOC_ID);
	        for(TKorisnik tuser : users.getKorisnik())
	        {
	        	if(tuser.getKorisnickoIme().equals(user.getKorisnickoIme())){
	        		postojiSaTimImenom=true;
	        		System.out.println("postoji");
	        	}
			
	        }
	        
	        if(postojiSaTimImenom==false){
	        	
	        	TKorisnik k = new TKorisnik();
	        	k.setKorisnickoIme(user.getKorisnickoIme());
	        	k.setEmail(user.getEmail());
	        	k.setIme(user.getIme());
	        	k.setLozinka(user.getLozinka());
	        	k.setPrezime(user.getPrezime());
	        	if(user.getUloga().equals("Odbornik")){
	        		k.setUloga(Role.ULOGA_ODBORNIK);
	        	}else{
	        		if(user.getUloga().equals("Predsednik")){
	        			k.setUloga(Role.ULOGA_PREDSEDNIK);
	        		}
	        	}
			
	        	byte[] salt = new byte[0];
			
	        	try {
	        		salt=PasswordStorage.generateSalt();
	        	} catch (NoSuchAlgorithmException e) {
	        		e.printStackTrace();
	        		System.out.println(e.toString());
	        	}
	        	k.setSalt(PasswordStorage.base64Encode(salt));
	        	/*hash pass*/
	        	byte[] hashedPassword = new byte[0];
	        	try {
	        		hashedPassword = PasswordStorage.hashPassword(k.getLozinka(), salt);
	        	} catch (NoSuchAlgorithmException e) {
	        		e.printStackTrace();
	            	System.out.println(e.toString());
	        	} catch (InvalidKeySpecException e) {
	        		e.printStackTrace();
	        		System.out.println(e.toString());
	        	}
	        	k.setLozinka(PasswordStorage.base64Encode(hashedPassword));
	        	users.getKorisnik().add(k);
			
	        	BeanManager<Korisnici> bm1 = new BeanManager<>("Schema/Korisnici.xsd");
	        	bm1.write(users, DatabaseConnection.USERS_DOC_ID, DatabaseConnection.USERS_COL_ID);
	        	retVal = "homePage";
	        }
	        else{
	        	request.setAttribute("postojiVec", "postojiVec");
	        	UserDto userDto = new UserDto();
	    		model.addAttribute("user", userDto);
	        	retVal="registerPage";
	        }
			
		}
		
		else
		{
			retVal = "homePage";
			System.out.println("error");
		}

		return retVal;
	}
}
