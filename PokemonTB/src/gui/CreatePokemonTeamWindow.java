package gui;
import javax.swing.*;

import classes.Pokemon;
import classes.PokemonTeam;
import db.db;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CreatePokemonTeamWindow extends JFrame {
    
	private static final long serialVersionUID = 1L;

//	Pokemon p1 = new Pokemon(1,"bulbasaur","grass","poison",49,49,45,65,65,45,"overgrow","NA","chlorophyll");
//	List<Pokemon> pokemons = new ArrayList<>(Arrays.asList(p1));
	public List<PokemonTeam> equiposPokemon;
	private List<Pokemon> pokemons = db.importarPokemonsDesdeCSV();
	public int index;
	public JLabel labelPokemon;
	public JPanel panelPokemon;
	CreatePokemonTeamWindow currentInstance = CreatePokemonTeamWindow.this;

	

	
	public CreatePokemonTeamWindow(PokemonTeam team, PokemonTeamWindow pokemonTeamWindow, int pos) {
		ImageIcon icon = new ImageIcon("resources/other/MainImage.png");
		setIconImage(icon.getImage());
        setTitle(team.getName());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(470, 340);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                pokemonTeamWindow.cargarEquipos(team);
            }
        });
        
        List<Pokemon> pokemons = new ArrayList<>(db.importarPokemonsDesdeCSV());
        List<PokemonTeam> equiposPokemon = new ArrayList<>(db.loadEquipos(LoginUserWindow.getNombreUsario()));
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(2, 3, 10, 10));
        for (int i = 0; i < 6; i++) {
            int panelIndex = i + 1;
            JPanel panelPokemon = new JPanel();
            panelPokemon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panelPokemon.setBackground(Color.WHITE);
            panelPokemon.setLayout(new BorderLayout());
            
            switch (panelIndex) {
            case 1:
            	if (team.getP1() != null) {
            		String imagePath = "resources/PokemonLogosPruebas/" + team.getP1().getId() + ".png";
            		File file = new File(imagePath);
                	
    				if(file.exists()) {
            			ImageIcon originalIcon = new ImageIcon(imagePath);
                        Image originalImage = originalIcon.getImage();
                        Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                        ImageIcon resizedIcon = new ImageIcon(resizedImage);
                        labelPokemon = new JLabel(resizedIcon);
    				} else {
    					labelPokemon = new JLabel("P:" + getPokemonFromTeam(team, panelIndex).getPokemon());
    				}
                } else {
                    labelPokemon = new JLabel("Pokemon " + panelIndex);
                }
                break;
            case 2:
            	if (team.getP2() != null) {
            		String imagePath = "resources/PokemonLogosPruebas/" + team.getP2().getId() + ".png";
            		File file = new File(imagePath);
                	
    				if(file.exists()) {
            			ImageIcon originalIcon = new ImageIcon(imagePath);
                        Image originalImage = originalIcon.getImage();
                        Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                        ImageIcon resizedIcon = new ImageIcon(resizedImage);
                        labelPokemon = new JLabel(resizedIcon);
    				} else {
    					labelPokemon = new JLabel("P:" + getPokemonFromTeam(team, panelIndex).getPokemon());
    				}
                } else {
                    labelPokemon = new JLabel("Pokemon " + panelIndex);
                }
                break;
            case 3:
            	if (team.getP3() != null) {
            		String imagePath = "resources/PokemonLogosPruebas/" + team.getP3().getId() + ".png";
            		File file = new File(imagePath);
                	
    				if(file.exists()) {
            			ImageIcon originalIcon = new ImageIcon(imagePath);
                        Image originalImage = originalIcon.getImage();
                        Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                        ImageIcon resizedIcon = new ImageIcon(resizedImage);
                        labelPokemon = new JLabel(resizedIcon);
    				} else {
    					labelPokemon = new JLabel("P:" + getPokemonFromTeam(team, panelIndex).getPokemon());
    				}
                } else {
                    labelPokemon = new JLabel("Pokemon " + panelIndex);
                }
                break;
            case 4:
            	if (team.getP4() != null) {
            		String imagePath = "resources/PokemonLogosPruebas/" + team.getP4().getId() + ".png";
            		File file = new File(imagePath);
                	
    				if(file.exists()) {
            			ImageIcon originalIcon = new ImageIcon(imagePath);
                        Image originalImage = originalIcon.getImage();
                        Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                        ImageIcon resizedIcon = new ImageIcon(resizedImage);
                        labelPokemon = new JLabel(resizedIcon);
    				} else {
    					labelPokemon = new JLabel("P:" + getPokemonFromTeam(team, panelIndex).getPokemon());
    				}
                } else {
                    labelPokemon = new JLabel("Pokemon " + panelIndex);
                }
                break;
            case 5:
            	if (team.getP5() != null) {
            		String imagePath = "resources/PokemonLogosPruebas/" + team.getP5().getId() + ".png";
            		File file = new File(imagePath);
                	
    				if(file.exists()) {
            			ImageIcon originalIcon = new ImageIcon(imagePath);
                        Image originalImage = originalIcon.getImage();
                        Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                        ImageIcon resizedIcon = new ImageIcon(resizedImage);
                        labelPokemon = new JLabel(resizedIcon);
    				} else {
    					labelPokemon = new JLabel("P:" + getPokemonFromTeam(team, panelIndex).getPokemon());
    				}
                } else {
                    labelPokemon = new JLabel("Pokemon " + panelIndex);
                }
                break;
            case 6:
            	if (team.getP6() != null) {
            		String imagePath = "resources/PokemonLogosPruebas/" + team.getP6().getId() + ".png";
            		File file = new File(imagePath);
                	
    				if(file.exists()) {
            			ImageIcon originalIcon = new ImageIcon(imagePath);
                        Image originalImage = originalIcon.getImage();
                        Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                        ImageIcon resizedIcon = new ImageIcon(resizedImage);
                        labelPokemon = new JLabel(resizedIcon);
    				} else {
    					labelPokemon = new JLabel("P:" + getPokemonFromTeam(team, panelIndex).getPokemon());
    				}
                } else {
                    labelPokemon = new JLabel("Pokemon " + panelIndex);
                }
                break;
            default:
                System.out.println("Opción no válida");
            }
 

            panelPokemon.add(labelPokemon, BorderLayout.CENTER);

            int arc = 20;
            panelPokemon.setPreferredSize(new Dimension(100, 100));
            panelPokemon.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(arc, arc, arc, arc)
            ));

            panelPokemon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            panelPokemon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                        List<Pokemon> listaPokemons = db.importarPokemonsDesdeCSV();

                        //prueba para cargar antes la pokede
                        List<Pokemon> listaPokemonsPrueba = new ArrayList<>();
                        for (int i = 0; i < 10; i++) {
                            listaPokemonsPrueba.add(listaPokemons.get(i));
                        }
