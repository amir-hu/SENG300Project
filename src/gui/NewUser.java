package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import logic.Patient;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewUser {

	private JFrame frame;
	private JTextField txtEnterFirstName;
	private JTextField txtEnterLastName;
	private JTextField txtEnterEmailAddress;
	private JTextField txtEnterUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser window = new NewUser();
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
	public NewUser() {
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
		
		txtEnterFirstName = new JTextField();
		txtEnterFirstName.setText("Enter First Name");
		txtEnterFirstName.setBounds(131, 13, 116, 22);
		frame.getContentPane().add(txtEnterFirstName);
		txtEnterFirstName.setColumns(10);
		
		txtEnterLastName = new JTextField();
		txtEnterLastName.setText("Enter Last Name");
		txtEnterLastName.setBounds(131, 48, 116, 22);
		frame.getContentPane().add(txtEnterLastName);
		txtEnterLastName.setColumns(10);
		
		txtEnterEmailAddress = new JTextField();
		txtEnterEmailAddress.setText("Enter Email Address");
		txtEnterEmailAddress.setBounds(131, 89, 116, 22);
		frame.getContentPane().add(txtEnterEmailAddress);
		txtEnterEmailAddress.setColumns(10);
		
		txtEnterUsername = new JTextField();
		txtEnterUsername.setText("Enter Username");
		txtEnterUsername.setBounds(131, 124, 116, 22);
		frame.getContentPane().add(txtEnterUsername);
		txtEnterUsername.setColumns(10);
		
		JButton btnHitToCreate = new JButton("Hit to Create Account");
		btnHitToCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char[] pass = passwordField.getPassword();
				String password = "";
				for(int i = 0;i < pass.length;i++ ) {
					password = password + pass[i];
				}
				Patient pat = new Patient(txtEnterFirstName.getText(),txtEnterLastName.getText(),
						txtEnterUsername.getText(),txtEnterEmailAddress.getText(),password);
				pat.create();
				System.exit(-1);
				
			}
		});
		btnHitToCreate.setBounds(116, 215, 166, 25);
		frame.getContentPane().add(btnHitToCreate);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 165, 116, 22);
		frame.getContentPane().add(passwordField);
		
	}
}
