package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class AppointmentForm1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void open(String PatientUser, ArrayList<String[]> appointmentList) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentForm1 window = new AppointmentForm1(PatientUser, appointmentList);
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
	public AppointmentForm1(String PatientUser, ArrayList<String[]> appointmentList) {
		initialize(PatientUser, appointmentList);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String PatientUser, ArrayList<String[]> appointmentList) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 55, 389, 153);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		DefaultListModel app=new DefaultListModel<String>();
		List<String> myList = new ArrayList<>();
		ArrayList<String> DocUser = new ArrayList<String>();

		File folder = new File("src/doctorRecords/");
		File[] listOfFiles = folder.listFiles();
		ArrayList<String[]> existingAppointments=appointmentList;
		ArrayList<String> docName= new ArrayList<String>();
		for (int i=0; i<existingAppointments.size();i++) {
			docName.add(existingAppointments.get(i)[0]);
		}
		System.out.println(docName);
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
					    	System.out.println(docName.contains(name));
					    	if (docName.contains("Dr. "+name)) {
					    		System.out.println("removing");
					    		app.removeElement("Dr. "+name);
					    		DocUser.remove(username);
					    	}
				    	}
					}
					 
				myReader.close();
				}catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }  
		}
		//checks for existing appointments with the doc and removes the doc from the list
		
		
	    // final JList list = new JList(myList.toArray(new String[myList.size()]));
		list.setModel(app);
		scrollPane.setViewportView(list);
		
		JButton nextFormBtn = new JButton("Next");
		nextFormBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getSelectedIndex()!=-1) {
					int docIndex=list.getSelectedIndex();
					frame.setVisible(false);
					AppointmentForm2.open(DocUser.get(docIndex), PatientUser, 0);
				}	
			}
		});
		nextFormBtn.setBounds(175, 227, 89, 23);
		frame.getContentPane().add(nextFormBtn);
		
		JButton cancelButton_Form1 = new JButton("Cancel");
		cancelButton_Form1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatientHome.open(PatientUser);
			}
		});
		cancelButton_Form1.setBounds(0, 11, 89, 23);
		frame.getContentPane().add(cancelButton_Form1);
	}

}
