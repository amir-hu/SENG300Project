package gui;

import java.awt.EventQueue;

import logic.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import logic.Admin;

public class AdminHome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHome window = new AdminHome();
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
	public AdminHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Admin admin = new Admin();
		frame = new JFrame();
		frame.setBounds(100, 100, 622, 356);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		System.out.println(Admin.name);
		JLabel lblWelcomeAd = new JLabel("Welcome " + Admin.name);
		lblWelcomeAd.setBounds(121, 13, 154, 16);
		frame.getContentPane().add(lblWelcomeAd);
		
		JButton btnNewDoctor = new JButton("New Doctor");
		btnNewDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewDoctor start = new NewDoctor();
				start.open();
			}
		});
		btnNewDoctor.setBounds(121, 72, 183, 25);
		frame.getContentPane().add(btnNewDoctor);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnEditDoctorSchedule = new JButton("Edit Doctor Schedule");
		btnEditDoctorSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditSchedule start = new EditSchedule();
				start.open();
			}
		});
		btnEditDoctorSchedule.setBounds(121, 125, 183, 25);
		frame.getContentPane().add(btnEditDoctorSchedule);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnEditPatientSchedule = new JButton("Edit Patient Schedule");
		btnEditPatientSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditPatientSchedule start = new EditPatientSchedule();
				start.open();
			}
		});
		btnEditPatientSchedule.setBounds(121, 178, 183, 25);
		frame.getContentPane().add(btnEditPatientSchedule);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
