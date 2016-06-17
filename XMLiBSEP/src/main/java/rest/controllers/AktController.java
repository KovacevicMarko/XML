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

import model.Akt;
import model.Amandman;

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

import securityPackage.SessionHandler;
import businessLogic.BeanHelperMethods;
import businessLogic.BeanManager;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import common.ApproveAmandmanOnAct;
import common.CommonQueries;
import common.DatabaseConnection;
import common.Role;

import dto.AktSearchDto;
import dto.AktSearchRefDto;
import dto.UserDto;

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
		
		if(!SessionHandler.isValidSession(req.getSession(), Role.ULOGA_ODBORNIK)){
			retVal = new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
			return retVal;
		}
		
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
	
	@RequestMapping(value="/approve/", method = RequestMethod.GET)
	 //public ResponseEntity approveAkt(@RequestBody AktApproveDto dto,HttpServletRequest req) {
		public ResponseEntity approveAkt() {
	 
		ResponseEntity retVal;
		 
		//if(!SessionHandler.isValidSession(req.getSession(), Role.ULOGA_PREDSEDNIK)){
		//	retVal = new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
		//	return retVal;
		//}
		
		/*String aktId = dto.getAktId();
		System.out.println(aktId);
		List<String> amandmanIds = dto.getAmandmanIds();
		int numberOfProposedAmandmans = dto.getNumberOfAmandmans();*/
		
		String aktId = "1082855909737227691.xml";
		int numberOfProposedAmandmans = 1;
		List<String> amandmanIds = new ArrayList<String>();
		amandmanIds.add("2571930459973688753.xml");
			
		//UserDto userOnSession = (UserDto) req.getSession().getAttribute("user");
		
		//String username = userOnSession.getKorisnickoIme();
		String username = "jocko";
		
		BeanHelperMethods bhm = new BeanHelperMethods();
		
		BeanManager<Amandman> bmAmandman = new BeanManager<>("Schema/Amandman.xsd");
		
		BeanManager<Akt> bm = new BeanManager<>("Schema/Akt.xsd");
		Akt akt = bm.read(aktId, true);
		System.out.println("Id akta" + akt.getId());
		
		akt.setSignature(null);
		
		Akt newAkt=null;
		System.out.println("Usao u izmenu.");
		List<Amandman> amandmani;
		
		if(!amandmanIds.isEmpty()){
			
			amandmani = bhm.getAmandmansFromIds(amandmanIds);
			ApproveAmandmanOnAct appClass = new ApproveAmandmanOnAct<>(akt);
			
			System.out.println("Usao");
			bm.writeDocument(akt, DatabaseConnection.AKT_BACKUP_COL_ID, false, username);
				
				//Update akt and write amandman into approved
				for(Amandman am : amandmani)
				{
					newAkt = appClass.approveAmandmanOnAkt(am.getSadrzajAmandmana().getGlavaAmandman(), akt);
					akt = newAkt;
					
					bmAmandman.deleteDocument(am.getId() + ".xml");
					am.setSignature(null);
					bmAmandman.writeDocument(am, DatabaseConnection.AMANDMAN_USVOJEN_COL_ID, false, username);
					
				}
				
				//Delete akt from predlozeni collection
				bm.deleteDocument(aktId);
				akt.setSignature(null);
				//Writing into new collection
				if(amandmani.size()<numberOfProposedAmandmans)
				{	
					bm.writeDocument(akt, DatabaseConnection.AKT_USVOJEN_POJEDINOSTI_COL_ID, false, username);
				}
				else
				{
					bm.writeDocument(akt, DatabaseConnection.AKT_USVOJEN_CELOSTI_COL_ID, false, username);
				}
				
		}			
		else
		{
			akt.setSignature(null);
			bm.deleteDocument(aktId);
			bm.writeDocument(akt, DatabaseConnection.AKT_USVOJEN_NACELO_COL_ID, false, username);
		}
		
		retVal = new ResponseEntity(HttpStatus.OK);
		return retVal;
		
    }
	
	@RequestMapping(value="/getAmandmansForAktId/", method = RequestMethod.POST)//AKT_DOC_ID
	 public ResponseEntity getAmandmans(@RequestBody String docId,HttpServletRequest req) {
	 
		 ResponseEntity retVal;
		 
		 if(!SessionHandler.isValidSession(req.getSession(), Role.ULOGA_ODBORNIK)){
				retVal = new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
				return retVal;
			}
			
		UserDto userOnSession = (UserDto) req.getSession().getAttribute("user");
		
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
		
		if(!SessionHandler.isValidSession(req.getSession(), Role.ULOGA_ODBORNIK)){
			retVal = new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
			return retVal;
		}
		
		UserDto userOnSession = (UserDto) req.getSession().getAttribute("user");
		
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
	
	@RequestMapping(value = "/searchByTag/", method = RequestMethod.POST)
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
	public ResponseEntity getAktbyId(@RequestBody String data) {//String data
		
	        TransformerFactory factory = TransformerFactory.newInstance();
	        Source xslt = new StreamSource(new File("transform/akt.xsl"));
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

	        File htmlFile = new File("transform/tmp.html");
	       
	        
	        try {
	            FileInputStream fis = new FileInputStream(htmlFile);
	            String akt = "";
	            for (String line : Files.readAllLines(Paths.get("transform/tmp.html"))) {
	                akt+=line;
	            }
	            System.out.println(akt);


	            return new ResponseEntity(akt,HttpStatus.OK);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return new ResponseEntity(HttpStatus.BAD_REQUEST);
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
	
	@RequestMapping(value="/downloadAkt/{fileName}",method=RequestMethod.POST)
    public void downloadPDF(HttpServletRequest request, HttpServletResponse response, @PathVariable String fileName) throws IOException {
        System.out.println("docID= "+ fileName);
        fileName += ".xml";
        try {
        	generatePdf(fileName);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        response.setContentType("application/pdf");
        try (InputStream is = new FileInputStream(new File("Akt.pdf"))){
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
           System.out.println("Error");
            throw 
            new RuntimeException("IOError writing file to output stream");
        }
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
