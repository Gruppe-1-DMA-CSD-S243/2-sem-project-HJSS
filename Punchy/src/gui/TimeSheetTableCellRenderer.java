package gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import model.TimeRegistration;

public class TimeSheetTableCellRenderer implements TableCellRenderer {
	
	private static final TableCellRenderer RENDERER = new DefaultTableCellRenderer();
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		if (value instanceof TimeRegistration) {
			
			if (((TimeRegistration) value).getStartTime().getHour() == row) {
				value = ((TimeRegistration) value).getProject().getProjectName();
			}
			else if (((TimeRegistration) value).getStartTime().getHour() == row - 1) {
				value = ((TimeRegistration) value).getDescription();
			}
			else {
				value = "";
			}
		}
		
		Component c = RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (column > 0) {
			Object result = table.getModel().getValueAt(row, column);
			Color color = null;
			
			if (result instanceof TimeRegistration) {
				if (column % 2 == 0) {
					color = Color.decode("#5789FF");
				}
				else {
					color = Color.decode("#CD57FF");
				}
				//color = Color.decode("#7957ff");
				
			}
			
			c.setBackground(color);
		}
		
		return c;
	}

}
