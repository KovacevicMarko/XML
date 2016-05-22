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
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class ChooseCertificate extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String KEY_STORE_FILE = "./keyStores/";
	private final JPanel panel = new JPanel();
	private JTextField alias;
	private JPasswordField passwordField;
	
	private KeyStore selectedKeyStore;
	private char[] keyStorePassword;
	private String nameOfFile;
	
	public ChooseCertificate(KeyStore selectedKeyStore,char[] keyStorePassword,String nameOfFile)
	{
		this.nameOfFile = nameOfFile;
		this.selectedKeyStore= selectedKeyStore;
		this.keyStorePassword= keyStorePassword;
		setTitle("Enter Certificate Alias and Password");
		setBounds(100, 100, 360, 164);
		getContentPane().setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][][grow]", "[][]"));
		
		JLabel lblAlias = new JLabel("Alias");
		panel.add(lblAlias, "cell 0 0");
		alias = new JTextField();
		panel.add(alias, "cell 2 0,growx");
		alias.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword, "cell 0 1");
		passwordField = new JPasswordField();
		panel.add(passwordField, "cell 2 1,growx");
		
		final ChooseCertificate cid = this;
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		final KeyStore selKeyStore = selectedKeyStore;
		final char[]keyStorePass = keyStorePassword;
		final String staticNameOfFile = nameOfFile + ".jks";
		final char[]certPassword = passwordField.getPassword();
		final ChooseCertificate ecid = this;
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//ucitavamo podatke
				BufferedInputStream in;
				
				try {
					in = new BufferedInputStream(new FileInputStream(KEY_STORE_FILE + staticNameOfFile));
					selKeyStore.load(in, keyStorePass);
					if(selKeyStore.isKeyEntry(alias.getText())) {
						Certificate cert = selKeyStore.getCertificate(alias.getText());
						PrivateKey privKey = null;
						try {
							privKey = (PrivateKey)selKeyStore.getKey(alias.getText(), passwordField.getPassword());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "The certificate doesn`t exists.");
							return;
							
						}
						
						ecid.dispose();
						CertificateInformationDialog scid = new CertificateInformationDialog(cert);
						try {
							scid.readKeyStore(cert);
							
							scid.setModal(true);
							scid.setLocationRelativeTo(MainFrame.getInstance());
							scid.setVisible(true);
							scid.setResizable(false);
							
						} catch (Exception kse) {
						}
							
					}else{
						JOptionPane.showMessageDialog(null, "The certificate doesn`t exists.");
						return;
					}
				} catch (NoSuchAlgorithmException | CertificateException | IOException |  KeyStoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				cid.dispose();
				}
			});
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					cid.dispose();
				}
			});
			buttonPane.add(cancelButton);
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

	public KeyStore getSelectedKeyStore() {
		return selectedKeyStore;
	}

	public void setSelectedKeyStore(KeyStore selectedKeyStore) {
		this.selectedKeyStore = selectedKeyStore;
	}

	public char[] getKeyStorePassword() {
		return keyStorePassword;
	}

	public void setKeyStorePassword(char[] keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}


}
