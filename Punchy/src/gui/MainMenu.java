package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.EventListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import controller.LoginController;
import controller.TimeRegistrationController;
import controller.TimeSheetController;
import model.Employee;
import model.TimeRegistration;
import model.TimeSheet;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {
	
	private TimeSheetController timeSheetController;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel northPanel = new JPanel();
	private final JPanel centerPanel = new JPanel();
	private final JPanel centerNorthPanel = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel centerWestPanel = new JPanel();
	private final JTable tblTimeSheet = new JTable();
	private TimeSheetTableModel tblModel;
	private final JLabel lblTitle = new JLabel("Hovedmenu");
	private final JButton btnNewButton = new JButton("New button");
	private final JButton btnNewTimeRegistration = new JButton("Ny tidsregistrering");
	private final JButton btnPlaceHolder1 = new JButton("Placeholder1");
	private final JButton btnPlaceHolder2 = new JButton("Placeholder2");
	private final JButton btnPlaceHolder3 = new JButton("Placeholder3");
	private final JButton btnPlaceHolder4 = new JButton("Placeholder4");
	
	SwingWorker<Boolean, Integer> displayTimeSheetWorker = new SwingWorker<>() {

		@Override
		protected Boolean doInBackground() throws Exception {
			TimeSheet ts = timeSheetController.findTimeSheetByEmployeeAndDate(LoginController.getInstance().getLoggedInEmployee(), LocalDate.now(), true);
			displayTimeSheet(ts);
			
			return true;
		}
	};

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
		
		timeSheetController = new TimeSheetController();
		displayTimeSheetWorker.execute();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		setBounds(screenWidth / 4, screenHeight / 4, screenWidth / 2, screenHeight / 2);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		try {
			Image logo = ImageIO.read(getClass().getResourceAsStream("/Punchy.png"));
			Image resizedImage = logo.getScaledInstance(980 / 8, 287 / 8, Image.SCALE_SMOOTH);
			ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
			lblTitle.setIcon(resizedImageIcon);
			lblTitle.setText("");
		} catch (IOException e) {
			
		}
		
		northPanel.add(lblTitle);
		
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
		
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		tblTimeSheet.setModel(new DefaultTableModel(
			new Object[][] {
				{"0:00", null, null, null, null, null, null, null},
				{"1:00", null, null, null, null, null, null, null},
				{"2:00", null, null, null, null, null, null, null},
				{"3:00", null, null, null, null, null, null, null},
				{"4:00", null, null, null, null, null, null, null},
				{"5:00", null, null, null, null, null, null, null},
				{"6:00", null, null, null, null, null, null, null},
				{"7:00", null, null, null, null, null, null, null},
				{"8:00", null, null, null, null, null, null, null},
				{"9:00", null, null, null, null, null, null, null},
				{"10:00", null, null, null, null, null, null, null},
				{"11:00", null, null, null, null, null, null, null},
				{"12:00", null, null, null, null, null, null, null},
				{"13:00", null, null, null, null, null, null, null},
				{"14:00", null, null, null, null, null, null, null},
				{"15:00", null, null, null, null, null, null, null},
				{"16:00", null, null, null, null, null, null, null},
				{"17:00", null, null, null, null, null, null, null},
				{"18:00", null, null, null, null, null, null, null},
				{"19:00", null, null, null, null, null, null, null},
				{"20:00", null, null, null, null, null, null, null},
				{"21:00", null, null, null, null, null, null, null},
				{"22:00", null, null, null, null, null, null, null},
				{"23:00", null, null, null, null, null, null, null},
			},
			new String[] {
				"", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag", "Søndag"
			}
		));
		tblTimeSheet.setShowVerticalLines(true);
		tblTimeSheet.setShowHorizontalLines(false);
		tblTimeSheet.setRowSelectionAllowed(false);
		tblTimeSheet.setCellSelectionEnabled(true);
		tblTimeSheet.setIntercellSpacing(new Dimension(5, 0));
		//TODO: fix cellRenderer
		TimeSheetTableCellRenderer cellRenderer = new TimeSheetTableCellRenderer();
		tblTimeSheet.setDefaultRenderer(Object.class, cellRenderer);
		
		scrollPane.setViewportView(tblTimeSheet);
		centerWestPanel.setBorder(new EmptyBorder(0, 0, 0, 5));
		
		centerPanel.add(centerWestPanel, BorderLayout.WEST);
		GridBagLayout gbl_centerWestPanel = new GridBagLayout();
		gbl_centerWestPanel.columnWidths = new int[]{0, 0};
		gbl_centerWestPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_centerWestPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_centerWestPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerWestPanel.setLayout(gbl_centerWestPanel);
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		btnNewButton.setEnabled(false);
		centerWestPanel.add(btnNewButton, gbc_btnNewButton);
		
		GridBagConstraints gbc_btnNewTimeRegistration = new GridBagConstraints();
		gbc_btnNewTimeRegistration.fill = GridBagConstraints.BOTH;
		gbc_btnNewTimeRegistration.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewTimeRegistration.gridx = 0;
		gbc_btnNewTimeRegistration.gridy = 1;
		btnNewTimeRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchTimeRegistrationView();
			}
		});
		centerWestPanel.add(btnNewTimeRegistration, gbc_btnNewTimeRegistration);
		
		GridBagConstraints gbc_btnPlaceHolder1 = new GridBagConstraints();
		gbc_btnPlaceHolder1.fill = GridBagConstraints.BOTH;
		gbc_btnPlaceHolder1.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlaceHolder1.gridx = 0;
		gbc_btnPlaceHolder1.gridy = 2;
		centerWestPanel.add(btnPlaceHolder1, gbc_btnPlaceHolder1);
		
		GridBagConstraints gbc_btnPlaceHolder2 = new GridBagConstraints();
		gbc_btnPlaceHolder2.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlaceHolder2.fill = GridBagConstraints.BOTH;
		gbc_btnPlaceHolder2.gridx = 0;
		gbc_btnPlaceHolder2.gridy = 3;
		centerWestPanel.add(btnPlaceHolder2, gbc_btnPlaceHolder2);
		
		GridBagConstraints gbc_btnPlaceHolder3 = new GridBagConstraints();
		gbc_btnPlaceHolder3.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlaceHolder3.fill = GridBagConstraints.BOTH;
		gbc_btnPlaceHolder3.gridx = 0;
		gbc_btnPlaceHolder3.gridy = 4;
		centerWestPanel.add(btnPlaceHolder3, gbc_btnPlaceHolder3);
		
		GridBagConstraints gbc_btnPlaceHolder4 = new GridBagConstraints();
		gbc_btnPlaceHolder4.fill = GridBagConstraints.BOTH;
		gbc_btnPlaceHolder4.gridx = 0;
		gbc_btnPlaceHolder4.gridy = 5;
		centerWestPanel.add(btnPlaceHolder4, gbc_btnPlaceHolder4);
	}
	
	private void displayTimeSheet(TimeSheet timeSheet) {
		tblModel = new TimeSheetTableModel(timeSheet);
		tblTimeSheet.setModel(tblModel);
	}
	
	private void launchTimeRegistrationView() {
		TimeRegistrationView timeRegistrationView = new TimeRegistrationView();
		timeRegistrationView.setBounds(getBounds());
		timeRegistrationView.setVisible(true);
		hideFrame();
	}
	
	private void hideFrame() {
		this.setVisible(false);
		this.dispose();
	}

}
