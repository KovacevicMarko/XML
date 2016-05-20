package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import actions.LoadKeyStoreAction;

public class CheckKeyStoreDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4364959852825719413L;
	
	private final JPanel panel = new JPanel();
	private JPasswordField passwordField;
	private JDialog parentDialog;
	private String fileName;
	
	public CheckKeyStoreDialog(String fileName, JDialog parentDialog)
	{
		this.setFileName(fileName);
		this.setParentDialog(parentDialog);
		setTitle("Enter password for chosen KeyStore");
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
		
		getRootPane().setDefaultButton(okButton);
		
		{
			final CheckKeyStoreDialog that = this;
			 
			if(parentDialog instanceof CertificateDialog){
				final CertificateDialog thatParentDialog = (CertificateDialog)parentDialog;
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(thatParentDialog instanceof CertificateDialog){
							//TODO
							//((CertificateDialog)thatParentDialog).getKeyStore().setSelectedIndex(0);
						}
						that.dispose();
					}
				});
				
				okButton.addActionListener(new LoadKeyStoreAction(this, parentDialog, passwordField, fileName));
				
				buttonPane.add(cancelButton);
			}else{
				
				final ChooseKeyStore thatParentDialog = (ChooseKeyStore)parentDialog;
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
				
					@Override
					public void actionPerformed(ActionEvent e) {
						
						((ChooseKeyStore)thatParentDialog).getKeyStoresFromFileSystem().setSelectedIndex(0);
						that.dispose();
					}
				});
				okButton.addActionListener(new LoadKeyStoreAction(this, parentDialog, passwordField, fileName));
				
				buttonPane.add(cancelButton);
			}
			
		}
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

}
