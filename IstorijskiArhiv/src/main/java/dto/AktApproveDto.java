package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AktApproveDto implements Serializable {
	
	private String aktId;
	private List<String> amandmanIds;
	private int numberOfAmandmans;
	
	public int getNumberOfAmandmans() {
		return numberOfAmandmans;
	}

	public void setNumberOfAmandmans(int numberOfAmandmans) {
		this.numberOfAmandmans = numberOfAmandmans;
	}

	public AktApproveDto() {
		// TODO Auto-generated constructor stub
	}

	public String getAktId() {
		return aktId;
	}

	public void setAktId(String aktId) {
		this.aktId = aktId;
	}

	public List<String> getAmandmanIds() {
		return amandmanIds;
	}

	public void setAmandmanIds(List<String> amandmanIds) {
		this.amandmanIds = amandmanIds;
	}
	
	
}
