package logic;

public class Doctor extends User {

	public String Authenticate(String username,String password) {
		return super.Authenticate("src/doctorRecords/" + username + ".txt", password);
	}

}
