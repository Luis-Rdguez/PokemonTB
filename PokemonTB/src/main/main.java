package main;

import db.db;
import gui.StartWindow;

public class main {
	private static String dbname = "PokemonBD.db";
	
	public static void main(String[] args) {
		db.conectBD(dbname);
		db.creacionBD();
		StartWindow frame = new StartWindow();
		frame.setVisible(true);
	}

	

}