package rest.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import businessLogic.BeanManager;
import common.ApproveAmandmanOnAct;
import common.DatabaseConnection;
import common.Role;
import enums.TTipIzmeneEnum;
import model.Akt;
import model.Amandman;
import model.Deo.Glava;
import model.Korisnici;
import model.PrelazneIZavrsneOdredbe;
import model.TClan;
import model.TClan.Stav;
import model.TClanAmandnam;
import model.TClanAmandnam.StavAmandman.TackaAmandman;
import model.TDeo;
import model.TKorisnik;
import model.TOdbornik;
import model.TSadrzajAmandmana;
import model.TSadrzajAmandmana.GlavaAmandman;
import model.TSadrzajGlave;
import model.TSadrzajStava;
import model.TSadrzajStava.Tacka;
import model.TSadrzajTacke;
import model.TSadrzajTacke.Alineja;
import model.TTekst;
import model.TTekstIzmene;
import password.PasswordStorage;

@RestController
@RequestMapping(value = "/initialize/")
public class TestController {
	
	@Autowired
	ArhivController arhiv;
	
	@RequestMapping(method = RequestMethod.GET)
	public String Initialize()
	{
//		InitializeKorisnik();
//		System.out.println("USPESNO INIZIJALIZOVAN KORISNIK!");
//		InitializeAkt();
//	    System.out.println("USPESNO INIZIJALIZOVAN AKT!");
//		InitializeAmandman();
//		System.out.println("USPESNO INIZIJALIZOVAN AMANDMAN!");
		//InitializeAktEncrypt();
//		System.out.println("USPESNO INIZIJALIZOVAN AKT ENKRIPT!");
//		TestReadAkt();
	
//		DeleteActs();
	    
	    readAct();
		
		return "Ajmo Kocko";
	}
	
	private void readAct()
	{
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		Akt akt = bm1.read("16450434468119619897.xml", false);
		System.out.println("******************" + akt.getId());
		
		BeanManager<Amandman> bm2 = new BeanManager("Schema/Amandman.xsd");
		Amandman amandman = bm2.read("11513499627483176774.xml", false);
		
		ApproveAmandmanOnAct approve = new ApproveAmandmanOnAct<>(akt);
		Akt akt2 = approve.approveAmandmanOnAkt(amandman.getSadrzajAmandmana().getGlavaAmandman(), akt);
		System.out.println(akt2.getNazivAkt());
		
		
		BeanManager<Akt> bm12 = new BeanManager<>("Schema/Akt.xsd");
		bm12.writeDocument(akt2, DatabaseConnection.AKT_USVOJEN_COL_ID, true, "jocko");
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
        //hash pass//
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
        //hash pass//
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
		bm1.write(korisnici, DatabaseConnection.USERS_DOC_ID, DatabaseConnection.USERS_COL_ID, false,"jocko");
		
	}
	
	private void InitializeAkt()
	{
		
		Akt akt = createAkt();
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		bm1.writeDocument(akt, DatabaseConnection.AKT_PREDLOZEN_COL_ID , true,"jocko");
		
	}
	
