package gui;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientHome {
	private String userName;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	public static void open(String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientHome window = new PatientHome(username);
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
	public PatientHome(String username) {
		setUserName(username);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//reads the file
		String filename= "src/patientRecords/"+getUserName()+".txt";
		String name=getUserName();
		try {
			File patient = new File(filename);
		    Scanner myReader = new Scanner(patient);
		    if (myReader.hasNextLine()) {
		    	name = myReader.nextLine();
		    }
		    myReader.close();
		    } catch (FileNotFoundException e) {
		    	System.out.println("An error occurred.");
		    	e.printStackTrace();
		    }
		JLabel UserName = new JLabel("Hello "+name);
		UserName.setHorizontalAlignment(SwingConstants.CENTER);
		UserName.setBounds(10, 11, 414, 14);
		frame.getContentPane().add(UserName);
		
		JList list = new JList();
		list.setBounds(24, 178, 386, -130);
		frame.getContentPane().add(list);
		
		JButton ManageAppointmentBtn = new JButton("Manage Appointment(s)");
		ManageAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update appointment
			}
		});
		ManageAppointmentBtn.setBounds(10, 230, 140, 25);
		frame.getContentPane().add(ManageAppointmentBtn);
		
		JButton DeleteAppointmentBtn = new JButton("Delete Appointment");
		DeleteAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//List of appointments
			}
		});
		DeleteAppointmentBtn.setBounds(284, 230, 140, 25);
		frame.getContentPane().add(DeleteAppointmentBtn);
		
		JButton AddAppointmentBtn = new JButton("Add Appointment");
		AddAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//take to doctors list --> calendar
			}
		});
		AddAppointmentBtn.setBounds(146, 204, 140, 25);
		frame.getContentPane().add(AddAppointmentBtn);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
