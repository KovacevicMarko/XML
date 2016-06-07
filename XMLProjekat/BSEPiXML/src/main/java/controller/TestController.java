package controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import model.Akt;
import model.Deo;
import model.Korisnici;
import model.PrelazneIZavrsneOdredbe;
import model.TKorisnik;
import model.TOdbornik;
import model.TSadrzajDela;
import model.TSadrzajDela.Glava;
import model.TSadrzajGlave;
import model.TSadrzajGlave.Clan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import password.PasswordStorage;
import businessLogic.BeanManager;

import common.DatabaseConnection;
import common.Role;

@Controller
@RequestMapping(value = "/initialize")
public class TestController {
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String Initialize()
	{
		//InitializeKorisnik();
		InitializeAkt();
		InitializeAmandman();

		return "homePage";
	}
	
	private void InitializeKorisnik()
	{
		Korisnici korisnici = new Korisnici();
		TKorisnik k = new TKorisnik();
		k.setKorisnickoIme("despinica");
		k.setEmail("despinica");
		k.setIme("despinica");
		k.setLozinka("despinica");
		k.setPrezime("despinica");
		k.setUloga(Role.ULOGA_GRADJANIN);
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
             hashedPassword = PasswordStorage.hashPassword("despinica", salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        k.setLozinka(PasswordStorage.base64Encode(hashedPassword));
		
		
		TKorisnik k2 = new TKorisnik();
		k2.setKorisnickoIme("jocko");
		k2.setEmail("jocko2");
		k2.setIme("jocko");
		k2.setLozinka("jocko");
		k2.setPrezime("jocko");
		k2.setUloga(Role.ULOGA_ODBORNIK);
		salt = new byte[0];
		
		try {
            salt=PasswordStorage.generateSalt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        k2.setSalt(PasswordStorage.base64Encode(salt));
        /*hash pass*/
        hashedPassword = new byte[0];
        try {
             hashedPassword = PasswordStorage.hashPassword("jocko", salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        k2.setLozinka(PasswordStorage.base64Encode(hashedPassword));
		
		korisnici.getKorisnik().add(k2);
		korisnici.getKorisnik().add(k2);
		
		BeanManager<Korisnici> bm1 = new BeanManager<>("Schema/Korisnici.xsd");
		bm1.write(korisnici, DatabaseConnection.USERS_DOC_ID, DatabaseConnection.USERS_COL_ID);
	}
	
	private void InitializeAkt()
	{
		Clan clan = new Clan();
		clan.setOznaka("clan1");
		
		TSadrzajGlave sadrzajGlave = new TSadrzajGlave();
		sadrzajGlave.getClan().add(clan);
		
		Glava glava = new Glava();
		glava.setNaziv("glava1");
		glava.setOznaka("glava1");
		glava.setSadrzaj(sadrzajGlave);
		
		TSadrzajDela sadrzajDela = new TSadrzajDela();
		sadrzajDela.getGlava().add(glava);
		
		Deo deo = new Deo();
		deo.setNaziv("deo1");
		deo.setOznaka("oznaka1");
		deo.setSadrzaj(sadrzajDela);
		
		TOdbornik odbornik = new TOdbornik();
		odbornik.setIme("odbornikIme");
		odbornik.setPrezime("odbornikPrezime");
		odbornik.setStranka("odbornikStranka");
		
		PrelazneIZavrsneOdredbe pzo = new PrelazneIZavrsneOdredbe();
		pzo.setDatum(null);
		pzo.setPredlagac(odbornik);
		
		Akt akt = new Akt();
		akt.setNaziv("Akt1");
		akt.setPreambula("Preambula1");
		akt.setPrelazneIZavrsneOdredbe(pzo);
		
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		String docID = DatabaseConnection.AKT_DOC_ID;
		bm1.write(akt, DatabaseConnection.AKT_DOC_ID,  DatabaseConnection.AKT_COL_ID);
		
	}
	
	private void InitializeAmandman()
	{
		
	}

}
