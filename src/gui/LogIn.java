package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class LogIn {

	private JFrame frame;
	private JPasswordField pwdPassword;
	private JComboBox list;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_5;
	private JLabel label_8;

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
		frame = new JFrame();
		frame.setBackground(new Color(0, 51, 153));
		frame.getContentPane().setEnabled(false);
		frame.setAutoRequestFocus(false);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().setBackground(new Color(0, 0, 153));
		
		label = new JLabel("PLZ LOGGIN");
		label.setBounds(0, 2, 432, -14);
		
		label_2 = new JLabel("");
		label_2.setBounds(0, 219, 432, 31);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(label);
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setBounds(84, 64, 183, 31);
		txtpnUsername.setText("Username");
		frame.getContentPane().add(txtpnUsername);
		
		pwdPassword = new JPasswordField();
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
				String username = txtpnUsername.getText();
				char[] password = pwdPassword.getPassword();
				int index = list.getSelectedIndex();
				System.out.println(username + password[0] + index);
				System.exit(-1);
			}
		});
		
		list = new JComboBox();
		list.setBounds(84, 126, 183, 31);
		list.setModel(new DefaultComboBoxModel(new String[] {"Doctor", "Patient", "Admin"}));
		
				list.setSelectedIndex(1);
				frame.getContentPane().add(list);
		frame.getContentPane().add(btnConfirm);
		
		label_8 = new JLabel("");
		label_8.setBounds(0, 188, 432, 31);
		frame.getContentPane().add(label_8);
		frame.getContentPane().add(label_2);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
