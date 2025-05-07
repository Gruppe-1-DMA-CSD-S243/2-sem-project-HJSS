package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TimeRegistrationController;
import model.Employee;
import model.Project;
import model.TimeRegistration;

import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {
	
	private TimeRegistrationController timeRegistrationController;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNorth;
	private JButton btnLogin;
	private JTextField tfEmployeeNumber;
	private JButton btnAbsence;
	private JButton btnDriving;
	private JButton btnReceipt;
	private JButton btnReport;
	private JTable table;
	private JList<Project> listProjects;
	private JPanel centerPanel;
	private JPanel eastPanel;
	private JPanel westPanel;
	private JPanel southPanel;
	private JPanel northPanel;
	private JLabel lblEast2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		initGUI();
		timeRegistrationController = new TimeRegistrationController();
	}
	private void initGUI() {
		setTitle("ECONTA CONCULTING GROUP - PUNCHY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		lblNorth = new JLabel("Velkommen Til Hovedmenuen");
		northPanel.add(lblNorth);
		
		southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		GridBagLayout gbl_westPanel = new GridBagLayout();
		gbl_westPanel.columnWidths = new int[]{89, 0};
		gbl_westPanel.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0};
		gbl_westPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_westPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		westPanel.setLayout(gbl_westPanel);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginToTimeRegistration();
			}
		});
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogin.anchor = GridBagConstraints.NORTH;
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 0;
		westPanel.add(btnLogin, gbc_btnLogin);
		
		tfEmployeeNumber = new JTextField();
		GridBagConstraints gbc_tfEmployeeNumber = new GridBagConstraints();
		gbc_tfEmployeeNumber.insets = new Insets(0, 0, 5, 0);
		gbc_tfEmployeeNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEmployeeNumber.gridx = 0;
		gbc_tfEmployeeNumber.gridy = 1;
		westPanel.add(tfEmployeeNumber, gbc_tfEmployeeNumber);
		tfEmployeeNumber.setColumns(10);
		
		btnAbsence = new JButton("Registrer fravær");
		btnAbsence.setEnabled(false);
		GridBagConstraints gbc_btnAbsence = new GridBagConstraints();
		gbc_btnAbsence.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAbsence.insets = new Insets(0, 0, 5, 0);
		gbc_btnAbsence.gridx = 0;
		gbc_btnAbsence.gridy = 2;
		westPanel.add(btnAbsence, gbc_btnAbsence);
		
		btnDriving = new JButton("Registrer kørsel");
		btnDriving.setEnabled(false);
		GridBagConstraints gbc_btnDriving = new GridBagConstraints();
		gbc_btnDriving.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDriving.insets = new Insets(0, 0, 5, 0);
		gbc_btnDriving.gridx = 0;
		gbc_btnDriving.gridy = 3;
		westPanel.add(btnDriving, gbc_btnDriving);
		
		btnReceipt = new JButton("Registrer kvittering");
		btnReceipt.setEnabled(false);
		GridBagConstraints gbc_btnReceipt = new GridBagConstraints();
		gbc_btnReceipt.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReceipt.insets = new Insets(0, 0, 5, 0);
		gbc_btnReceipt.gridx = 0;
		gbc_btnReceipt.gridy = 4;
		westPanel.add(btnReceipt, gbc_btnReceipt);
		
		btnReport = new JButton("Indberet registrering");
		btnReport.setEnabled(false);
		GridBagConstraints gbc_btnReport = new GridBagConstraints();
		gbc_btnReport.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReport.insets = new Insets(0, 0, 5, 0);
		gbc_btnReport.gridx = 0;
		gbc_btnReport.gridy = 5;
		westPanel.add(btnReport, gbc_btnReport);
		
		eastPanel = new JPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
		GridBagLayout gbl_eastPanel = new GridBagLayout();
		gbl_eastPanel.columnWidths = new int[]{70, 0};
		gbl_eastPanel.rowHeights = new int[]{14, 0, 0, 0};
		gbl_eastPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_eastPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		eastPanel.setLayout(gbl_eastPanel);
		
		JLabel lblEast = new JLabel("Vælg projekt du");
		GridBagConstraints gbc_lblEast = new GridBagConstraints();
		gbc_lblEast.insets = new Insets(0, 0, 5, 0);
		gbc_lblEast.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblEast.gridx = 0;
		gbc_lblEast.gridy = 0;
		eastPanel.add(lblEast, gbc_lblEast);
		
		lblEast2 = new JLabel("ønsker at tidsregistrere:");
		GridBagConstraints gbc_lblEast2 = new GridBagConstraints();
		gbc_lblEast2.insets = new Insets(0, 0, 5, 0);
		gbc_lblEast2.gridx = 0;
		gbc_lblEast2.gridy = 1;
		eastPanel.add(lblEast2, gbc_lblEast2);
		
		listProjects = new JList<>();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 2;
		eastPanel.add(listProjects, gbc_list);
		
		listProjects.setCellRenderer(new ProjectListCellRenderer());

		
		listProjects.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		        Project selectedProject = listProjects.getSelectedValue();
		        if (selectedProject != null) {
		            TimeRegistrationView registrationView = new TimeRegistrationView(selectedProject);
		            // Optionally, pass the selectedProject to TimeRegistrationView if needed
		            registrationView.setModal(true);  // Makes the dialog block until dismissed
		            registrationView.setVisible(true);
		        }
		    }
		});

		
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		centerPanel.add(scrollPane);
		
		table = new JTable();
		centerPanel.add(table);
	}
	
	private void loginToTimeRegistration() {
		String employeeNumber = tfEmployeeNumber.getText();
		
		try {
			getTimeRegistrationController().makeNewTimeRegistration();
			Employee currentEmployee = getTimeRegistrationController().findEmployee(employeeNumber);
			if (currentEmployee == null) {
			    System.out.println("No employee found with number: " + employeeNumber);
			    return;
			}
			getTimeRegistrationController().assignEmployeeToTimeRegistration(currentEmployee);
			if (getTimeRegistrationController() == null) {
			    System.out.println("TimeRegistrationController is not initialized.");
			    return;
			}

			
			System.out.println(currentEmployee);
		} catch(Exception e) {
			System.out.println("Fejl");
		}
	}

	public void showProjects(List<Project> projects) {
        DefaultListModel<Project> model = new DefaultListModel<>();
        for (Project project : projects) {
            model.addElement(project); // assuming Project has getProjectName()
        }
        listProjects.setModel(model);
    }
	
	public TimeRegistrationController getTimeRegistrationController() {
		return this.timeRegistrationController;
	}


}
