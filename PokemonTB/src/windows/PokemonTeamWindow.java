package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

import classes.PokemonTeam;
import classes.User;

public class PokemonTeamWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> nombres;
	private ArrayList<JLabel> labels;
	private JLabel label;
	private JPanel panel;
	
	public ArrayList<String> cargarNombres() {

        try (BufferedReader reader = new BufferedReader(new FileReader("resources/pokemonteams.csv"))) {
            String linea;
            reader.readLine();
            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split(",");
                
                for (int i = 0; i < elementos.length; i++) {
                    elementos[i] = elementos[i].replaceAll("\"", "").trim();
                }

                if (elementos.length > 0) {
                    nombres.add(elementos[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		return nombres;
		
	}
	
	public ArrayList<JLabel> cargarLabels() {
		cargarNombres();
		for(String s : nombres) {
			label = new JLabel(s);
			labels.add(label);
		}
		return labels;	
	}
	
	public PokemonTeamWindow() {
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                cargarLabels();
                cargarEquipos(labels, panel);
                revalidate();
            	repaint();
            }
        });
		ImageIcon icon = new ImageIcon("resources/other/MainImage.png");
		setIconImage(icon.getImage());
        setTitle("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new GridLayout(3, 1));
        nombres = new ArrayList<String>();
        labels = new ArrayList<JLabel>();
        panel = new JPanel();
        
        
        
        JPanel equipoPanel = new JPanel();
        equipoPanel.setLayout(new GridLayout(6, 1));
    	equipoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    	
        // Fila 3: Botones "Añadir Equipo", "Eliminar Equipo" y "Comparar equipos"
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addEquipoButton = new JButton("Añadir Equipo");
        addEquipoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	// Creamos un frame para guardar el nombre del equipo
                JFrame frameNombre = new JFrame("Nombre Equipo");
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
                    	cargarNombres();
                        String nombreEquipo = nombreEquipoField.getText();
                        User u1 = new User("hola", "hola", "hola", "hola", "hola", 6);
                    	PokemonTeam t1 = new PokemonTeam(nombreEquipo, u1);
                    	u1.anadirEquipo(t1);
                        if(!nombres.contains(nombreEquipo)) {
                        	nombres.add(nombreEquipo);
                        	JLabel equipoLabel = new JLabel(nombreEquipo);
                        	labels.add(equipoLabel);
                        	cargarEquipos(labels, equipoPanel);
                        	
                            CreatePokemonTeamWindow cp = new CreatePokemonTeamWindow(null);
                            cp.setVisible(true);
//                            try {
//								anadirEquipoAFichero(nombreEquipo);
//							} catch (IOException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
                        } else {
                        	showMessage("El nombre del equipo ya existe.");
                        }
                        
                        
                        
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
                frameNombre.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                        if(nombreEquipo != null && nombres.contains(nombreEquipo)) {
                        	for(JLabel label : labels) {
                        		if (labels.size() >= 1 && label.getText().equals(nombreEquipo)) {
                        			nombres.remove(label.getText());
                        			labels.remove(label);
                        			cargarEquipos(labels, equipoPanel);
                        			break;
                        		} else {
                        			JOptionPane.showMessageDialog(null, "El equipo no existe");
                        		}
                        	}
                        	
                        }
                        revalidate();
                    	repaint();
                    	frameNombre.setVisible(false);
                        
                    }
                    
                });
                
                panel.add(continuarButton);
                frameNombre.setVisible(true);
                frameNombre.setLocationRelativeTo(null);
            }	
        });
        JButton compararEquiposButton = new JButton("Competir");
        
        compararEquiposButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
            		CompareWindow cw = new CompareWindow();
                    cw.setVisible(true);
                    dispose();
                }
            });
        
        JPanel containerButton = new JPanel(new BorderLayout());
        
        JPanel backPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuWindow vt = new MenuWindow();
				vt.setVisible(true);
				setVisible(false);
			}
        	
        });
        backPanel.add(backButton, BorderLayout.SOUTH);
        
        
        buttonPanel.add(eliminarEquipoButton);
        buttonPanel.add(compararEquiposButton);
        buttonPanel.add(addEquipoButton); 
        containerButton.add(buttonPanel, BorderLayout.NORTH);
        add(containerButton);
        add(equipoPanel);
        add(backPanel);
        
        
        setLocationRelativeTo(null);
    }
	
	private void showMessage(String message) {
        JOptionPane.getRootFrame().dispose(); 
        JOptionPane.showMessageDialog(null, message);
        
    }
	
	private void cargarEquipos(ArrayList<JLabel> labels, JPanel panel) {
		panel.removeAll();
		for(JLabel label : labels) {
			label.setBorder(new LineBorder(Color.BLACK));
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				    if (e.getClickCount() == 2) {
					CreatePokemonTeamWindow vt = new CreatePokemonTeamWindow(null);
					vt.setVisible(true);
					vt.setLocationRelativeTo(null);
                        
                    }
                }
            });
			panel.add(label);
		}
		revalidate();
		repaint();
	}
	
//	public static void anadirEquipoAFichero(String nombreEquipo) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/pokemonteams.csv", true))) {
//            
//            writer.write(nombreEquipo);
//        }
//    }
    
}
