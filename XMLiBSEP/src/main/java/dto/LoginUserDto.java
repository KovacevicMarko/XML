package dto;


import model.AbstractIdMaker;

@SuppressWarnings("serial")
public class LoginUserDto extends AbstractIdMaker{

	private String password;
	
	private String username;
	
	
	public LoginUserDto()
	{
		/*Default constructor*/
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
