package main;

import java.sql.SQLException;

import db.db;
import gui.StartWindow;

public class main {
	private static String dbname = "PokemonBD.db";
	public static int va = 0;
	
	public static int getVa() {
		return va;
	}

	public static void setVa(int va) {
		main.va = va;
	}

	public static void main(String[] args) {
		db.conectBD(dbname);
		db.creacionBD();
		va+=1;
		StartWindow frame = new StartWindow();
		frame.setVisible(true);
	}
	
	public static void cerrarbd() {
		if(va == 0) {
			try {
                if (db.getCon() != null && !db.getCon().isClosed()) {
                    db.cerrarConexion();
                    System.out.println("Conexión a la base de datos cerrada.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
	}
	

}
