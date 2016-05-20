package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame
{

	/**
	 * Serial version of class.
	 */
	private static final long serialVersionUID = 1L;
	
	private static MainFrame instance;
	private boolean selfSigned;
	
	private JPanel contentPane;
	
	public static MainFrame getInstance() 
	{

		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	
	private MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Certificates");
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmCreateCertificate = new JMenuItem("Create certificate");
		mntmCreateCertificate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mntmCreateCertificate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CertificateDialog cd = new CertificateDialog();
				cd.setModal(true);
				cd.setLocationRelativeTo(MainFrame.getInstance());
				cd.setVisible(true);
				
			}
		});
		
		JMenuItem mntmChooseCertificate = new JMenuItem("Choose certificate");
		mntmChooseCertificate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mntmChooseCertificate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChooseKeyStore ckd = new ChooseKeyStore();
				ckd.setModal(true);
				ckd.setLocationRelativeTo(MainFrame.getInstance());
				ckd.setVisible(true);
			}
		});

		
		JMenuItem mntmCreateKeystore = new JMenuItem("Create key store");
		mntmCreateKeystore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
		mntmCreateKeystore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateKeyStoreDialog ckd = new CreateKeyStoreDialog();
				ckd.setModal(true);
				ckd.setLocationRelativeTo(MainFrame.getInstance());
				ckd.setVisible(true);
			}
		});
		mnFile.add(mntmCreateKeystore);
		
		mnFile.addSeparator();
		
		mnFile.add(mntmCreateCertificate);
		mnFile.add(mntmChooseCertificate);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mntmExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		mnFile.addSeparator();
		
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}


	public boolean isSelfSigned() {
		return selfSigned;
	}


	public void setSelfSigned(boolean selfSigned) {
		this.selfSigned = selfSigned;
	}
	

}
