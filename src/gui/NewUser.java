package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextField;

import logic.Patient;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class NewUser {

	private JFrame frame;
	private JTextField txtEnterFirstName;
	private JTextField txtEnterLastName;
	private JTextField txtEnterEmailAddress;
	private JTextField txtEnterUsername;
	private JPasswordField pwdPassword;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

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
		frame.getContentPane().setBackground(new Color(245, 255, 250));
		frame.setBounds(100, 100, 866, 666);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtEnterFirstName = new JTextField();
		txtEnterFirstName.setText("Enter First Name");
		txtEnterFirstName.setBounds(372, 312, 127, 22);
		frame.getContentPane().add(txtEnterFirstName);
		txtEnterFirstName.setColumns(10);
		
		txtEnterLastName = new JTextField();
		txtEnterLastName.setText("Enter Last Name");
		txtEnterLastName.setBounds(372, 345, 127, 22);
		frame.getContentPane().add(txtEnterLastName);
		txtEnterLastName.setColumns(10);
		
		txtEnterEmailAddress = new JTextField();
		txtEnterEmailAddress.setText("Enter Email Address");
		txtEnterEmailAddress.setBounds(372, 378, 127, 22);
		frame.getContentPane().add(txtEnterEmailAddress);
		txtEnterEmailAddress.setColumns(10);
		
		txtEnterUsername = new JTextField();
		txtEnterUsername.setText("Enter Username");
		txtEnterUsername.setBounds(372, 411, 127, 22);
		frame.getContentPane().add(txtEnterUsername);
		txtEnterUsername.setColumns(10);
		
		JButton btnHitToCreate = new JButton("");
		btnHitToCreate.setBorder(null);
		btnHitToCreate.setBackground(new Color(26, 179, 115));
		btnHitToCreate.setForeground(new Color(0, 0, 0));
		btnHitToCreate.setIcon(new ImageIcon(NewUser.class.getResource("/images/button.png")));
		btnHitToCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char[] pass = pwdPassword.getPassword();
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
		btnHitToCreate.setBounds(331, 477, 197, 36);
		frame.getContentPane().add(btnHitToCreate);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("newpassword");
		pwdPassword.setToolTipText("");
		pwdPassword.setBounds(372, 444, 127, 22);
		frame.getContentPane().add(pwdPassword);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(NewUser.class.getResource("/images/health_logo.jpg")));
		lblNewLabel.setBounds(10, -16, 850, 286);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		
		lblNewLabel_1 = new JLabel("");
		ImageIcon img_temp = new ImageIcon(NewUser.class.getResource("/images/text_box.png"));
		Image temp = img_temp.getImage();
		Image mod_temp = temp.getScaledInstance(230, 230, java.awt.Image.SCALE_SMOOTH);
		img_temp = new ImageIcon(mod_temp);
		lblNewLabel_1.setIcon(img_temp);
		
		lblNewLabel_1.setBounds(314, 200, 230, 427);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
