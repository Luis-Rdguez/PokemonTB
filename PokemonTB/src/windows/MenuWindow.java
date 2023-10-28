package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 340);
        setLayout(new BorderLayout());

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(2, 3, 10, 10));

        JButton NewTeam = new JButton("New team");
        JButton LoadTeam = new JButton("Load team");
        JButton Pokedex = new JButton("Pokedex");
        JButton Logout = new JButton("Logout");
        this.getContentPane().setLayout(new GridLayout(2, 2));
        this.getContentPane().add(NewTeam);
        this.getContentPane().add(LoadTeam);
        this.getContentPane().add(Pokedex);
        this.getContentPane().add(Logout);
}
}
