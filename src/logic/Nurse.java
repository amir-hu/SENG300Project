package logic;

public class Nurse extends User {
	
	public String Authenticate(String username,String password) {
		return super.Authenticate("src/nurseRecords/" + username + ".txt", password);
	}


}
