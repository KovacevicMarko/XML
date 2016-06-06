package dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

import model.Akt;
import model.TOdbornik;

public class AmandmanDto {

    protected String pravniOsnov;
    protected TOdbornik predlagac;
    protected String akt;
    protected String predmetIzmene;
    protected String ciljIzmene;
    protected String datumPredlaganja;
	
    
    public String getPravniOsnov() {
		return pravniOsnov;
	}
	public void setPravniOsnov(String pravniOsnov) {
		this.pravniOsnov = pravniOsnov;
	}
	public TOdbornik getPredlagac() {
		return predlagac;
	}
	public void setPredlagac(TOdbornik predlagac) {
		this.predlagac = predlagac;
	}
	public String getAkt() {
		return akt;
	}
	public void setAkt(String akt) {
		this.akt = akt;
	}
	public String getPredmetIzmene() {
		return predmetIzmene;
	}
	public void setPredmetIzmene(String predmetIzmene) {
		this.predmetIzmene = predmetIzmene;
	}
	public String getCiljIzmene() {
		return ciljIzmene;
	}
	public void setCiljIzmene(String ciljIzmene) {
		this.ciljIzmene = ciljIzmene;
	}
	public String getDatumPredlaganja() {
		return datumPredlaganja;
	}
	public void setDatumPredlaganja(String datumPredlaganja) {
		this.datumPredlaganja = datumPredlaganja;
	}
    
    
	
}
