package main;

import db.db;
import gui.StartWindow;

public class main {

	
	public static void main(String[] args) {

		db.conectBD();
		db.creacionBD();
		StartWindow frame = new StartWindow();
		frame.setVisible(true);
	}

	

}
