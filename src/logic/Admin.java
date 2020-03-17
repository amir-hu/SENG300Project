package logic;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Admin extends User {
	
	public static String name = "test";
	private static String username = "";
	public ArrayList<String> schedule = new ArrayList<String>(); 

	public String Authenticate(String username,String password) {
		String response =super.Authenticate("src/adminRecords/" + username + ".txt", password);
		Admin.name = super.name;
		Admin.username = super.username;
		return response;
	}

}
