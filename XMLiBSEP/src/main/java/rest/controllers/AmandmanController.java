package rest.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Akt;
import model.Amandman;
import model.TClanAmandnam;
import model.TSadrzajAmandmana.GlavaAmandman;
import model.TTekstIzmene;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import businessLogic.BeanManager;
import common.ApproveAmandmanOnAct;
import common.DatabaseConnection;
import dto.UserDto;

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
	
	@RequestMapping(value = "/approveAmandman/", method = RequestMethod.POST)
	public ResponseEntity approveAmandman(@RequestBody Amandman amandman, HttpServletRequest req)
	{
		ResponseEntity retVal;
		boolean flag;
		
		String aktId = amandman.getSadrzajAmandmana().getNazivAkta();
		BeanManager<Akt> aktBeanManager = new BeanManager<Akt>();
		Akt akt = aktBeanManager.read(aktId, false);
		//ApproveAmandmanOnAct approveAmandmanOnAct = new ApproveAmandmanOnAct();
		List<GlavaAmandman> glavaList = amandman.getSadrzajAmandmana().getGlavaAmandman();
		if(glavaList.size() != 0)
		{
			//approveAmandmanOnAct.approveAmandmanOnAkt(glavaList, akt);
			
		}
		
		
		retVal = new ResponseEntity(true,HttpStatus.OK);
		return retVal;
	}
	

}
