package actions;

import gui.CertificateDialog;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class CreateCertificateAction extends AbstractAction {
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		CertificateDialog d = new CertificateDialog();
		d.setVisible(true);
		//d.show();
		
	}
}
