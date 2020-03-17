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
		frame.setBounds(100, 100, 914, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		System.out.println(Admin.name);
		JLabel lblWelcomeAd = new JLabel("Welcome " + Admin.name);
		lblWelcomeAd.setBounds(384, 13, 154, 16);
		frame.getContentPane().add(lblWelcomeAd);
	}

}
