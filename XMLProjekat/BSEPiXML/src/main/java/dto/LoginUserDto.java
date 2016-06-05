package dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import model.AbstractIdMaker;

@SuppressWarnings("serial")
public class LoginUserDto extends AbstractIdMaker{

	
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	@NotNull
	private String password;
	
	
	@Pattern(regexp = "^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$")
	@NotNull
	private String username;
	
	
	public LoginUserDto()
	{
		
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
