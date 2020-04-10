package gui;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logic.Doctor;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NurseHome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NurseHome window = new NurseHome();
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
	public NurseHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 914, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 72, 252, 318);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		
		DefaultListModel app=new DefaultListModel<String>();
		ArrayList<String> DocUser = new ArrayList<String>();

		File folder = new File("src/doctorRecords/");
		File[] listOfFiles = folder.listFiles();
		
		JLabel lblNewLabel = new JLabel("Hello Nurse");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(274, 6, 153, 26);
		frame.getContentPane().add(lblNewLabel);
		
		//System.out.println(docName);
		for (File file : listOfFiles) {
		    if (file.isFile()) {
			    Scanner myReader;
				try {
					myReader = new Scanner(file);
					if (myReader.hasNextLine()) {
				    	String name = myReader.nextLine();
				    	app.addElement("Dr. "+name);
				    	System.out.println("Dr. "+name);
				    	if (myReader.hasNextLine()) {
					    	String username = myReader.nextLine();
					    	DocUser.add(username);
				    	}
					}
					 
				myReader.close();
				}catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }  
		}
		list.setModel(app);
		
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(408, 72, 466, 318);
		frame.getContentPane().add(scrollPane_1);
		
		JList list_1 = new JList();
		//list_1.setEnabled(false);
		scrollPane_1.setViewportView(list_1);
		
		JLabel DoctorName_lbl_NH = new JLabel("Select a Doctor then Retrieve to see their schedule");
		DoctorName_lbl_NH.setHorizontalAlignment(SwingConstants.CENTER);
		DoctorName_lbl_NH.setBounds(407, 35, 466, 26);
		frame.getContentPane().add(DoctorName_lbl_NH);
		
		JLabel DoctorSelection_lbl_NH = new JLabel("Select a doctor");
		DoctorSelection_lbl_NH.setHorizontalAlignment(SwingConstants.CENTER);
		DoctorSelection_lbl_NH.setBounds(53, 41, 252, 26);
		frame.getContentPane().add(DoctorSelection_lbl_NH);
		
		JButton retrieve_btn_NH = new JButton("Retrieve Schedule");
		retrieve_btn_NH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.getSelectedIndex()!=-1) {
					ArrayList<String> schedule=new ArrayList<String>();
					DoctorName_lbl_NH.setText(list.getSelectedValue()+"'s Schedule");
					String username = DocUser.get(list.getSelectedIndex());
					DefaultListModel time=new DefaultListModel<String>();
					File file = new File("src/doctorRecords/" + username + ".txt");
					try {
						Scanner scan = new Scanner(file);
						scan.nextLine();scan.nextLine();scan.nextLine();scan.nextLine();
						while(scan.hasNextLine()) {
							String s=scan.nextLine();
							String[] det=s.split("%");
							String appointment=String.format("%s%20s",det[0],det[1]);
							schedule.add(appointment);
						}
						scan.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (int i=0; i<schedule.size(); i++) {
						time.addElement(schedule.get(i));
					}
					list_1.setModel(time);
				}
			}
		});
		retrieve_btn_NH.setBounds(53, 401, 252, 36);
		frame.getContentPane().add(retrieve_btn_NH);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "Are you sure?", "Logging Out", JOptionPane.YES_NO_OPTION);
				if (input==0) {
					frame.setVisible(false);
					LogIn.main(null);
				}
			}
		});
		logoutBtn.setBounds(720, 0, 168, 26);
		frame.getContentPane().add(logoutBtn);
		
		
		
	
	}
}
