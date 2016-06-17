package common;

import java.util.List;

import model.Akt;
import model.TClan;
import model.TClan.Stav;
import model.TClanAmandnam;
import model.TClanAmandnam.StavAmandman;
import model.TClanAmandnam.StavAmandman.TackaAmandman;
import model.TDeo;
import model.TSadrzajAmandmana.GlavaAmandman;
import model.TSadrzajAmandmana.GlavaAmandman.OdeljakAmandman;
import model.TSadrzajStava;
import model.TSadrzajStava.Tacka;
import model.TSadrzajTacke;
import model.TTekst;
import model.TTekstIzmene;
import enums.TTipIzmeneEnum;

public class ApproveAmandmanOnAct<T> 
{
	private ApproveAmandmanOnAct instance;
	private Akt akt;
	
	public ApproveAmandmanOnAct(Akt aktOld)
	{
		this.akt = aktOld;
	}
	
	private ApproveAmandmanOnAct getInstance(Akt aktOld)
	{
		if(instance == null)
		{
			instance = new ApproveAmandmanOnAct(aktOld);
		}
		return instance;
	}
	

	
	public Akt approveAmandmanOnAkt(List<GlavaAmandman> glavaList, Akt aktOld)
	{
		//this.akt = aktOld;
		instance = getInstance(aktOld);
		boolean flagOdeljak = glavaList.get(0).getOdeljakAmandman().size()==0?false:true;
		
		for(GlavaAmandman glava : glavaList)
		{
			if(flagOdeljak)
			{
				for(OdeljakAmandman odeljak : glava.getOdeljakAmandman())
				{
					applyClanOnActs(odeljak.getClanAmandman());
				}
			}else
			{
				applyClanOnActs(glava.getClanAmandman());
			}
		}
		
		return akt;
	}
	

	
	private void applyClanOnActs(List<TClanAmandnam> clanoviAmandmana)
	{
		for(TClanAmandnam clan : clanoviAmandmana)
		{
			if(checkIfChangeIsNull(clan.getIzmenaClana()))
			{
				applyStavOnActs(clan.getOznakaClana(), clan.getStavAmandman());
			}
			else
			{
				if(clan.getIzmenaClana().getTipIzmene().equals(TTipIzmeneEnum.Dodavanje.toString()))
				{
					String tekstDodaj = clan.getIzmenaClana().getIzmenaSadrzaja();
					instance.addContent(clan.getOznakaClana(), clan, tekstDodaj, null, null);
				}
			}
		}
	}
	
	private void applyStavOnActs(String clanOznaka, List<StavAmandman> stavoviAmandmana)
	{
		for(StavAmandman stav : stavoviAmandmana)
		{
			if(checkIfChangeIsNull(stav.getIzmenaStava()))
			{
				applyTackaOnActs(clanOznaka, stav.getOznakaStava(), stav.getTackaAmandman());
			}
			else
			{
				if(stav.getIzmenaStava().getTipIzmene().equals(TTipIzmeneEnum.Izmena.toString()))
				{
					String value = stav.getIzmenaStava().getIzmenaSadrzaja();
					
					instance.setContent(clanOznaka, stav,value, null, null);
				}
				else if(stav.getIzmenaStava().getTipIzmene().equals(TTipIzmeneEnum.Brisanje.toString()))
				{
					instance.deleteContent(clanOznaka, stav, null, null);
				}
				else if(stav.getIzmenaStava().getTipIzmene().equals(TTipIzmeneEnum.Dodavanje.toString()))
				{
					String tekstDodaj = stav.getIzmenaStava().getIzmenaSadrzaja();
					instance.addContent(clanOznaka, stav, tekstDodaj, stav.getOznakaStava(), null);
				}
			}
		}
		
	}
	
