package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import classes.Pokemon;
import classes.PokemonTeam;
import classes.User;
import db.db;


public class PokedexWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	private List<Pokemon> pokemons = db.importarPokemonsDesdeCSV();;
	
	private JTable tablaPokemons;
	private DefaultTableModel modeloDatosPokemon;
	private JTextField txtFiltro;
	

	
	public static void main(String[] args) {
		
//		pokemons = db.importarPokemonsDesdeCSV();
//		User u1 = new User("w", "w", "w", "w", "w", 4546);
//		PokemonTeam pt = new PokemonTeam("e1", u1);
//		PokedexWindow frame = new PokedexWindow(pokemons, pt, 0);
//		frame.setVisible(true);
	}
	
	public PokedexWindow(List<Pokemon> pokemons, PokemonTeam team, int pos, CreatePokemonTeamWindow cp, PokemonTeamWindow pw) {
		ImageIcon icon = new ImageIcon("resources/other/MainImage.png");
		setIconImage(icon.getImage());
		this.pokemons = pokemons;
		
		DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    setIcon((ImageIcon) value);
                    setHorizontalAlignment(CENTER);
                    setVerticalAlignment(CENTER);
                    setText("");
                    setBackground(table.getBackground());
                    if (isSelected || hasFocus) {
            			setBackground(table.getSelectionBackground());
            			
            		}
                } else {
                    setIcon(null);
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
                return this;

            }
        };
		
			this.initTables();
			this.loadPokemons();
		
	
		
		JScrollPane scrollPanePokemon = new JScrollPane(this.tablaPokemons);
		scrollPanePokemon.setBorder(new TitledBorder("Pokemons"));
		this.tablaPokemons.setFillsViewportHeight(true);
		this.tablaPokemons.setRowHeight(30);
		this.tablaPokemons.setDefaultRenderer(Object.class, tableCellRenderer);
		int[] columnas = {0, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int column : columnas) {
        	this.tablaPokemons.getColumnModel().getColumn(column).setPreferredWidth(30);
        }
		
		this.txtFiltro = new JTextField(20);
		
		JPanel panelFiltro = new JPanel();
		panelFiltro.add(new JLabel("Filtro por nombre: "));
		this.txtFiltro.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filtrarPokemons();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filtrarPokemons();
			}

			@Override
			public void changedUpdate(DocumentEvent e) { }			
		});
		panelFiltro.add(txtFiltro);
		
		JPanel panelPokedex = new JPanel();
		panelPokedex.setLayout(new BorderLayout());
		panelPokedex.add(BorderLayout.CENTER, scrollPanePokemon);
		panelPokedex.add(BorderLayout.NORTH, panelFiltro);
		
		
		JButton back = new JButton("Back");
