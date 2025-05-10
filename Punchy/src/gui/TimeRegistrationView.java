package gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.IllegalTimeRegistrationException;
import controller.TimeRegistrationController;
import model.Employee;
import model.Project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.DropMode;

public class TimeRegistrationView extends JFrame {
	
	private TimeRegistrationController timeRegistrationController;
	private Project selectedProject;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel contentPanel = new JPanel();
	private final JPanel northPanel = new JPanel();
	private final JPanel centerPanel = new JPanel();
	private final JPanel centerWestPanel = new JPanel();
	private final JPanel centerEastPanel = new JPanel();
	private final JPanel buttonPanel = new JPanel();
	private final JLabel lblProjects = new JLabel("Projekter");
	private final JList<Project> listProjects = new JList();
	private final JButton btnAddSelectedProject = new JButton("Tilføj projekt");
	private final JButton btnSubmit = new JButton("Tilføj");
	private final JButton btnCancel = new JButton("Annuller");
	private final JLabel lblTitle = new JLabel("Ny tidsregistrering");
	private final JLabel lblProjectError = new JLabel("");
	private final JPanel centerCenterPanel = new JPanel();
	private final JLabel lblAssignedProject = new JLabel("Projekt");
	private final JLabel lblDate = new JLabel("Dato");
	private final JLabel lblStartTime = new JLabel("Start tid");
	private final JLabel lblEndTime = new JLabel("Slut tid");
	private final JLabel lblDescription = new JLabel("Beskrivelse");
	private final JTextField txtAssignedProject = new JTextField();
	private final JTextField txtDate = new JTextField();
	private final JTextField txtStartTime = new JTextField();
	private final JTextField txtEndTime = new JTextField();
	private final JTextField txtDescription = new JTextField();
	private final JButton btnClockIn = new JButton("Stempel ind");
	private final JButton btnClockOut = new JButton("Stempel ud");
	private final JButton btnAddDescription = new JButton("Tilføj beskrivelse");
	private final JLabel lblClockInError = new JLabel("");
	private final JLabel lblClockOutError = new JLabel("");
	private final JLabel lblDescriptionError = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeRegistrationView frame = new TimeRegistrationView(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TimeRegistrationView(Employee employee) {
		txtDescription.setColumns(10);
		txtEndTime.setEditable(false);
		txtEndTime.setColumns(10);
		txtStartTime.setEditable(false);
		txtStartTime.setColumns(10);
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		txtAssignedProject.setEditable(false);
		txtAssignedProject.setColumns(10);
		initGUI();
		
		timeRegistrationController = new TimeRegistrationController();
		if (timeRegistrationController.getTimeRegistrationDB().findActiveTimeRegistration(employee) != null) {
			timeRegistrationController.setCurrentTimeRegistration(timeRegistrationController.findActiveTimeRegistration(employee));
			setAssignedProjectText(timeRegistrationController.getCurrentTimeRegistration().getProject());
		}
		else {
			timeRegistrationController.makeNewTimeRegistration();
			timeRegistrationController.assignEmployeeToTimeRegistration(employee);
		}
		displayProjects(timeRegistrationController.findProjectsByEmployee(employee));
		setDateText(timeRegistrationController.getCurrentTimeRegistration().getDate());
		setStartTimeText(timeRegistrationController.getCurrentTimeRegistration().getStartTime());
	}
	private void initGUI() {
		setTitle("Ny tidsregistrering");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		contentPanel.add(northPanel, BorderLayout.NORTH);
		
		northPanel.add(lblTitle);
		
		contentPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		centerWestPanel.setSize(new Dimension(this.getWidth() / 4, 0));
		centerPanel.add(centerWestPanel, BorderLayout.WEST);
		GridBagLayout gbl_centerWestPanel = new GridBagLayout();
		gbl_centerWestPanel.columnWidths = new int[]{0, 0, 0};
		gbl_centerWestPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_centerWestPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_centerWestPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerWestPanel.setLayout(gbl_centerWestPanel);
		
		GridBagConstraints gbc_btnAddSelectedProject = new GridBagConstraints();
		gbc_btnAddSelectedProject.fill = GridBagConstraints.BOTH;
		gbc_btnAddSelectedProject.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddSelectedProject.gridx = 0;
		gbc_btnAddSelectedProject.gridy = 0;
		btnAddSelectedProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSelectedProjectToTimeRegistration();
			}
		});
		centerWestPanel.add(btnAddSelectedProject, gbc_btnAddSelectedProject);
		
		GridBagConstraints gbc_lblProjectError = new GridBagConstraints();
		gbc_lblProjectError.insets = new Insets(0, 0, 5, 0);
		gbc_lblProjectError.gridx = 1;
		gbc_lblProjectError.gridy = 0;
		centerWestPanel.add(lblProjectError, gbc_lblProjectError);
		
		GridBagConstraints gbc_btnClockIn = new GridBagConstraints();
		gbc_btnClockIn.fill = GridBagConstraints.BOTH;
		gbc_btnClockIn.insets = new Insets(0, 0, 5, 5);
		gbc_btnClockIn.gridx = 0;
		gbc_btnClockIn.gridy = 1;
		btnClockIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clockIn();
			}
		});
		centerWestPanel.add(btnClockIn, gbc_btnClockIn);
		
		GridBagConstraints gbc_lblClockInError = new GridBagConstraints();
		gbc_lblClockInError.insets = new Insets(0, 0, 5, 0);
		gbc_lblClockInError.gridx = 1;
		gbc_lblClockInError.gridy = 1;
		centerWestPanel.add(lblClockInError, gbc_lblClockInError);
		
		GridBagConstraints gbc_btnClockOut = new GridBagConstraints();
		gbc_btnClockOut.fill = GridBagConstraints.BOTH;
		gbc_btnClockOut.insets = new Insets(0, 0, 5, 5);
		gbc_btnClockOut.gridx = 0;
		gbc_btnClockOut.gridy = 2;
		btnClockOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clockOut();
			}
		});
		centerWestPanel.add(btnClockOut, gbc_btnClockOut);
		
		GridBagConstraints gbc_lblClockOutError = new GridBagConstraints();
		gbc_lblClockOutError.insets = new Insets(0, 0, 5, 0);
		gbc_lblClockOutError.gridx = 1;
		gbc_lblClockOutError.gridy = 2;
		centerWestPanel.add(lblClockOutError, gbc_lblClockOutError);
		
		GridBagConstraints gbc_btnAddDescription = new GridBagConstraints();
		gbc_btnAddDescription.fill = GridBagConstraints.BOTH;
		gbc_btnAddDescription.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddDescription.gridx = 0;
		gbc_btnAddDescription.gridy = 3;
		btnAddDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDescriptionToTimeRegistration();
			}
		});
		centerWestPanel.add(btnAddDescription, gbc_btnAddDescription);
		
		GridBagConstraints gbc_lblDescriptionError = new GridBagConstraints();
		gbc_lblDescriptionError.gridx = 1;
		gbc_lblDescriptionError.gridy = 3;
		centerWestPanel.add(lblDescriptionError, gbc_lblDescriptionError);
		
		centerEastPanel.setPreferredSize(new Dimension(this.getWidth() / 4, 0));
		centerPanel.add(centerEastPanel, BorderLayout.EAST);
		GridBagLayout gbl_centerEastPanel = new GridBagLayout();
		gbl_centerEastPanel.columnWidths = new int[]{0, 0};
		gbl_centerEastPanel.rowHeights = new int[]{0, 0, 0};
		gbl_centerEastPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_centerEastPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		centerEastPanel.setLayout(gbl_centerEastPanel);
		
		GridBagConstraints gbc_lblProjects = new GridBagConstraints();
		gbc_lblProjects.insets = new Insets(0, 0, 5, 0);
		gbc_lblProjects.gridx = 0;
		gbc_lblProjects.gridy = 0;
		centerEastPanel.add(lblProjects, gbc_lblProjects);
		
		GridBagConstraints gbc_listProjects = new GridBagConstraints();
		gbc_listProjects.fill = GridBagConstraints.BOTH;
		gbc_listProjects.gridx = 0;
		gbc_listProjects.gridy = 1;
		listProjects.setBorder(new EmptyBorder(0, 0, 10, 0));
		listProjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		centerEastPanel.add(listProjects, gbc_listProjects);
		
		listProjects.setCellRenderer(new ProjectListCellRenderer());
		centerCenterPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
		
		centerCenterPanel.setPreferredSize(new Dimension(this.getWidth() / 2, 0));
		centerPanel.add(centerCenterPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerCenterPanel = new GridBagLayout();
		gbl_centerCenterPanel.columnWidths = new int[]{0, 17, 0};
		gbl_centerCenterPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_centerCenterPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_centerCenterPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerCenterPanel.setLayout(gbl_centerCenterPanel);
		
		GridBagConstraints gbc_lblAssignedProject = new GridBagConstraints();
		gbc_lblAssignedProject.anchor = GridBagConstraints.EAST;
		gbc_lblAssignedProject.insets = new Insets(0, 0, 5, 5);
		gbc_lblAssignedProject.gridx = 0;
		gbc_lblAssignedProject.gridy = 0;
		centerCenterPanel.add(lblAssignedProject, gbc_lblAssignedProject);
		
		GridBagConstraints gbc_txtAssignedProject = new GridBagConstraints();
		gbc_txtAssignedProject.insets = new Insets(0, 0, 5, 0);
		gbc_txtAssignedProject.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAssignedProject.gridx = 1;
		gbc_txtAssignedProject.gridy = 0;
		centerCenterPanel.add(txtAssignedProject, gbc_txtAssignedProject);
		
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 1;
		centerCenterPanel.add(lblDate, gbc_lblDate);
		
		GridBagConstraints gbc_txtDate = new GridBagConstraints();
		gbc_txtDate.insets = new Insets(0, 0, 5, 0);
		gbc_txtDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDate.gridx = 1;
		gbc_txtDate.gridy = 1;
		centerCenterPanel.add(txtDate, gbc_txtDate);
		
		GridBagConstraints gbc_lblStartTime = new GridBagConstraints();
		gbc_lblStartTime.anchor = GridBagConstraints.EAST;
		gbc_lblStartTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartTime.gridx = 0;
		gbc_lblStartTime.gridy = 2;
		centerCenterPanel.add(lblStartTime, gbc_lblStartTime);
		
		GridBagConstraints gbc_txtStartTime = new GridBagConstraints();
		gbc_txtStartTime.insets = new Insets(0, 0, 5, 0);
		gbc_txtStartTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStartTime.gridx = 1;
		gbc_txtStartTime.gridy = 2;
		centerCenterPanel.add(txtStartTime, gbc_txtStartTime);
		
		GridBagConstraints gbc_lblEndTime = new GridBagConstraints();
		gbc_lblEndTime.anchor = GridBagConstraints.EAST;
		gbc_lblEndTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndTime.gridx = 0;
		gbc_lblEndTime.gridy = 3;
		centerCenterPanel.add(lblEndTime, gbc_lblEndTime);
		
		GridBagConstraints gbc_txtEndTime = new GridBagConstraints();
		gbc_txtEndTime.insets = new Insets(0, 0, 5, 0);
		gbc_txtEndTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEndTime.gridx = 1;
		gbc_txtEndTime.gridy = 3;
		centerCenterPanel.add(txtEndTime, gbc_txtEndTime);
		
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 4;
		centerCenterPanel.add(lblDescription, gbc_lblDescription);
		
		GridBagConstraints gbc_txtDescription = new GridBagConstraints();
		gbc_txtDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescription.gridx = 1;
		gbc_txtDescription.gridy = 4;
		centerCenterPanel.add(txtDescription, gbc_txtDescription);
		listProjects.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		        Project selectedProject = listProjects.getSelectedValue();
		        if (selectedProject != null) {
		            this.selectedProject = selectedProject;
		        }
		    }
		});
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitTimeRegistration();
			}
		});
		
		buttonPanel.add(btnSubmit);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelTimeRegistrationView();
			}
		});
		
		buttonPanel.add(btnCancel);
	}
	
	private void displayProjects(List<Project> projects) {
		DefaultListModel<Project> model = new DefaultListModel<>();
		for (Project project : projects) {
			model.addElement(project);
		}
		listProjects.setModel(model);
	}
	
	private void addSelectedProjectToTimeRegistration() {
		if (selectedProject != null) {
			timeRegistrationController.assignProjectToTimeRegistration(selectedProject);
			setAssignedProjectText(timeRegistrationController.getCurrentTimeRegistration().getProject());
			lblProjectError.setText("");
		}
		else {
			lblProjectError.setText("Du skal vælge et projekt at tilføje");
		}
	}
	
	private void addDescriptionToTimeRegistration() {
		timeRegistrationController.setDescription(txtDescription.getText());
	}
	
	private void setAssignedProjectText(Project project) {
		if (project != null) {
			txtAssignedProject.setText(project.getProjectName());
			btnAddSelectedProject.setText("Skift projekt");
		}
	}
	
	private void setDateText(LocalDate date) {
		if (date != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formattedDate = date.format(formatter);
			txtDate.setText(formattedDate);
		}
	}
	
	private void setStartTimeText(LocalDateTime startTime) {
		if (startTime != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String formattedStartTime = startTime.format(formatter);
			txtStartTime.setText(formattedStartTime);
		}
	}
	
	private void setEndTimeText(LocalDateTime endTime) {
		if (endTime != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String formattedEndTime = endTime.format(formatter);
			txtEndTime.setText(formattedEndTime);
		}
	}
	
	private void clockIn() {
		if (timeRegistrationController.getCurrentTimeRegistration().getStartTime() == null) {
			timeRegistrationController.clockIn();
			setStartTimeText(timeRegistrationController.getCurrentTimeRegistration().getStartTime());
			lblClockInError.setText("");
		}
		else {
			lblClockInError.setText("Du har allerede stemplet ind");
		}
	}
	
	private void clockOut() {
		// FLYT CLOCKOUT LOGIK FRA GUI TIL CONTROLLER KLASSE
		lblClockOutError.setText("");
		try {
			timeRegistrationController.clockOut();
			setEndTimeText(timeRegistrationController.getCurrentTimeRegistration().getEndTime());
		} catch (IllegalTimeRegistrationException e) {
			lblClockOutError.setText(e.getMessage());
		}
		
		
//				lblClockOutError.setText("Du skal stemple ud først");
//			} else if (timeRegistrationController.getCurrentTimeRegistration().getEndTime() != null) {
//				lblClockOutError.setText("Du har allerede stemplet ud");
//			} else {
//				
//				setEndTimeText(timeRegistrationController.getCurrentTimeRegistration().getEndTime());
//				
//			}
			
			
			
	}
	
	private void submitTimeRegistration() {
		timeRegistrationController.submitRegistration(timeRegistrationController.getCurrentTimeRegistration());
	}
	
	private void cancelTimeRegistrationView() {
		hideFrame();
	}

	private void hideFrame() {
		this.setVisible(false);
		this.dispose();
	}

}
