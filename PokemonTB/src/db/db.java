package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classes.Pokemon;
import classes.PokemonTeam;
import classes.User;

public class db {
	
	public static void main(String[] args) {
        // Supongamos que tienes una lista de equipos llamada 'teamList'
        // y quieres escribirlos en un archivo CSV llamado 'pokemonteams.csv'
		User u1 = new User("w", "w", "w", "w", "w", 4546);
		PokemonTeam pt = new PokemonTeam("e1", u1);
		PokemonTeam pt1 = new PokemonTeam("e2", u1);
        List<PokemonTeam> teamList = new ArrayList<>(Arrays.asList(pt, pt1)); // Debes proporcionar la implementación de esta función
        exportarEquiposPokemonACSV(teamList, "resources/pokemonteams.csv");
    }
	
	
	
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
	
	public static void exportarEquiposPokemonACSV(List<PokemonTeam> teamList, String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escribir encabezados en el archivo CSV
            writer.write("Team Name,User,Pokemon1,Pokemon2,Pokemon3,Pokemon4,Pokemon5,Pokemon6");
            writer.newLine();

            // Escribir datos de cada equipo en el archivo CSV
            for (PokemonTeam team : teamList) {
                writer.write(team.toCSVString());
                writer.newLine();
            }

            System.out.println("CSV file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
