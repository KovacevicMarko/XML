package actions;

import gui.CertificateDialog;
import gui.CheckKeyStoreDialog;
import gui.ChooseCertificate;
import gui.ChooseKeyStore;
import gui.MainFrame;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class LoadKeyStoreAction extends AbstractAction {

	private JPasswordField password;
	private String fileName;
	private JDialog parentDialog;
	private JDialog panel;
	private int selectedIndex;
	private boolean isFromCertificateDialog;

	public LoadKeyStoreAction(JDialog panel, JDialog parentDialog,
			JPasswordField password, String fileName, boolean flag) {
		this.password = password;
		this.parentDialog = parentDialog;
		this.fileName = fileName;
		this.panel = panel;
		this.isFromCertificateDialog = flag;

		if (parentDialog instanceof CertificateDialog) {
			this.selectedIndex = ((CertificateDialog) parentDialog)
					.getKeyStore().getSelectedIndex();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");
			FileInputStream file = new FileInputStream("./keyStores/"
					+ fileName + ".jks");
			keyStore.load(file, password.getPassword());

			if (parentDialog instanceof CertificateDialog) {
				((CertificateDialog) parentDialog)
						.setSelectedKeyStore(keyStore);
				((CertificateDialog) parentDialog).setKeyStorePassword(password
						.getPassword());
				((CertificateDialog) parentDialog).getKeyStore()
						.setSelectedIndex(selectedIndex);
			} else {

				if (!isFromCertificateDialog) {
					ChooseCertificate ccf = new ChooseCertificate(keyStore,
							this.password.getPassword(), this.fileName);
					ccf.setModal(true);
					ccf.setLocationRelativeTo(MainFrame.getInstance());
					ccf.setVisible(true);
					this.parentDialog.dispose();
					this.panel.dispose();


				} else {
					CertificateDialog c = (CertificateDialog) ((CheckKeyStoreDialog) parentDialog)
							.getParentDialog();
					JComboBox box = c.getCa();
					box.setEnabled(true);
					box.addItem("none");

				}
			}
			panel.dispose();
		} catch (Exception exp) {
			JOptionPane.showMessageDialog(panel, "Could not open keyStore!", "Error", JOptionPane.ERROR_MESSAGE);
			if(parentDialog instanceof CertificateDialog){
				((CertificateDialog)parentDialog).getKeyStore().setSelectedIndex(0);
			}else{
				((ChooseKeyStore)parentDialog).getKeyStoresFromFileSystem().setSelectedIndex(0);
			}
		}
	}

}
