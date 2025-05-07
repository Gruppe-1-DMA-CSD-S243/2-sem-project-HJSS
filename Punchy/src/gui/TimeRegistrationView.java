package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.border.MatteBorder;

public class TimeRegistrationView extends JDialog {

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
	private JTextField tfManualStartTime;
	private JTextField tfManualEndTime;
	private JTextField tfManualDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TimeRegistrationView dialog = new TimeRegistrationView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TimeRegistrationView() {
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
				JLabel lblProjectName = new JLabel("Project X");
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
					tfAutomaticStartTime = new JTextField();
					tfAutomaticStartTime.setEditable(false);
					tfAutomaticStartTime.setText("Starttid");
					GridBagConstraints gbc_tfAutomaticStartTime = new GridBagConstraints();
					gbc_tfAutomaticStartTime.insets = new Insets(0, 0, 5, 0);
					gbc_tfAutomaticStartTime.gridx = 0;
					gbc_tfAutomaticStartTime.gridy = 1;
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
					gbc_tfAutomaticEndTime.gridy = 2;
					centerWestPanel.add(tfAutomaticEndTime, gbc_tfAutomaticEndTime);
					tfAutomaticEndTime.setColumns(15);
				}
				{
					tfAutomaticDescription = new JTextField();
					tfAutomaticDescription.setText("Indtast Beskrivelse");
					GridBagConstraints gbc_tfAutomaticDescription = new GridBagConstraints();
					gbc_tfAutomaticDescription.insets = new Insets(0, 0, 5, 0);
					gbc_tfAutomaticDescription.gridx = 0;
					gbc_tfAutomaticDescription.gridy = 3;
					centerWestPanel.add(tfAutomaticDescription, gbc_tfAutomaticDescription);
					tfAutomaticDescription.setColumns(15);
				}
				{
					JButton btnStartEndRegistration = new JButton("Stempel Ind");
					GridBagConstraints gbc_btnStartEndRegistration = new GridBagConstraints();
					gbc_btnStartEndRegistration.gridx = 0;
					gbc_btnStartEndRegistration.gridy = 5;
					centerWestPanel.add(btnStartEndRegistration, gbc_btnStartEndRegistration);
					btnStartEndRegistration.setPreferredSize(new Dimension(100, 50));
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
				gbl_centerEastPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				centerEastPanel.setLayout(gbl_centerEastPanel);
				{
					JLabel lblManual = new JLabel("Manuel Tidsregistrering:");
					GridBagConstraints gbc_lblManual = new GridBagConstraints();
					gbc_lblManual.insets = new Insets(0, 0, 5, 0);
					gbc_lblManual.anchor = GridBagConstraints.NORTH;
					gbc_lblManual.gridx = 0;
					gbc_lblManual.gridy = 0;
					centerEastPanel.add(lblManual, gbc_lblManual);
				}
				{
					tfManualStartTime = new JTextField();
					tfManualStartTime.setText("Indtast Starttid");
					tfManualStartTime.setToolTipText("");
					GridBagConstraints gbc_tfManualStartTime = new GridBagConstraints();
					gbc_tfManualStartTime.insets = new Insets(0, 0, 5, 0);
					gbc_tfManualStartTime.gridx = 0;
					gbc_tfManualStartTime.gridy = 1;
					centerEastPanel.add(tfManualStartTime, gbc_tfManualStartTime);
					tfManualStartTime.setColumns(15);
				}
				{
					tfManualEndTime = new JTextField();
					tfManualEndTime.setText("Indtast Sluttid");
					GridBagConstraints gbc_tfManualEndTime = new GridBagConstraints();
					gbc_tfManualEndTime.insets = new Insets(0, 0, 5, 0);
					gbc_tfManualEndTime.gridx = 0;
					gbc_tfManualEndTime.gridy = 2;
					centerEastPanel.add(tfManualEndTime, gbc_tfManualEndTime);
					tfManualEndTime.setColumns(15);
				}
				{
					tfManualDescription = new JTextField();
					tfManualDescription.setText("Indtast Beskrivelse");
					GridBagConstraints gbc_tfManualDescription = new GridBagConstraints();
					gbc_tfManualDescription.insets = new Insets(0, 0, 5, 0);
					gbc_tfManualDescription.gridx = 0;
					gbc_tfManualDescription.gridy = 3;
					centerEastPanel.add(tfManualDescription, gbc_tfManualDescription);
					tfManualDescription.setColumns(15);
				}
				{
					JButton btnSubmitRegistration = new JButton("Indberet Tidsregistrering");
					GridBagConstraints gbc_btnSubmitRegistration = new GridBagConstraints();
					gbc_btnSubmitRegistration.gridx = 0;
					gbc_btnSubmitRegistration.gridy = 5;
					centerEastPanel.add(btnSubmitRegistration, gbc_btnSubmitRegistration);
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
