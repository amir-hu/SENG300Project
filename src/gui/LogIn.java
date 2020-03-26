package gui;
import java.awt.EventQueue;	

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Toolkit.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import logic.Admin;
import logic.Doctor;
import logic.Nurse;
import logic.Patient;
import logic.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTextField;
import javax.swing.UIManager;



public class LogIn {

	private JFrame frame;
	private JPasswordField pwdPassword;
	private JComboBox list;
	private JLabel label_2;
	private User[] arr= {new Doctor(),new Patient(),new Admin(),new Nurse()};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
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
	public LogIn() {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		User p1 = new User();
		
		
		
		frame = new JFrame();
		frame.setAutoRequestFocus(false);
		frame.getContentPane().setForeground(new Color(245, 255, 250));
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().setBackground(new Color(245, 255, 250));
			
		
		
		label_2 = new JLabel("",new ImageIcon(LogIn.class.getResource("/images/health_logo.jpg")),JLabel.CENTER);
		label_2.setBackground(new Color(255, 0, 0));
		label_2.setBounds(0, 0, 848, 238);
		frame.getContentPane().setLayout(null);
		
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnUsername.setForeground(Color.BLACK);
		txtpnUsername.setBackground(Color.WHITE);
		txtpnUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtpnUsername.setText("");
			}
		});
		txtpnUsername.setBounds(315, 313, 183, 31);
		txtpnUsername.setText("Username");
		
		frame.getContentPane().add(txtpnUsername);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pwdPassword.setText("");
			}
		});
		pwdPassword.setBounds(315, 347, 183, 31);
		pwdPassword.setHorizontalAlignment(SwingConstants.LEFT);
		pwdPassword.setBackground(Color.WHITE);
		pwdPassword.setEchoChar('*');
		pwdPassword.setText("Password");
		frame.getContentPane().add(pwdPassword);
		
		
		
		//ImageIcon icon = new ImageIcon( newimg );
		   
		JButton Login = new JButton("");
		Login.setForeground(new Color(245, 255, 250));
		Login.setBackground(new Color(245, 255, 250));
		
		ImageIcon img = new ImageIcon(LogIn.class.getResource("/images/button1.png"));
		
		Image enter = img.getImage();
		Image mod_enter = enter.getScaledInstance(231, 152, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(mod_enter);
		
		Login.setIcon(img);
		
		Login.setBounds(315, 421, 183, 37);
		Login.addActionListener(new ActionListener() {
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
					}if(index==2) {
						AdminHome.run();
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
		list.setForeground(new Color(0, 0, 0));
		list.setBackground(new Color(245, 255, 250));
		list.setBounds(315, 379, 183, 31);
		list.setModel(new DefaultComboBoxModel(new String[] {"Doctor", "Patient", "Admin","Nurse"}));
		
				list.setSelectedIndex(1);
				frame.getContentPane().add(list);
		frame.getContentPane().add(Login);
		frame.getContentPane().add(label_2);
		
		JButton btnNewPatient = new JButton("New to Alberta Health Services? Click Here");
		btnNewPatient.setForeground(new Color(0, 102, 153));
		btnNewPatient.setBackground(new Color(245, 255, 250));
		btnNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewUser start = new NewUser();
				start.open();
			}
		});
		btnNewPatient.setBounds(269, 571, 285, 25);
		frame.getContentPane().add(btnNewPatient);
		
		
		
		JLabel label = new JLabel("");
		//label.setIcon(new ImageIcon(LogIn.class.getResource("/images/text_box.png")));
		
		ImageIcon img2 = new ImageIcon(LogIn.class.getResource("/images/text_box.png"));
		
		Image first = img2.getImage();
		Image mod_img2 = first.getScaledInstance(230, 230, java.awt.Image.SCALE_SMOOTH);
		img2 = new ImageIcon(mod_img2);
		
		label.setIcon(img2);
		
		//JLabel label = new JLabel("");
		label.setBounds(290, 261, 231, 230);
		frame.getContentPane().add(label);
		frame.setBounds(100, 100, 864, 665);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
