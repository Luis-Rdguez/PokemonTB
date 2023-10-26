package windows;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import classes.Pokemon;


public class PokedexWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	private List<Pokemon> pokemons;
	
	private JTable tablaPokemons;
	private DefaultTableModel modeloDatosPokemon;
	private JTextField txtFiltro;

	
	public static void main(String[] args) {
		Pokemon p1 = new Pokemon(1,"bulbasaur","grass","poison",49,49,45,65,65,45,"overgrow","NA","chlorophyll");
		List<Pokemon> pokemons = new ArrayList<>(Arrays.asList(p1));
		PokedexWindow frame = new PokedexWindow(pokemons);
		frame.setVisible(true);
	}
	
	public PokedexWindow(List<Pokemon> pokemons) {
		this.pokemons = pokemons;
		
		this.initTables();
		
		this.loadPokemons();
		
		JScrollPane scrollPaneComics = new JScrollPane(this.tablaPokemons);
		scrollPaneComics.setBorder(new TitledBorder("Comics"));
		this.tablaPokemons.setFillsViewportHeight(true);
		
		this.txtFiltro = new JTextField(20);	
		
		JPanel panelFiltro = new JPanel();
		panelFiltro.add(new JLabel("Filtro por título: "));
		panelFiltro.add(txtFiltro);
		
		JPanel panelPokedex = new JPanel();
		panelPokedex.setLayout(new BorderLayout());
		panelPokedex.add(BorderLayout.CENTER, scrollPaneComics);
		panelPokedex.add(BorderLayout.NORTH, panelFiltro);
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	private void initTables() {
		Vector<String> cabeceraPokemons = new Vector<String>(Arrays.asList( "ID","Pokemon","Type 1","Type 2","Attack","Defense","HP","Special attack","Special defense","Speed","Ability 1","Ability 2","Hidden ability"));
		this.modeloDatosPokemon = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraPokemons);
		this.tablaPokemons = new JTable(this.modeloDatosPokemon) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {
				if (col == 0 || col == 12) {
					return false;
	         	} else {
	         		return true;
	         	}
	         }
		};
		
	}
	
	private void loadPokemons() {
		//Se borran los datos del modelo de datos
		this.modeloDatosPokemon.setRowCount(0);
		//Se añaden los comics uno a uno al modelo de datos
		this.pokemons.forEach(c -> this.modeloDatosPokemon.addRow(
				new Object[] {c.getId(), c.getPokemon(), c.getType_1(), c.getType_2(), c.getAttack(), c.getDefense(), c.getHp(), c.getSpecial_attack(), c.getSpecial_defense(), c.getSpeed(), c.getAbility_1(), c.getAbility_2(), c.getAbility_hidden()} )
		);
	}
}
