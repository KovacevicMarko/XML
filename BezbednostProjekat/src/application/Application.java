package application;

import gui.MainFrame;

public class Application {

	public static void main(String[] args) {

		MainFrame mf = MainFrame.getInstance();
		mf.setSize(500, 500);
		mf.setVisible(true);
		/*
		try {
			UIManager.setLookAndFeel("napkin.NapkinLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
