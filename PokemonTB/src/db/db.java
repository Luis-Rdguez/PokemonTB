package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import classes.Pokemon;
import classes.PokemonTeam;
import classes.User;
import windows.LoginUserWindow;

public class db {

	private static Connection con;
	private static Statement s;
	private static ResultSet rs;
	private static boolean LOGGING = true;
	
	public static void main(String[] args) {

        List<PokemonTeam> pkmTeam = new ArrayList<PokemonTeam>(importarEquiposPokemonDesdeCSV("resources/pokemonteams.csv"));
        System.out.println(pkmTeam);
    }
	
	// Logger
	private static Logger logger = null;
	// Metodo local para loggear
	private static void log(Level level, String msg, Throwable excepcion) {
		if (!LOGGING) return;
		if (logger == null) {
			logger = Logger.getLogger(db.class.getName());
			logger.setLevel(Level.ALL);
		}
		if (excepcion == null)
			logger.log(level, msg);
		else
			logger.log(level, msg, excepcion);
	}
	
	public static void conectBD() {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:PokemonBD.db");
			s = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para cerrar la conexion con la base de datos despues de haber hecho todas las operaciones necesarias
	 */
	public static void cerrarConexion() {
		try {
			log( Level.INFO, "Cerrando conexion", null );
			con.close();
			s.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			log(Level.SEVERE, "Error: No se ha podido cerrar la Base de datos", e);
		}
	}
	
	// Metodos para Bases de Datos
	
	public static boolean creacionBD(String nombreBD) {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			log(Level.INFO, "Abriendo conexión con " + nombreBD, null);
			// CREACION DE TABLAS
			// Tabla User
			s = con.createStatement();
			String sent = "CREATE TABLE IF NOT EXISTS User (username varchar(30) NOT NULL, "
					+ "password varchar(40), "
					+ "firstSurname varchar(30), "
					+ "secondSurname varchar(30), "
					+ "email varchar(50), "
					+ "telefono long, "
					+ "PRIMARY KEY (username));";
		//  Comprobar si el statement es correcto
		//	log( Level.INFO, "Statement: " + sent , null);
			s.executeUpdate(sent);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log(Level.SEVERE, "Error: No se ha podido conectar a la Base de datos", e);
			return false;
		}
	}
	
	public static void añadirUsuario(User usuario) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void seleccionarUsuarioPorNombre(String nombre) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void añadirEquipoPokemon(PokemonTeam pt) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void eliminarEquipoPokemon(PokemonTeam pt) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
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
	
	public static List<User> importarUsuariosDesdeCSV(String filePath) {
		List<User> result = new ArrayList<User>();
		List<PokemonTeam> listaEquipos = new ArrayList<PokemonTeam>(importarEquiposPokemonDesdeCSV("resources/pokemonteams.csv"));
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] fields = linea.split(";");

                String username = fields[0];
                String password = fields[1];
                String firstSurname = fields[2];
                String secondSurname = fields[3];
                String email = fields[4];
                int telephone = Integer.parseInt(fields[5]);
                User user = new User(username, password, firstSurname, secondSurname, email, telephone);
                
                for (PokemonTeam pt : listaEquipos) {
                    if (pt.getUser() != null && pt.getUser().equals(username)) {
                        user.anadirEquipo(pt);
                    }
                }
                if (user.getEquipos().isEmpty()) {
                	user.setEquipos(null);
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
	
	public static List<PokemonTeam> importarEquiposPokemonDesdeCSV(String filePath) {
		List<PokemonTeam> result = new ArrayList<PokemonTeam>();
		List<Pokemon> pkm = new ArrayList<Pokemon>(importarPokemonsDesdeCSV());
//		List<User> usr = new ArrayList<User>(importarUsuariosDesdeCSV("resources/user.csv"));
		String userName = LoginUserWindow.getNombreUsario();
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
//                String user = findUserByUsername(userName, username);

                // Crear el equipo solo si se encuentra el usuario
                // Solo va si se hace desde el inicio de sesion ya que la variable userName es el nombre que se escribe en el login
                
                if (username != null) {
                    PokemonTeam team = new PokemonTeam(teamName, username);

                    // Agregar Pokémon al equipo si están presentes en el CSV
                    for (int i = 2; i < fields.length; i++) {
                        String pokemonName = fields[i];
                        if (!pokemonName.isEmpty()) {
                            Pokemon pokemon = findPokemonByName(pkm, pokemonName);
                            if (pokemon != null) {
                                switch (i) {
                                    case 2:
                                        team.setP1(pokemon);
                                        break;
                                    case 3:
                                        team.setP2(pokemon);
                                        break;
                                    case 4:
                                        team.setP3(pokemon);
                                        break;
                                    case 5:
                                        team.setP4(pokemon);
                                        break;
                                    case 6:
                                        team.setP5(pokemon);
                                        break;
                                    case 7:
                                        team.setP6(pokemon);
                                        break;
                                    default:
                                        break;
                                }
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
//	private static String findUserByUsername(List<User> userList, String username) {
//        for (User user : userList) {
//            if (user.getUsername().equals(username)) {
//                return username;
//            }
//        }
//        return null;
//    }
	public static Pokemon findPokemonByName(List<Pokemon> pokemonList, String pokemonName) {
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getPokemon().equals(pokemonName)) {
                return pokemon;
            }
        }
        return null;
    }
}
