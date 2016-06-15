package controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import model.Akt;
import model.Akt.PrelazneIZavrsneOdredbe;
import model.Amandman;
import model.Korisnici;
import model.TClan.Stav;
import model.TClanAmandnam;
import model.TClanAmandnam.StavAmandman;
import model.TDeo.Glava;
import model.TKorisnik;
import model.TOdbornik;
import model.TSadrzajAmandmana;
import model.TSadrzajAmandmana.GlavaAmandman;
import model.TSadrzajGlave;
import model.TSadrzajStava;
import model.TSadrzajStava.Tacka;
import model.TSadrzajTacke;
import model.TSadrzajTacke.Alineja;

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
//		InitializeKorisnik();
//		System.out.println("USPESNO INIZIJALIZOVAN KORISNIK!");
//		InitializeAkt();
//		System.out.println("USPESNO INIZIJALIZOVAN AKT!");
//		InitializeAmandman();
//		System.out.println("USPESNO INIZIJALIZOVAN AMANDMAN!");
//		InitializeAktEncrypt();
		DecryptAkt();
//		System.out.println("USPESNO INIZIJALIZOVAN AKT ENKRIPT!");
//		TestReadAkt();
	
//		DeleteActs();
		
		return "test";
	}
	private void DecryptAkt()
	{
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		bm1.read(true, DatabaseConnection.AKT_ENCRYPT_DOC_ID);
	}
	private void DeleteActs()
	{
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		
		bm1.deleteDocument("/Akt.xml");
		bm1.deleteDocument("/AktEncrypt.xml");
		
		bm1.deleteDocument("16506433079667505009.xml");
		bm1.deleteDocument("1701816993435181422.xml");
		bm1.deleteDocument("1961348271332506490.xml");
		
		bm1.deleteDocument("2886738861788396180.xml");
		bm1.deleteDocument("3019671464692161100.xml");
		
		System.out.println("USPESNO OBRISANI DOKUMENTI AKTA");
		
		BeanManager<Akt> bm2 = new BeanManager<>("Schema/Amandman.xsd");
		bm2.deleteDocument("/Amandman.xml");
		bm2.deleteDocument("2237058586671322019.xml");
		bm2.deleteDocument("3328921903491720347.xml");
		bm2.deleteDocument("8503535133437807659.xml");
		
		System.out.println("USPESNO OBRISANI DOKUMENTI AMANDMANA");
	}
	
	private void TestReadAkt()
	{
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		Akt akt = bm1.read(DatabaseConnection.AKT_PREDLOZEN_DOC_ID, true);
		System.out.println(akt.getNazivAkt());
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
		
        Date date = new Date();
        
        
        GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		try {
			k.setDatumPromeneLozinke((DatatypeFactory.newInstance().newXMLGregorianCalendar(c)));
			k2.setDatumPromeneLozinke((DatatypeFactory.newInstance().newXMLGregorianCalendar(c)));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		korisnici.getKorisnik().add(k);
		korisnici.getKorisnik().add(k2);
		
		BeanManager<Korisnici> bm1 = new BeanManager<>("Schema/Korisnici.xsd");
		bm1.write(korisnici, DatabaseConnection.USERS_DOC_ID, DatabaseConnection.USERS_COL_ID, false, "jocko");
		
	}
	
	private void InitializeAkt()
	{
		/*
		Akt akt = getAkt();
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		bm1.writeDocument(akt, DatabaseConnection.AKT_PREDLOZEN_COL_ID , true, "jocko");
		*/
	}
	
	private void InitializeAmandman()
	{
		/*
		TClanAmandnam.StavAmandman stav= new StavAmandman();
		stav.setOznakaStava("stav1");
		stav.setTekstIzmene("Predlog izmene iznosa navednom u ovom stavu se menja sa n na m.");
		
		TClanAmandnam clanAmandnam = new TClanAmandnam();
		clanAmandnam.setOznakaClana("clan1");
		clanAmandnam.getStavAmandman().add(stav);
		
		GlavaAmandman glavaAmandmana = new GlavaAmandman();
		glavaAmandmana.setOznaka("glava1");
		glavaAmandmana.getClanAmandnam().add(clanAmandnam);
		
		TSadrzajAmandmana sadrzajAmandmana = new TSadrzajAmandmana();
		sadrzajAmandmana.setNazivAkta("Akt1");
		sadrzajAmandmana.setCiljIzmene("Cilj izmene amandmana");
		sadrzajAmandmana.setPredmetIzmene("Predmet izmene amandmana");
		sadrzajAmandmana.getGlavaAmandman().add(glavaAmandmana);
		
		Amandman amandman = new Amandman();
		amandman.setPravniOsnov("Pravni osnov1");
		amandman.setSadrzajAmandmana(sadrzajAmandmana);
		TOdbornik odbornik = new TOdbornik();
		odbornik.setIme("odbornikIme");
		odbornik.setPrezime("odbornikPrezime");
		odbornik.setStranka("odbornikStranka");
		amandman.setPredlagac(odbornik);
		
		
		BeanManager<Amandman> bm1 = new BeanManager<>("Schema/Amandman.xsd");
		bm1.writeDocument(amandman, DatabaseConnection.AMANDMAN_PREDLOZEN_COL_ID, true, "jocko");*/
	}
	
	private void InitializeAktEncrypt()
	{
		/*Akt akt = getAkt();
		
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		if(bm1.write(akt, DatabaseConnection.AKT_ENCRYPT_DOC_ID,  DatabaseConnection.AKT_ENCRYPT_COL_ID, true, "jocko")){
			System.out.println("Uspesno zapisan enkriptovan akt");
		}else{
			System.out.println("Ne uspesno zapisan enkriptovan akt");
		}*/
	}
	
	private void getAkt()
	{/*
		Alineja alineja = new Alineja();
		alineja.setOznaka("Alineja1");
		alineja.setSadrzaj("Sadrzaj alineje o donosenju akta.");
		
		TSadrzajTacke sadrzajTacke = new TSadrzajTacke();
		sadrzajTacke.getAlineja().add(alineja);
		sadrzajTacke.setTekst("Sadrzaj tacke o donosenju akta.");
		
		Tacka tacka = new Tacka();
		tacka.setOznaka("Tacka1");
		tacka.setSadrzaj(sadrzajTacke);
		
		Tacka tacka2 = new Tacka();
		tacka2.setOznaka("Tacka2");
		tacka2.setSadrzaj(sadrzajTacke);
			
		
		TSadrzajStava sadrzajStava = new TSadrzajStava();

		sadrzajStava.setTekst("Sadrzaj stava o donosenju akta.");
		sadrzajStava.getTacka().add(tacka);	
		sadrzajStava.getTacka().add(tacka2);
		
		Stav stav = new Stav();
		stav.setOznaka("stav1");
		stav.setSadrzaj(sadrzajStava);
		
		Clan clan = new Clan();
		clan.setOznaka("clan1");
		clan.getStav().add(stav);
		
		
		TSadrzajGlave sadrzajGlave = new TSadrzajGlave();
		sadrzajGlave.getClan().add(clan);
		
		Glava glava = new Glava();
		glava.setNaziv("glava1");
		glava.setOznaka("glava1");
		glava.setSadrzaj(sadrzajGlave);
		
		Glava glava2 = new Glava();
		glava2.setNaziv("glava2");
		glava2.setOznaka("glava2");
		glava2.setSadrzaj(sadrzajGlave);
		
		Deo deo = new Deo();
		deo.setNaziv("deo1");
		deo.setOznaka("oznaka1");
		deo.getGlava().add(glava);
		deo.getGlava().add(glava2);
		
		TOdbornik odbornik = new TOdbornik();
		odbornik.setIme("odbornikIme");
		odbornik.setPrezime("odbornikPrezime");
		odbornik.setStranka("odbornikStranka");
		
		PrelazneIZavrsneOdredbe pzo = new PrelazneIZavrsneOdredbe();
		pzo.setPredlagac(odbornik);
		
		Akt akt = new Akt();
		akt.setNaziv("Akt1");
		akt.setPreambula("Preambula1");
		
		akt.getDeo().add(deo);
		
		Date date = new Date();
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		try {
			pzo.setDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		akt.setPrelazneIZavrsneOdredbe(pzo);
		
		return akt;
		*/
	}
	
	private String GenerateRandNumber()
	{
		Random randomGenerator = new Random();
		return Integer.toString(randomGenerator.nextInt(Integer.MAX_VALUE));
	}

}
