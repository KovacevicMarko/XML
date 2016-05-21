package actions;

import gui.CertificateDialog;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class LoadCertificateAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField password;
	private String fileName;
	private JDialog parentDialog;
	private JDialog panel;
	private int selectedIndex;
	
	public LoadCertificateAction(JDialog panel,JDialog parentDialog, JPasswordField password, String fileName) 
	{
		this.password = password;
		this.parentDialog= parentDialog;
		this.fileName = fileName;
		this.panel = panel;
		if(parentDialog instanceof CertificateDialog)
		{
			this.selectedIndex =((CertificateDialog)parentDialog).getCa().getSelectedIndex();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS","SUN");
			FileInputStream file = new FileInputStream("./keyStore/"+fileName);
			keyStore.load(file, password.getPassword());
			if(parentDialog instanceof CertificateDialog){
				((CertificateDialog)parentDialog).setSelectedKeyStore(keyStore);
				((CertificateDialog)parentDialog).setKeyStorePassword(password.getPassword());
				((CertificateDialog)parentDialog).getKeyStore().setSelectedIndex(selectedIndex);
			}else{
				//BaseWindow.getInstance().getContentPane().getLayout().addLayoutComponent("Djoka", new JButton("Djoka"));
				//((ShowKeyStoresDialog)parentDialog).setSelectedKeyStore(keyStore);
				//((ShowKeyStoresDialog)parentDialog).setKeyStorePassword(password.getPassword());
				
			}
			panel.dispose();
		} catch (Exception exp) {
			JOptionPane.showMessageDialog(panel, "Could not open keyStore!", "Error", JOptionPane.ERROR_MESSAGE);
			if(parentDialog instanceof CertificateDialog){
				((CertificateDialog)parentDialog).getKeyStore().setSelectedIndex(0);
			}else{
				//((ShowKeyStoresDialog)parentDialog).getKeyStoresFromFileSystem().setSelectedIndex(0);
			}
		}
	}
}
