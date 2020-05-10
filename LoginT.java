import java.util.ArrayList;
import java.util.List;

//Author: Anthony Irizarry
//Date: 5/8/2020
//Purpose: Creates arraylist for the objects of the Login class which are a username, password and role to access the database
public class LoginT {

	//array of Login objects
	static Tickets robjs[] = new Tickets[10];
	
	//arraylist to hold spreadsheet rows and columns
	static ArrayList<List<String>> array= new ArrayList<>();	
	
	
	//Instance Variables
	private String username;
	private String password;
	private boolean role;
	//Getters and Setters
	
	
	//Username
	public String getusername() {
		return this.username;
	}
	
	public void setusername(String username) {
		this.username= username;
	}
	//Password
	public String getpassword() {
		return this.password;
	}
	
	public void setpassword(String password) {
		this.password= password;
	}
	
	//Role
	public boolean getRole() {
		return this.role;
	}
	
	
	
	
	
	
}
