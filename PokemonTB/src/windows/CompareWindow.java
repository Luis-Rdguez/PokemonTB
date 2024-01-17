package windows;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import classes.*;
import db.db;

public class CompareWindow  extends JFrame{
	
	private JPanel contentPane;
	private List<PokemonTeam> listaEquipos;
	private List<Pokemon> pokemons;
	private JPanel panelPokemon;
	private PokemonTeam equipo1;
    private PokemonTeam equipo2;
    private String nombreEquipoInput;
    private JPanel panelTeam1, panelTeam2;
    private JLabel labelPokemon;
	private static final long serialVersionUID = 1L;	
	
	public CompareWindow() {
		
		ImageIcon icon = new ImageIcon("resources/other/MainImage.png");
		setIconImage(icon.getImage());
        setTitle("Compare teams");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(3,1));
        
        int arc = 20;
        
        panelTeam1= new JPanel(new GridLayout(2, 3));
        panelTeam1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelTeam1.setBackground(Color.WHITE);
        JLabel l = new JLabel("Team 1", SwingConstants.CENTER);
        panelTeam1.add(l);
        
        panelTeam1.setPreferredSize(new Dimension(100, 100));
        panelTeam1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            BorderFactory.createEmptyBorder(arc, arc, arc, arc)
        ));

        panelTeam1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelTeam1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                	 cargarEquipo1();
                	 
                	 
                }
            }
        });
        
        panelContainer.add(panelTeam1);
        panelTeam2= new JPanel(new GridLayout(2,3));
        
        panelTeam2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelTeam2.setBackground(Color.WHITE);
        panelTeam2.add(new JLabel("Team 2", SwingConstants.CENTER), BorderLayout.CENTER);
        panelTeam2.setPreferredSize(new Dimension(100, 100));
        panelTeam2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            BorderFactory.createEmptyBorder(arc, arc, arc, arc)
        ));

        panelTeam2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelTeam2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                	cargarEquipo2();
                }
            }
        });
        panelContainer.add(panelTeam2);
        
        contentPane.add(panelContainer, BorderLayout.CENTER);

        JButton competir = new JButton("Competir");
        
        competir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!equipo1.getName().equals(equipo2.getName())) {
					String resultado = compararPokemons(equipo1, equipo2);
			        JOptionPane.showMessageDialog(CompareWindow.this, resultado, "Ha ganado: ", JOptionPane.INFORMATION_MESSAGE);			    
				} else {
					JOptionPane.showMessageDialog(CompareWindow.this, "No puede competir contra si mismo", equipo1.getName(), JOptionPane.ERROR_MESSAGE);
				}
		       }
        });
        panelContainer.add(competir, BorderLayout.SOUTH);
        
        JButton bBack = new JButton("Back");
        
        bBack.addActionListener(new ActionListener() {
    		@Override
    			public void actionPerformed(ActionEvent e) {
    			PokemonTeamWindow vt = new PokemonTeamWindow();
            	vt.setVisible(true);
            	dispose();;
    			}
    	});
        contentPane.add(bBack, BorderLayout.SOUTH);
        
        setVisible(true);
	}
	
	public void cargarEquipo1() {
		JFrame frame = new JFrame("Seleccionar Equipo");
        JPanel panel = new JPanel(new GridLayout(3, 1));
        listaEquipos = new ArrayList<>(db.importarEquiposPokemonDesdeCSV("resources/pokemonteams.csv"));
        
        JLabel mensajeLabel = new JLabel("¿Qué equipo quieres seleccionar?");
        JComboBox<String> nombreEquipoField = new JComboBox<>();
        for(PokemonTeam pt : listaEquipos) {
        	if(pt.getUser().equals(LoginUserWindow.getNombreUsario())) {
        		nombreEquipoField.addItem(pt.getName());
        	}
        }
        JButton seleccionarButton = new JButton("Seleccionar Equipo");

        seleccionarButton.addActionListener(ev -> {
            // Acciones a realizar al seleccionar el equipo
        	panelTeam1.removeAll();
            nombreEquipoInput = nombreEquipoField.getSelectedItem().toString();
            for(PokemonTeam pt : listaEquipos) {
            	if(pt.getName().equals(nombreEquipoInput)) {
            		equipo1 = pt;
            		break;
            	}
            }
            pokemons = new ArrayList<>(db.importarPokemonsDesdeCSV());
            for (PokemonTeam pt : listaEquipos) {
                if(pt.getName().equals(nombreEquipoInput)) {
                	List<JPanel> panelesPokemon = new ArrayList<>();
                	for (int i = 0; i < 6; i++) {
                		int panelIndex = i+1;
                		panelPokemon = new JPanel();
                        panelPokemon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        switch (panelIndex) {
                        case 1:
                        	if (pt.getP1() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP1().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        case 2:
                        	if (pt.getP2() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP2().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        case 3:
                        	if (pt.getP3() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP3().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        case 4:
                        	if (pt.getP4() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP4().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        case 5:
                        	if (pt.getP5() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP5().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        case 6:
                        	if (pt.getP6() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP6().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        default:
                            System.out.println("Opción no válida");
                        }
                        panelPokemon.add(labelPokemon);
                        panelesPokemon.add(panelPokemon);
                    }
                	for (JPanel panelPokemon : panelesPokemon) {
                        panelTeam1.add(panelPokemon);
                    }
                	
                }
            }
            
            panelTeam1.revalidate();
            panelTeam1.repaint();
            panelTeam1.validate(); 
            frame.dispose();
        });

        panel.add(mensajeLabel);
        panel.add(nombreEquipoField);
        panel.add(seleccionarButton);

        frame.getContentPane().add(panel);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    
	}
	
	public void cargarEquipo2() {
		JFrame frame = new JFrame("Seleccionar Equipo");
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel mensajeLabel = new JLabel("¿Qué equipo quieres seleccionar?");
        listaEquipos = new ArrayList<>(db.importarEquiposPokemonDesdeCSV("resources/pokemonteams.csv"));
        JComboBox<String> nombreEquipoField = new JComboBox<>();
        for(PokemonTeam pt : listaEquipos) {
        	if(pt.getUser().equals(LoginUserWindow.getNombreUsario())) {
        		nombreEquipoField.addItem(pt.getName());
        	}
        }
        JButton seleccionarButton = new JButton("Seleccionar Equipo");

        seleccionarButton.addActionListener(ev -> {
            // Acciones a realizar al seleccionar el equipo
        	panelTeam2.removeAll();
            nombreEquipoInput = nombreEquipoField.getSelectedItem().toString();
            for(PokemonTeam pt : listaEquipos) {
            	if(pt.getName().equals(nombreEquipoInput)) {
            		equipo2 = pt;
            		break;
            	}
            }
            pokemons = new ArrayList<>(db.importarPokemonsDesdeCSV());
            for (PokemonTeam pt : listaEquipos) {
                if(pt.getName().equals(nombreEquipoInput)) {
                	List<JPanel> panelesPokemon = new ArrayList<>();
                	for (int i = 0; i < 6; i++) {
                		int panelIndex = i+1;
                		panelPokemon = new JPanel();
                        panelPokemon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        switch (panelIndex) {
                        case 1:
                        	if (pt.getP1() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP1().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        case 2:
                        	if (pt.getP2() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP2().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        case 3:
                        	if (pt.getP3() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP3().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        case 4:
                        	if (pt.getP4() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP4().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        case 5:
                        	if (pt.getP5() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP5().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        case 6:
                        	if (pt.getP6() != null) {
                        		String imagePath = "resources/PokemonLogosPruebas/" + pt.getP6().getId() + ".png";
                        		File file = new File(imagePath);
                            	
                				if(file.exists()) {
                        			ImageIcon originalIcon = new ImageIcon(imagePath);
                                    Image originalImage = originalIcon.getImage();
                                    Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                                    labelPokemon = new JLabel(resizedIcon);
                				} else {
                					labelPokemon = new JLabel("P:" + getPokemonFromTeam(pt, panelIndex).getPokemon());
                				}
                            } else {
                                labelPokemon = new JLabel("Pokemon " + panelIndex);
                            }
                            break;
                        default:
                            System.out.println("Opción no válida");
                        }
                        panelPokemon.add(labelPokemon);
                        panelesPokemon.add(panelPokemon);
                        
                    }
                	for (JPanel panelPokemon : panelesPokemon) {
                        panelTeam2.add(panelPokemon);
                    }
                	
                }
            }
            panelTeam2.revalidate();
            panelTeam2.repaint();
            frame.dispose();
        });

        panel.add(mensajeLabel);
        panel.add(nombreEquipoField);
        panel.add(seleccionarButton);

        frame.getContentPane().add(panel);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    
	}
	
	
	public String compararPokemons(PokemonTeam t1, PokemonTeam t2) {
		List<Pokemon> pt1 = new ArrayList<Pokemon>();
		List<Pokemon> pt2 = new ArrayList<Pokemon>();
		pt1.add(t1.getP1());
		pt1.add(t1.getP2());
		pt1.add(t1.getP3());
		pt1.add(t1.getP4());
		pt1.add(t1.getP5());
		pt1.add(t1.getP6());
		
		pt2.add(t2.getP1());
		pt2.add(t2.getP2());
		pt2.add(t2.getP3());
		pt2.add(t2.getP4());
		pt2.add(t2.getP5());
		pt2.add(t2.getP6());
		
		return combateRecursivo(pt1,pt2);
		
//		int contT1 = 0;
//		int contT2 = 0;
//		
//		if(t1.getP1().getAttack() > t2.getP1().getHp() + t2.getP1().getDefense()) {
//			contT1 += 1;
//		} else {
//			contT2 += 1;
//		}
//		
//		if(t2.getP1().getAttack() > t1.getP1().getHp() + t1.getP1().getDefense()){
//			contT2 += 1;
//		} else {
//			contT1 += 1;
//		}
//		if(mayorValor(contT1, contT2) == contT1) {
//			return "Ha ganado el equipo: " + t1.getName();
//		} else {
//			return "Ha ganado el equipo: " + t2.getName();
//		}
		
	}
	
	public String combateRecursivo(List<Pokemon> t1, List<Pokemon> t2) {
		if (!t1.isEmpty() && !t2.isEmpty()) {
			Pokemon pt1 = t1.get(0);
			Pokemon pt2 = t2.get(0);
			
			pt2 = atacar(pt1, pt2);
	        pt1 = atacar(pt2, pt1);
			
	        if (pt1.getHp() <= 0) {
	            t1.remove(0);
	        }
	        if (pt2.getHp() <= 0) {
	            t2.remove(0);
	        }
			return combateRecursivo(t1,t2);
		}else if(t1.isEmpty()) {
			return "Ha ganado el equipo 2, y ha conservado "+ t2.size() +" Pokemon(s): "+ printPokemons(t2);
		}else {
			return "Ha ganado el equipo 1, y ha conservado "+ t1.size() +" Pokemon(s): "+ printPokemons(t1);
		}
	}
	
	public Pokemon atacar(Pokemon t1, Pokemon t2) {
	    // Lógica de batalla: compara los atributos de ataque y defensa y los aplica con un multiplicador random
	    double multiplicadorAtaque = generarMultiplicadorAleatorio();
	    double multiplicadorDefensa = generarMultiplicadorAleatorio();

	    int ataqueFinal = (int) (t1.getAttack() * multiplicadorAtaque);
	    int defensaFinal = (int) (t2.getDefense() * multiplicadorDefensa);

	    int danioInfligido = Math.max(0, ataqueFinal - defensaFinal);
	    return recibirDanio(t2, danioInfligido);
	}

	private double generarMultiplicadorAleatorio() {
	    double MIN_MULTIPLIER = 0.5;
	    double MAX_MULTIPLIER = 1.5;
	    Random random = new Random();
	    return random.nextDouble((MAX_MULTIPLIER - MIN_MULTIPLIER))+MIN_MULTIPLIER;
	}

	private Pokemon recibirDanio(Pokemon pokemon, int danio) {
	    // Lógica para recibir daño y actualizar los puntos de salud (HP)
	    int hp = pokemon.getHp();
	    hp -= danio;
	    pokemon.setHp(hp);
	    return pokemon;
	}

	
	public String printPokemons(List<Pokemon> p) {

        // StringBuilder para construir el string resultante
        StringBuilder resultado = new StringBuilder();
        resultado.append(p.get(0).getPokemon());
        
        for (int i = 1; i < p.size(); i++) {
            resultado.append(", ").append(p.get(i).getPokemon());
        }

        return resultado.toString();
	}
	
	public int mayorValor(int valor1, int valor2) {
		
		if(valor1 > valor2) {
			return valor1;
		} else if(valor1 < valor2){
			return valor2;
		} else {
			return valor1;
		}
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
