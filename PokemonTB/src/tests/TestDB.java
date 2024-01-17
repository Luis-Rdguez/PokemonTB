package tests;

import org.junit.Assert.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.logging.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import classes.Pokemon;
import classes.PokemonTeam;
import classes.User;
import db.db;

public class TestDB {
	private static String dbName = "test";
	private static db service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new db();
		db.conectBD(dbName);
		db.creacionBD();
	}
	
	@Test
	public void testseleccionarUsuarioPorNombre() {
		User u = service.seleccionarUsuarioPorNombre("a");
		
		assertTrue(u.getUsername().equals("a"));
	}
	
	@Test
	public void testEquipos() {
		PokemonTeam team = new PokemonTeam("t1","a");
		Pokemon pokemon = db.importarPokemonsDesdeCSV().get(0);
		team.setP1(pokemon);
		team.setP2(pokemon);
		team.setP3(pokemon);
		team.setP4(pokemon);
		team.setP5(pokemon);
		team.setP6(pokemon);
		db.añadirEquipoPokemon(team);
		ArrayList<PokemonTeam> t1 = db.loadEquipos("a");
		assertTrue(!t1.isEmpty());
		db.eliminarEquipoPokemon(t1.get(0));
		ArrayList<PokemonTeam> t2 = db.loadEquipos("a");
		assertTrue(t2.isEmpty());
	}
}
