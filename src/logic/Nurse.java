package logic;

public class Nurse extends User {
	
	//Authenticates Nurse logging in
	public String Authenticate(String username,String password) {
		return super.Authenticate("src/nurseRecords/" + username + ".txt", password);
	}


}
