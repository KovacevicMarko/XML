package controller;

import javax.xml.datatype.XMLGregorianCalendar;

import model.Akt;
import model.Deo;
import model.Korisnici;
import model.PrelazneIZavrsneOdredbe;
import model.TKorisnik;
import model.TOdbornik;
import model.TSadrzajDela;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import businessLogic.BeanManager;

import common.DatabaseConnection;
import common.Role;

@Controller
@RequestMapping(value = "/initialize")
public class TestController {
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String Initialize()
	{
		InitializeKorisnik();
		//InitializeAkt();
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
		
		TKorisnik k2 = new TKorisnik();
		k2.setKorisnickoIme("jocko");
		k2.setEmail("jocko");
		k2.setIme("jocko");
		k2.setLozinka("jocko");
		k2.setPrezime("jocko");
		k2.setUloga(Role.ULOGA_ODBORNIK);
		
		korisnici.getKorisnik().add(k);
		korisnici.getKorisnik().add(k2);
		
		BeanManager<Korisnici> bm1 = new BeanManager<>("Schema/Korisnici.xsd");
		String docID = DatabaseConnection.USERS_DOC_ID;
		bm1.write(korisnici, docID, docID);
	}
	
	private void InitializeAkt()
	{
		TSadrzajDela.Glava glava = new TSadrzajDela.Glava();
		
		glava.setNaziv("glava1");
		glava.setOznaka("glava1");
		
		TSadrzajDela sadrzajDela = new TSadrzajDela();
		sadrzajDela.getContent().add(glava);
		
		Deo deo = new Deo();
		deo.setNaziv("deo1");
		deo.setOznaka("deo1");
		deo.setSadrzaj(sadrzajDela);
	
		TOdbornik odbornik = new TOdbornik();
		odbornik.setIme("ImeOdbornik");
		odbornik.setPrezime("PrezimeOdbornik");
		odbornik.setStranka("StrankaOdbornik");
		PrelazneIZavrsneOdredbe pzo = new PrelazneIZavrsneOdredbe();
		XMLGregorianCalendar dateTime = null;
		pzo.setDatum(dateTime);
		pzo.setPredlagac(odbornik);
		
		
		
		Akt akt = new Akt();
		akt.setNaziv("ak1");
		akt.setPreambula("preambula1");
		akt.getDeo().add(deo);
		akt.setPrelazneIZavrsneOdredbe(pzo);
		
		BeanManager<Akt> bm1 = new BeanManager<>("Schema/Akt.xsd");
		String docID = DatabaseConnection.AKT_DOC_ID;
		bm1.write(akt, docID, docID);
		
	}
	
	private void InitializeAmandman()
	{
		
	}

}
