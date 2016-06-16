package rest.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import businessLogic.BeanManager;
import common.DatabaseConnection;
import common.HelperClass;
import common.Role;
import dto.ChangePassDto;
import dto.LoginUserDto;
import dto.UserDto;
import factory.UserFactory;
import model.Korisnici;
import model.TKorisnik;
import password.PasswordStorage;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@RequestMapping(value = "/checkSession/", method = RequestMethod.GET)
	public ResponseEntity getUserOnSession(HttpServletRequest req){
		
		ResponseEntity retVal;
		
		if(req.getSession().getAttribute("user")!=null){
			UserDto userOnSession = (UserDto) req.getSession().getAttribute("user");
			retVal = new ResponseEntity(userOnSession,HttpStatus.OK);
		}
		else{
			retVal = new ResponseEntity(null,HttpStatus.OK);
		}
		
		return retVal;
	}
	
	@RequestMapping(value = "/logIn/", method = RequestMethod.POST)
	public ResponseEntity logIn(@RequestBody LoginUserDto user, HttpServletRequest request) {

		ResponseEntity retVal = new ResponseEntity("Wrong username or password", HttpStatus.NOT_FOUND);

		HelperClass helpClass = new HelperClass();
		HelperClass.HelperObject helpObj = helpClass.new HelperObject();

		BeanManager<Korisnici> bm = new BeanManager<>("Schema/Korisnici.xsd");

		/* citamo sve korisnike iz baze */
		Korisnici users = bm.read(DatabaseConnection.USERS_DOC_ID, false);
		
		for (TKorisnik tuser : users.getKorisnik()) {
			
			if (user.getUsername().equals(tuser.getKorisnickoIme())
					&& PasswordStorage.checkPassword(user.getPassword(), tuser.getLozinka(), tuser.getSalt())) {

				System.out.println("Usao");
				// provera da li je sifra istekla
				if (!HelperClass.CheckPasswordDate(tuser, helpObj)) {
					System.out.println("HERE MONTH");
					// TODO REDIRECT ON PAGE TO CHANGE PASSWORD

					ChangePassDto userDto = new ChangePassDto();
					userDto.setKorisnickoIme(user.getUsername());
					// model.addAttribute("user", userDto);
					retVal = new ResponseEntity("Password expired", HttpStatus.NOT_ACCEPTABLE);
					return retVal;
				}

				// provera da li je ostalo dovoljno dana do upozorenja
				if (helpObj.getFlag()) {
					// TODO SET WARNING MESSAGE +
					// helpObj.getNumberOfExpiredDays();
				}

				UserDto foundUser = UserFactory.createUserDto(tuser);
				System.out.println(foundUser.getUloga());
				System.out.println(Role.ULOGA_ODBORNIK.toUpperCase());
				request.getSession().setAttribute("user", foundUser);
				retVal = new ResponseEntity(foundUser, HttpStatus.OK);
				break;
			}

		}

		return retVal;

	}

	@RequestMapping(value = "/logOut/", method = RequestMethod.GET)
	public ResponseEntity<String> logOut(HttpServletRequest request) {
		ResponseEntity<String> retVal;

		request.getSession().invalidate();

		String msg = "Succesfully loged out!";

		retVal = new ResponseEntity<String>(msg, HttpStatus.OK);

		return retVal;
	}

	@RequestMapping(value = "/register/", method = RequestMethod.POST)
	public ResponseEntity register(HttpServletRequest request, @RequestBody UserDto user) {
		
		
		ResponseEntity retVal = new ResponseEntity("Person with this username already exist!",
				HttpStatus.NOT_ACCEPTABLE);

		BeanManager<Korisnici> bm = new BeanManager<>("Schema/Korisnici.xsd");

		boolean postojiSaTimImenom = false;

		// citamo sve korisnike iz baze //
		Korisnici users = bm.read(DatabaseConnection.USERS_DOC_ID, false);
		for (TKorisnik tuser : users.getKorisnik()) {
			if (tuser.getKorisnickoIme().equals(user.getKorisnickoIme())) {
				postojiSaTimImenom = true;
			}

		}

		if (!postojiSaTimImenom) {

			TKorisnik newUser = new TKorisnik();
			newUser.setKorisnickoIme(user.getKorisnickoIme());
			newUser.setEmail(user.getEmail());
			newUser.setIme(user.getIme());
			newUser.setLozinka(user.getLozinka());
			newUser.setPrezime(user.getPrezime());

			if (user.getUloga().equals(Role.ULOGA_ODBORNIK)) {
				newUser.setUloga(Role.ULOGA_ODBORNIK);
			} else {
				if (user.getUloga().equals(Role.ULOGA_PREDSEDNIK)) {
					newUser.setUloga(Role.ULOGA_PREDSEDNIK);
				}
			}

			byte[] salt = new byte[0];

			try {
				salt = PasswordStorage.generateSalt();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			newUser.setSalt(PasswordStorage.base64Encode(salt));

			// hash pass //
			byte[] hashedPassword = new byte[0];
			try {
				hashedPassword = PasswordStorage.hashPassword(newUser.getLozinka(), salt);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}
			newUser.setLozinka(PasswordStorage.base64Encode(hashedPassword));
			
			//setDatumPromeneLozinke
			Date date = new Date();	        
	        GregorianCalendar c = new GregorianCalendar();
			c.setTime(date);
			try {
				newUser.setDatumPromeneLozinke((DatatypeFactory.newInstance().newXMLGregorianCalendar(c)));
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Dodavanje registrovanog usera na sessiju, da automatski bude
			// ulogovan.
			request.getSession().setAttribute("user", newUser);
			retVal = new ResponseEntity(newUser, HttpStatus.OK);
			// Dodavanje novog usera u listu svih
			users.getKorisnik().add(newUser);
			// Persistencija
			//TODO da li parametar write metode treba da bude newUser.username (zbog certifikata) ?
			BeanManager<Korisnici> bm1 = new BeanManager<>("Schema/Korisnici.xsd");
			bm1.write(users, DatabaseConnection.USERS_DOC_ID, DatabaseConnection.USERS_COL_ID, false,"jocko");

		}

		return retVal;
		
	}

}
