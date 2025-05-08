package gui;

import javax.swing.table.AbstractTableModel;

import model.TimeRegistration;
import model.TimeSheet;

public class TimeSheetTableModel extends AbstractTableModel {
	
	private TimeRegistration[][] registrations;
	private static final String[] COL_NAMES = {
			"", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag", "Søndag"
	};
	
	public TimeSheetTableModel(TimeSheet timeSheet) {
		
	}
	
	@Override
	public int getRowCount() {
		return registrations[0].length;
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setData(TimeSheet timeSheet) {
		super.fireTableDataChanged();
	}

}
