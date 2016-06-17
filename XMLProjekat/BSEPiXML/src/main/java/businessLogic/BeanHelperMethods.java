package businessLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.CommonQueries;
import model.Akt;
import model.Amandman;

public class BeanHelperMethods {
	
	public BeanHelperMethods() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Amandman> getAmandmansForAkt(Akt akt){
		
		BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
		List<Amandman> allAmandmans = bm.executeQuery(CommonQueries.getAllProposedAmandmans());
		List<Amandman> amandmansForAkt = new ArrayList<>(); 
		
		String idAkta = akt.getId();
		
		for(Amandman amandman : allAmandmans){
			if(amandman.getSadrzajAmandmana().getNazivAkta().equals(idAkta)){
				amandmansForAkt.add(amandman);
			}
		}
		
		return amandmansForAkt;
	}
	
	public HashMap<String,List<?>> getProposedAktsAndAmans(){
		
		HashMap<String,List<?>> mapa = new HashMap<>();
		
		BeanManager<Akt> bmAkt = new BeanManager<>("Schema/Akt.xsd");
		BeanManager<Amandman> bmAman = new BeanManager<>("Schema/Amandman.xsd");
		
		List<Akt> akti = bmAkt.executeQuery(CommonQueries.getAllProposedActs());
		List<Amandman> amandmani = bmAman.executeQuery(CommonQueries.getAllProposedAmandmans());
		
		mapa.put("akti", akti);
		mapa.put("amandmani", amandmani);
		
		return mapa;
		
	}

}
