package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.LineBorder;

import classes.Pokemon;
import classes.PokemonTeam;
import classes.User;
import db.db;

public class PokemonTeamWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	
	private static ArrayList<String> nombres;
	private static ArrayList<JLabel> labels;
	private static JLabel label;
	public JPanel equipoPanel;
	public static String nombreEquipo;
	PokemonTeamWindow currentInstance = PokemonTeamWindow.this;
	
	public static String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		PokemonTeamWindow.nombreEquipo = nombreEquipo;
	}
	
	public JPanel getEquipoPanel() {
		return equipoPanel;
	}
	
	public void setEquipoPanel(JPanel equipoPanel) {
        this.equipoPanel = equipoPanel;
    }
	
   
   	public static void main(String[] args) {
		
   	}
	
	public static ArrayList<String> cargarNombres() {
		nombres = new ArrayList<>();
		ArrayList<PokemonTeam> teams = db.loadEquipos(LoginUserWindow.getNombreUsario());
		for(PokemonTeam t: teams) {
			nombres.add(t.getName());
		}
//        try (BufferedReader reader = new BufferedReader(new FileReader("resources/pokemonteams.csv"))) {
//            String linea;
//            reader.readLine();
//            while ((linea = reader.readLine()) != null) {
//                String[] elementos = linea.split(";");
//                String teamName = elementos[0];
//                String username = elementos[1];
//
//                if (username != null && username.equals(LoginUserWindow.getNombreUsario())) {
//                	
//                    nombres.add(teamName);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
		return nombres;
		
	}
	
	public static ArrayList<JLabel> cargarLabels() {
		nombres = cargarNombres();
		labels = new ArrayList<>();
		for(String s : nombres) {
			label = new JLabel(s);
			labels.add(label);
		}
		return labels;	
	}
	
	public void mostrarEquipos() {
		List<PokemonTeam> teams = new ArrayList<>(db.loadEquipos(LoginUserWindow.getNombreUsario()));
        for(PokemonTeam pt : teams) {
        	cargarEquipos(pt);
        }
	}
	
	public PokemonTeamWindow() {
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                mostrarEquipos();
            }
        });
		ImageIcon icon = new ImageIcon("resources/other/MainImage.png");
		setIconImage(icon.getImage());
        setTitle("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new GridLayout(3, 1));
        nombres = new ArrayList<String>();
        labels = new ArrayList<JLabel>();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);

        JMenuItem pokedexItem = new JMenuItem("Pokedex");
        JMenuItem logoutItem = new JMenuItem("Logout");

        pokedexItem.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    List<Pokemon> pokemons = new ArrayList<>(db.importarPokemonsDesdeCSV());
                    PokedexWindow frame = new PokedexWindow(pokemons, null, 0, null, null);
                    frame.setVisible(true);
                    dispose();
                }
            });
        
        
        equipoPanel = new JPanel();
        equipoPanel.setLayout(new GridLayout(6, 1));
    	equipoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    	
        // Fila 3: Botones "Añadir Equipo", "Eliminar Equipo" y "Comparar equipos"
        JMenuItem addEquipoButton = new JMenuItem("Añadir Equipo");
        addEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Creamos un frame para guardar el nombre del equipo
                JFrame frameNombre = new JFrame("Nombre Equipo");
                frameNombre.setSize(500, 130);
                JPanel panel = new JPanel();
                JLabel labelPregunta = new JLabel("Escoja el nombre del equipo:", SwingConstants.CENTER);
                panel.add(labelPregunta, BorderLayout.NORTH);
                JTextField nombreEquipoField = new JTextField(40);
                panel.add(nombreEquipoField, BorderLayout.CENTER);
                frameNombre.add(panel);

                JButton continuarButton = new JButton("Continuar");
                continuarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombreEquipo = nombreEquipoField.getText();
                        nombres = cargarNombres();
                    	PokemonTeam t1 = new PokemonTeam(nombreEquipo, LoginUserWindow.getNombreUsario());
                    	
                        if(!nombres.contains(nombreEquipo)) {
                            CreatePokemonTeamWindow cp = new CreatePokemonTeamWindow(t1, currentInstance, 0);
                            cp.setVisible(true);
                            
                        } else {
                        	showMessage("El nombre del equipo ya existe.");
                        }
                        
                        
                        
                        revalidate();
                        repaint();
                        frameNombre.setVisible(false);
                         
                    }
                });
                panel.add(continuarButton);
                
                
                frameNombre.setLocationRelativeTo(null);
                frameNombre.setVisible(true);
                
                
                
            }
        });
        
        JMenuItem eliminarEquipoButton = new JMenuItem("Eliminar Equipo");
        eliminarEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Creamos un frame para guardar el nombre del equipo
                JFrame frameNombre = new JFrame("Nombre Equipo");
                frameNombre.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frameNombre.setSize(500, 130);
                JPanel panel = new JPanel();
                
                frameNombre.add(panel);
                List<PokemonTeam> listaEquipos = new ArrayList<>(db.loadEquipos(LoginUserWindow.getNombreUsario()));
                JLabel labelPregunta = new JLabel("¿Qué equipo quieres eliminar?", SwingConstants.CENTER);
                JComboBox<String> nombreEquipoField = new JComboBox<>();
                for(PokemonTeam pt : listaEquipos) {
                	nombreEquipoField.addItem(pt.getName());
                }
                frameNombre.add(labelPregunta, BorderLayout.NORTH);
                panel.add(nombreEquipoField, BorderLayout.CENTER);
                JButton continuarButton = new JButton("Continuar");
                continuarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nombreEquipo = nombreEquipoField.getSelectedItem().toString();
                        nombres = cargarNombres();
                        labels = cargarLabels();
                        PokemonTeam pt = null;
                        for(PokemonTeam t : listaEquipos) {
                        	if(t.getName().equals(nombreEquipo)) {
                        		pt = t;
                        		break;
                        	}
                        }
                        
                        db.eliminarEquipoPokemon(pt);
                        mostrarEquipos();
