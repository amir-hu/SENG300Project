package gui;

import java.awt.EventQueue;

import logic.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class DoctorHome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorHome window = new DoctorHome();
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
	public DoctorHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Doctor doc = new Doctor();
		frame = new JFrame();
		frame.setBounds(100, 100, 914, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		System.out.println(Doctor.name);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int input = JOptionPane.showConfirmDialog(null, "Are you sure?", "Logging Out", JOptionPane.YES_NO_OPTION);
				if (input==0) {
					frame.setVisible(false);
					LogIn.main(null);
				}
			}
		});
		logoutBtn.setBounds(733, 0, 155, 23);
		frame.getContentPane().add(logoutBtn);
		JLabel lblWelcomeDr = new JLabel("Welcome Dr. " + Doctor.name);
		lblWelcomeDr.setBounds(384, 13, 154, 16);
		frame.getContentPane().add(lblWelcomeDr);
		doc.getSchedule();
		JList list = new JList(doc.scheduleWithDetails.toArray());
		list.setBounds(84, 42, 378, 380);
		frame.getContentPane().add(list);
		
		
		Calendar calender = Calendar.getInstance();
		String[] free = new String[14];
		JDateChooser dateChooser = new JDateChooser();
//		Date selectedDate=dateChooser.getDate();
		Format formatter = new SimpleDateFormat("MM-dd-yyyy");
//		String date = formatter.format(selectedDate);
		for(int i = 0;i<14;i++) {
//			free[i] = Integer.toString(calender.get(Calendar.MONTH ) + 1) + "-" + (calender.get(Calendar.DATE)) + "-" + Integer.toString(calender.get(Calendar.YEAR));
			free[i] = formatter.format(calender.getTime());
			calender.add(Calendar.DATE,1);
		}
		String[] timeFree = new String[10];
		int start = 9;
		for(int i = 0;i < 10;i++) {
			if(start==9) { 
				timeFree[i] = "0" + start + ":00";
				}else {
			timeFree[i] = start + ":00";
				}
			start++;
		}
		JComboBox comboBox = new JComboBox(free);
		comboBox.setBounds(557, 69, 254, 22);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(timeFree);
		comboBox_1.setBounds(557, 142, 254, 22);
		frame.getContentPane().add(comboBox_1);
		JButton btnAddToSchedule = new JButton("Add To Schedule");
		btnAddToSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				doc.schedule.add(comboBox.getSelectedItem() + "%" + comboBox_1.getSelectedItem() + " Hrs");
//				doc.updateSchedule();
//				list.setListData(doc.schedule.toArray());
				

					
				//if/else added for when schedule slot already taken
				if(doc.showSchedule().contains(comboBox.getSelectedItem() + "%" + comboBox_1.getSelectedItem() +" Hrs")) {
					JOptionPane.showMessageDialog(frame, "Error: Schedule Slot Taken");
					//System.out.println(comboBox.getSelectedItem() + " " + comboBox_1.getSelectedItem());
				}
				else {
					doc.schedule.add(comboBox.getSelectedItem() + "%" + comboBox_1.getSelectedItem() +" Hrs");
					doc.scheduleWithDetails.add(comboBox.getSelectedItem() + "%" + comboBox_1.getSelectedItem() +" Hrs");
					doc.updateSchedule();
					list.setListData(doc.scheduleWithDetails.toArray());
					JOptionPane.showMessageDialog(frame, "Appointment Added");
				}


			}
		});
		btnAddToSchedule.setBounds(557, 191, 255, 35);
		
		frame.getContentPane().add(btnAddToSchedule);
		
		JButton btnNewButton = new JButton("Delete From Scedule");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				if(doc.scheduleWithDetails.get(index).split("With").length > 1) {
					JOptionPane.showMessageDialog(frame, "Please edit patient appointment through the option on the side");
				}else {
				doc.scheduleWithDetails.remove(index);
				doc.schedule.remove(index);
				doc.updateSchedule();
//				doc.getSchedule();
				list.setListData(doc.scheduleWithDetails.toArray());
				list.updateUI();
				list.ensureIndexIsVisible(doc.scheduleWithDetails.size());			
				}
			}
		});
		btnNewButton.setBounds(557, 245, 255, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Patient records");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatientRecordScreen.run();
				
			}
		});
		btnNewButton_1.setBounds(556, 298, 255, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Edit patient Appointments");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditPatientSchedule eps = new EditPatientSchedule();
				eps.open();
			}
		});
		btnNewButton_2.setBounds(557, 355, 255, 35);
		frame.getContentPane().add(btnNewButton_2);
	}
}
