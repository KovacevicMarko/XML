package common;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import model.TKorisnik;

public class HelperClass {

	private static int CHANGE_PASSWORD_MONTH = 6;
	private static int CHANGE_PASSWROD_DAY_WARNING = 15;
	
	public static boolean CheckPasswordDate(TKorisnik korisnik, HelperObject helperObj)
	{
		Date now = new Date();
		Date dateOfPassword = korisnik.getDatumPromeneLozinke().toGregorianCalendar().getTime();
		
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(dateOfPassword);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(now);

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		int diffDay = diffYear * 365 + endCalendar.get(Calendar.DAY_OF_YEAR) - startCalendar.get(Calendar.DAY_OF_YEAR);
		
		
		if(diffMonth >= CHANGE_PASSWORD_MONTH)
		{
			return false;
		}
		if(diffMonth == CHANGE_PASSWORD_MONTH -1 )
		{
			if(diffDay <= CHANGE_PASSWROD_DAY_WARNING)
			{
				helperObj.setFlag(true);
				helperObj.setNumberOfExpiredDays(diffDay);
			}
		}
		
		
		return true;
	}
	
	public class HelperObject
	{
		boolean flag;
		int numberOfExpiredDays;
		
		public HelperObject()
		{
			
		}
		
		public boolean getFlag()
		{
			return flag;
		}
		public void setFlag(boolean flag)
		{
			this.flag = flag;
		}
		
		public int getNumberOfExpiredDays()
		{
			return numberOfExpiredDays;
		}
		
		public void setNumberOfExpiredDays(int number)
		{
			this.numberOfExpiredDays = number;
		}
		
	}
}
