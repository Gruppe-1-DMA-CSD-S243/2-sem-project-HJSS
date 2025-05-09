package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import model.Project;

public class ProjectListCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;
	private DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
	
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Project currentProject = (Project) value;
		String representation = "";
		if(currentProject != null) {
			representation = String.format(currentProject.getProjectName());
		}
		JLabel renderer = (JLabel) dlcr.getListCellRendererComponent(list, representation, index, isSelected, cellHasFocus);
		return renderer;
	}

}
