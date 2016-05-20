package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import security.KeyStoreWriter;
import gui.CreateKeyStoreDialog;

public class CreateKeyStoreAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CreateKeyStoreDialog diag;
	private KeyStoreWriter keyStoreWritter;
	
	public CreateKeyStoreAction(CreateKeyStoreDialog diag){
		this.diag = diag;
		keyStoreWritter = new KeyStoreWriter();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = diag.getNameKS().getText();
		char[] password = diag.getPasswordKS().getText().toCharArray();
		
		if(name.isEmpty() || password.length==0)
		{
			JOptionPane.showMessageDialog(null, "You need to fill all fields!");
		}
		else
		{
			diag.dispose();
			keyStoreWritter.loadKeyStore(null, password);
			keyStoreWritter.saveKeyStore("./keyStores/"+name+".jks", password);			
		}
		
	}

}
