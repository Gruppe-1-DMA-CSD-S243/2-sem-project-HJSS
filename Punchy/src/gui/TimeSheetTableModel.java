package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.TimeRegistration;
import model.TimeSheet;

public class TimeSheetTableModel extends AbstractTableModel {
	
	private Object[][] data;
	private List<TimeRegistration> registrations;
	private static final String[] COL_NAMES = {
			"", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag", "Søndag"
	};
	
	public TimeSheetTableModel(TimeSheet timeSheet) {
		data = new Object[24][8];
		registrations = timeSheet.getTimeRegistrations();
		
		for (int i = 0; i < 24; i++) {
			data[i][0] = "" + i + ":00";
		}
		
		for (int row = 0; row < 24; row++) {
			for (int col = 1; col < 8; col++) {
				data[row][col] = null;
			}
		}
		
		fillTimeRegistrations();
	}
	
	private void fillTimeRegistrations() {
		for (TimeRegistration registration : registrations) {
			int dayOfWeek = registration.getDate().getDayOfWeek().getValue();
			int startHour = registration.getStartTime().getHour();
			int endHour = registration.getEndTime().getHour();
			
			for (int i = startHour; i < endHour; i++) {
				data[i][dayOfWeek] = registration;
			}
		}
	}
	
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}
	
	public String getColumnName(int col) {
		return COL_NAMES[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	public void setData(TimeSheet timeSheet) {
		super.fireTableDataChanged();
	}

}
