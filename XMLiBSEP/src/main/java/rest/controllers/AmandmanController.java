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
import java.util.List;

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
import model.TOdbornik;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.json.JSONObject;
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
import common.DatabaseConnection;
import common.Role;

import dto.AktSearchDto;
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
		
		if(!SessionHandler.isValidSession(req.getSession(), Role.ULOGA_ODBORNIK)){
			retVal = new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
			return retVal;
		}
		
		UserDto userOnSession = (UserDto) req.getSession().getAttribute("user");
		String username = userOnSession.getKorisnickoIme();
		
		BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
		
		TOdbornik odbornik = new TOdbornik();
		odbornik.setIme(userOnSession.getIme());
		odbornik.setPrezime(userOnSession.getPrezime());
		odbornik.setUsername(username);
		odbornik.setStranka("Stranka");
		
		amandman.setPredlagacAmandmana(odbornik);
		
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
		
		BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
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
    	HashMap<String,ArrayList<String>> usvojeni = bm.searchByContent(content, DatabaseConnection.AMANDMAN_USVOJEN_COL_ID);
    	
    	HashMap<String,ArrayList<String>> allAkts = new HashMap<>(predlozeni);
    	allAkts.putAll(usvojeni);
    	    
        retVal = new ResponseEntity(allAkts,HttpStatus.OK);
        
		return retVal;
	}
	
	@RequestMapping(value = "/searchByTag/", method = RequestMethod.POST)
	public ResponseEntity searchAmandmanByTag(@RequestBody AktSearchDto tagAndContent) {
		
		String tagName = tagAndContent.getTagName();
		String content = tagAndContent.getContent();
		
		ResponseEntity retVal;
		
		BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
		
    	HashMap<String,ArrayList<String>> predlozeni = bm.searchByContentAndTag(content, DatabaseConnection.AMANDMAN_PREDLOZEN_COL_ID, tagName);
    	HashMap<String,ArrayList<String>> usvojeni = bm.searchByContentAndTag(content, DatabaseConnection.AMANDMAN_USVOJEN_COL_ID,tagName);
    	
    	HashMap<String,ArrayList<String>> allAkts = new HashMap<>(predlozeni);
    	allAkts.putAll(usvojeni);
        
        retVal = new ResponseEntity(allAkts,HttpStatus.OK);
        
		return retVal;
	}
	
	@RequestMapping(value = "/getAmandmanById/", method = RequestMethod.POST)
	public ResponseEntity getAmandmanbyId(@RequestBody String data) {//@RequestBody String data
		//data="15169449515975435548"+".xml";
		data += ".xml";
        System.out.println("ID je : "+data);
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("transform/amandman.xsl"));
        try {
            Transformer transformer = factory.newTransformer(xslt);
            BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
            Amandman amandman=bm.read(data, true);
            bm.convertToXml(amandman);
            
            Source text = new StreamSource(new File("tmp.xml"));
            transformer.transform(text, new StreamResult(new File("transform/tmp.html").getPath()));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        File htmlFile = new File("transform/tmp.html");
       
        
        try {
            FileInputStream fis = new FileInputStream(htmlFile);
            String amandman = "";
            for (String line : Files.readAllLines(Paths.get("transform/tmp.html"))) {
            	amandman+=line;
            }
            System.out.println(amandman);
            JSONObject obj = new JSONObject();
            obj.put("amandman", amandman);
            
            return new ResponseEntity<String>(obj.toString(),HttpStatus.OK);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/downloadAmandman/{fileName}",
            method=RequestMethod.POST)
    public void downloadPDF(HttpServletRequest request,
                           HttpServletResponse response,
                           @PathVariable String fileName) throws IOException {
       	System.out.println("docID= "+ fileName);
        fileName += ".xml";
        try {
			generatePdf(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
        response.setContentType("application/pdf");
        try (InputStream is = new FileInputStream(new File("transform/amandman.pdf"))){
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
	//docId+=".xml";
	BeanManager<Amandman> bm = new BeanManager<>("Schema/Amandman.xsd");
    Amandman akt=bm.read(docId, true);
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
    File xsltFile = new File("transform/amandman-fo.xsl");
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
	
	
	
	File pdfFile = new File("transform/amandman.pdf");
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