	private void applyTackaOnActs(String clanOznaka, String stavOznaka, List<TackaAmandman> tacke)
	{
		for(TackaAmandman tacka : tacke)
		{
			if(checkIfChangeIsNull(tacka.getIzmenaTacke()))
			{
				
			}else
			{
				if(tacka.getIzmenaTacke().getTipIzmene().equals(TTipIzmeneEnum.Izmena.toString()))
				{
					String value = tacka.getIzmenaTacke().getIzmenaSadrzaja();
					
					instance.setContent(clanOznaka, tacka, value, stavOznaka, null );
				}
				else if(tacka.getIzmenaTacke().getTipIzmene().equals(TTipIzmeneEnum.Brisanje.toString()))
				{
					instance.deleteContent(clanOznaka, tacka, stavOznaka, null);
				}
				else if(tacka.getIzmenaTacke().getTipIzmene().equals(TTipIzmeneEnum.Dodavanje.toString()))
				{
					
				}
			}
			
		}
	}
	
	private void addContent(String clanOznaka, T beanAmandman, String value, String stavOznaka, String tackaOznaka)
	{
		if(beanAmandman instanceof TClanAmandnam)
		{
			applyAddOnAct(((TClanAmandnam) beanAmandman).getOznakaClana(), value);
		}
		else if(beanAmandman instanceof StavAmandman)
		{
			applyAddOnStav(clanOznaka, ((StavAmandman) beanAmandman).getOznakaStava(), value);
		}
	}
	
	private void setContent(String clanOznaka, T beanAmandnam, String value, String stavOznaka, String tackaOznaka)
	{
		if(beanAmandnam instanceof StavAmandman)
		{	
			String oznakaStava = ((StavAmandman) beanAmandnam).getOznakaStava();
			applyChangeOnAct(clanOznaka, oznakaStava, value);
		}
		else if(beanAmandnam instanceof TackaAmandman)
		{
			String oznakaTacka = ((TackaAmandman) beanAmandnam).getOznakaTacke();
			applyChangeOnTacka(clanOznaka, stavOznaka, oznakaTacka, value);
		}
	}
	
	private void deleteContent(String clanOznaka, T beanAmandman, String stavOznaka, String tackaOznaka)
	{
		if(beanAmandman instanceof StavAmandman)
		{	
			String oznakaStava = ((StavAmandman) beanAmandman).getOznakaStava();
			applyDeleteOnAct(clanOznaka, oznakaStava);
		}
		else if(beanAmandman instanceof TackaAmandman)
		{
			String oznakaTacka = ((TackaAmandman) beanAmandman).getOznakaTacke();
			applyDeleteOnTacka(clanOznaka, stavOznaka, oznakaTacka);
		}
	}
	
	private void applyAddOnAct(String clanOznaka, String value)
	{
		TClan clan = getClanOnAct(clanOznaka);
		Stav stav = new Stav();
		String novaOznaka = clan.getStav().get(clan.getStav().size()-1).getOznakaStav();
		stav.setOznakaStav(setNewId(novaOznaka));
		TSadrzajStava sadrzaj = new TSadrzajStava();
		TTekst tekst = new TTekst();
		tekst.setTekst(value);
		sadrzaj.setTekstStav(tekst);
		stav.setSadrzaj(sadrzaj);
		clan.getStav().add(stav);
		
	}
	
	private void applyAddOnStav(String clanOznaka, String stavOznaka, String value)
	{
		Stav stav = getStavOnClan(stavOznaka, getClanOnAct(clanOznaka));
		
		String novaOznaka = stav.getSadrzaj().getTacka().get(stav.getSadrzaj().getTacka().size()-1).getOznakaTacka();
		Tacka tacka = new Tacka();
		
		tacka.setOznakaTacka(setNewId(novaOznaka));
		TSadrzajTacke sadrzaj = new TSadrzajTacke();
		TTekst tekst = new TTekst();
		tekst.setTekst(value);
		sadrzaj.setTekstTacka(tekst);
		tacka.setSadrzaj(sadrzaj);
		stav.getSadrzaj().getTacka().add(tacka);
		
	}
	
