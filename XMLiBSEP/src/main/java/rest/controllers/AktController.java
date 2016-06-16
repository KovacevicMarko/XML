package rest.controllers;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import businessLogic.BeanHelperMethods;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;import businessLogic.BeanManager;
import common.CommonQueries;
import common.DatabaseConnection;
import common.Role;
import dto.AktApproveDto;
import dto.AktSearchDto;
import dto.AktSearchRefDto;
import dto.UserDto;
import model.Akt;
import model.Amandman;

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
		
		if(bm.writeDocument(akt, DatabaseConnection.AKT_ENCRYPT_DOC_ID, true, username)==null){
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
	 public ResponseEntity approveAkt(@RequestBody AktApproveDto dto,HttpServletRequest req) {
	 
		 ResponseEntity retVal;
		 
		 String aktId = dto.getAktId();
		 ArrayList<String> amandmanIds = dto.getAmandmanIds();
		 int numberOfAmandmans = dto.getNumberOfAmandmans();
		 
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
		Akt akt = bm.read(aktId, true);
		
		if(!amandmanIds.isEmpty()){
			//TODO to implement update AKT.
			if(amandmanIds.size()<numberOfAmandmans){
				//Upisuje se u "USVOJEN_U_POJEDINOSTIMA i snimiti stari u bekap kolekciju
			}
			
			else{
				//UPISUJE SE U "USVOJEN_U_CELOSTI" i upisati stari u bekap kolekciju
			}
			
			/*
			 for(Amandman amandman in Amandmans){
			 	updateAkt(akt, amandman);
			 }
			*/
				
		
		}
		
		else{
			//Upisuje se u "USVOJENO_U_NACELU"
		}
		
		//bm.deleteDocument(aktId);
		
	//	bm.write(akt, aktId, DatabaseConnection.AKT_USVOJEN_COL_ID, true, username);
		
		List<Akt> predlozeniAkti = bm.executeQuery(CommonQueries.getAllProposedActs());
		retVal = new ResponseEntity(predlozeniAkti,HttpStatus.OK);
		return retVal;
		
    }
	
	@RequestMapping(value="/getAmandmansForAktId/", method = RequestMethod.POST)//AKT_DOC_ID
	 public ResponseEntity getAmandmans(@RequestBody String docId,HttpServletRequest req) {
	 
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
		
		BeanHelperMethods bhm = new BeanHelperMethods();
		List<Amandman> amandmansForAkt = bhm.getAmandmansForAkt(akt);
				
		retVal = new ResponseEntity(amandmansForAkt,HttpStatus.OK);
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
		Akt akt = bm.read(aktId, true);
		bm.deleteDocument(aktId);
		
		
		BeanHelperMethods bhm = new BeanHelperMethods();
		List<Amandman> amandmans =  bhm.getAmandmansForAkt(akt);
		
		for(Amandman amandman : amandmans){
			bm.deleteDocument(amandman.getId());
		}
		
		HashMap<String, List<?>> proposedAktsAndAmans = bhm.getProposedAktsAndAmans();
		
		retVal = new ResponseEntity(proposedAktsAndAmans,HttpStatus.OK);		
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
	public ResponseEntity searchAktByTag(@RequestBody AktSearchDto tagAndContent) {
		
		String tagName = tagAndContent.getTagName();
		String content = tagAndContent.getContent();
				
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
	@RequestMapping(value = "/getAktById/", method = RequestMethod.GET)
	public ResponseEntity getAktbyId(String data) {//@RequestBody String data
		//data="15169449515975435548"+".xml";
        System.out.println("ID je : "+data);
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("transform/akt.xsl"));
        if(xslt==null){
            System.out.println("xslt = null");
        }
        try {
            Transformer transformer = factory.newTransformer(xslt);
            BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
            Akt akt=bm.read(data, true);
            bm.convertToXml(akt);
            
            Source text = new StreamSource(new File("tmp.xml"));
            transformer.transform(text, new StreamResult(new File("transform/tmp.html")));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

	@RequestMapping(value = "/findReferences/", method = RequestMethod.POST)
	public ResponseEntity findReferences(@RequestBody AktSearchRefDto aktRefDto){
		
		ResponseEntity retVal = null;
		
		String aktId = aktRefDto.getAktId();
		boolean isApproved = aktRefDto.isApproved();
		
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		
		Akt akt = bm.read(aktId, true); 
		
		
		HashMap<String,ArrayList<String>> referencedAktsMap = null;
		
		if(!isApproved){
			referencedAktsMap = bm.searchByContentAndTag(".xml", DatabaseConnection.AKT_PREDLOZEN_COL_ID, "refAkt");
		}
		else{
			referencedAktsMap = bm.searchByContentAndTag(".xml", DatabaseConnection.AKT_USVOJEN_COL_ID, "refAkt");
		}
		
		//List<String> referencedAkts = new ArrayList<>();
		//referencedAkts.t
		List<String> referencedAkts = new ArrayList<>();
		
		
		Iterator it = referencedAktsMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			if(pair.getKey().equals(aktId)){
				for(String s : (ArrayList<String>) pair.getValue()){
					if(!referencedAkts.contains(s)){
						referencedAkts.add(s);
					}
				}
			}
		}
		
			
		retVal = new ResponseEntity(referencedAkts,HttpStatus.OK);
		
		return retVal;
	}
	
	 //@RequestMapping(value="/downloadAkt/", method=RequestMethod.GET)
	public void generatePdf(String docId) throws Exception{
		docId="3847327626572118583"+".xml";
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
        Akt akt=bm.read(docId, true);
        bm.convertToXml(akt);
        FopFactory fopFactory=null;
        
        try {
			fopFactory = FopFactory.newInstance(new File("transform/fop.xconf"));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        TransformerFactory transformerFactory = new TransformerFactoryImpl();
        File xsltFile = new File("transform/akt-fo.xsl");
        StreamSource transformSource = new StreamSource(xsltFile);
		StreamSource source = new StreamSource(new File("tmp.xml"));
		FOUserAgent userAgent = fopFactory.newFOUserAgent();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Transformer xslFoTransformer =transformerFactory.newTransformer(transformSource);
		
			
		Fop fop =fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
			
	
		Result res =null;
			try {
				res=new SAXResult(fop.getDefaultHandler());
			} catch (FOPException e) {
				e.printStackTrace();
			}
				
		
			xslFoTransformer.transform(source, res);
		
		
		
		File pdfFile = new File("transform/akt.pdf");
		OutputStream out=null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(pdfFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			out.write(outStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("[INFO] File \"" + pdfFile.getCanonicalPath() + "\" generated successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return new ResponseEntity(akt,HttpStatus.OK);
	}

}
