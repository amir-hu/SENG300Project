package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
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
	public static void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentForm1 window = new AppointmentForm1();
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
	public AppointmentForm1() {
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 55, 389, 153);
		frame.getContentPane().add(scrollPane);
		
		//JList list = new JList();
		List<String> myList = new ArrayList<>();
		ArrayList<String> DocUser = new ArrayList<String>();

		File folder = new File("src/doctorRecords/");
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
			    Scanner myReader;
				try {
					myReader = new Scanner(file);
					if (myReader.hasNextLine()) {
				    	String name = myReader.nextLine();
				    	myList.add(name);
					}
					if (myReader.hasNextLine()) {
				    	String username = myReader.nextLine();
				    	DocUser.add(username);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }  
		}
	     final JList list = new JList(myList.toArray(new String[myList.size()]));
	 
		scrollPane.setViewportView(list);
		
		JButton nextFormBtn = new JButton("Next");
		nextFormBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int docIndex=list.getSelectedIndex();
				AppointmentForm2.open(DocUser.get(docIndex));
			}
		});
		nextFormBtn.setBounds(175, 227, 89, 23);
		frame.getContentPane().add(nextFormBtn);
	}

}