	private void InitializeAmandman()
	{
		TackaAmandman tacka = new TackaAmandman();
		tacka.setOznakaTacke("tacka1");
		TTekstIzmene textImetodIzmene3 = new TTekstIzmene();
		textImetodIzmene3.setIzmenaSadrzaja("Brisemo tacku iz Akta");
		textImetodIzmene3.setTipIzmene(TTipIzmeneEnum.Brisanje.toString());
		tacka.setIzmenaTacke(textImetodIzmene3);
		
		TTekstIzmene textImetodIzmene4 = new TTekstIzmene();
		textImetodIzmene4.setIzmenaSadrzaja("Menjamo tekst iz tacke.");
		textImetodIzmene4.setTipIzmene(TTipIzmeneEnum.Izmena.toString());
		
		TackaAmandman tacka2 = new TackaAmandman();
		tacka2.setOznakaTacke("tacka2");
		tacka2.setIzmenaTacke(textImetodIzmene4);
		
		
		//kreiranje stav amandmana
		TClanAmandnam.StavAmandman stav = new TClanAmandnam.StavAmandman();
		stav.setOznakaStava("stav1");
		TTekstIzmene textImetodIzmene = new TTekstIzmene();
		textImetodIzmene.setIzmenaSadrzaja("Brisemo stav iz Akta");
		textImetodIzmene.setTipIzmene(TTipIzmeneEnum.Dodavanje.toString());
		//stav.setIzmenaStava(textImetodIzmene);
		stav.getTackaAmandman().add(tacka);
		stav.getTackaAmandman().add(tacka2);
		
		
		TTekstIzmene textImetodIzmene5 = new TTekstIzmene();
		textImetodIzmene5.setIzmenaSadrzaja("Dodajemo tacku u stav.");
		textImetodIzmene5.setTipIzmene(TTipIzmeneEnum.Dodavanje.toString());
		
		stav.setIzmenaStava(textImetodIzmene5);
		
		
		
		//Kreiranje clana i dodavanje stava na njega.
		TClanAmandnam clanAmandnam = new TClanAmandnam();
		clanAmandnam.setOznakaClana("clan1");
		TTekstIzmene textImetodIzmene2 = new TTekstIzmene();
		textImetodIzmene2.setIzmenaSadrzaja("Dodajemo novi stav u clan");
		textImetodIzmene2.setTipIzmene(TTipIzmeneEnum.Dodavanje.toString());
		//clanAmandnam.setIzmenaClana(textImetodIzmene2);
		clanAmandnam.getStavAmandman().add(stav);
		
		//Kreiranje glave i dodavanje clana
		GlavaAmandman glavaAmandmana = new GlavaAmandman();
		glavaAmandmana.setOznakaGlave("glava1");
		glavaAmandmana.getClanAmandman().add(clanAmandnam);
		
		//Kreiranje sadrzaja amandmana i dodavanje glave.
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
		odbornik.setUsername("odbornikUsername");
		amandman.setPredlagacAmandmana(odbornik);
		Date date = new Date();
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		try {
			amandman.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		amandman.setId(GenerateRandNumber());
		
		BeanManager<Amandman> bm1 = new BeanManager<>("Schema/Amandman.xsd");
		bm1.writeDocument(amandman, DatabaseConnection.AMANDMAN_PREDLOZEN_COL_ID, true,"jocko");
	}
	
	private void InitializeAktEncrypt()
	{
		Akt akt = createAkt();
		
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		if(bm1.write(akt, DatabaseConnection.AKT_ENCRYPT_DOC_ID,  DatabaseConnection.AKT_ENCRYPT_COL_ID, true,"jocko")){
			System.out.println("Uspesno zapisan enkriptovan akt");
		}else{
			System.out.println("Ne uspesno zapisan enkriptovan akt");
		}
	}
	
	private Akt createAkt()
	{
		Alineja alineja = new Alineja();
		alineja.setOznakaAlineja("Alineja1");
		alineja.setSadrzajAlineja("Sadrzaj alineje o donosenju akta.");
		
		TSadrzajTacke sadrzajTacke = new TSadrzajTacke();
		sadrzajTacke.getAlineja().add(alineja);
		TTekst text = new TTekst();
		text.setTekst("Text tacke.");
		sadrzajTacke.setTekstTacka(text);
		
		Tacka tacka = new Tacka();
		tacka.setOznakaTacka("Tacka1");
		tacka.setSadrzaj(sadrzajTacke);
		
		Tacka tacka2 = new Tacka();
		tacka2.setOznakaTacka("Tacka2");
		tacka2.setSadrzaj(sadrzajTacke);
			
		
		TSadrzajStava sadrzajStava = new TSadrzajStava();
		
		TTekst text2 = new TTekst();
		text2.setTekst("Sadrzaj stava o donosenju akta");
		
		sadrzajStava.setTekstStav(text2);
		sadrzajStava.getTacka().add(tacka);	
		sadrzajStava.getTacka().add(tacka2);
		
		Stav stav = new Stav();
		stav.setOznakaStav("stav1");
		stav.setSadrzaj(sadrzajStava);
		
		Stav stav2 = new Stav();
		stav2.setOznakaStav("stav2");
		stav2.setSadrzaj(sadrzajStava);
		
		TClan clan = new TClan();
		clan.setOznakaClan("clan1");
		clan.getStav().add(stav);
		clan.getStav().add(stav2);
		
		
		TSadrzajGlave sadrzajGlave = new TSadrzajGlave();
		sadrzajGlave.getClan().add(clan);
		
		TDeo.Glava glava = new TDeo.Glava();
		glava.setNazivGlava("glava1");
		glava.setOznakaGlava("glava1");
		glava.setSadrzajGlava(sadrzajGlave);
		
		TDeo.Glava glava2 = new TDeo.Glava();
		glava2.setNazivGlava("glava2");
		glava2.setOznakaGlava("glava2");
		glava2.setSadrzajGlava(sadrzajGlave);
		
		TDeo deo = new TDeo();
		deo.setNazivDeo("deo1");
		deo.setOznakaDeo("oznaka1");
		deo.getGlava().add(glava);
		deo.getGlava().add(glava2);
		
		TOdbornik odbornik = new TOdbornik();
		odbornik.setIme("odbornikIme");
		odbornik.setPrezime("odbornikPrezime");
		odbornik.setStranka("odbornikStranka");
		odbornik.setUsername("odbornikUsername");
		
		Akt.PrelazneIZavrsneOdredbe pzo = new Akt.PrelazneIZavrsneOdredbe();
		pzo.setPredlagac(odbornik);
		
		Akt akt = new Akt();
		akt.setNazivAkt("Akt2");
		akt.setPreambula("Preambula2");
		
		akt.getDeo().add(deo);
		
		Date date = new Date();
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		try {
		//	akt.setTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
			pzo.setDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//akt.setId(GenerateRandNumber());
		akt.setPrelazneIZavrsneOdredbe(pzo);
		
		return akt;
		
	}
	
	private String GenerateRandNumber()
	{
		Random randomGenerator = new Random();
		return Integer.toString(randomGenerator.nextInt(Integer.MAX_VALUE));
	}
	

}
