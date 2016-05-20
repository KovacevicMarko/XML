package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class CertificateDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 342596914454271913L;
	private JLabel keyStore;
	private JLabel CA;
	private JLabel commonName;
	private JLabel surname;
	private JLabel givenname;
	private JLabel orgUnit;
	private JLabel orgName;
	private JLabel country;
	private JLabel sigAlg;
	

	public static String strName;
	private String str;
	private JTextField tfKeyStore;
	private JTextField tfCA;
	private JTextField tfCommonName;
	private JTextField tfSurname;
	private JTextField tfGivenname;
	private JTextField tfOrgUnit;
	private JTextField tfOrgName;
	private JTextField tfCountry;
	private JTextField tfSigAlg;

	private Boolean pressedOK;
	@SuppressWarnings("unused")
	private KeyStore ks;

	public CertificateDialog() {
		
		setSize(400,300);
		getContentPane().setLayout(new MigLayout("insets 10 10 10 20"));
		
	
		pressedOK=false;
		
		sigAlg = new JLabel("Algorithm:");
		keyStore = new JLabel("Key Store:");
		CA = new JLabel("CA:");
		
		commonName = new JLabel("Common Name:");
		surname = new JLabel("Surname:");
		givenname = new JLabel("Givenname:");
		orgUnit = new JLabel("Organization Unit:");
		orgName = new JLabel("Organization Name:");
		country = new JLabel("Country:");
		
		JButton btnOK = new JButton("OK");
		
	/*	btnOK.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				strName = "CN="+tfCommonName.getText() + ",SURNAME="+tfSurname.getText()+",GIVENNAME="+tfGivenname.getText()+",O="+tfOrgName.getText()+",OU="+tfOrgUnit.getText()+",C="+tfCountry.getText();
				
				AliasPasswordDialog dialog = new AliasPasswordDialog();
				dialog.setVisible(true);
				dispose();
			}
		});
	*/	
		JButton btnCancel = new JButton("Cancel");
		
		btnCancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				strName=null;
				dispose();
			
			}
		});
		tfSigAlg = new JTextField("SHA1withRSA");
		tfSigAlg.setEditable(false);
		tfKeyStore = new JTextField();
		tfCA = new JTextField();
		tfCommonName = new JTextField();
		tfCountry = new JTextField();
		tfGivenname = new JTextField();
		tfOrgName = new JTextField();
		tfOrgUnit = new JTextField();
		tfSurname = new JTextField();
		
		
		getContentPane().add(new JLabel("Generator Certificate"), "cell 0 0,align center, height 30, span");
		getContentPane().add(sigAlg, "cell 0 1,align right, height 30");
		getContentPane().add(keyStore, "cell 0 2,align right, height 30");
		getContentPane().add(CA, "cell 0 3,align right, height 30");
		getContentPane().add(commonName, "cell 0 4,align right, height 30");
		getContentPane().add(surname, "cell 0 5,align right, height 30");
		getContentPane().add(givenname, "cell 0 6,align right,  height 30");
		getContentPane().add(orgUnit, "cell 0 7,align right,  height 30");
		getContentPane().add(orgName, "cell 0 8,align right, height 30");
		getContentPane().add(country, "cell 0 9,align right, height 30");
		
		//dugme
		getContentPane().add(btnOK, "cell 0 10, align center, span");
		getContentPane().add(btnCancel, "cell 1 10, align center, span");
		
		//text field
		getContentPane().add(tfSigAlg, "cell 1 1,align left, height 20,width 100::");
		getContentPane().add(tfKeyStore, "cell 1 2,align left, height 20,width 100::");
		getContentPane().add(tfCA, "cell 1 3,align left, height 20,width 100::");
		getContentPane().add(tfCommonName, "cell 1 4,align left, height 20,width 100::");
		getContentPane().add(tfSurname, "cell 1 5,align left, height 20, width 100::");
		getContentPane().add(tfGivenname, "cell 1 6,align left,  height 20, width 100::");
		getContentPane().add(tfOrgUnit, "cell 1 7,align left,  height 20, width 100::");
		getContentPane().add(tfOrgName, "cell 1 8,align left, height 20, width 100::");
		getContentPane().add(tfCountry, "cell 1 9,align left, height 20, width 40::");

		
	}
	
	public JTextField getTfCommonName() {
		return tfCommonName;
	}

	public JTextField getTfSurname() {
		return tfSurname;
	}

	public JTextField getTfGivenname() {
		return tfGivenname;
	}

	public JTextField getTfOrgUnit() {
		return tfOrgUnit;
	}

	public JTextField getTfOrgName() {
		return tfOrgName;
	}

	public JTextField getTfCountry() {
		return tfCountry;
	}

	
	public Boolean getPressedOK() {
		return pressedOK;
	}

	public void setPressedOK(Boolean pressedOK) {
		this.pressedOK = pressedOK;
	}

	public String getStr() {
		return str;
	}
}
