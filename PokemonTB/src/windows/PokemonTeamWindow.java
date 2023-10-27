package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

import pruebasPokemon.CreatePokemonTeamWindow;

public class PokemonTeamWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> nombres;
	private ArrayList<JLabel> labels;

	public PokemonTeamWindow() {
        setTitle("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new GridLayout(2, 1));
        nombres = new ArrayList<String>();
        labels = new ArrayList<JLabel>();
        
        JPanel equipoPanel = new JPanel();
        equipoPanel.setLayout(new GridLayout(6, 1));
//    	equipoPanel.setLayout(new BoxLayout(equipoPanel, BoxLayout.Y_AXIS));
    	equipoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    	
        // Fila 3: Botones "Añadir Equipo", "Eliminar Equipo" y "Editar Equipo"
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addEquipoButton = new JButton("Añadir Equipo");
        addEquipoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	// Creamos un frame para guardar el nombre del equipo
                JFrame frameNombre = new JFrame("Nombre Equipo");
                frameNombre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameNombre.setSize(500, 130);
                JPanel panel = new JPanel();
                JLabel labelPregunta = new JLabel("Escoja el nombre del equipo:", SwingConstants.CENTER);
                panel.add(labelPregunta, BorderLayout.NORTH);
                JTextField nombreEquipoField = new JTextField(40);
                panel.add(nombreEquipoField, BorderLayout.CENTER);
                frameNombre.add(panel);
                JButton continuarButton = new JButton("Continuar");
                continuarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombreEquipo = nombreEquipoField.getText();
                        nombres.add(nombreEquipo);
                    	JLabel equipoLabel = new JLabel(nombreEquipo);
                    	labels.add(equipoLabel);
                    	equipoLabel.setBorder(new LineBorder(Color.BLACK));
                    	equipoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
                        for(JLabel label: labels) {
                        	equipoPanel.add(label);
                        }
                        equipoLabel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (e.getClickCount() == 2) {
                                    CreatePokemonTeamWindow vt = new CreatePokemonTeamWindow();
                                    vt.setVisible(true);
                                    vt.setLocationRelativeTo(null);
                                }
                            }
                        });
                        
                        revalidate();
                        repaint();
                        frameNombre.setVisible(false);
                         
                    }
                });
                panel.add(continuarButton);
                
                
                frameNombre.setLocationRelativeTo(null);
                frameNombre.setVisible(true);
                
                
                
            }
        });
        
        JButton eliminarEquipoButton = new JButton("Eliminar Equipo");
        eliminarEquipoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	// Creamos un frame para guardar el nombre del equipo
                JFrame frameNombre = new JFrame("Nombre Equipo");
                frameNombre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameNombre.setSize(500, 130);
                JPanel panel = new JPanel();
                
                frameNombre.add(panel);
                JLabel labelPregunta = new JLabel("¿Qué equipo quiere eliminar?:", SwingConstants.CENTER);
                frameNombre.add(labelPregunta, BorderLayout.NORTH);
                JTextField nombreEquipoField = new JTextField(40);
                panel.add(nombreEquipoField, BorderLayout.CENTER);
                JButton continuarButton = new JButton("Continuar");
                continuarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombreEquipo = nombreEquipoField.getText();
                        if(nombres.contains(nombreEquipo)) {
                        	for(JLabel label : labels) {
                        		if (label.toString().equals(nombreEquipo)) {
                        			nombres.remove(label.toString());
                        			labels.remove(label);
                        			equipoPanel.remove(label);
                        		}
                        	}
                        	
                        }
                        equipoPanel.revalidate();
                    	equipoPanel.repaint();
                    	frameNombre.setVisible(false);
                        
                    }
                });
                panel.add(continuarButton);
                frameNombre.setVisible(true);
                frameNombre.setLocationRelativeTo(null);
            }	
        });
        JButton editarEquipoButton = new JButton("Editar Equipo");
        JPanel containerButton = new JPanel(new GridLayout(2,1));
        buttonPanel.add(eliminarEquipoButton);
        buttonPanel.add(editarEquipoButton);
        buttonPanel.add(addEquipoButton); 
        containerButton.add(buttonPanel, BorderLayout.SOUTH);
        add(containerButton, BorderLayout.SOUTH);
        add(equipoPanel, BorderLayout.SOUTH);
        

        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    
}
