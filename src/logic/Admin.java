package logic;

public class Admin extends User {
	
	public String Authenticate(String username,String password) {
		return super.Authenticate("src/adminRecords/" + username + ".txt", password);
	}

}