//		back.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				CreatePokemonTeamWindow d = new CreatePokemonTeamWindow(team, i);
//				d.setVisible(true);
//				dispose();
//			}
//		});
        this.tablaPokemons.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = tablaPokemons.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / tablaPokemons.getRowHeight();
                
                if (row < tablaPokemons.getRowCount() && row >= 0 && column < tablaPokemons.getColumnCount() && column >= 0) {
                    Object value = tablaPokemons.getValueAt(row, column);
                    if (e.getClickCount() == 2) {
	                    int filaPokemon = tablaPokemons.getSelectedRow();
	                    Object nombrePokemon = tablaPokemons.getValueAt(filaPokemon, 1).toString();
	                    for(Pokemon p : pokemons) {
	                    	if(p.getPokemon().equals(nombrePokemon)) {
	                    		List<PokemonTeam> equipos = db.loadEquipos(LoginUserWindow.getNombreUsario()); 
	                    		switch (pos) {
                			    case 1:
                			    	team.setP1(p);
                			        break;
                			    case 2:
                			    	team.setP2(p);
                			        break;
                			    case 3:
                			    	team.setP3(p);
                			        break;
                			    case 4:
                			    	team.setP4(p);
                			        break;
                			    case 5:
                			    	team.setP5(p);
                			        break;
                			    case 6:
                			    	team.setP6(p);
                			        break;
                				}
                				CreatePokemonTeamWindow ct = new CreatePokemonTeamWindow(team, pw, pos);
                    			ct.setVisible(true);
                    			dispose();
	                    			
	                    		
	                    		
	                    	}
	                    }
	                    if (value instanceof JButton) {
	                        JButton button = (JButton) value;
	                        System.out.println("Button Clicked in PokedexWindow");
	                        // Puedes realizar otras acciones necesarias aquí
	                    }
                    }
                }
            }
        });
		
		panelPokedex.add(BorderLayout.SOUTH, back);
		
		this.getContentPane().add(panelPokedex);
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		back.addActionListener(new ActionListener() {
            @Override
        	public void actionPerformed(ActionEvent e) {
        		PokemonTeamWindow ptw = new PokemonTeamWindow();
        		ptw.setVisible(true);
        		dispose();
        	}
            });
		
	}
	private void filtrarPokemons() {
		//Se vacían las dos tablas
		this.modeloDatosPokemon.setRowCount(0);
		this.modeloDatosPokemon.setRowCount(0);
		
		//Se añaden a la tabla sólo los comics que contengan el texto del filtro
		this.pokemons.forEach(c -> {
			if(c.getPokemon().contains(this.txtFiltro.getText())) {
				String imagePath = "resources/PokemonLogosPruebas/" + c.getId() + ".png";
				ImageIcon originalIcon = new ImageIcon(imagePath);
	            Image originalImage = originalIcon.getImage();
	            Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
	            ImageIcon resizedIcon = new ImageIcon(resizedImage);
	            
	            ImageIcon type1Icon = loadTypeImage(c.getType_1());
	            ImageIcon type2Icon = loadTypeImage(c.getType_2());

	            this.modeloDatosPokemon.addRow(
	                    new Object[]{resizedIcon, c.getPokemon(), type1Icon, type2Icon, c.getAttack(), c.getDefense(), c.getHp(), c.getSpecial_attack(), c.getSpecial_defense(), c.getSpeed(), c.getAbility_1(), c.getAbility_2(), c.getAbility_hidden()}
	            );
			}
        });
	}
	
	private void initTables() {
		Vector<String> cabeceraPokemons = new Vector<String>(Arrays.asList( "ID","Pokemon","Type 1","Type 2","Attack","Defense","HP","Special attack","Special defense","Speed","Ability 1","Ability 2","Hidden ability"));
		this.modeloDatosPokemon = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraPokemons) {
			public boolean isCellEditable(int row, int column) {
                return false;
			}
		};
		this.tablaPokemons = new JTable(this.modeloDatosPokemon);
	}
	
	
	private void loadPokemons() {
		//Se borran los datos del modelo de datos
		this.modeloDatosPokemon.setRowCount(0);
		//Se añaden los pokemons uno a uno al modelo de datos
		this.pokemons.forEach(c -> {
			String imagePath = "resources/PokemonLogosPruebas/" + c.getId() + ".png";
			ImageIcon originalIcon = new ImageIcon(imagePath);
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_FAST);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            
            ImageIcon type1Icon = loadTypeImage(c.getType_1());
            ImageIcon type2Icon = loadTypeImage(c.getType_2());

            this.modeloDatosPokemon.addRow(
                    new Object[]{resizedIcon, c.getPokemon(), type1Icon, type2Icon, c.getAttack(), c.getDefense(), c.getHp(), c.getSpecial_attack(), c.getSpecial_defense(), c.getSpeed(), c.getAbility_1(), c.getAbility_2(), c.getAbility_hidden()}
            );
        });
	}
	
	//chatgpt assisted
	private ImageIcon loadTypeImage(String type) {
        if (type == null) {
            return new ImageIcon(); // ImageIcon vacío para el tipo null
        }
        String typeImagePath = "resources/PokemonTypes/" + type.toLowerCase() + ".png";
        ImageIcon originalIcon = new ImageIcon(typeImagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(20, 20, Image.SCALE_FAST);
        return new ImageIcon(resizedImage);
    }
	
    public JTable getTablaPokemons() {
        return tablaPokemons;
    }
}
