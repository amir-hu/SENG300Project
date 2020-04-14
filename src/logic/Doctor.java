package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Doctor extends User {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String position;

	public static String name = "test";
	private static String username = "";
	public ArrayList<String> schedule = new ArrayList<String>(); 
	public ArrayList<String> scheduleWithDetails= new ArrayList<String>(); 
	
	public Doctor(String fname, String username,String password,String position) {
		firstName = fname;
		this.username = username;
		this.password = password;
		this.position = position;
	}
	
	public Doctor(String username) {
		this.username = username;
		File file = new File("src/DoctorRecords/" + username + ".txt");
		try {
			Scanner scan = new Scanner(file);
			name = scan.nextLine();
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Doctor() {
		// TODO Auto-generated constructor stub
	}
	
	//Authentication function which looks for the inputted username in the doctor records to check if account exists
	public String Authenticate(String username,String password) {
		String response =super.Authenticate("src/doctorRecords/" + username + ".txt", password);
		Doctor.name = super.name;
		Doctor.username = super.username;
		return response;
	}
	
	//Initializes the doctor's schedule for the first time by scanning the doctor's
	public List<String> getSchedule() {
		File file = new File("src/doctorRecords/" + username + ".txt");
		try {
			Scanner scan = new Scanner(file);
			scan.nextLine();scan.nextLine();scan.nextLine();scan.nextLine();
			int i = 0;
			while(scan.hasNextLine()) {
				String time = scan.nextLine(); 
				schedule.add(time.split("% With ")[0]);
				scheduleWithDetails.add(time);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedule;
	}
	
	//Returns doctor's current schedule
	public List<String> showSchedule() {
		return schedule;
	}
	
	//Takes in user's input from GUI and adds or deletes an entry on the doctor's schedule file
	public void updateSchedule() {
		
		try {
			String allfile = "";
			File file = new File("src/doctorRecords/" + username + ".txt");
			Scanner scan = new Scanner(file);
			allfile = scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine();
			scan.close();
			Writer wr = new FileWriter("src/doctorRecords/"+username + ".txt");
			wr.write(allfile);
			for(int i = 0;i < scheduleWithDetails.size();i++) {
				wr.write("\n" + scheduleWithDetails.get(i));
			}
			wr.flush();wr.close();
			System.out.println("WROTE TO FILE");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		wr.write(firstName + " " + lastName + "\n" + username + "\n" + password + "\n" + email);
		
	}
	
	//Creates a new doctor file in doctor Records
	public void create() {
		try {
			Writer wr = new FileWriter("src/doctorRecords/"+username + ".txt");
			wr.write(firstName.trim() + "\n" + username.trim() + "\n" + password.trim() + "\n" + position.trim());
			wr.flush();wr.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
