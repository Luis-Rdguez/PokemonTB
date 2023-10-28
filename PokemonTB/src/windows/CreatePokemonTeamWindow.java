package windows;
import javax.swing.*;

import classes.Pokemon;

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
	
	public CreatePokemonTeamWindow() {
        setTitle("Create Pokemon Team Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 340);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(2, 3, 10, 10));

        for (int i = 0; i < 6; i++) {
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setBackground(Color.WHITE);
            panel.setLayout(new BorderLayout());
            panel.add(new JLabel("Panel " + (i + 1), SwingConstants.CENTER), BorderLayout.CENTER);

            int arc = 20;
            panel.setPreferredSize(new Dimension(100, 100));
            panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(arc, arc, arc, arc)
            ));

            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	PokemonSelectorWindow vt = new PokemonSelectorWindow(pokemons);
                	vt.setVisible(true);
                	setVisible(false);
                    JOptionPane.showMessageDialog(null, "Esto abre la base de datos de pokemons ");
                }
            });

            panelContainer.add(panel);
        }

        add(panelContainer, BorderLayout.CENTER);


        JButton guardarEquipoButton = new JButton("Guardar equipo");
        guardarEquipoButton.addActionListener(e -> {
        	PokemonTeamWindow vt = new PokemonTeamWindow();
        	vt.setVisible(true);
        	vt.setLocationRelativeTo(null);
        	this.setVisible(false);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guardarEquipoButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    
}
