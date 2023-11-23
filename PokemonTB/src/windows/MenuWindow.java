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
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import classes.Pokemon;

public class MenuWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
        MenuWindow frame = new MenuWindow();
        frame.setVisible(true);
    }

	public MenuWindow() {
		ImageIcon icon = new ImageIcon("resources/other/MainImage.png");
		setIconImage(icon.getImage());
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 340);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(2, 3, 10, 10));

        JButton OpenTeams = new JButton("Open Teams");
        JButton Pokedex = new JButton("Pokedex");
        JButton Logout = new JButton("Logout");
        this.getContentPane().setLayout(new GridLayout(3, 1));
        this.getContentPane().add(OpenTeams);
        this.getContentPane().add(Pokedex);
        this.getContentPane().add(Logout);

        OpenTeams.addActionListener(new ActionListener() {
            @Override
            	public void actionPerformed(ActionEvent e) {
            		PokemonTeamWindow ptw = new PokemonTeamWindow();
            		ptw.setVisible(true);
            		dispose();
            	}
            });
        Pokedex.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    Pokemon p1 = new Pokemon(1,"bulbasaur","grass","poison",49,49,45,65,65,45,"overgrow","NA","chlorophyll");
                    List<Pokemon> pokemons = new ArrayList<>(Arrays.asList(p1));
                    PokedexWindow frame = new PokedexWindow(pokemons, "p");
                    frame.setVisible(true);
                    dispose();
                }
            });
        Logout.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    StartWindow sw = new StartWindow();
                    sw.setVisible(true);
                    dispose();
                }
            });
}
}