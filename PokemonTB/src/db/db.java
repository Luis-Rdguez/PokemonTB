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
		User u2 = new User("x", "x", "x", "x", "x", 4546);
        List<User> listuser = new ArrayList<>(Arrays.asList(u1, u2));
        exportarUsuariosACSV(listuser, "resources/user.csv");
		PokemonTeam pt = new PokemonTeam("e1", u1);
		PokemonTeam pt1 = new PokemonTeam("e2", u1);
        List<PokemonTeam> teamList = new ArrayList<>(Arrays.asList(pt, pt1));
        exportarEquiposPokemonACSV(teamList, "resources/pokemonteams.csv");
        List<PokemonTeam> pkmTeam = new ArrayList<PokemonTeam>(importarEquiposPokemonDesdeCSV("resources/pokemonteams.csv"));
        List<User> lUser = new ArrayList<>(importarUsuariosDesdeCSV("resources/user.csv"));
        for (User user : lUser) {
            System.out.println("Usuario: " + user.getUsername());
            System.out.println("Equipos:");

            List<PokemonTeam> userTeams = user.getEquipos();
            for (PokemonTeam team : userTeams) {
                System.out.println("\t" + team.getName());
            }
        }
        for (PokemonTeam team : pkmTeam) {
            System.out.println("Equipo: " + team.getName());
            System.out.println("Usuario: " + (team.getU1() != null ? team.getU1().getUsername() : "N/A"));

            // Imprimir información de los Pokémon en el equipo
            System.out.println("Pokémon:");
            System.out.println("\tPokemon 1: " + (team.getP1() != null ? team.getP1().getPokemon() : "N/A"));
            System.out.println("\tPokemon 2: " + (team.getP2() != null ? team.getP2().getPokemon() : "N/A"));
            System.out.println("\tPokemon 3: " + (team.getP3() != null ? team.getP3().getPokemon() : "N/A"));
            System.out.println("\tPokemon 4: " + (team.getP4() != null ? team.getP4().getPokemon() : "N/A"));
            System.out.println("\tPokemon 5: " + (team.getP5() != null ? team.getP5().getPokemon() : "N/A"));
            System.out.println("\tPokemon 6: " + (team.getP6() != null ? team.getP6().getPokemon() : "N/A"));

        }
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
	
	public static ArrayList<User> importarUsuariosDesdeCSV(String filePath) {
		ArrayList<User> result = new ArrayList<User>();
		List<PokemonTeam> listaEquipos = new ArrayList<PokemonTeam>(importarEquiposPokemonDesdeCSV("resources/pokemonteams.csv"));
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Leer la primera línea que generalmente contiene encabezados y descartarla
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");

                // Obtener datos del CSV
                String username = fields[0];
                String password = fields[1];
                String firstSurname = fields[2];
                String secondSurname = fields[3];
                String email = fields[4];
                int telephone = Integer.parseInt(fields[5]);
                // Crear el usuario
                User user = new User(username, password, firstSurname, secondSurname, email, telephone);
                
                // Asignar equipos al usuario si están presentes en la lista de equipos
                for (PokemonTeam team : listaEquipos) {
                    if (team.getU1() != null && team.getU1().getUsername().equals(username)) {
                        user.anadirEquipo(team);
                    }
                }
                result.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
	}
	
	public static void exportarUsuariosACSV(List<User> listUsers, String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escribir encabezados en el archivo CSV
            writer.write("UserName,Password,FirstSurname,SecondSurname,email,telefono");
            writer.newLine();

            // Escribir datos de cada equipo en el archivo CSV
            for (User user : listUsers) {
                writer.write(user.toCSVString());
                writer.newLine();
            }

            System.out.println("CSV file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static ArrayList<PokemonTeam> importarEquiposPokemonDesdeCSV(String filePath) {
		ArrayList<PokemonTeam> result = new ArrayList<PokemonTeam>();
		List<Pokemon> pkm = new ArrayList<Pokemon>(importarPokemonsDesdeCSV());
		List<User> usr = new ArrayList<User>(importarUsuariosDesdeCSV("resources/user.csv"));
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Leer la primera línea que generalmente contiene encabezados y descartarla
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");

                // Obtener datos del CSV
                String teamName = fields[0];
                String username = fields[1];

                // Encontrar el usuario en la lista
                User user = findUserByUsername(usr, username);

                // Crear el equipo solo si se encuentra el usuario
                if (user != null) {
                    PokemonTeam team = new PokemonTeam(teamName, user);

                    // Agregar Pokémon al equipo si están presentes en el CSV
                    for (int i = 2; i < fields.length; i++) {
                        String pokemonName = fields[i];
                        if (!pokemonName.isEmpty()) {
                            Pokemon pokemon = findPokemonByName(pkm, pokemonName);
                            switch (i) {
                            case 2: team.setP1(pokemon); break;
                            case 3: team.setP2(pokemon); break;
                            case 4: team.setP3(pokemon); break;
                            case 5: team.setP4(pokemon); break;
                            case 6: team.setP5(pokemon); break;
                            case 7: team.setP6(pokemon); break;
                            default:
                                break;
                            }
                        }
                    }

                    // Agregar el equipo a la lista
                    result.add(team);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
	private static User findUserByUsername(List<User> userList, String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
	private static Pokemon findPokemonByName(List<Pokemon> pokemonList, String pokemonName) {
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getPokemon().equals(pokemonName)) {
                return pokemon;
            }
        }
        return null;
    }
}
