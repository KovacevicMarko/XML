package securityPackage;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;


import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

/**
 * 
 * Primer demonstrira koriscenje programskog API-a za 
 * renderovanje PDF-a na osnovu XSL-FO transformacije.
 *
 */
public class XSLFOTransformer {
	
	private FopFactory fopFactory;
	
	private TransformerFactory transformerFactory;
	
	public XSLFOTransformer() throws SAXException, IOException {
		fopFactory = FopFactory.newInstance(new File("src/main/java/fop.xconf"));
		transformerFactory = new TransformerFactoryImpl();
	}

	public void testAkt() throws Exception {
		File xsltFile = new File("transform/akt-fo.xsl");
		StreamSource transformSource = new StreamSource(xsltFile);
		StreamSource source = new StreamSource(new File("transform/Akt.xml"));
		FOUserAgent userAgent = fopFactory.newFOUserAgent();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
		Result res = new SAXResult(fop.getDefaultHandler());
		xslFoTransformer.transform(source, res);

		File pdfFile = new File("transform/akt.pdf");
		OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
		out.write(outStream.toByteArray());

		out.close();

	}
	
	public void testAmandman() throws Exception {
		File xsltFile = new File("transform/amandman-fo.xsl");
		StreamSource transformSource = new StreamSource(xsltFile);
		StreamSource source = new StreamSource(new File("transform/Amandman.xml"));
		FOUserAgent userAgent = fopFactory.newFOUserAgent();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
		Result res = new SAXResult(fop.getDefaultHandler());
		xslFoTransformer.transform(source, res);

		File pdfFile = new File("transform/amandman.pdf");
		OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
		out.write(outStream.toByteArray());

		out.close();

	}

	public static void main(String[] args) throws Exception {
		new XSLFOTransformer().testAkt();
		//new XSLFOTransformer().testAmandman();
		
		
	}

}
