package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import logic.Doctor;
import logic.Patient;
import logic.User;

public class EditSchedule {
	
	private JFrame frame;
	private JPasswordField pwdPassword;
	private JComboBox list;
	private JLabel label_2;
	private JLabel label_5;
	private JLabel label_8;
	private User[] arr= {new Doctor(),new Patient()};

	/**
	 * Launch the application.
	 */
	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditSchedule window = new EditSchedule();
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
	public EditSchedule() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setBackground(new Color(0, 51, 153));
		frame.getContentPane().setEnabled(false);
		frame.setAutoRequestFocus(false);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().setBackground(new Color(0, 0, 153));
		
		
		File folder = new File("src/doctorRecords/");
		ArrayList <String> namestxt = new ArrayList <String>();
		namestxt.addAll(Arrays.asList(folder.list()));
		ArrayList <String> names = new ArrayList <String>();
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(166, 115, 89, 45);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				Doctor doc = new Doctor(names.get(index));
				DoctorHome.run();
			}
		});
		

		for(int i = 0; i < namestxt.size(); i++)
	      {
	          if(namestxt.get(i).contains(".txt"))
	          {
	              names.add(namestxt.get(i).replace(".txt", ""));
	             
	          } 
	       }
		frame.getContentPane().setLayout(null);
		
		list = new JComboBox();
		list.setBounds(122, 59, 183, 31);
		list.setModel(new DefaultComboBoxModel<>(names.toArray(new String[names.size()])));
				list.setSelectedIndex(1);
				frame.getContentPane().add(list);
		frame.getContentPane().add(btnConfirm);
		
		label_8 = new JLabel("");
		label_8.setBounds(0, 188, 432, 31);
		frame.getContentPane().add(label_8);;
		frame.getContentPane().add(btnConfirm);
		
		
		
	}

}
