package rest.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import businessLogic.BeanHelperMethods;

@RestController
@RequestMapping(value="/common/")
public class CommonController {
	
	@RequestMapping(value="/getProposedActsAndAmans/", method = RequestMethod.GET)
	 public ResponseEntity getProposedActsAndAmandmans() 
	{
		ResponseEntity retVal;
		
		BeanHelperMethods bhm = new BeanHelperMethods();
		
		HashMap<String, List<?>> mapa = bhm.getProposedAktsAndAmans();
	    
	    retVal = new ResponseEntity(mapa,HttpStatus.OK);
	    return retVal;
	    
	 }
	
	
}
