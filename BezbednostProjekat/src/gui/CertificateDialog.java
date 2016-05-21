package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import actions.GenerateCertificateAction;

public class CertificateDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField surname;
	private JTextField common_name;
	private JTextField organization_unit;
	private JTextField organization_name;
	private JTextField given_name;
	private JTextField state_name;
	private JTextField country_code;
	private JTextField email_address;
	private JTextField dateField;
	private JTextField endDate;
	private JComboBox ca;
	private JComboBox keyStoreComboBox;
	private JTextField alias;
	private JPasswordField passwordField;
	private KeyStore selectedKeyStore;
	private boolean first = true;
	private Certificate certificate;
	private PrivateKey privateKey;
	/**
	 * Password if keyStore is loaded.
	 */
	private char[] keyStorePassword;
	
	private char[] certificatePassword;
	/**
	 * Create the dialog.
	 */
	public CertificateDialog() 
	{
		setBounds(100, 100, 300, 444);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][][][][][][][][]"));
		

		JLabel lblKeystore = new JLabel("KeyStore");
		contentPanel.add(lblKeystore, "cell 1 0");
	
	
		final CertificateDialog that = this;
		keyStoreComboBox = new JComboBox();
		keyStoreComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (keyStoreComboBox.getSelectedIndex() == 0)
				{
					ca.removeAllItems();
					return;
				}
				else {
					CheckKeyStoreDialog cksd = new CheckKeyStoreDialog(keyStoreComboBox.getSelectedItem().toString(), that);
					cksd.setModal(true);
					cksd.setLocationRelativeTo(MainFrame.getInstance());
					cksd.setVisible(true);
					keyStorePassword = cksd.getPasswordField().getPassword();
					loadCAbyKeyStores(getSelectedKeyStore());
				}
			}
		});
		contentPanel.add(keyStoreComboBox, "cell 3 0,growx");
		
		
		ca = new JComboBox();
		ca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String alias = ca.getSelectedItem().toString();
				certificate = null;
				try {
					certificate = getSelectedKeyStore().getCertificate(alias);

					
				} catch (KeyStoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(certificate != null){
					String keyStoreName = keyStoreComboBox.getSelectedItem().toString() + ".jks";
					KeyStore keyStore = null;
					try {
						keyStore = KeyStore.getInstance("JKS","SUN");
					} catch (KeyStoreException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchProviderException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					FileInputStream file = null;
					try {
						file = new FileInputStream("./keyStores/" + keyStoreName);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						keyStore.load(file, keyStorePassword);
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CertificateException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					CheckCertificateDialog ccd = new CheckCertificateDialog(keyStoreName, keyStorePassword, keyStore, alias);
					ccd.setModal(true);
					ccd.setLocationRelativeTo(MainFrame.getInstance());
					ccd.setVisible(true);
					
					certificatePassword = ccd.getPassword();
					privateKey = ccd.getPrivateKey();
					System.out.println("PASSSS" + certificatePassword);
				}
				
			}
		});
		contentPanel.add(ca, "cell 3 1,growx");
		
		JLabel CA = new JLabel("CA");
		contentPanel.add(CA, "cell 1 1");	
	
		JLabel lblSurname = new JLabel("Surname(S)");
		contentPanel.add(lblSurname, "cell 1 2");	
		surname = new JTextField();
		contentPanel.add(surname, "cell 3 2,growx");
		surname.setColumns(10);
	
	
		JLabel lblNewLabel = new JLabel("Common name(CN)");
		contentPanel.add(lblNewLabel, "cell 1 3");
		common_name = new JTextField();
		contentPanel.add(common_name, "cell 3 3,growx");
		common_name.setColumns(10);
	
	
		JLabel lblNewLabel_1 = new JLabel("Organization unit(OU)");
		contentPanel.add(lblNewLabel_1, "cell 1 4");	
		organization_unit = new JTextField();
		contentPanel.add(organization_unit, "cell 3 4,growx");
		organization_unit.setColumns(10);
	
	
		JLabel lblOrganizationNameon = new JLabel("Organization name(O)");
		contentPanel.add(lblOrganizationNameon, "cell 1 5");
		organization_name = new JTextField();
		contentPanel.add(organization_name, "cell 3 5,growx");
		organization_name.setColumns(10);
	
	
		JLabel lblNewLabel_2 = new JLabel("Given name");
		contentPanel.add(lblNewLabel_2, "cell 1 6");	
		given_name = new JTextField();
		contentPanel.add(given_name, "cell 3 6,growx");
		given_name.setColumns(10);
	
	
		JLabel lblStateName = new JLabel("State name");
		contentPanel.add(lblStateName, "cell 1 7");	
		state_name = new JTextField();
		contentPanel.add(state_name, "cell 3 7,growx");
		state_name.setColumns(10);
	
	
		JLabel lblCountryCode = new JLabel("Country code(C)");
		contentPanel.add(lblCountryCode, "cell 1 8");	
		country_code = new JTextField();
		contentPanel.add(country_code, "cell 3 8,growx");
		country_code.setColumns(10);
	
		//TODO GET EMAIL VALID CHECK
		JLabel lblEmailAdress = new JLabel("e-mail adress");
		contentPanel.add(lblEmailAdress, "cell 1 9");	
		email_address = new JTextField();
		contentPanel.add(email_address, "cell 3 9,growx");
		email_address.setColumns(10);
	
		//TODO 
		JLabel lblDate = new JLabel("Start Date ");
		contentPanel.add(lblDate, "cell 1 10");
		dateField = new JTextField();
		contentPanel.add(dateField, "cell 3 10,growx");
		dateField.setColumns(10);
	
		//TODO 
		JLabel lblEndDate = new JLabel("End Date");
		contentPanel.add(lblEndDate, "cell 1 11");
		endDate = new JTextField();
		contentPanel.add(endDate, "cell 3 11,growx");
		endDate.setColumns(10);
	
		
		JLabel lblAlias = new JLabel("Alias");
		contentPanel.add(lblAlias, "cell 1 12");	
		alias = new JTextField();
		contentPanel.add(alias, "cell 3 12,growx");
		alias.setColumns(10);
	
	
		JLabel lblPassword = new JLabel("Password");
		contentPanel.add(lblPassword, "cell 1 13");
		passwordField = new JPasswordField();
		contentPanel.add(passwordField, "cell 3 13,growx");
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new GenerateCertificateAction(this));
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			
		
			final CertificateDialog certDial = this;
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					certDial.dispose();
				}
			});
			buttonPane.add(cancelButton);
		
	
	loadKeyStores();
	}
	
	public KeyStore getSelectedKeyStore() {
		return selectedKeyStore;
	}

	public void setSelectedKeyStore(KeyStore selectedKeyStore) {
		this.selectedKeyStore = selectedKeyStore;
	}

	public JTextField getSurname() {
		return surname;
	}
	
	public void setSurname(JTextField surname) {
		this.surname = surname;
	}
	
	public JTextField getCommon_name() {
		return common_name;
	}
	
	public void setCommon_name(JTextField common_name) {
		this.common_name = common_name;
	}
	
	public JTextField getOrganization_unit() {
		return organization_unit;
	}
	
	public void setOrganization_unit(JTextField organization_unit) {
		this.organization_unit = organization_unit;
	}
	
	public JTextField getOrganization_name() {
		return organization_name;
	}
	
	public void setOrganization_name(JTextField organization_name) {
		this.organization_name = organization_name;
	}
	
	public JTextField getGiven_name() {
		return given_name;
	}
	
	public void setGiven_name(JTextField given_name) {
		this.given_name = given_name;
	}
	
	public JTextField getState_name() {
		return state_name;
	}
	
	public void setState_name(JTextField state_name) {
		this.state_name = state_name;
	}
	
	public JTextField getCountry_code() {
		return country_code;
	}
	
	public void setCountry_code(JTextField country_code) {
		this.country_code = country_code;
	}
	
	public JTextField getEmail_address() {
		return email_address;
	}
	
	public void setEmail_address(JTextField email_address) {
		this.email_address = email_address;
	}
	
	public JTextField getDateField() {
		return dateField;
	}
	
	public void setDateField(JTextField dateField) {
		this.dateField = dateField;
	}
	
	public JTextField getEndDate() {
		return endDate;
	}
	
	public void setEndDate(JTextField endDate) {
		this.endDate = endDate;
	}
	
	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JComboBox getCa() {
		return ca;
	}

	public void setCa(JComboBox ca) {
		this.ca = ca;
	}

	public JComboBox getKeyStore() {
		return keyStoreComboBox;
	}

	public void setKeyStore(JComboBox keyStore) {
		this.keyStoreComboBox = keyStore;
	}

	public JTextField getAlias() {
		return alias;
	}

	public void setAlias(JTextField alias) {
		this.alias = alias;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
	
	/**
	 * Loads all keyStores from folder 'keyStore'.
	 */
	private void loadKeyStores(){
		File folder = new File("./keyStores");
		File[] keyStoreFiles = folder.listFiles();
		
		KeyStore keyStore;
		this.keyStoreComboBox.addItem(" ");
		for(File keyStoreFile : keyStoreFiles){
			this.keyStoreComboBox.addItem(splitName(keyStoreFile.getName()));
		}
	}
	
	private void loadCAbyKeyStores(KeyStore keystore){
		ca.removeAllItems();
		System.out.println("here");
		this.ca.addItem("SELF SIGNDED");

		try {
			Enumeration enumeration = keystore.aliases();
			while(enumeration.hasMoreElements()){
				
				String alias = (String)enumeration.nextElement();
	            System.out.println("alias name: " + alias);
	            this.ca.addItem(alias);
				
			}
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Splituj naziv po tacki i vrati samo naziv bez extenzije.
	 * @param name
	 * @return
	 */
	private String splitName(String name)
	{
		return name.split(".jks")[0];
	}

	public char[] getKeyStorePassword() {
		return keyStorePassword;
	}

	public void setKeyStorePassword(char[] keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public char[] getCertificatePassword() {
		return certificatePassword;
	}

	public void setCertificatePassword(char[] certificatePassword) {
		this.certificatePassword = certificatePassword;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	
}
