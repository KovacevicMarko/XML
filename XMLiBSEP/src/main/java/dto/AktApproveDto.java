package dto;

import java.util.ArrayList;

public class AktApproveDto {
	
	private String aktId;
	private ArrayList<String> amandmanIds;
	private int numberOfAmandmans;
	private int numberOfApprovedAmandmans;
	
	public int getNumberOfAmandmans() {
		return numberOfAmandmans;
	}

	public void setNumberOfAmandmans(int numberOfAmandmans) {
		this.numberOfAmandmans = numberOfAmandmans;
	}

	public int getNumberOfApprovedAmandmans() {
		return numberOfApprovedAmandmans;
	}

	public void setNumberOfApprovedAmandmans(int numberOfApprovedAmandmans) {
		this.numberOfApprovedAmandmans = numberOfApprovedAmandmans;
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

	public ArrayList<String> getAmandmanIds() {
		return amandmanIds;
	}

	public void setAmandmanIds(ArrayList<String> amandmanIds) {
		this.amandmanIds = amandmanIds;
	}
	
	
}
