package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {

	//checks to see if the user exists or if the information is correct
	public String Authenticate(String username,String password) {
		File file = new File(username);
		try {
			Scanner scan = new Scanner(file);
//			System.out.println(scan.nextLine());
//			System.out.println(scan.nextLine());
			scan.nextLine();
			scan.nextLine();
			if(scan.nextLine().equals(password)) {
				return "true";
			}
		} catch (FileNotFoundException e) {
			System.out.println("DASFAS");
			return "DNE";
		}
		
		return "false";
	}

	
	
}
