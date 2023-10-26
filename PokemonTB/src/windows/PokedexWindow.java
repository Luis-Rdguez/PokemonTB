package windows;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import classes.Pokemon;


public class PokedexWindow {
	
	private List<Pokemon> pokemons;
	
	private JTable tablaPokemons;
	private DefaultTableModel modeloDatosPokemon;
	private JTextField txtFiltro;
	
	public void JFramePokemons(List<Pokemon> pokemons) {
		
		this.initTables();
		
		JScrollPane scrollPaneComics = new JScrollPane(this.tablaPokemons);
		scrollPaneComics.setBorder(new TitledBorder("Comics"));
		this.tablaPokemons.setFillsViewportHeight(true);
		
		this.txtFiltro = new JTextField(20);	
		
		JPanel panelFiltro = new JPanel();
		panelFiltro.add(new JLabel("Filtro por título: "));
		panelFiltro.add(txtFiltro);
		
		
		
	}
	
	private void initTables() {
		
	}
}
