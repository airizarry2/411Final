
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; 

//Author: Anthony Irizarry
//Date: 2/27/2020

public class LoginT {


	static Tickets robjs[] = new Tickets[10];
	
	//arraylist to hold spreadsheet rows and columns
	static ArrayList<List<String>> array= new ArrayList<>();	
	
	
	//Instance Variables
	private String username;
	private String password;
	private boolean role;
	//Getters and Setters
	
	//id getter and setter
	public String getusername() {
		return this.username;
	}
	
	public void setusername(String username) {
		this.username= username;
	}
	
	public String getpassword() {
		return this.password;
	}
	
	public void setpassword(String password) {
		this.password= password;
	}
	
	public void setRole(boolean role) {
		this.role=role;
	}
	
	public boolean getRole() {
		return this.role;
	}
	
	
	
	
	
	
}
