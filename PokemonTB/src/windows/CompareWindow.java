package windows;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
        panelContainer.setLayout(new GridLayout(2, 3, 10, 10));
        
        int arc = 20;
        
        JPanel panelTeam1= new JPanel();
        panelTeam1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelTeam1.setBackground(Color.WHITE);
        panelTeam1.setLayout(new BorderLayout());
        panelTeam1.add(new JLabel("Team 1", SwingConstants.CENTER), BorderLayout.CENTER);
        
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
        JPanel panelTeam2= new JPanel();
        
        panelTeam2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelTeam2.setBackground(Color.WHITE);
        panelTeam2.setLayout(new BorderLayout());
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
		        String resultado = compararPokemons(equipo1, equipo2);
		        JOptionPane.showMessageDialog(CompareWindow.this, resultado, "Ha ganado: ", JOptionPane.INFORMATION_MESSAGE);
		    }
        });
        panelContainer.add(competir, BorderLayout.CENTER);
        
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

        JLabel mensajeLabel = new JLabel("¿Qué equipo quieres seleccionar?");
        JTextField nombreEquipoField = new JTextField();
        JButton seleccionarButton = new JButton("Seleccionar Equipo");

        seleccionarButton.addActionListener(ev -> {
            // Acciones a realizar al seleccionar el equipo
            nombreEquipoInput = nombreEquipoField.getText();
            listaEquipos = new ArrayList<>(db.importarEquiposPokemonDesdeCSV("resources/pokemonteams.csv"));
            for(PokemonTeam pt : listaEquipos) {
            	if(pt.getName().equals(nombreEquipoInput)) {
            		equipo1 = pt;
            	}
            }
            pokemons = new ArrayList<>(db.importarPokemonsDesdeCSV());
            for (PokemonTeam pt : listaEquipos) {
                if(pt.getName().equals(nombreEquipoInput)) {
                	for (int i = 1; i <= 6; i++) {
                        try {
                            String methodName = "getP" + i;
                            for(Pokemon pokemon : pokemons) {
                            	if(pokemon.equals((Pokemon) PokemonTeam.class.getMethod(methodName).invoke(pt))) {
                            		pokemon = (Pokemon) PokemonTeam.class.getMethod(methodName).invoke(pt);
                            		panelPokemon = new JPanel();
                                    panelPokemon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                    panelPokemon.add(new JLabel("Name: " + pokemon.getPokemon()));
                                    getContentPane().add(panelPokemon);
                            	}
                            }
                            
                        } catch (Exception s) {
                            s.printStackTrace();
                        }
                    }
                }
            }
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
        JTextField nombreEquipoField = new JTextField();
        JButton seleccionarButton = new JButton("Seleccionar Equipo");

        seleccionarButton.addActionListener(ev -> {
            // Acciones a realizar al seleccionar el equipo
            nombreEquipoInput = nombreEquipoField.getText();
            listaEquipos = new ArrayList<>(db.importarEquiposPokemonDesdeCSV("resources/pokemonteams.csv"));
            for(PokemonTeam pt : listaEquipos) {
            	if(pt.getName().equals(nombreEquipoInput)) {
            		equipo2 = pt;
            	}
            }
            pokemons = new ArrayList<>(db.importarPokemonsDesdeCSV());
            for (PokemonTeam pt : listaEquipos) {
                if(pt.getName().equals(nombreEquipoInput)) {
                	for (int i = 1; i <= 6; i++) {
                        try {
                            String methodName = "getP" + i;
                            for(Pokemon pokemon : pokemons) {
                            	if(pokemon.equals((Pokemon) PokemonTeam.class.getMethod(methodName).invoke(pt))) {
                            		pokemon = (Pokemon) PokemonTeam.class.getMethod(methodName).invoke(pt);
                            		panelPokemon = new JPanel();
                                    panelPokemon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                    panelPokemon.add(new JLabel("Name: " + pokemon.getPokemon()));
                                    getContentPane().add(panelPokemon);
                            	}
                            }
                            
                        } catch (Exception s) {
                            s.printStackTrace();
                        }
                    }
                }
            }
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
		int contT1 = 0;
		int contT2 = 0;
		
		if(t1.getP1().getAttack() > t2.getP1().getHp() + t2.getP1().getDefense()) {
			contT1 += 1;
		} else {
			contT2 += 1;
		}
		
		if(t2.getP1().getAttack() > t1.getP1().getHp() + t1.getP1().getDefense()){
			contT2 += 1;
		} else {
			contT1 += 1;
		}
		if(mayorValor(contT1, contT2) == contT1) {
			return "Ha ganado" + t1.getName();
		} else {
			return "Ha ganado" + t2.getName();
		}
		
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
	
}
