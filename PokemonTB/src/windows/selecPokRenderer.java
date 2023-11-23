package windows;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import classes.Pokemon;

public class selecPokRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
	private static final long serialVersionUID = 1L;
	
	private Pokemon pokemon;
	private PokedexWindow pokedex;
	
	public selecPokRenderer(PokedexWindow pokedex, ) {
		this.pokedex = pokedex;
	}
	
	private JButton select(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		JButton button = new JButton("Select");
		button.setEnabled(true);				
		button.setBackground(table.getBackground());
		
		if (isSelected || hasFocus) {
			button.setBackground(table.getSelectionBackground());
		}
		
		button.addActionListener(e -> {
			
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
