package actions;

import gui.CertificateDialog;

import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;

import security.CertificateGenerator;
import security.IssuerData;
import security.SubjectData;

/**
 * Class implements action that generates a certificate from filled input fields.
 *
 *
 */
@SuppressWarnings("serial")
public class GenerateCertificateAction extends AbstractAction{

	/**
	 * Dialog to fill data.
	 */
	private CertificateDialog diag;
	
	/**
	 * Initializes a new instance of GenerateCertificateAction action.
	 * @param diag Dialog to fill data.
	 */
	public GenerateCertificateAction(CertificateDialog diag) {
		this.diag = diag;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		CertificateGenerator generator = new CertificateGenerator();
		Certificate cerf = diag.getCertificate();
		
		KeyPair keyPair = generator.generateKeyPair();
		KeyStore keyStore = diag.getSelectedKeyStore();

		X500NameBuilder builder = generateBuilder();
		// TODO Hardcoded - needs to be fixed.
		String certificateNumber = "1";
		Date startDate = null, endDate = null;
		SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
		try {
			startDate = iso8601Formater.parse(diag.getDateField().getText());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			endDate = iso8601Formater.parse(diag.getEndDate().getText());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		IssuerData issuerData = null;
		
		if(cerf != null){
			String alias ="";
			try {
				alias = keyStore.getCertificateAlias(cerf);
			} catch (KeyStoreException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			X500NameBuilder buildertmp = parseDataFromCertificate((X509Certificate)cerf);
			issuerData = new IssuerData(diag.getPrivateKey(), buildertmp.build());
			System.out.println(issuerData);
		}else{
			issuerData = new IssuerData(keyPair.getPrivate(), builder.build());
		}
		 
		SubjectData subjectData = new SubjectData(keyPair.getPublic(),builder.build(),certificateNumber,startDate, endDate);
		
		X509Certificate cert = generator.generateCertificate(issuerData, subjectData);
		
		String alias = diag.getAlias().getText();
		char[] password = diag.getPasswordField().getPassword();
		try {
			diag.getSelectedKeyStore().setKeyEntry(alias,keyPair.getPrivate(),password,  new X509Certificate[] {cert});
			
			FileOutputStream outputFile = new FileOutputStream("./keyStores/" + (String)diag.getKeyStore().getSelectedItem() + ".jks");
			diag.getSelectedKeyStore().store(outputFile, diag.getKeyStorePassword());
		} catch (Exception e1) {

			e1.printStackTrace();
		}

	    diag.dispose();
	}
	
	
	/**
	 * Generate builder used for issuer and subject data.
	 * @return Generated builder.
	 */
	private X500NameBuilder generateBuilder(){
		

		
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	    builder.addRDN(BCStyle.CN, diag.getCommon_name().getText());
	    builder.addRDN(BCStyle.SURNAME, diag.getSurname().getText());
	    builder.addRDN(BCStyle.GIVENNAME, diag.getGiven_name().getText());
	    builder.addRDN(BCStyle.O, diag.getOrganization_name().getText());
	    builder.addRDN(BCStyle.OU, diag.getOrganization_unit().getText());
	    builder.addRDN(BCStyle.C, diag.getCountry_code().getText());
	    builder.addRDN(BCStyle.E, diag.getEmail_address().getText());
	    // TODO Hardcoded - needs to be fixed.
	    builder.addRDN(BCStyle.UID, "123445");
	    return builder;
	}
	
	/**
	 	 * Method for parsing Certificate and getting Issuer data.
	 	 * @return Issuer 
	 +	 * */
 	public X500NameBuilder parseDataFromCertificate(X509Certificate certificateForParse){
		
		String stringForParse = certificateForParse.toString();
	  
	 		String [] arrayForParse = stringForParse.split("Issuer:")[1].split(",");
	 		String UID = arrayForParse[0].split("=")[1];
	 		String EMAILADDRESS = arrayForParse[1].split("=")[1];
	 		String C = arrayForParse[2].split("=")[1];
	 		String OU = arrayForParse[3].split("=")[1];
	 		String O = arrayForParse[4].split("=")[1];
	 		String GIVENNAME = arrayForParse[5].split("=")[1];
	 		String SURNAME = arrayForParse[6].split("=")[1];
	 		String CN = arrayForParse[7].split("=")[1].split("\n")[0];

	 		
	 		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	 	    builder.addRDN(BCStyle.CN, CN);
	 	    builder.addRDN(BCStyle.SURNAME, SURNAME);
	 	    builder.addRDN(BCStyle.GIVENNAME, GIVENNAME);
	 	    builder.addRDN(BCStyle.O, O);
	 	    builder.addRDN(BCStyle.OU, OU);
	 	    builder.addRDN(BCStyle.C, C);
	 	    builder.addRDN(BCStyle.E, EMAILADDRESS);
	 	    builder.addRDN(BCStyle.UID, UID);
	 		
	 		return builder;
	 	}
	

}
