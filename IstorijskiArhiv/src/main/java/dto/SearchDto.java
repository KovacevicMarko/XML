package dto;

public class SearchDto {
	private String tag;
	private String sadrzaj;
	
	public SearchDto() {
		
	}
	
	
	public SearchDto(String tag, String sadrzaj) {
		super();
		this.tag = tag;
		this.sadrzaj = sadrzaj;
	}


	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getSadrzaj() {
		return sadrzaj;
	}
	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
}
