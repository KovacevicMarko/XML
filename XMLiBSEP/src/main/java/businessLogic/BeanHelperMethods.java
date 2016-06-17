package businessLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.CommonQueries;
import model.Akt;
import model.Amandman;
import model.TSadrzajAmandmana.GlavaAmandman;

public class BeanHelperMethods {
	
	public BeanHelperMethods() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Amandman> getAmandmansForAkt(Akt akt){
		
		BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
		List<Amandman> allAmandmans = bm.executeQuery(CommonQueries.getAllProposedAmandmans());
		List<Amandman> amandmansForAkt = new ArrayList<>(); 
		
		//Mora biti prosledjen id akta bez xml-a.
		String idAkta = akt.getId();
		if(idAkta.endsWith(".xml")){
			idAkta = idAkta.split("\\.")[0];
		}
		
		for(Amandman amandman : allAmandmans){
			String s = amandman.getSadrzajAmandmana().getNazivAkta();
			if(s.endsWith(".xml")){
				s = s.split("\\.")[0];
			}
			if(s.equals(idAkta)){
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
	
	public List<Amandman> getAmandmansFromIds(List<String> ids){
		
		BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
		
		List<Amandman> amandmans = new ArrayList<>();
		
		for(int i = 0; i<ids.size(); i++){
				Amandman am = bm.read(ids.get(i), true);
				amandmans.add(am);
		}
		
		System.out.println("Broj pronadjenih" + amandmans.size());
		return amandmans;
		
	}

}
