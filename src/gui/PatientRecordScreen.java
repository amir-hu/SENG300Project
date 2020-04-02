package gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import logic.Patient;

public class PatientRecordScreen {

	private JFrame frame;
	private JTextField txtSomething;

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientRecordScreen window = new PatientRecordScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PatientRecordScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Patient pat = new Patient();
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		File folder = new File("src/patientRecords/");
		ArrayList <String> namestxt = new ArrayList <String>();
		namestxt.addAll(Arrays.asList(folder.list()));
		ArrayList <String> names = new ArrayList <String>();
		for(int i = 0; i < namestxt.size(); i++)
	      {
	          if(namestxt.get(i).contains(".txt"))
	          {
	              names.add(namestxt.get(i).replace(".txt", ""));
	             
	          } 
	       }
		
		JComboBox comboBox = new JComboBox(names.toArray());
		comboBox.setBounds(55, 51, 168, 22);
		frame.getContentPane().add(comboBox);
		JTextPane textPane = new JTextPane();
		textPane.setBounds(55, 126, 635, 271);
		frame.getContentPane().add(textPane);
		JButton btnNewButton = new JButton("Get Info");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String records = pat.getRecords(names.get(comboBox.getSelectedIndex()));
				textPane.setText(records);
			}
		});
		btnNewButton.setBounds(359, 50, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSetRecord = new JButton("Save");
		btnSetRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pat.setRecord(names.get(comboBox.getSelectedIndex()), textPane.getText());
			}
		});
		btnSetRecord.setBounds(593, 50, 97, 25);
		frame.getContentPane().add(btnSetRecord);
		
		
		
		
		
		
	
}
}