package rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import businessLogic.BeanManager;
import common.CommonQueries;
import common.DatabaseConnection;
import common.Role;
import dto.AktSearchDto;
import dto.UserDto;
import model.Akt;
import model.TClan;
import model.TDeo;
import model.TSadrzajGlave;

@RestController
@RequestMapping(value = "/akt/")
public class AktController {
	
	@RequestMapping(method = RequestMethod.GET)//AKT_DOC_ID
	 public ResponseEntity getProposedAndApprovedActs() {
	 
		 ResponseEntity retVal;
		 BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		  
	     ArrayList<Akt> aktiPredlozeni=bm.executeQuery(CommonQueries.getAllProposedActs());
	     ArrayList<Akt> aktiUsvojeni = bm.executeQuery(CommonQueries.getAllApprovedActs());
	     
	     HashMap<String,ArrayList<Akt>> akti = new HashMap<String,ArrayList<Akt>>();
	     
	     akti.put("aktiUsvojeni", aktiUsvojeni);
	     akti.put("aktiPredlozeni", aktiPredlozeni);
	     
		 retVal = new ResponseEntity(akti,HttpStatus.OK);
		 return retVal;
     }
	
	@RequestMapping(value = "/addAkt/", method = RequestMethod.POST)
	public ResponseEntity addAkt(@RequestBody Akt akt, HttpServletRequest req){
		
		ResponseEntity retVal; 
		
		UserDto userOnSession = (UserDto) req.getSession().getAttribute("user");
		String username = userOnSession.getKorisnickoIme();
		
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		
		boolean isWritingFailed = false;
		
		if(bm.writeDocument(akt, DatabaseConnection.AKT_PREDLOZEN_COL_ID, true, username)==null){
			isWritingFailed = true;
		}
		
		if(isWritingFailed){
			retVal = new ResponseEntity("Bad request, check your xml",HttpStatus.BAD_REQUEST);
			return retVal;
		}

		retVal = new ResponseEntity(akt,HttpStatus.OK);
		return retVal;
		
	}
	
	@RequestMapping(value="/approve/", method = RequestMethod.POST)//AKT_DOC_ID
	 public ResponseEntity approveAkt(@RequestBody String docId,HttpServletRequest req) {
	 
		 ResponseEntity retVal;
		 
		 if(req.getSession().getAttribute("user")==null){
				
			retVal = new ResponseEntity(null,HttpStatus.BAD_REQUEST);
			return retVal;
				
		 }	
			
		UserDto userOnSession = (UserDto) req.getSession().getAttribute("user");
			
		//PROVERA DA SAMO ODBORNIK MOZE DA TRAZI OVU FUNKCIONALNOST.
		if(userOnSession.getUloga().equals(Role.ULOGA_ODBORNIK)){
			retVal = new ResponseEntity(null,HttpStatus.BAD_REQUEST);
			return retVal;
		}
		
		String username = userOnSession.getKorisnickoIme();
		
		
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		Akt akt = bm.read(docId, true);
		
		bm.deleteDocument(docId);
		
		
		bm.write(akt, docId, DatabaseConnection.AKT_USVOJEN_COL_ID, true, username);
		
		retVal = new ResponseEntity(HttpStatus.OK);
		return retVal;
		
    }
	
	
	
	@RequestMapping(value = "/withdraw/", method = RequestMethod.DELETE)
	public ResponseEntity withdrawAkt(@RequestBody String aktId, HttpServletRequest req){
		
		ResponseEntity retVal; 
		
		if(req.getSession().getAttribute("user")==null){
			
			retVal = new ResponseEntity(null,HttpStatus.BAD_REQUEST);
			return retVal;
			
		}	
		
		UserDto userOnSession = (UserDto) req.getSession().getAttribute("user");
		
		//PROVERA DA SAMO ODBORNIK MOZE DA TRAZI OVU FUNKCIONALNOST.
		if(userOnSession.getUloga().equals(Role.ULOGA_PREDSEDNIK)){
			retVal = new ResponseEntity(null,HttpStatus.BAD_REQUEST);
			return retVal;
		}
		
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		bm.deleteDocument(aktId);
		
		retVal = new ResponseEntity(HttpStatus.OK);		
		return retVal;
	}
	
	
	@RequestMapping(value = "/search/", method = RequestMethod.POST)
	public ResponseEntity searchAkt(@RequestBody String content) {
		
		ResponseEntity retVal;
		
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
    	HashMap<String,ArrayList<String>> predlozeni = bm.searchByContent(content, DatabaseConnection.AKT_PREDLOZEN_COL_ID);
    	
    	if(predlozeni.isEmpty()){
    		retVal = new ResponseEntity("Content not found",HttpStatus.NOT_FOUND);
    		return retVal;
    	}
    	
        retVal = new ResponseEntity(predlozeni,HttpStatus.OK);
        
		return retVal;
	}
	
	@RequestMapping(value = "/searchByTag/", method = RequestMethod.GET)
	//public ResponseEntity searchAktByTag(@RequestBody AktSearchDto tagAndContent) {
	public ResponseEntity searchAktByTag() {
		
//		String tagName = tagAndContent.getTagName();
//		String content = tagAndContent.getContent();
		
		String tagName = "SadrzajAlineja";
		String content = "Sadrzaj";
		
		ResponseEntity retVal;
		
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		
    	HashMap<String,ArrayList<String>> predlozeni = bm.searchByContentAndTag(content, DatabaseConnection.AKT_PREDLOZEN_COL_ID, tagName);
    	
    	if(predlozeni.isEmpty()){
    		retVal = new ResponseEntity(null,HttpStatus.OK);
    		return retVal;
    	}
        
        retVal = new ResponseEntity(predlozeni,HttpStatus.OK);
        
		return retVal;
	}
	

}
