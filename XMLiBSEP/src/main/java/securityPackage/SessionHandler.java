package securityPackage;

import javax.servlet.http.HttpSession;

import common.Role;
import dto.UserDto;

public class SessionHandler {
	
	public static boolean isValidSession(HttpSession session , String role){
		
		if(!role.equals(Role.ULOGA_GRADJANIN) || 
				!role.equals(Role.ULOGA_ODBORNIK) ||
				      !role.equals(Role.ULOGA_PREDSEDNIK))
		{
			return false;
		}
				      
		
		if(role.equals(Role.ULOGA_GRADJANIN)){
			return true;
		}
		
		if(session.getAttribute("user")==null){
			return false;
		}
		
		UserDto user = (UserDto) session.getAttribute("user");
			
		if(role.equals(Role.ULOGA_PREDSEDNIK)) 
		{
			if(!user.getUloga().equals(Role.ULOGA_PREDSEDNIK)){
				return false;
			}
			else{
				return true;
			}
		}
		
		return true;
	}

}
