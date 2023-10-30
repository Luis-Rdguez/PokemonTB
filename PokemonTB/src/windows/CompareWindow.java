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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CompareWindow  extends JFrame{
	
	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	
	
	public CompareWindow() {
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
                JOptionPane.showMessageDialog(null, "Esto selecciona un equipo");
            }
        });
        panelContainer.add(panelTeam1);
        JPanel panelTeam2= new JPanel();
        panelTeam2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelTeam2.setBackground(Color.WHITE);
        panelTeam2.setLayout(new BorderLayout());
        panelTeam2.add(new JLabel("Panel 2", SwingConstants.CENTER), BorderLayout.CENTER);
        panelTeam2.setPreferredSize(new Dimension(100, 100));
        panelTeam2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            BorderFactory.createEmptyBorder(arc, arc, arc, arc)
        ));

        panelTeam2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelTeam2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	JOptionPane.showMessageDialog(null, "Esto selecciona un equipo");
            }
        });

        panelContainer.add(panelTeam2);
        
        contentPane.add(panelContainer, BorderLayout.CENTER);


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
}
