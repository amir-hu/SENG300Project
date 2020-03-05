package gui;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientHome {
	private String userName;
	private JFrame frame;
	private ArrayList<String> appointmentList;

	/**
	 * Launch the application.
	 */
	
	public static void open(String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientHome window = new PatientHome(username);
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
	public PatientHome(String username) {
		setUserName(username);
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
		//reads the file
		String filename= "src/patientRecords/"+getUserName()+".txt";
		String name=getUserName();
		String appointment;
		appointmentList=new ArrayList<String>();
		try {
			File patient = new File(filename);
		    Scanner myReader = new Scanner(patient);
		    if (myReader.hasNextLine()) {
		    	name = myReader.nextLine();
		    }
		    for (int i=0; i<3;i++) {
		    	if (myReader.hasNextLine()) {
			    	appointment = myReader.nextLine();
			    }
		    }
		    while (myReader.hasNextLine()) {
		    	appointment = myReader.nextLine();
		    	appointmentList.add(appointment);
		    }
		    System.out.print(appointmentList);
		    myReader.close();
		    } catch (FileNotFoundException e) {
		    	System.out.println("An error occurred.");
		    	e.printStackTrace();
		    }
		JLabel UserName = new JLabel("Hello "+name);
		UserName.setHorizontalAlignment(SwingConstants.CENTER);
		UserName.setBounds(10, 11, 414, 14);
		frame.getContentPane().add(UserName);
		
		DefaultListModel app=new DefaultListModel();
		for (int i=0; i<appointmentList.size();i++) {
			app.addElement(appointmentList.get(i));
		}
		
		JButton ManageAppointmentBtn = new JButton("Manage Appointment(s)");
		ManageAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update appointment
			}
		});
		JList list = new JList();
		list.setModel(app);
		list.setBounds(10, 36, 414, 157);
		frame.getContentPane().add(list);
		ManageAppointmentBtn.setBounds(10, 230, 140, 25);
		frame.getContentPane().add(ManageAppointmentBtn);
		
		JButton DeleteAppointmentBtn = new JButton("Delete Appointment");

		DeleteAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename= "src/patientRecords/"+getUserName()+".txt";
				int delIndex=list.getSelectedIndex();
				String delRecord=appointmentList.get(delIndex);
				//remove the record from file
				if (delIndex>=0 && delIndex<appointmentList.size()) {
					try {
						String contents= fileToString(filename);
						contents = contents.replaceAll(delRecord+"\n", "");
						PrintWriter writer = new PrintWriter(new File(filename));
					    writer.append(contents);
					    writer.flush();
					    writer.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					appointmentList.remove(delIndex);
					app.removeElementAt(delIndex);
					list.setModel(app);
					//List of appointments
				}
			}
		});
		DeleteAppointmentBtn.setBounds(284, 230, 140, 25);
		frame.getContentPane().add(DeleteAppointmentBtn);
		
		JButton AddAppointmentBtn = new JButton("Add Appointment");
		AddAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//take to doctors list --> calendar
			}
		});
		AddAppointmentBtn.setBounds(146, 204, 140, 25);
		frame.getContentPane().add(AddAppointmentBtn);
	}
	public static String fileToString(String filePath) throws Exception{
	      String input = null;
	      Scanner sc = new Scanner(new File(filePath));
	      StringBuffer sb = new StringBuffer();
	      while (sc.hasNextLine()) {
	         input = sc.nextLine();
	         sb.append(input+"\n");
	      }
	      return sb.toString();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}