	private void applyDeleteOnAct(String clanOznaka, String oznakaStava)
	{
		TClan clan = getClanOnAct(clanOznaka);
		for(TClan.Stav stav : clan.getStav())
		{
			if(stav.getOznakaStav().toLowerCase().equals(oznakaStava.toLowerCase()))
			{	
				clan.getStav().remove(stav);
				break;
			}
		}
	}
	
	private void applyChangeOnAct(String clanOznaka, String stavOznaka, String value)
	{
		TClan clan = getClanOnAct(clanOznaka);
		for(TClan.Stav stav : clan.getStav())
		{
			if(stav.getOznakaStav().toLowerCase().equals(stavOznaka.toLowerCase()))
			{	
				stav.getSadrzaj().getTekstStav().setTekst(value);
				break;
			}
		}
	}
	
	private void applyChangeOnTacka(String clanOznaka, String stavOznaka,String tackaOznaka, String value)
	{
		TClan clan = getClanOnAct(clanOznaka);
		for(TClan.Stav stav : clan.getStav())
		{
			if(stav.getOznakaStav().toLowerCase().equals(stavOznaka.toLowerCase()))
			{	
				for(Tacka tacka : stav.getSadrzaj().getTacka())
				{
					if(tacka.getOznakaTacka().toLowerCase().equals(tackaOznaka.toLowerCase()))
					{
						tacka.getSadrzaj().getTekstTacka().setTekst(value);
						break;
					}
				}
			}
		}
	}
	
	private void applyDeleteOnTacka(String clanOznaka, String oznakaStava, String oznakaTacke)
	{
		TClan clan = getClanOnAct(clanOznaka);
		for(TClan.Stav stav : clan.getStav())
		{
			if(stav.getOznakaStav().toLowerCase().equals(oznakaStava.toLowerCase()))
			{	
				for(Tacka tacka : stav.getSadrzaj().getTacka())
				{
					if(tacka.getOznakaTacka().toLowerCase().equals(oznakaTacke.toLowerCase()))
					{
						stav.getSadrzaj().getTacka().remove(tacka);
						break;
					}
				}
			}
		}
	}
	private Stav getStavOnClan(String stavOznaka, TClan clan)
	{
		for(Stav stav : clan.getStav())
		{
			if(stav.getOznakaStav().toLowerCase().equals((stavOznaka.toLowerCase())))
			{
				return stav;
			}
		}
		return null;
	}
	
	private TClan getClanOnAct(String oznakaClana)
	{
		TClan retVal = null;
		for(TDeo deo : this.akt.getDeo())
		{
			for(TDeo.Glava glava : deo.getGlava())
			{
				for(TClan clan : glava.getSadrzajGlava().getClan())
				{
					if(clan.getOznakaClan().toLowerCase().equals(oznakaClana.toLowerCase()))
					{
						retVal = clan;
						return retVal;
					}
				}
			}
		}
		return retVal;
	}
	
	private Stav getStavOnAct(String oznakaClana, String oznakaStava)
	{
		Stav retVal = null;
		for(TDeo deo : akt.getDeo())
		{
			for(TDeo.Glava glava : deo.getGlava())
			{
				for(TClan clan : glava.getSadrzajGlava().getClan())
				{
					if(clan.getOznakaClan().toLowerCase().equals(oznakaClana.toLowerCase()))
					{
						for(Stav stav : clan.getStav())
						{
							if(stav.getOznakaStav().toLowerCase().equals(oznakaStava.toLowerCase()))
							{
								retVal = stav;
							}
						}
					}
				}
			}
		}
		return retVal;
	}
	
	private boolean checkIfChangeIsNull(TTekstIzmene tekstIzmene)
	{
		if(tekstIzmene == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private String setNewId(String oznaka)
	{
		int br = 0;
		String novi = "";
		try
		{
			novi = oznaka.substring(0, oznaka.length()-1);
			br = Integer.parseInt(oznaka.substring(oznaka.length()-1));
		}
		catch(Exception ex)
		{
			return oznaka +"1";
		} 
		br +=1;
		
		return novi + Integer.toString(br);
	}
}