//                        List<PokemonTeam> pt = new ArrayList<>(db.importarEquiposPokemonDesdeCSV("resources/pokemonteams.csv"));
//                        if(nombreEquipo != null && nombres.contains(nombreEquipo)) {
//                        	for(PokemonTeam team : pt) {
//                        		if(team.getName().equals(nombreEquipo)) {
//                        			pt.remove(team);
//                        			db.exportarEquiposPokemonACSV(pt, "resources/pokemonteams.csv");
//                        			cargarEquipos(team);
//                        			break;
//                        		}
//                        	}
//                        	
//                        	
//                        }
                        
                    	frameNombre.setVisible(false);
                        
                    }
                    
                });
                
                panel.add(continuarButton);
                frameNombre.setVisible(true);
                frameNombre.setLocationRelativeTo(null);
            }	
        });
        JMenuItem compararEquiposButton = new JMenuItem("Competir");
        
        compararEquiposButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
            		CompareWindow cw = new CompareWindow();
                    cw.setVisible(true);
                    dispose();
                }
            });
        
        JPanel containerButton = new JPanel(new BorderLayout());
        
        JPanel backPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                StartWindow sw = new StartWindow();
                sw.setVisible(true);
                dispose();
            }
        	
        });
        backPanel.add(backButton, BorderLayout.SOUTH);

        optionsMenu.add(pokedexItem);
        optionsMenu.add(eliminarEquipoButton);
        optionsMenu.add(compararEquiposButton);
        optionsMenu.add(addEquipoButton);
        add(containerButton);
        add(equipoPanel);
        add(backPanel);
        
        
        setLocationRelativeTo(null);
    }
	
	private void showMessage(String message) {
        JOptionPane.getRootFrame().dispose(); 
        JOptionPane.showMessageDialog(null, message);
        
    }
	
	public void cargarEquipos(PokemonTeam team) {
		labels = cargarLabels();
		equipoPanel.removeAll();
		for(JLabel label : labels) {
			label.setBorder(new LineBorder(Color.BLACK));
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				    if (e.getClickCount() == 2) {
						CreatePokemonTeamWindow vt = new CreatePokemonTeamWindow(team, currentInstance, 0);
						vt.setVisible(true);
						vt.setLocationRelativeTo(null);
                        
                    }
                }
            });
			equipoPanel.add(label);
		}
		equipoPanel.revalidate();
		equipoPanel.repaint();
	}
	

    
}
