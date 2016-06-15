package factory;

import dto.UserDto;
import model.TKorisnik;

public class UserFactory {
	
	//private static UserDto userDto;

	public static UserDto createUserDto(TKorisnik korisnik){
		
		UserDto userDto = new UserDto();
		userDto.setKorisnickoIme(korisnik.getKorisnickoIme());
		userDto.setLozinka(korisnik.getLozinka());
		userDto.setIme(korisnik.getIme());
		userDto.setPrezime(korisnik.getPrezime());
		userDto.setUloga(korisnik.getUloga());
		userDto.setEmail(korisnik.getEmail());
    	
    	return userDto;
		
	}

}
