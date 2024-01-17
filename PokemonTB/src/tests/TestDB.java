package tests;

import org.junit.Assert.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
}
