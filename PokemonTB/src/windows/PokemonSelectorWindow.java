package windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import classes.Pokemon;

public class PokemonSelectorWindow extends JFrame{
	private static final long serialVersionUID = 1L;

	private List<Pokemon> pokemons;
	
	private JTable tablaPokemons;
	private DefaultTableModel modeloDatosPokemon;
	private JTextField txtFiltro;
	private JCheckBox sel;

	
	public static void main(String[] args) {
		Pokemon p1 = new Pokemon(1,"bulbasaur","grass","poison",49,49,45,65,65,45,"overgrow","NA","chlorophyll");
		List<Pokemon> pokemons = new ArrayList<>(Arrays.asList(p1));
		PokemonSelectorWindow frame = new PokemonSelectorWindow(pokemons);
		frame.setVisible(true);
	}
	
	public PokemonSelectorWindow(List<Pokemon> pokemons) {
		ImageIcon icon = new ImageIcon("resources/other/MainImage.png");
		setIconImage(icon.getImage());
		this.pokemons = pokemons;
		
		this.initTables();
		
		this.loadPokemons();
		
		JScrollPane scrollPanePokemon = new JScrollPane(this.tablaPokemons);
		scrollPanePokemon.setBorder(new TitledBorder("Pokemons"));
		this.tablaPokemons.setFillsViewportHeight(true);
		
		this.txtFiltro = new JTextField(20);	
		
		JPanel panelFiltro = new JPanel();
		panelFiltro.add(new JLabel("Filtro por título: "));
		panelFiltro.add(txtFiltro);
		
		JPanel panelPokedex = new JPanel();
		panelPokedex.setLayout(new BorderLayout());
		panelPokedex.add(BorderLayout.CENTER, scrollPanePokemon);
		panelPokedex.add(BorderLayout.NORTH, panelFiltro);
		
		JButton accept = new JButton("Accept");
		panelPokedex.add(BorderLayout.SOUTH, accept);
		
		this.getContentPane().add(panelPokedex);
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		accept.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    CreatePokemonTeamWindow cpt = new CreatePokemonTeamWindow();
                    cpt.setVisible(true);
                    dispose();
                }
            });
	}
	
	private void initTables() {
		Vector<String> cabeceraPokemons = new Vector<String>(Arrays.asList( "Selection", "ID","Pokemon","Type 1","Type 2","Attack","Defense","HP","Special attack","Special defense","Speed","Ability 1","Ability 2","Hidden ability"));
		this.modeloDatosPokemon = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraPokemons);
		this.tablaPokemons = new JTable(this.modeloDatosPokemon);
	}
	
	
	private void loadPokemons() {
		//Se borran los datos del modelo de datos
		this.modeloDatosPokemon.setRowCount(0);
		//Se añaden los pokemons uno a uno al modelo de datos
		this.sel = new JCheckBox();
		this.pokemons.forEach(c -> this.modeloDatosPokemon.addRow(
				new Object[] {this.add(sel), c.getId(), c.getPokemon(), c.getType_1(), c.getType_2(), c.getAttack(), c.getDefense(), c.getHp(), c.getSpecial_attack(), c.getSpecial_defense(), c.getSpeed(), c.getAbility_1(), c.getAbility_2(), c.getAbility_hidden()} )
		);
	}
}
