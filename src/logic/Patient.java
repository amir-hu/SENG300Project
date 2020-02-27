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
	
	
	public Patient(String fname,String lName, String username,String email,String password) {
		firstName = fname;
		lastName = lName;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public void create() {
		try {
			Writer wr = new FileWriter("src/Records/"+username + ".txt");
			wr.write(firstName + " " + lastName + "\n" + username + "\n" + password + "\n" + email);
			wr.flush();wr.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
