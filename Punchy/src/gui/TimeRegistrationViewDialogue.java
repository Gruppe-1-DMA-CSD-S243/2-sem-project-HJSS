package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.border.MatteBorder;

import controller.TimeRegistrationController;
import model.Employee;
import model.Project;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class TimeRegistrationViewDialogue extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfAutomaticDescription;
	private JTextField tfAutomaticStartTime;
	private JTextField tfAutomaticEndTime;
	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel centerNorthPanel;
	private JPanel centerWestPanel;
	private JPanel centerEastPanel;
	private JPanel buttonPane;
	private JList<Project> listProjects;
	
	private TimeRegistrationController timeRegistrationController;
	private JLabel lblProjects;
	private JButton btnAddProject;
	
	private Project selectedProject;
	private JLabel lblProjectName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TimeRegistrationViewDialogue dialog = new TimeRegistrationViewDialogue(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public TimeRegistrationViewDialogue(Employee employee) {
		initGUI();
		timeRegistrationController = new TimeRegistrationController();
		timeRegistrationController.makeNewTimeRegistration();
		timeRegistrationController.assignEmployeeToTimeRegistration(employee);
		displayProjects(timeRegistrationController.findProjectsByEmployee(employee));
		setStartTimeText(timeRegistrationController.getCurrentTimeRegistration().getStartTime());
	}

	
	public void initGUI() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			northPanel = new JPanel();
			northPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			contentPanel.add(northPanel, BorderLayout.NORTH);
			{
				lblProjectName = new JLabel("Project X");
				northPanel.add(lblProjectName);
			}
		}
		{
			centerPanel = new JPanel();
			contentPanel.add(centerPanel, BorderLayout.CENTER);
			centerPanel.setLayout(new BorderLayout(0, 0));
			{
				centerNorthPanel = new JPanel();
				centerNorthPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
				{
					JLabel lblChoice = new JLabel("Vælg mellem automatisk eller manuel tidsregistrering");
					centerNorthPanel.add(lblChoice);
				}
			}
			{
				centerWestPanel = new JPanel();
				centerWestPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				centerPanel.add(centerWestPanel, BorderLayout.WEST);
				GridBagLayout gbl_centerWestPanel = new GridBagLayout();
				gbl_centerWestPanel.columnWidths = new int[]{210, 0};
				gbl_centerWestPanel.rowHeights = new int[]{14, 0, 0, 0, 0, 0, 0};
				gbl_centerWestPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_centerWestPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				centerWestPanel.setLayout(gbl_centerWestPanel);
				{
					JLabel lblAutomatic = new JLabel("Automatisk Tidsregistrering:");
					GridBagConstraints gbc_lblAutomatic = new GridBagConstraints();
					gbc_lblAutomatic.insets = new Insets(0, 0, 5, 0);
					gbc_lblAutomatic.anchor = GridBagConstraints.NORTH;
					gbc_lblAutomatic.gridx = 0;
					gbc_lblAutomatic.gridy = 0;
					centerWestPanel.add(lblAutomatic, gbc_lblAutomatic);
				}
				{
					btnAddProject = new JButton("Tilføj Projekt");
					btnAddProject.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							addProjectToTimeRegistration();
						}
					});
					GridBagConstraints gbc_btnAddProject = new GridBagConstraints();
					gbc_btnAddProject.insets = new Insets(0, 0, 5, 0);
					gbc_btnAddProject.gridx = 0;
					gbc_btnAddProject.gridy = 1;
					centerWestPanel.add(btnAddProject, gbc_btnAddProject);
					btnAddProject.setPreferredSize(new Dimension(144, 25));
				}
				{
					tfAutomaticStartTime = new JTextField();
					tfAutomaticStartTime.setEditable(false);
					tfAutomaticStartTime.setText("Starttid");
					GridBagConstraints gbc_tfAutomaticStartTime = new GridBagConstraints();
					gbc_tfAutomaticStartTime.insets = new Insets(0, 0, 5, 0);
					gbc_tfAutomaticStartTime.gridx = 0;
					gbc_tfAutomaticStartTime.gridy = 2;
					centerWestPanel.add(tfAutomaticStartTime, gbc_tfAutomaticStartTime);
					tfAutomaticStartTime.setColumns(15);
				}
				{
					tfAutomaticEndTime = new JTextField();
					tfAutomaticEndTime.setText("Sluttid");
					tfAutomaticEndTime.setEditable(false);
					GridBagConstraints gbc_tfAutomaticEndTime = new GridBagConstraints();
					gbc_tfAutomaticEndTime.insets = new Insets(0, 0, 5, 0);
					gbc_tfAutomaticEndTime.gridx = 0;
					gbc_tfAutomaticEndTime.gridy = 3;
					centerWestPanel.add(tfAutomaticEndTime, gbc_tfAutomaticEndTime);
					tfAutomaticEndTime.setColumns(15);
				}
				{
					tfAutomaticDescription = new JTextField();
					tfAutomaticDescription.setText("Indtast Beskrivelse");
					GridBagConstraints gbc_tfAutomaticDescription = new GridBagConstraints();
					gbc_tfAutomaticDescription.insets = new Insets(0, 0, 5, 0);
					gbc_tfAutomaticDescription.gridx = 0;
					gbc_tfAutomaticDescription.gridy = 4;
					centerWestPanel.add(tfAutomaticDescription, gbc_tfAutomaticDescription);
					tfAutomaticDescription.setColumns(15);
					
					// Add FocusListener to clear text on focus
					tfAutomaticDescription.addFocusListener(new FocusAdapter() {
					    @Override
					    public void focusGained(FocusEvent e) {
					        if (tfAutomaticDescription.getText().equals("Indtast Beskrivelse")) {
					            tfAutomaticDescription.setText("");
					        }
					    }

					    @Override
					    public void focusLost(FocusEvent e) {
					        if (tfAutomaticDescription.getText().isEmpty()) {
					            tfAutomaticDescription.setText("Indtast Beskrivelse");
					        }
					    }
					});
				}
				{
					JButton btnStartEndRegistration = new JButton("Stempel Ind");
					btnStartEndRegistration.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							clockInOrClockOut();
						}
					});
					GridBagConstraints gbc_btnStartEndRegistration = new GridBagConstraints();
					gbc_btnStartEndRegistration.gridx = 0;
					gbc_btnStartEndRegistration.gridy = 5;
					centerWestPanel.add(btnStartEndRegistration, gbc_btnStartEndRegistration);
					btnStartEndRegistration.setPreferredSize(new Dimension(160, 25));
				}
			}
			{
				centerEastPanel = new JPanel();
				centerEastPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				centerPanel.add(centerEastPanel, BorderLayout.EAST);
				GridBagLayout gbl_centerEastPanel = new GridBagLayout();
				gbl_centerEastPanel.columnWidths = new int[] {210, 0};
				gbl_centerEastPanel.rowHeights = new int[]{14, 0, 0, 0, 0, 0, 0};
				gbl_centerEastPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_centerEastPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				centerEastPanel.setLayout(gbl_centerEastPanel);
				{
					lblProjects = new JLabel("All projects");
					GridBagConstraints gbc_lblProjects = new GridBagConstraints();
					gbc_lblProjects.insets = new Insets(0, 0, 5, 0);
					gbc_lblProjects.gridx = 0;
					gbc_lblProjects.gridy = 1;
					centerEastPanel.add(lblProjects, gbc_lblProjects);
				}
				{
					listProjects = new JList();
					listProjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					GridBagConstraints gbc_listProjects = new GridBagConstraints();
					gbc_listProjects.insets = new Insets(0, 0, 5, 0);
					gbc_listProjects.fill = GridBagConstraints.BOTH;
					gbc_listProjects.gridx = 0;
					gbc_listProjects.gridy = 2;
					centerEastPanel.add(listProjects, gbc_listProjects);
					
					listProjects.setCellRenderer(new ProjectListCellRenderer());
					listProjects.addListSelectionListener(e -> {
					    if (!e.getValueIsAdjusting()) {
					        Project selectedProject = listProjects.getSelectedValue();
					        if (selectedProject != null) {
					            this.selectedProject = selectedProject;
					        }
					    }
					});
				}
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelTimeRegistrationView();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void displayProjects(List<Project> projects) {
		DefaultListModel<Project> model = new DefaultListModel<>();
		for (Project project : projects) {
			model.addElement(project);
		}
		listProjects.setModel(model);
	}
	
	private TimeRegistrationController getTimeRegistrationController() {
		return this.timeRegistrationController;
	}
	
	private void addProjectToTimeRegistration() {
		if (selectedProject != null) {
			timeRegistrationController.assignProjectToTimeRegistration(selectedProject);
			lblProjectName.setText(timeRegistrationController.getCurrentTimeRegistration().getProject().getProjectName());
		}
	}
	
	private void clockInOrClockOut() {
		if (timeRegistrationController.getCurrentTimeRegistration().getStartTime() == null) {
			timeRegistrationController.clockIn();
			setStartTimeText(timeRegistrationController.getCurrentTimeRegistration().getStartTime());
		}
		else if (timeRegistrationController.getCurrentTimeRegistration().getStartTime() != null && 
				timeRegistrationController.getCurrentTimeRegistration().getEndTime() == null) {
			timeRegistrationController.clockOut();
		}
		else {
			System.out.println("Error!");
		}
	}
	
	private void setStartTimeText(LocalDateTime startTime) {
		if (startTime != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String formattedDate = startTime.format(formatter);
			tfAutomaticStartTime.setText(formattedDate);
		}
	}
	
	private void setEndTimeText(LocalDateTime endTime) {
		if (endTime != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String formattedDate = endTime.format(formatter);
			tfAutomaticEndTime.setText(formattedDate);
		}
	}
	
	private void cancelTimeRegistrationView() {
		hideFrame();
	}

	private void hideFrame() {
		this.setVisible(false);
		this.dispose();
	}
	
	

}
