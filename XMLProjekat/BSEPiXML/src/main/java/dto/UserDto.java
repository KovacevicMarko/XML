package dto;

import javax.xml.bind.annotation.XmlElement;

import model.TKorisnik;

public class UserDto {

    protected String korisnickoIme;
    protected String lozinka;
    protected String ime;
    protected String prezime;
    protected String uloga;
    protected String email;
    
    public UserDto(){
    	
    }
    
    public UserDto(TKorisnik korisnik){
    	this.korisnickoIme=korisnik.getKorisnickoIme();
    	this.lozinka=korisnik.getLozinka();
    	this.ime=korisnik.getIme();
    	this.prezime=korisnik.getPrezime();
    	this.uloga=korisnik.getUloga();
    	this.email=korisnik.getEmail();
    }
    
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

    
    
}
