package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.security.cert.Certificate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class CertificateInformationDialog extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel panel = new JPanel();
	private Certificate certificate;
	
	public CertificateInformationDialog(Certificate cert) 
	{
		setTitle("Certificate information");
		this.setCertificate(cert);
		setBounds(100, 100, 855, 589);
		getContentPane().setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText(cert.toString());
		textPane.setBounds(10, 11, 819, 495);
		
		panel.add(textPane);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}
}
