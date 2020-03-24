package gui;

import java.awt.EventQueue;
import logic.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
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
		JLabel lblWelcomeDr = new JLabel("Welcome Dr. " + Doctor.name);
		lblWelcomeDr.setBounds(384, 13, 154, 16);
		frame.getContentPane().add(lblWelcomeDr);
		
		JList list = new JList(doc.getSchedule().toArray());
		list.setBounds(84, 42, 378, 380);
		frame.getContentPane().add(list);
		
		
		Calendar calender = Calendar.getInstance();
		String[] free = new String[14];
		for(int i = 0;i<14;i++) {
			free[i] = Integer.toString(calender.get(Calendar.YEAR)) + "/" + (calender.get(Calendar.MONTH) + 1) + "/" + Integer.toString(calender.get(Calendar.DATE));
			calender.add(Calendar.DATE,1);
		}
		String[] timeFree = new String[10];
		int start = 9;
		for(int i = 0;i < 10;i++) {
			timeFree[i] = start + ":00";
			start++;
		}
		JComboBox comboBox = new JComboBox(free);
		comboBox.setBounds(557, 69, 154, 22);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(timeFree);
		comboBox_1.setBounds(557, 142, 154, 22);
		frame.getContentPane().add(comboBox_1);
		JButton btnAddToSchedule = new JButton("Add To Schedule");
		btnAddToSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				//if/else added for when schedule slot already taken
				if(doc.showSchedule().contains(comboBox.getSelectedItem() + " " + comboBox_1.getSelectedItem())) {
						JOptionPane.showMessageDialog(frame, "Error: Schedule Slot Taken");
						System.out.println(comboBox.getSelectedItem() + " " + comboBox_1.getSelectedItem());
				}
				else {
						doc.schedule.add(comboBox.getSelectedItem() + " " + comboBox_1.getSelectedItem());
						doc.updateSchedule();
						list.setListData(doc.schedule.toArray());
						JOptionPane.showMessageDialog(frame, "Appointment Added");
				}
			}
		});
		btnAddToSchedule.setBounds(557, 222, 154, 25);
		
		frame.getContentPane().add(btnAddToSchedule);
		
		JButton btnNewButton = new JButton("Delete From Scedule");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				doc.schedule.remove(index);
				doc.updateSchedule();
				list.setListData(doc.schedule.toArray());
				list.updateUI();
				list.ensureIndexIsVisible(doc.schedule.size());			
			}
		});
		btnNewButton.setBounds(557, 366, 154, 25);
		frame.getContentPane().add(btnNewButton);
	}
}
