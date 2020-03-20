package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logic.Doctor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class NewDoctor {

	private JFrame frame;
	private JTextField txtEnterName;
	private JTextField txtEnterUsername;
	private JTextField txtEnterPosition;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewDoctor window = new NewDoctor();
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
	public NewDoctor() {
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
		
		txtEnterName = new JTextField();
		txtEnterName.setText("Enter Name");
		txtEnterName.setBounds(131, 13, 116, 22);
		frame.getContentPane().add(txtEnterName);
		txtEnterName.setColumns(10);
		
		txtEnterUsername = new JTextField();
		txtEnterUsername.setText("Enter Username");
		txtEnterUsername.setBounds(131, 48, 116, 22);
		frame.getContentPane().add(txtEnterUsername);
		txtEnterUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 89, 116, 22);
		frame.getContentPane().add(passwordField);
		
		txtEnterPosition = new JTextField();
		txtEnterPosition.setText("Enter Position");
		txtEnterPosition.setBounds(131, 124, 116, 22);
		frame.getContentPane().add(txtEnterPosition);
		txtEnterPosition.setColumns(10);
		
		JButton btnHitToCreate = new JButton("Hit to Create Account");
		btnHitToCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char[] pass = passwordField.getPassword();
				String password = "";
				for(int i = 0;i < pass.length;i++ ) {
					password = password + pass[i];
				}
				Doctor doc = new Doctor(txtEnterName.getText(),txtEnterUsername.getText(),
						password,txtEnterPosition.getText());
				doc.create();
				System.exit(-1);
				
			}
		});
		btnHitToCreate.setBounds(116, 215, 166, 25);
		frame.getContentPane().add(btnHitToCreate);
	
		
	}

}
