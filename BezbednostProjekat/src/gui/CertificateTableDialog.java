package gui;

import java.awt.Dimension;
import java.awt.RenderingHints.Key;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.KeyStore;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import tableModel.CertificateTableModel;

public class CertificateTableDialog extends JDialog{

	private JTable table = new JTable();
	private static KeyStore keyStore;
	
	public CertificateTableDialog(KeyStore k, char[] keyStorePassword, String nameOfFile){
		setSize(new Dimension(800, 600));
		setTitle("Certificate");		
		initTable(k, keyStorePassword, nameOfFile);
		
	}
	
	
	private void initTable(final KeyStore k,final char[] keyStorePassword,final String nameOfFile) {
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		CertificateTableModel tableModel = new CertificateTableModel(new String[] {
				"Alias", "Issuer", "Valid from","Valid until" }, 0);

		table.setModel(tableModel);
		tableModel.fillTable(k);
		
		
		// Dozvoljeno selektovanje redova
		table.setRowSelectionAllowed(true);
		// Ali ne i selektovanje kolona
		table.setColumnSelectionAllowed(false);
		// Dozvoljeno selektovanje samo jednog reda u jedinici vremena
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(final MouseEvent e)
			{
             if((e.getClickCount()%2 == 0))
             {
                 JTable target = (JTable)e.getSource() ;
                 final int row = target.getSelectedRow();
                 final int colonne = target.getSelectedColumn();
                 String s = table.getModel().getValueAt(
                             table.getSelectedRow(),0).toString();
                // System.out.println(s);
                 ChooseCertificate ccf = new ChooseCertificate(k,
                		 keyStorePassword, nameOfFile,s);
				 ccf.setModal(true);
				 ccf.setLocationRelativeTo(MainFrame.getInstance());
				 ccf.setVisible(true);
			
             }
			}
		});
	}
}