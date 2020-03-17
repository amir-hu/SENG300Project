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

import logic.Mail;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PatientHome {
	private String userName;
	private JFrame frame;
	private ArrayList<String[]> appointmentList;

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
		appointmentList=new ArrayList<String[]>();
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
		    	
		    	String[] apointmentDetails= processAppointmentDetails(appointment);
		    	appointmentList.add(apointmentDetails);
		    }
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
		String appoint;
		for (int i=0; i<appointmentList.size();i++) {
			
			appoint=appointmentIntoString(appointmentList,i);
			
			app.addElement(appoint); ////////change
		}
		
		
		JList list = new JList();
		list.setFont(new Font("Courier New", Font.PLAIN, 13));
		list.setModel(app);
		list.setBounds(10, 36, 414, 157);
		frame.getContentPane().add(list);
		
		
		JButton ManageAppointmentBtn = new JButton("Manage Appointment(s)");
		ManageAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update appointment
				int modifyIndex=list.getSelectedIndex();
				String docAppointmentToUpdate= appointmentList.get(modifyIndex)[0];
				File folder = new File("src/doctorRecords/");
				File[] listOfFiles = folder.listFiles();
				String docUsername=new String();
				for (File file : listOfFiles) {
				    if (file.isFile()) {
				    	Scanner docReader;
					    try {
							docReader=new Scanner(file);
							if (docReader.hasNextLine()) {
								String docName="Dr. "+docReader.nextLine();
								if (docName.equals(docAppointmentToUpdate)) {	
									if (docReader.hasNextLine()){
										docUsername=docReader.nextLine();
										break;
									}
								}
							}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }
				}
				AppointmentForm2.open(docUsername, getUserName(), 1);
				/*
				 * String filename= "src/patientRecords/"+getUserName()+".txt";
				int changeIndex=list.getSelectedIndex();
				appointmentList[]
				String changedRecord=writtenAppointmentIntoString(appointmentList,changeIndex);
				*/
			}
		});
		
		ManageAppointmentBtn.setBounds(10, 230, 140, 25);
		frame.getContentPane().add(ManageAppointmentBtn);
		
		
		JButton DeleteAppointmentBtn = new JButton("Delete Appointment");

		DeleteAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(list.isSelectionEmpty())) {
					String filename= "src/patientRecords/"+getUserName()+".txt";
					int delIndex=list.getSelectedIndex();
					String delRecord=writtenAppointmentIntoString(appointmentList,delIndex);
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
						
						app.removeElementAt(delIndex);
						list.setModel(app);
						//List of appointments
					}
					File folder = new File("src/doctorRecords/");
					File[] listOfFiles = folder.listFiles();
					String docUsername=new String();
					for (File file : listOfFiles) {
					    if (file.isFile()) {
					    	Scanner docReader;
						    try {
								docReader=new Scanner(file);
								if (docReader.hasNextLine()) {
									String docName="Dr. "+docReader.nextLine();
									System.out.println("docName: "+docName);
									if (docName.equals(appointmentList.get(delIndex)[0])) {
										System.out.println("docName found");
										if (docReader.hasNextLine()){
											docUsername=docReader.nextLine();
											break;
										}
									}
								}
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    }
					}
					
					String docFilename= "src/doctorRecords/"+docUsername+".txt";
					String delAppointment=appointmentList.get(delIndex)[1]+"%"+appointmentList.get(delIndex)[2].trim();
					if (delIndex>=0 && delIndex<appointmentList.size()) {
						try {
							String contents= fileToString(docFilename);
							Mail.Send("Your appointment on " + delAppointment + " has now been deleated",
									"legitmedicalcentre@gmail.com");
							System.out.println("now deleting");
							contents = contents.replaceAll(delAppointment+"\n", "");
							PrintWriter writer = new PrintWriter(new File(docFilename));
						    writer.append(contents);
						    writer.flush();
						    writer.close();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					appointmentList.remove(delIndex);
				}
				
			}
		});
		DeleteAppointmentBtn.setBounds(284, 230, 140, 25);
		frame.getContentPane().add(DeleteAppointmentBtn);
		
		JButton AddAppointmentBtn = new JButton("Add Appointment");
		AddAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//take to doctors list --> calendar
				String patientUser = getUserName();
				AppointmentForm1.open(patientUser, getAppointmentList());
			}
		});
		AddAppointmentBtn.setBounds(146, 204, 140, 25);
		frame.getContentPane().add(AddAppointmentBtn);
	}
	protected String writtenAppointmentIntoString(ArrayList<String[]> appointmentList2, int index) {
		String[] array=appointmentList.get(index);
		String toBeupdated= array[0]+"%"+array[1]+"%"+array[2].trim();
		return toBeupdated;
	}

	private String appointmentIntoString(ArrayList<String[]> appointmentList, int i) {
		String[] array=appointmentList.get(i);
		String name=array[0];
		while (name.length()!=15) {
			name=name+" ";
		}
		String output=String.format("%s%15s%20s", name ,array[1],array[2]);
		return output;
	}

	private String[] processAppointmentDetails(String appointment) {
		
		String[] Details = new String[3];
		Details=appointment.split("%");
		
		return Details;
	}

	public static String fileToString(String filePath) throws Exception{
	      String input = null;
	      Scanner sc = new Scanner(new File(filePath));
	      StringBuffer sb = new StringBuffer();
	      while (sc.hasNextLine()) {
	         input = sc.nextLine();
	         sb.append(input+"\n");
	      }
	      String output=sb.toString();
	      return output;//.substring(0, output.length()-1);
	}
	public static String getOldAppointment(String patientFile, String docName) {
		String output = new String();
		String appointmentDoc;
	    try {
			Scanner sc = new Scanner(new File(patientFile));
			for (int k=0; k<4; k++) {
				if (sc.hasNextLine()) {
					appointmentDoc=sc.nextLine();
					//System.out.println("doctor: "+ appointmentDoc);
				}
	    	}
			while (sc.hasNextLine()) {
				String[] record=sc.nextLine().split("%");
				appointmentDoc=record[0];
				System.out.println("doctor: "+ appointmentDoc);
				if(appointmentDoc.equals("Dr. "+docName)) {
					
					output=record[1]+"%"+record[2];
					System.out.println("output: "+ output);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public ArrayList<String[]> getAppointmentList() {
		return this.appointmentList;
	}
	
}
