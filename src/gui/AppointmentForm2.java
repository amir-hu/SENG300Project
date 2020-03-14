package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Button;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class AppointmentForm2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void open(String username, String PatientUser) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentForm2 window = new AppointmentForm2(username, PatientUser);
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
	public AppointmentForm2(String username, String PatientUser) {
		initialize(username, PatientUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String username, String PatientUser) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(258, 42, 152, 189);
		frame.getContentPane().add(scrollPane);
		JButton completeSelectionBtn = new JButton("Done");
		completeSelectionBtn.setEnabled(false);
		
		JList list=new JList();
		DefaultListModel app=new DefaultListModel<String>();
		//List<String> myList = new ArrayList<>();
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		dateChooser.setBounds(10, 73, 168, 20);
		frame.getContentPane().add(dateChooser);
		String filename= "src/doctorRecords/"+username+".txt";
		JButton btnNewButton = new JButton("Retrive Available Slots");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date selectedDate=dateChooser.getDate();
				Format formatter = new SimpleDateFormat("MM-dd-yyyy");
				String date = formatter.format(selectedDate);
				String[] appointmentTime= new String[2];
				ArrayList<String> bookedTime=new ArrayList<String>();
				
				try {
					File doctor = new File(filename);
				    Scanner myReader = new Scanner(doctor);
				    for (int i=0; i<4;i++) {
				    	if (myReader.hasNextLine()) {
				    		String trash=myReader.nextLine();
					    }
				    }
				    ///////////////////////////////////////////////////////////////////////////////////////////////////
				    app.clear();
				    for (int j=0;j<11;j++) {
						if (j==0) {
							app.addElement("09:00 Hrs");
						}else {
							app.addElement(String.valueOf(j+9)+":00 Hrs");
						}
					}
				    while (myReader.hasNextLine()) {
				    	String appointment = myReader.nextLine();
				    	appointmentTime=appointment.split("%");
				    	
				    	if ((appointmentTime[0].equals(date))) {
				    		if (app.contains(appointmentTime[1].trim())) {
				    			//System.out.println("removing "+appointmentTime[1]);
				    			app.removeElement(appointmentTime[1].trim());
				    		}
				    	}
					    
				    }		
				    System.out.println(app);
				    myReader.close();
				    } catch (FileNotFoundException e1) {
				    	System.out.println("An error occurred.");
				    	e1.printStackTrace();
				    }
				list.setModel(app);
				scrollPane.setViewportView(list);
				completeSelectionBtn.setEnabled(true);
			}
			
		
		});
		btnNewButton.setBounds(10, 138, 184, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		completeSelectionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex()!=-1){
					int timeIndex= list.getSelectedIndex();
					String docName="";
					File doctor = new File(filename);
				    Scanner myReader;
					try {
						myReader = new Scanner(doctor);
						if (myReader.hasNextLine()) {
					    	docName=myReader.nextLine();
					    } 
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Date selectedDate=dateChooser.getDate();
					Format formatter = new SimpleDateFormat("MM-dd-yyyy");
					String date = formatter.format(selectedDate);
				    String newRecord=date+"%"+app.get(timeIndex);
					String newAppointment="Dr. "+docName+"%"+date+"%"+app.get(timeIndex);
					System.out.println(newAppointment);
					//add to Doctor's records
					BufferedWriter docWriter;
					try {
						docWriter = new BufferedWriter(new FileWriter("src/doctorRecords/"+username+".txt/",true));
						docWriter.write("\n"+newRecord);
						docWriter.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//add to patients record
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter("src/patientRecords/"+PatientUser+".txt/",true));
						writer.write("\n"+newAppointment);
						writer.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				
			}
		});
		completeSelectionBtn.setBounds(286, 238, 89, 23);
		frame.getContentPane().add(completeSelectionBtn);
	}
}
