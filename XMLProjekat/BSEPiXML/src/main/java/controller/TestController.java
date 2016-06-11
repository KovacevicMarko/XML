package controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import model.Akt;
import model.Amandman;
import model.Deo;
import model.Korisnici;
import model.PrelazneIZavrsneOdredbe;
import model.TKorisnik;
import model.TOdbornik;
import model.TSadrzajAmandmana;
import model.TSadrzajAmandmana.GlavaAmandman;
import model.TSadrzajAmandmana.GlavaAmandman.ClanAmandnam;
import model.TSadrzajAmandmana.GlavaAmandman.ClanAmandnam.StavAmandman;
import model.TSadrzajClana;
import model.TSadrzajClana.Stav;
import model.TSadrzajDela;
import model.TSadrzajDela.Glava;
import model.TSadrzajGlave;
import model.TSadrzajGlave.Clan;
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
		//InitializeKorisnik();
		InitializeAkt();
		//InitializeAmandman();
		//InitializeAktEncrypt();

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
		bm1.write(korisnici, DatabaseConnection.USERS_DOC_ID, DatabaseConnection.USERS_COL_ID);
		
	}
	
	private void InitializeAkt()
	{
		
		Akt akt = getAkt();
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		bm1.write(akt, DatabaseConnection.AKT_DOC_ID,  DatabaseConnection.AKT_COL_ID);
		
	}
	
	private void InitializeAmandman()
	{
		StavAmandman stav = new StavAmandman();
		stav.setOznakaStava("stav1");
		stav.setTekstIzmene("Predlog izmene iznosa navednom u ovom stavu se menja sa n na m.");
		
		ClanAmandnam clanAmandnam = new ClanAmandnam();
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
		Date date = new Date();
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		try {
			amandman.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		amandman.setID(GenerateRandNumber());
		
		BeanManager<Amandman> bm1 = new BeanManager<>("Schema/Amandman.xsd");
		bm1.write(amandman, DatabaseConnection.AMANDMAN_DOC_ID,  DatabaseConnection.AMANDMAN_COL_ID);
	}
	
	private void InitializeAktEncrypt()
	{
		Akt akt = getAkt();
		
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		bm1.write(akt, DatabaseConnection.AKT_ENCRYPT_DOC_ID,  DatabaseConnection.AKT_ENCRYPT_COL_ID);
	}
	
	private Akt getAkt()
	{
		Alineja alineja = new Alineja();
		alineja.setOznaka("Alineja1");
		alineja.setSadrzaj("Sadrzaj alineje o donosenju akta.");
		
		TSadrzajTacke sadrzajTacke = new TSadrzajTacke();
		sadrzajTacke.getAlineja().add(alineja);
		sadrzajTacke.setTekst("Sadrzaj tacke o donosenju akta.");
		
		Tacka tacka = new Tacka();
		tacka.setSadrzaj(sadrzajTacke);
		
		TSadrzajStava sadrzajStava = new TSadrzajStava();
		//sadrzajStava.getTacka().add(tacka);
		sadrzajStava.setTekst("Sadrzaj stava o donosenju akta.");
		
		
		Stav stav = new Stav();
		stav.setOznaka("stav1");
		stav.setSadrzaj(sadrzajStava);
		
		TSadrzajClana sadrzajClana = new TSadrzajClana();
		sadrzajClana.getStav().add(stav);
		
		Clan clan = new Clan();
		clan.setOznaka("clan1");
		clan.setSadrzaj(sadrzajClana);
		
		Clan clan2 = new Clan();
		clan2.setOznaka("clan2");
		
		
		TSadrzajGlave sadrzajGlave = new TSadrzajGlave();
		sadrzajGlave.getClan().add(clan);
		//sadrzajGlave.getClan().add(clan2);
		
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
		pzo.setPredlagac(odbornik);
		
		Akt akt = new Akt();
		akt.setNaziv("Akt1");
		akt.setPreambula("Preambula1");
		
		akt.getDeo().add(deo);
		
		Date date = new Date();
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		try {
			akt.setTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
			pzo.setDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		akt.setID(GenerateRandNumber());
		akt.setPrelazneIZavrsneOdredbe(pzo);
		
		return akt;
		
	}
	
	private String GenerateRandNumber()
	{
		Random randomGenerator = new Random();
		return Integer.toString(randomGenerator.nextInt(Integer.MAX_VALUE));
	}

}
