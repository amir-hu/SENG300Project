package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		label_2 = new JLabel("");
		label_2.setBounds(0, 219, 432, 31);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtpnUsername.setText("");
			}
		});
		txtpnUsername.setBounds(84, 64, 183, 31);
		txtpnUsername.setText("Username");
		
		frame.getContentPane().add(txtpnUsername);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pwdPassword.setText("");
			}
		});
		pwdPassword.setBounds(84, 95, 183, 31);
		pwdPassword.setHorizontalAlignment(SwingConstants.LEFT);
		pwdPassword.setBackground(Color.WHITE);
		pwdPassword.setEchoChar('*');
		pwdPassword.setText("Password");
		frame.getContentPane().add(pwdPassword);
		
		label_5 = new JLabel("");
		label_5.setBounds(0, 95, 432, 31);
		frame.getContentPane().add(label_5);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(84, 157, 183, 31);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = txtpnUsername.getText().trim();
				char[] password = pwdPassword.getPassword();
				String pass = "";
				for(int i = 0; i<password.length;i++) {
					pass = pass + password[i];
				}
				String attempt = "";
				int index = list.getSelectedIndex();
				User use = arr[index];
				attempt = use.Authenticate(username, pass);
				if(attempt.equals("true")) {
					//no issues move on to the next screen
					if(index==1) {
					PatientHome.open(username);
					}if(index==0) {
						DoctorHome.run();
					}
				}else if(attempt.equals("false")) {
					JOptionPane.showMessageDialog(frame, "Log in information incorrect try again","ERROR", JOptionPane.ERROR_MESSAGE);
				}else if(attempt.equals("DNE")) {
					JOptionPane.showMessageDialog(frame, "Account does not exist ");
				}
				
				System.out.println(username + pass + index);
//				System.exit(-1);
			}
		});
		
		list = new JComboBox();
		list.setBounds(84, 126, 183, 31);
		list.setModel(new DefaultComboBoxModel(new String[] {"Doctor", "Patient"}));
		
				list.setSelectedIndex(1);
				frame.getContentPane().add(list);
		frame.getContentPane().add(btnConfirm);
		
		label_8 = new JLabel("");
		label_8.setBounds(0, 188, 432, 31);
		frame.getContentPane().add(label_8);
		frame.getContentPane().add(label_2);
		
	}

}
