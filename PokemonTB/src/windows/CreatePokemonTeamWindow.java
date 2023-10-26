package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreatePokemonTeamWindow extends JFrame {
   
	private static final long serialVersionUID = 1L;

	public CreatePokemonTeamWindow() {
        setTitle("Create Pokemon Team Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 340); // Aumentamos el tamaño de la ventana con el margen
        setLayout(new GridLayout(2, 3, 10, 10)); // Agregamos espacio entre los paneles

        for (int i = 0; i < 6; i++) {
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setBackground(Color.WHITE);
            panel.setLayout(new BorderLayout());
            panel.add(new JLabel("Panel " + (i + 1), SwingConstants.CENTER), BorderLayout.CENTER);

            // Agregar esquinas redondeadas
            int arc = 20; // El radio de las esquinas redondeadas
            panel.setPreferredSize(new Dimension(100, 100));
            panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(arc, arc, arc, arc)
            ));

            // Cambiar el cursor cuando el ratón está sobre el panel
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                	PokedexSelectorWindow vt = new PokedexSelectorWindow();
//                	vt.setVisible(true);
                }
            });

            add(panel);
        }
    }

    
}
