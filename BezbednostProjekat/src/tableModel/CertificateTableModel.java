package tableModel;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.swing.table.DefaultTableModel;

public class CertificateTableModel extends DefaultTableModel{

	public CertificateTableModel(Object[] colNames, int rowCount) {
		super(colNames, rowCount);
	}
	
	public void fillTable(KeyStore k){
		try {
			Enumeration enumeration = k.aliases();
			
			while(enumeration.hasMoreElements()){
				String alias = (String)enumeration.nextElement();
				X509Certificate cert = (X509Certificate)k.getCertificate(alias);
				String issuer=cert.getIssuerDN().toString();
				//UID=63730, EMAILADDRESS=t1@g.com, C=t1, OU=t1, O=t1, GIVENNAME=t1, SURNAME=t1, CN=t1
				String[] pars=issuer.split(",");
				String validFrom=cert.getNotBefore().toString();
				String validUntil=cert.getNotAfter().toString();
				addRow(new String[] { alias, pars[2], validFrom, validUntil});
				
			}
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fireTableDataChanged();
	}
	
	
	
	public boolean isCellEditable(int row, int column) {                
        return false;               
	};
}