//                      PokedexWindow vt = new PokedexWindow(listaPokemons, team, panelIndex, currentInstance, pokemonTeamWindow);
                        PokedexWindow vt = new PokedexWindow(listaPokemonsPrueba, team, panelIndex, currentInstance, pokemonTeamWindow);
                        vt.setVisible(true);
                        dispose();
                    }
                }
            });

            panelContainer.add(panelPokemon);
        }

        add(panelContainer, BorderLayout.CENTER);

        
        JButton guardarEquipoButton = new JButton("Guardar equipo");
        guardarEquipoButton.addActionListener(e -> {
        	//cargar los equipos en la base de datos
        	
//            Pokemon pokemon = db.findPokemonByName(db.importarPokemonsDesdeCSV(), selecPokRenderer.getNombrePokemon());
//            if (pokemon != null) {
//                switch (index) {
//                    case 1:
//                        team.setP1(pokemon);                      
//                        break;
//                    case 2:
//                    	team.setP2(pokemon);                   	
//                        break;
//                    case 3:
//                    	team.setP3(pokemon);                   	
//                        break;
//                    case 4:
//                    	team.setP4(pokemon);                    	
//                        break;
//                    case 5:
//                    	team.setP5(pokemon);                    	
//                        break;
//                    case 6:
//                    	team.setP6(pokemon);
//                        break;
//                    default:
//                        break;
//                }
//            }
            try {
                if (team.getP1() != null && team.getP2() != null && team.getP3() != null && team.getP4() != null && team.getP5() != null && team.getP6() != null) {
                    if(!equiposPokemon.contains(team)) {
                    	equiposPokemon.add(team);
                    } else {
                    	equiposPokemon.remove(team);
                    	switch (index) {
        			    case 1:
        			    	team.setP1(team.getP1());
        			        break;
        			    case 2:
        			    	team.setP2(team.getP2());
        			        break;
        			    case 3:
        			    	team.setP3(team.getP3());
        			        break;
        			    case 4:
        			    	team.setP4(team.getP4());
        			        break;
        			    case 5:
        			    	team.setP5(team.getP5());
        			        break;
        			    case 6:
        			    	team.setP6(team.getP6());
        			        break;
        				}
                    	equiposPokemon.add(team);
                    }
                }
            } catch (NullPointerException e1) {
      
            	JOptionPane.showMessageDialog(this, "Faltan Pokémon en el equipo", "Error", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
            
            ArrayList<PokemonTeam> teams = db.loadEquipos(LoginUserWindow.getNombreUsario());
            for(PokemonTeam t : equiposPokemon) {
            	Boolean contains = false;
            	for(PokemonTeam tdb : teams) {
            		if(t.getName().equals(tdb.getName())) {
            			db.eliminarEquipoPokemon(t);
            			db.añadirEquipoPokemon(t);
            			contains = true;
            		}
            	}
            	if(contains.equals(false)) {
            		db.añadirEquipoPokemon(t);
            	}
            }
        	dispose();
        	
        	
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guardarEquipoButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
	
	private Pokemon getPokemonFromTeam(PokemonTeam team, int index) {
	    switch (index) {
	        case 1:
	            return team.getP1();
	        case 2:
	            return team.getP2();
	        case 3:
	            return team.getP3();
	        case 4:
	            return team.getP4();
	        case 5:
	            return team.getP5();
	        case 6:
	            return team.getP6();
	        default:
	            throw new IllegalArgumentException("Índice de Pokémon no válido: " + index);
	    }
	}
	
	

    
}
