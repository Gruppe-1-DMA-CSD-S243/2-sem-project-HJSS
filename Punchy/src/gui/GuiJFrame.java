package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Employee;
import model.Project;

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

public class GuiJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNorth;
	private JButton btnLogin;
	private JTextField textField;
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
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiJFrame frame = new GuiJFrame();
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
	public GuiJFrame() {
		initGUI();
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
		btnLogin.setEnabled(false);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogin.anchor = GridBagConstraints.NORTH;
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 0;
		westPanel.add(btnLogin, gbc_btnLogin);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		westPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		
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
		gbl_eastPanel.rowHeights = new int[]{14, 0, 0};
		gbl_eastPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_eastPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		eastPanel.setLayout(gbl_eastPanel);
		
		JLabel lblEast = new JLabel("Dine Projekter");
		GridBagConstraints gbc_lblEast = new GridBagConstraints();
		gbc_lblEast.insets = new Insets(0, 0, 5, 0);
		gbc_lblEast.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblEast.gridx = 0;
		gbc_lblEast.gridy = 0;
		eastPanel.add(lblEast, gbc_lblEast);
		
		listProjects = new JList<>();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		eastPanel.add(listProjects, gbc_list);
		
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		centerPanel.add(scrollPane);
		
		table = new JTable();
		centerPanel.add(table);
	}
	
	


}
