package logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class Patient extends User {
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	
	//Patient account info
	public Patient(String fname,String lName, String username,String email,String password) {
		firstName = fname;
		lastName = lName;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Patient() {
		// TODO Auto-generated constructor stub
	}
	
	//Checks if sign in matches account in patientRecords
	public String Authenticate(String username,String password) {
		return super.Authenticate("src/patientRecords/" + username + ".txt", password);
	}

	//Creates new patient account in patientRecords folder
	public void create() {
		try {
			Writer wr = new FileWriter("src/patientRecords/"+username + ".txt");
			wr.write(firstName + " " + lastName + "\n" + username + "\n" + password + "\n" + email + "\n");
			wr.flush();wr.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	//Retrieves account info
	public String getRecords(String username) {
		File file = new File("src/patientRecords/Records/"+username + "Records.txt");
		String records = "";
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				records = records + scan.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			
		}
		return records;
	}
	
	public void setRecord(String username,String record) {
		try {
			Writer wr = new FileWriter("src/patientRecords/Records/"+username + "Records.txt");
			wr.write(record);
			wr.flush();wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
