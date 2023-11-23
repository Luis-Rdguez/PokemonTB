package windows;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import classes.Pokemon;
import classes.PokemonTeam;

public class selecPokRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
	private static final long serialVersionUID = 1L;
	
	private Pokemon pokemon;
	private PokedexWindow pokedex;
	private PokemonTeam team;
	private int pos;
	
	public selecPokRenderer(PokedexWindow pokedex, PokemonTeam team, int pos) {
		this.pokedex = pokedex;
		this.team = team;
		this.pos = pos;
	}
	
	private JButton select(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		JButton button = new JButton("Select");
		button.setEnabled(true);				
		button.setBackground(table.getBackground());
		
		if (isSelected || hasFocus) {
			button.setBackground(table.getSelectionBackground());
		}
		
		button.addActionListener(e -> {
			switch (this.pos) {
			case 1: {this.team.setP1(this.pokemon);}
			case 2: {this.team.setP2(this.pokemon);}
			case 3: {this.team.setP3(this.pokemon);}
			case 4: {this.team.setP4(this.pokemon);}
			case 5: {this.team.setP5(this.pokemon);}
			case 6: {this.team.setP6(this.pokemon);}
			default:
				throw new IllegalArgumentException("Unexpected value: " + this.pos);
			}
		});
		
		button.setOpaque(true);
		
		return button;
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return select(table, value, isSelected, false, row, column);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return select(table, value, isSelected, hasFocus, row, column);		
	}
	
	@Override
	public Object getCellEditorValue() {
		return pokemon;
	}
	
	@Override
    public boolean shouldSelectCell(EventObject event) {
        return true;
    }
	
}
