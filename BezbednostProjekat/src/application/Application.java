package application;

import gui.MainFrame;

public class Application {

	public static void main(String[] args) {

		MainFrame mf = MainFrame.getInstance();
		mf.setSize(520, 410);
		mf.setVisible(true);

	}

}
