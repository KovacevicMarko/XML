package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.KeyStore;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class ChooseKeyStore extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel panel = new JPanel();
	private JComboBox<String> keyStoresFromFileSystem;
	private KeyStore selectedKeyStore;
	private char[] keyStorePassword;
	private boolean flag = true;
	
	public ChooseKeyStore()
	{		
		setBounds(100, 100, 263, 122);
		setTitle("Choose key store");
		getContentPane().setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JLabel lblChoseKeystore = new JLabel("Chose KeyStore");
		panel.add(lblChoseKeystore, "cell 0 0,alignx trailing");
		
		final ChooseKeyStore cks = this;
		keyStoresFromFileSystem = new JComboBox<String>();
			
			
		panel.add(keyStoresFromFileSystem, "cell 1 0,growx");
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (keyStoresFromFileSystem.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "You must choose one key store!");
				}
				else 
				{
					CheckKeyStoreDialog cksd = new CheckKeyStoreDialog(keyStoresFromFileSystem.getSelectedItem().toString(), cks);
					cksd.setModal(true);
					cksd.setLocationRelativeTo(MainFrame.getInstance());
					cksd.setVisible(true);
				}
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		
		final ChooseKeyStore sksd = this;
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sksd.dispose();
			}
		});
		buttonPane.add(cancelButton);
	
		
		loadKeyStores();
	}
	
	/**
	 * Ucitaj sve keyStorove iz file-a.
	 */
	private void loadKeyStores()
	{
		File folder = new File("./keyStores");
		File[] keyStoreFiles = folder.listFiles();
		
		this.keyStoresFromFileSystem.addItem("----Choose----");
		for(File keyStoreFile : keyStoreFiles){
			this.keyStoresFromFileSystem.addItem(splitName(keyStoreFile.getName()));
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
	
	public JComboBox<String> getKeyStoresFromFileSystem() {
		return keyStoresFromFileSystem;
	}
	public void setKeyStoresFromFileSystem(JComboBox<String> keyStoresFromFileSystem) {
		this.keyStoresFromFileSystem = keyStoresFromFileSystem;
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
