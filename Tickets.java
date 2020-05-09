
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

public class Tickets {

	//array of Bankrecords objects
	static Tickets robjs[] = new Tickets[10];
	
	//arraylist to hold spreadsheet rows and columns
	static ArrayList<List<String>> array= new ArrayList<>();	
	
	
	//Instance Variables
	private int tid;
	private Date start_Date;
	private Date end_Date;
	private String ticket_descrip;
	private String status;
	//Getters and Setters
	
	//id getter and setter
	public int gettid() {
		return this.tid;
	}
	
	public void settid(int tid) {
		this.tid= tid;
	}
	
	public Date getsDate() {
		return this.start_Date;
	}
	
	public void setsDate(Date start_Date) {
		this.start_Date= start_Date;
	}
	

	public Date geteDate() {
		return this.end_Date;
	}
	
	public void seteDate(Date end_Date) {
		this.end_Date= end_Date;
	}
	
	public String getTick() {
		return this.ticket_descrip;
	}
	
	public void setTick(String ticket_descrip) {
		this.ticket_descrip= ticket_descrip;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status= status;
	}
	
	
}
