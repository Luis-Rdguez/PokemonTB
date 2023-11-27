package windows;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import classes.Pokemon;
import classes.PokemonTeam;
import classes.User;
import db.db;


public class PokedexWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	private List<Pokemon> pokemons;
	
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
	
	public PokedexWindow(List<Pokemon> pokemons, PokemonTeam team, int pos) {
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
		
		if(team == null) {
			this.initTables1();
			this.loadPokemons();
		} else {
			this.initTables2();
			this.loadPokemons();
			this.tablaPokemons.getColumnModel().getColumn(13).setCellRenderer(new selecPokRenderer(this, team, pos));
		}
		
	
		
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
		panelFiltro.add(txtFiltro);
		
		JPanel panelPokedex = new JPanel();
		panelPokedex.setLayout(new BorderLayout());
		panelPokedex.add(BorderLayout.CENTER, scrollPanePokemon);
		panelPokedex.add(BorderLayout.NORTH, panelFiltro);
		
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreatePokemonTeamWindow d = new CreatePokemonTeamWindow(team);
				d.setVisible(true);
				dispose();
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
            		if(team == null) {
                        MenuWindow mw = new MenuWindow();
                        mw.setVisible(true);
                        dispose();
            		}else {
            			dispose();
            		}

                }
            });
		
	}
	
	private void initTables1() {
		Vector<String> cabeceraPokemons = new Vector<String>(Arrays.asList( "ID","Pokemon","Type 1","Type 2","Attack","Defense","HP","Special attack","Special defense","Speed","Ability 1","Ability 2","Hidden ability"));
		this.modeloDatosPokemon = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraPokemons) {
			public boolean isCellEditable(int row, int column) {
                return false;
			}
		};
		this.tablaPokemons = new JTable(this.modeloDatosPokemon);
	}
	private void initTables2() {
		Vector<String> cabeceraPokemons = new Vector<String>(Arrays.asList( "ID","Pokemon","Type 1","Type 2","Attack","Defense","HP","Special attack","Special defense","Speed","Ability 1","Ability 2","Hidden ability","Select"));
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
			String imagePath = "resources/PokemonLogos/" + c.getId() + ".png";
			ImageIcon originalIcon = new ImageIcon(imagePath);
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
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
        Image resizedImage = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
