package gui;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import model.TimeRegistration;

public class TimeSheetTableCellRenderer implements TableCellRenderer {
	
	private static final TableCellRenderer RENDERER = new DefaultTableCellRenderer();
	private List<TimeRegistration> registrations;
	private static final int[] columnNumbers = {
			0, 1, 2, 3, 4, 5, 6, 7, 8
	};
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		Component c = RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (column > 0) {
			int dayOfWeek = columnNumbers[column];
			for (TimeRegistration registration : registrations) {
				if (registration.getDate().getDayOfWeek().getValue() == dayOfWeek && 
						row >= registration.getStartTime().getHour() && 
						row < registration.getEndTime().getHour()) {
					c.setBackground(Color.GREEN);
				}
			}
		}
		
		return c;
	}

}
