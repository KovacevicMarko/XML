package dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import model.Deo;
import model.PrelazneIZavrsneOdredbe;

public class AktDto implements Serializable {

	
    protected String preambula;
    
    protected String naziv;
    
    protected List<Deo> deo;
   
    protected PrelazneIZavrsneOdredbe prelazneIZavrsneOdredbe;
    
    protected String id;
    
    public AktDto() {
		// TODO Auto-generated constructor stub
	}
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPreambula() {
		return preambula;
	}

	public void setPreambula(String preambula) {
		this.preambula = preambula;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Deo> getDeo() {
		return deo;
	}

	public void setDeo(List<Deo> deo) {
		this.deo = deo;
	}

	public PrelazneIZavrsneOdredbe getPrelazneIZavrsneOdredbe() {
		return prelazneIZavrsneOdredbe;
	}

	public void setPrelazneIZavrsneOdredbe(
			PrelazneIZavrsneOdredbe prelazneIZavrsneOdredbe) {
		this.prelazneIZavrsneOdredbe = prelazneIZavrsneOdredbe;
	}
	
	
    
}
