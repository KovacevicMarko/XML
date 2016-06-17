package dto;

import java.io.Serializable;

public class AktSearchDto implements Serializable {
	
	private String tagName;
	private String content;
	
	public AktSearchDto() {
		// TODO Auto-generated constructor stub
	}
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
