package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class CheckCertificateDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String KEY_STORE_FILE = "./keyStores/";
	
	private final JPanel panel = new JPanel();
	private JPasswordField passwordField;
	private JDialog parentDialog;
	private String fileName;

	private char[] password;
	private char[] certifikatePassword;
	private PrivateKey privateKey;
	
	public CheckCertificateDialog(final String fileName, final char[] password, KeyStore keyStore, final String alias)
	{
		this.setPassword(password);
		this.setFileName(fileName);
		this.setParentDialog(parentDialog);
		setTitle("Enter password for chosen certificate");
		setBounds(100, 100, 316, 168);
		getContentPane().setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword, "cell 0 1,alignx trailing");
		passwordField = new JPasswordField();
		panel.add(passwordField, "cell 1 1,growx");
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		
		buttonPane.add(okButton);
		final KeyStore ks = keyStore;
		getRootPane().setDefaultButton(okButton);
		final CheckCertificateDialog that = this;
		
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				 
				//ucitavamo podatke
				BufferedInputStream in;
				
				try {
					in = new BufferedInputStream(new FileInputStream(KEY_STORE_FILE + fileName));
					ks.load(in, password);
					if(ks.isKeyEntry(alias)) {
						Certificate cert = ks.getCertificate(alias);
						PrivateKey privKey = null;
						try {
							privKey = (PrivateKey)ks.getKey(alias, passwordField.getPassword());
							setCertifikatePassword(passwordField.getPassword());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "The certificate doesn`t exists.");
							return;
							
						}
						that.setPrivateKey(privKey);
						
						that.dispose();
	
							
					}else{
						JOptionPane.showMessageDialog(null, "The certificate doesn`t exists.");
						return;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public JDialog getParentDialog() {
		return parentDialog;
	}

	public void setParentDialog(JDialog parentDialog) {
		this.parentDialog = parentDialog;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public char[] getCertifikatePassword() {
		return certifikatePassword;
	}

	public void setCertifikatePassword(char[] certifikatePassword) {
		this.certifikatePassword = certifikatePassword;
	}
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}


}
