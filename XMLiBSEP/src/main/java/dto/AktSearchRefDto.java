package dto;

import java.io.Serializable;

public class AktSearchRefDto implements Serializable {
	
    private String aktName;
    private String clanName;
    private String tackaName;
    
    public AktSearchRefDto() {
		// TODO Auto-generated constructor stub
	}

	public String getAktName() {
		return aktName;
	}

	public void setAktName(String aktName) {
		this.aktName = aktName;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	public String getTackaName() {
		return tackaName;
	}

	public void setTackaName(String tackaName) {
		this.tackaName = tackaName;
	}
    
    

}
