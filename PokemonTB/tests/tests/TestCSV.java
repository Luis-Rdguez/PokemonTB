package tests;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.Pokemon;
import classes.User;
import classes.PokemonTeam;
import db.db;


public class TestCSV {
	
    @Test
    public void testImportarPokemonsDesdeCSV() {
        List<Pokemon> pokemons = db.importarPokemonsDesdeCSV();
        // Comprobar que el metodo carga correctamente desde el CSV a una lista y que la lista no esta vacia
        assertNotNull(pokemons);
        assertFalse(pokemons.isEmpty());
        
        // Comprobar que el ID de Charmander sea 4
        Pokemon charmander = db.findPokemonByName(pokemons, "charmander");
        assertNotNull(charmander);
        assertEquals(4, charmander.getId());

        // Comprobar que el primer tipo de Bulbasur sea grass(planta)
        Pokemon bulbasaur = db.findPokemonByName(pokemons, "bulbasaur");
        assertNotNull(bulbasaur);
        assertEquals("grass", bulbasaur.getType_1());
    }

    @Test
    public void testImportarEquiposPokemonDesdeCSV() {
        String csvFilePath = "resources/pokemonteams.csv";
        List<PokemonTeam> pokemonTeams = db.importarEquiposPokemonDesdeCSV(csvFilePath);
        // Comprobar que el metodo carga correctamente desde el CSV a una lista y que la lista no esta vacia
        assertNotNull(pokemonTeams);
        assertFalse(pokemonTeams.isEmpty());
        
        //Comprobar que el primer equipo sea e3
        assertEquals("e3", pokemonTeams.get(0).getName());
        // Comprobar que el primer pokemon del equipo e3 sea bulbasur
        assertEquals("bulbasaur", pokemonTeams.get(0).getP1().getPokemon());
    }
    
    @Test
    public void testImportarUsuariosDesdeCSV() {
        String csvFilePath = "resources/user.csv";
        List<User> userList = db.importarUsuariosDesdeCSV(csvFilePath);
        for (User u: userList) {
        	System.out.println(u);
        }
        // Comprobar que el metodo carga correctamente desde el CSV a una lista y que la lista no esta vacia
        assertNotNull(userList);
        assertFalse(userList.isEmpty());
        
        // Comprobar que existe el usuario con nombre Ismael
        assertEquals("Ismael", findUserByUsername(userList, "Ismael"));
    }
    
	private static String findUserByUsername(List<User> userList, String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return username;
            }
        }
        return null;
    }
}
