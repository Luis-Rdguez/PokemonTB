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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import classes.*;

public class CompareWindow  extends JFrame{
	
	private JPanel contentPane;
	private List<PokemonTeam> listaEquipos;
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
        listaEquipos = new ArrayList<>();
        panelTeam1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                	 // Mostrar el JFrame
                    JFrame frame = new JFrame("Seleccionar Equipo");
                    JPanel panel = new JPanel(new GridLayout(3, 1));

                    JLabel mensajeLabel = new JLabel("¿Qué equipo quieres seleccionar?");
                    JTextField nombreEquipoField = new JTextField();
                    JButton seleccionarButton = new JButton("Seleccionar Equipo");

                    seleccionarButton.addActionListener(ev -> {
                        // Acciones a realizar al seleccionar el equipo
                        String nombreEquipoInput = nombreEquipoField.getText();
                        for (PokemonTeam pt : listaEquipos) {
                            if(pt.getName().equals(nombreEquipoInput)) {
                            	for (int i = 1; i <= 6; i++) {
                                    try {
                                        // Obtener dinámicamente el método getP1(), getP2(), getP3()
                                        String methodName = "getP" + i;
                                        Pokemon pokemon = (Pokemon) PokemonTeam.class.getMethod(methodName).invoke(pt);

                                        // Crear y agregar el panel correspondiente
                                        JPanel panelPokemon = new JPanel();
                                        panelPokemon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                        panelPokemon.add(new JLabel("Id: " + pokemon.getId()));
                                        getContentPane().add(panelPokemon);
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
            }
        });
        
        panelContainer.add(panelTeam1);
        JPanel panelTeam2= new JPanel();
        
        panelTeam2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                	 // Mostrar el JFrame
                    JFrame frame = new JFrame("Seleccionar Equipo");
                    JPanel panel = new JPanel(new GridLayout(3, 1));

                    JLabel mensajeLabel = new JLabel("¿Qué equipo quieres seleccionar?");
                    JTextField nombreEquipoField = new JTextField();
                    JButton seleccionarButton = new JButton("Seleccionar Equipo");

                    seleccionarButton.addActionListener(ev -> {
                        // Acciones a realizar al seleccionar el equipo
                        String nombreEquipoInput = nombreEquipoField.getText();
                        for (PokemonTeam pt : listaEquipos) {
                            if(pt.getName().equals(nombreEquipoInput)) {
                            	for (int i = 1; i <= 6; i++) {
                                    try {
                                        // Obtener dinámicamente el método getP1(), getP2(), getP3()
                                        String methodName = "getP" + i;
                                        Pokemon pokemon = (Pokemon) PokemonTeam.class.getMethod(methodName).invoke(pt);

                                        // Crear y agregar el panel correspondiente
                                        JPanel panelPokemon = new JPanel();
                                        panelPokemon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                        panelPokemon.add(new JLabel("Id: " + pokemon.getId()));
                                        getContentPane().add(panelPokemon);
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
            }
        });
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
