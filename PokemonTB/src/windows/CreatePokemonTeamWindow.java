package windows;
import javax.swing.*;

import classes.Pokemon;
import classes.PokemonTeam;
import db.db;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreatePokemonTeamWindow extends JFrame {
    
	private static final long serialVersionUID = 1L;

	Pokemon p1 = new Pokemon(1,"bulbasaur","grass","poison",49,49,45,65,65,45,"overgrow","NA","chlorophyll");
	List<Pokemon> pokemons = new ArrayList<>(Arrays.asList(p1));
	public List<PokemonTeam> equiposPokemon;

	
	public CreatePokemonTeamWindow(PokemonTeam team) {
		ImageIcon icon = new ImageIcon("resources/other/MainImage.png");
		setIconImage(icon.getImage());
        setTitle("Create Pokemon Team Window");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(470, 340);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(2, 3, 10, 10));
        for (int i = 0; i < 6; i++) {
        	int ind = i+1;
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setBackground(Color.WHITE);
            panel.setLayout(new BorderLayout());
            panel.add(new JLabel("Panel " + (ind), SwingConstants.CENTER), BorderLayout.CENTER);

            int arc = 20;
            panel.setPreferredSize(new Dimension(100, 100));
            panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(arc, arc, arc, arc)
            ));

            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            final int index = i;

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                        List<Pokemon> listaPokemons = db.importarPokemonsDesdeCSV();
                        PokedexWindow vt = new PokedexWindow(listaPokemons, team, index);
                        vt.setVisible(true);
                        setVisible(false);
                        equiposPokemon = new ArrayList<>(db.importarEquiposPokemonDesdeCSV("resources/pokemonteams.csv"));
                    	PokemonTeam pt = new PokemonTeam(PokemonTeamWindow.getNombreEquipo(), LoginUserWindow.getNombreUsario());

                        Pokemon pokemon = db.findPokemonByName(db.importarPokemonsDesdeCSV(), selecPokRenderer.getNombrePokemon());
                        if (pokemon != null) {
                            switch (index) {
                                case 1:
                                    pt.setP1(pokemon);
                                    break;
                                case 2:
                                	pt.setP2(pokemon);
                                    break;
                                case 3:
                                	pt.setP3(pokemon);
                                    break;
                                case 4:
                                	pt.setP4(pokemon);
                                    break;
                                case 5:
                                	pt.setP5(pokemon);
                                    break;
                                case 6:
                                	pt.setP6(pokemon);
                                    break;
                                default:
                                    break;
                            }
                        }
                        
                        equiposPokemon.add(pt);
                        JLabel label = new JLabel(pokemon.getPokemon());
                    	panel.add(label, BorderLayout.CENTER);
                    	panel.revalidate();
                    	panel.repaint();
                    }
                	
                	
                }
            });

            panelContainer.add(panel);
        }

        add(panelContainer, BorderLayout.CENTER);

        
        JButton guardarEquipoButton = new JButton("Guardar equipo");
        guardarEquipoButton.addActionListener(e -> {
        	//cargar los equipos en la base de datos
                
        	db.exportarEquiposPokemonACSV(equiposPokemon, "resources/pokemonteams.csv");
        	dispose();
        	
        	
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guardarEquipoButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
	
	

    
}
