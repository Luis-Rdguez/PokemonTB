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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import classes.Pokemon;
import db.db;

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
        setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);
        
        JMenuItem openTeamsItem = new JMenuItem("Open Teams");
        JMenuItem pokedexItem = new JMenuItem("Pokedex");
        JMenuItem logoutItem = new JMenuItem("Logout");
        
        optionsMenu.add(openTeamsItem);
        optionsMenu.add(pokedexItem);
        optionsMenu.addSeparator();
        optionsMenu.add(logoutItem);

        openTeamsItem.addActionListener(new ActionListener() {
            @Override
            	public void actionPerformed(ActionEvent e) {
            		PokemonTeamWindow ptw = new PokemonTeamWindow();
            		ptw.setVisible(true);
            		dispose();
            	}
            });
        pokedexItem.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    List<Pokemon> pokemons = new ArrayList<>(db.importarPokemonsDesdeCSV());
                    PokedexWindow frame = new PokedexWindow(pokemons, null, 0);
                    frame.setVisible(true);
                    dispose();
                }
            });
        logoutItem.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    StartWindow sw = new StartWindow();
                    sw.setVisible(true);
                    dispose();
                }
            });
}
}