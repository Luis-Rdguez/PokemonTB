package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import classes.Pokemon;
import classes.PokemonTeam;

public class db {

	
	
	// Metodos CSV
	public static List<Pokemon> importarPokemonsDesdeCSV() {
		List<Pokemon> pokemons = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader("resources/pokemon.csv"))) {
			String linea;
			
			//Lectura inicial para saltar la cabecera del fichero CSV.
			in.readLine();
			
			//Se leen líneas hasta llegar al final del fichero.
			while( (linea = in.readLine()) != null ) {
				//Se trasnforma cada línea en un objeto User y se añade a la lista.
				pokemons.add(Pokemon.parseCSV(linea));
			}
			
		} catch(Exception ex) {
			System.err.println(String.format("Error en el main: %s", ex.getMessage()));
		}
		return pokemons;
	}
	
	public static ArrayList<Pokemon> importarUsuariosDesdeCSV(String filePath) {
		ArrayList<Pokemon> result = new ArrayList<Pokemon>();
		
		return result;
	}
	
	public static void exportarUsuariosACSV(ArrayList<Pokemon> listaPokemons, String filePath) {
		
	}
	
	public static ArrayList<PokemonTeam> importarEquiposPokemonDesdeCSV(String filePath) {
		ArrayList<PokemonTeam> result = new ArrayList<PokemonTeam>();
		
		return result;
	}
	
	public static void exportarEquiposPokemonACSV(ArrayList<PokemonTeam> listaEquiposPokemon, String filePath) {
		
	}
}
