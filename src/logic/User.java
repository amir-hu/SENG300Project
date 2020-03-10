package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
	protected String name;
	protected String username;

	//checks to see if the user exists or if the information is correct
	public String Authenticate(String username,String password) {
		File file = new File(username);
		try {
			Scanner scan = new Scanner(file);
//			System.out.println(scan.nextLine());
			this.name = scan.nextLine();
			
			this.username = scan.nextLine();
			
			if(scan.nextLine().trim().equals(password)) {
				return "true";
			}
		} catch (FileNotFoundException e) {
			return "DNE";
		}
		
		return "false";
	}

	
	
}
