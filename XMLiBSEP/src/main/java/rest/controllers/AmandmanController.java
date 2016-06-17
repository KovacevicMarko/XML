package rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import businessLogic.BeanHelperMethods;
import businessLogic.BeanManager;
import common.DatabaseConnection;
import common.Role;
import dto.AktSearchDto;
import dto.AmandmanDto;
import dto.UserDto;
import model.Akt;
import model.Amandman;
import securityPackage.SessionHandler;

@RestController
@RequestMapping(value = "/amandman/")
public class AmandmanController {
	
	@RequestMapping(value="/getProposed/", method = RequestMethod.GET)
	 public ResponseEntity getProposedAmandmans() 
	{
		ResponseEntity retVal;
		
		BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
	    StringBuilder query = new StringBuilder();
	    query.append("fn:collection(\"");
	    query.append(DatabaseConnection.AMANDMAN_PREDLOZEN_COL_ID);
	    query.append("\")");
	    
	    ArrayList<Amandman> amandmani =bm.executeQuery(query.toString());
	    
	    if(amandmani==null || amandmani.isEmpty()){
	    	retVal = new ResponseEntity("Not found" ,HttpStatus.NOT_FOUND);
	    	return retVal;
	    }
	    
	    retVal = new ResponseEntity(amandmani,HttpStatus.OK);
	    return retVal;
	    
	 }
	
	
	@RequestMapping(value = "/addAmandman/", method = RequestMethod.POST)
	public ResponseEntity addAmandman(@RequestBody Amandman amandman, HttpServletRequest req){
		
		ResponseEntity retVal;
		
		if(!SessionHandler.isValidSession(req.getSession(), Role.ULOGA_ODBORNIK)){
			retVal = new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
			return retVal;
		}
		
		UserDto userOnSession = (UserDto) req.getSession().getAttribute("user");
		String username = userOnSession.getKorisnickoIme();
		
		BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
		
		boolean isWritingFailed = false;
		
		if(bm.writeDocument(amandman, DatabaseConnection.AMANDMAN_PREDLOZEN_COL_ID, true, username)==null){
			isWritingFailed = true;
		}
		
		if(isWritingFailed){
			retVal = new ResponseEntity("Bad request, check your xml",HttpStatus.BAD_REQUEST);
			return retVal;
		}

		retVal = new ResponseEntity(amandman,HttpStatus.OK);
		return retVal;
	}
	
	@RequestMapping(value = "/withdraw/", method = RequestMethod.DELETE)
	public ResponseEntity withdrawAmandman(@RequestBody String amandmanId, HttpServletRequest req){
		
		ResponseEntity retVal; 
		
		if(!SessionHandler.isValidSession(req.getSession(), Role.ULOGA_ODBORNIK)){
			retVal = new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
			return retVal;
		}	
		
		UserDto userOnSession = (UserDto) req.getSession().getAttribute("user");
		
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		bm.deleteDocument(amandmanId);
		
		BeanHelperMethods bhm = new BeanHelperMethods();
		
		HashMap<String, List<?>> proposedAktsAndAmans = bhm.getProposedAktsAndAmans();
		
		retVal = new ResponseEntity(proposedAktsAndAmans,HttpStatus.OK);	
		return retVal;
	}
	
	@RequestMapping(value = "/search/", method = RequestMethod.POST)
	public ResponseEntity searchAmandman(@RequestBody String content) {
		
		ResponseEntity retVal;
		
		BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
    	HashMap<String,ArrayList<String>> predlozeni = bm.searchByContent(content, DatabaseConnection.AMANDMAN_PREDLOZEN_COL_ID);
    	
    	if(predlozeni.isEmpty()){
    		retVal = new ResponseEntity("Content not found",HttpStatus.NOT_FOUND);
    		return retVal;
    	}
    	    
        retVal = new ResponseEntity(predlozeni,HttpStatus.OK);
        
		return retVal;
	}
	
	@RequestMapping(value = "/searchByTag/", method = RequestMethod.POST)
	public ResponseEntity searchAmandmanByTag(@RequestBody AktSearchDto tagAndContent) {
		
		String tagName = tagAndContent.getTagName();
		String content = tagAndContent.getContent();
		
		ResponseEntity retVal;
		
		BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
		
    	HashMap<String,ArrayList<String>> predlozeni = bm.searchByContentAndTag(content, DatabaseConnection.AMANDMAN_PREDLOZEN_COL_ID, tagName);
    	
    	if(predlozeni.isEmpty()){
    		retVal = new ResponseEntity("Not found content under "+tagName,HttpStatus.NOT_FOUND);
    		return retVal;
    	}
        
        retVal = new ResponseEntity(predlozeni,HttpStatus.OK);
        
		return retVal;
	}
}
