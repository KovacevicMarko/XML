package dto;

public class ChangePassDto {
	protected String korisnickoIme;
    protected String lozinka;
    protected String novaLozinka;
    
    
    
	public ChangePassDto() {
		
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
	public String getNovaLozinka() {
		return novaLozinka;
	}
	public void setNovaLozinka(String novaLozinka) {
		this.novaLozinka = novaLozinka;
	}
	
    
}
