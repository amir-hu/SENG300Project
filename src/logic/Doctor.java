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
	private String userName;
	private String email;
	private String password;
	private String position;

	public static String name = "test";
	private static String username = "";
	public ArrayList<String> schedule = new ArrayList<String>(); 
	public ArrayList<String> scheduleWithDetails= new ArrayList<String>(); 
	
	public Doctor(String fname, String username,String password,String position) {
		firstName = fname;
		this.userName = username;
		this.password = password;
		this.position = position;
	}
	
	public Doctor() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String Authenticate(String username,String password) {
		String response =super.Authenticate("src/doctorRecords/" + username + ".txt", password);
		Doctor.name = super.name;
		Doctor.username = super.username;
		return response;
	}
	
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
	
	public List<String> showSchedule() {
		return schedule;
	}
	
	
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
	

	public void create() {
		try {
			Writer wr = new FileWriter("src/doctorRecords/"+userName + ".txt");
			wr.write(firstName.trim() + "\n" + userName.trim() + "\n" + password.trim() + "\n" + position.trim());
			wr.flush();wr.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
