package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class CertificateInformationDialog extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String certificateString;
	JTextField tfversion;
	JTextField tfsubject;
	JTextField tfissuer;
	JTextField tfsnumb;
	JTextField tfvalidFrom;
	JTextField tfvalidUntil;
	JTextField tfpublicKey;
	JTextField tfsignAlg;

	private final JPanel panel = new JPanel();
	private Certificate certificate;
	
	public CertificateInformationDialog(Certificate cert) 
	{
		setTitle("Certificate information");
		this.setCertificate(cert);
		/*setBounds(100, 100, 855, 589);
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
		buttonPane.add(cancelButton);*/
		
		setSize(570,370);
		//GridBagLayout layout = new GridBagLayout();
		//setLayout(layout);
		getContentPane().setLayout(new MigLayout("insets 10 10 10 20"));

		JLabel jlcert = new JLabel("Certificate");
		JLabel jlversion = new JLabel("Version:");
		JLabel jlsubject = new JLabel("Subject:");
		JLabel jlissuer = new JLabel("Issuer:");
		JLabel jlsnumb = new JLabel("Serial Number:");
		JLabel jlvalidFrom = new JLabel("Valid From:");
		JLabel jlvalidUntil = new JLabel("Valid Until:");
		JLabel jlpublicKey = new JLabel("Public Key:");
		JLabel jlsignAlg = new JLabel("Signature Algorithm:");
		
		JButton btnOK = new JButton("OK");
		
		btnOK.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				certificateString = null;
				 dispose();
			}
		});
		
		tfversion = new JTextField();
		tfversion.setEditable(false);
		
		tfsubject = new JTextField();
		tfsubject.setEditable(false);
		
		tfissuer = new JTextField();
		tfissuer.setEditable(false);
		
		tfsnumb = new JTextField();
		tfsnumb.setEditable(false);
		
		tfvalidFrom = new JTextField();
		tfvalidFrom.setEditable(false);
		
		tfvalidUntil = new JTextField();
		tfvalidUntil.setEditable(false);
		
		tfpublicKey = new JTextField();
		tfpublicKey.setEditable(false);
		
		tfsignAlg = new JTextField();
		tfsignAlg.setEditable(false);

		//labele
		getContentPane().add(jlcert, "cell 0 0,align center, height 30, span");
		getContentPane().add(jlversion, "cell 0 1,align right, height 30");
		getContentPane().add(jlsubject, "cell 0 2,align right, height 30");
		getContentPane().add(jlissuer, "cell 0 3,align right,  height 30");
		getContentPane().add(jlsnumb, "cell 0 4,align right,  height 30");
		getContentPane().add(jlvalidFrom, "cell 0 5,align right, height 30");
		getContentPane().add(jlvalidUntil, "cell 0 6,align right, height 30");
		getContentPane().add(jlpublicKey, "cell 0 7,align right, height 30");
		getContentPane().add(jlsignAlg, "cell 0 8,align right, height 30");
		
		//dugme
		getContentPane().add(btnOK, "cell 0 9, align center, span");
		
		//text field
		getContentPane().add(tfversion, "cell 1 1,align left, height 20, width 40::");
		getContentPane().add(tfsubject, "cell 1 2,align left, height 20, width 400::");
		getContentPane().add(tfissuer, "cell 1 3,align left,  height 20, width 400::");
		getContentPane().add(tfsnumb, "cell 1 4,align left,  height 20, width 300::");
		getContentPane().add(tfvalidFrom, "cell 1 5,align left, height 20, width 300::");
		getContentPane().add(tfvalidUntil, "cell 1 6,align left, height 20, width 300::");
		getContentPane().add(tfpublicKey, "cell 1 7,align left, height 20, width 200::");
		getContentPane().add(tfsignAlg, "cell 1 8,align left, height 20, width 200::");
	}
		
	public void readKeyStore(Certificate cert){
		

		certificateString = cert.toString();
		X509Certificate xc =(X509Certificate)cert;
		
		
		Integer k = xc.getVersion();
		
		tfversion.setText(k.toString());
		tfsubject.setText(xc.getSubjectDN().toString());
		tfsignAlg.setText(xc.getSigAlgName());
		tfissuer.setText(xc.getIssuerDN().toString());
		tfsnumb.setText(xc.getSerialNumber().toString());
		tfvalidFrom.setText(xc.getNotBefore().toString());
		tfvalidUntil.setText(xc.getNotAfter().toString());
		tfpublicKey.setText(xc.getPublicKey().getAlgorithm().toString()+" (1024)");
	
}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}
}
