package businessLogic;

import model.*;

import java.util.HashMap;

public class BeanContainer {
	
	private HashMap<Class, BeanManager<?>> container = null;

    public BeanContainer(){
        initializeBeans();
    }

    private void initializeBeans()
    {
    	container = new HashMap<Class, BeanManager<?>>();
    	
        BeanManager<Akt> aktManager = new BeanManager<>();
        container.put(Akt.class,aktManager);
    
        BeanManager<Alineja> alinejaManager = new BeanManager<>();
        container.put(Alineja.class,alinejaManager);
        
        BeanManager<Amandman> amandmanManager = new BeanManager<>();
        container.put(Amandman.class,amandmanManager);
        
        BeanManager<Clan> clanManager = new BeanManager<>();
        container.put(Clan.class,clanManager);
        
        BeanManager<Deo> deoManager = new BeanManager<>();
        container.put(Deo.class, deoManager);
        
        BeanManager<Glava> glavaManager = new BeanManager<>();
        container.put(Glava.class, glavaManager);
        
        BeanManager<Korisnici> korisniciManager = new BeanManager<>();
        container.put(Korisnici.class, korisniciManager);
        
        BeanManager<Odeljak> odeljakManager = new BeanManager<>();
        container.put(Odeljak.class, odeljakManager);
        
        BeanManager<PrelazneIZavrsneOdredbe> prelazneOdredbeManager = new BeanManager<>();
        container.put(PrelazneIZavrsneOdredbe.class, prelazneOdredbeManager);
        
        BeanManager<Referenca> referencaManager = new BeanManager<>();
        container.put(Referenca.class, referencaManager);
        
        BeanManager<Stav> stavManager = new BeanManager<>();
        container.put(Stav.class, stavManager);
        
        BeanManager<Tacka> tackaManager = new BeanManager<>();
        container.put(Tacka.class, tackaManager);
        
        BeanManager<TGradjanin> tgradjManager = new BeanManager<>();
        container.put(TGradjanin.class, tgradjManager);
        
        BeanManager<TKorisnik> tkorisnikManager = new BeanManager<>();
        container.put(TKorisnik.class, tkorisnikManager);
        
        BeanManager<TOdbornik> todborManager = new BeanManager<>();
        container.put(TOdbornik.class, todborManager);
        
        BeanManager<TPredsednikSkupstine> tpredsednikManager = new BeanManager<>();
        container.put(TPredsednikSkupstine.class, tpredsednikManager);
        
        BeanManager<TSadrzajClana> tsClanManager = new BeanManager<>();
        container.put(TSadrzajClana.class, tsClanManager);
        
        BeanManager<TSadrzajDela> tsDeoManager = new BeanManager<>();
        container.put(TSadrzajDela.class, tsDeoManager);
        
        BeanManager<TSadrzajGlave> tsGlavaManager = new BeanManager<>();
        container.put(TSadrzajGlave.class, tsGlavaManager);
        
        BeanManager<TSadrzajOdeljka> tsOdeljakManager = new BeanManager<>();
        container.put(TSadrzajOdeljka.class, tsOdeljakManager);
        
        BeanManager<TSadrzajStava> tsStavManager = new BeanManager<>();
        container.put(TSadrzajStava.class, tsStavManager);
        
        BeanManager<TSadrzajTacke> tsTackaManager = new BeanManager<>();
        container.put(TSadrzajTacke.class, tsTackaManager);
        
    }
    
    public  BeanManager<?> getContainerManager(Class T){
        if (container == null){
            initializeBeans();
        }
        return container.get(T);
    }

}
