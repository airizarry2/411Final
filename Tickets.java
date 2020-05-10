import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//Author: Anthony Irizarry
//Date: 5/9/2020
//Purpose: Creates an arraylist for the objects of the Tickets class which are a ticket ID, start date, end date, ticket description and ticket status
public class Tickets {

	//array of Ticket objects
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
	//Ticket ID
	public int gettid() {
		return this.tid;
	}
	
	public void settid(int tid) {
		this.tid= tid;
	}
	
	//Ticket Start date
	public Date getsDate() {
		return this.start_Date;
	}
	
	public void setsDate(Date start_Date) {
		this.start_Date= start_Date;
	}
	
	//Ticket End date
	public Date geteDate() {
		return this.end_Date;
	}
	
	public void seteDate(Date end_Date) {
		this.end_Date= end_Date;
	}
	
	//Ticket description
	public String getTick() {
		return this.ticket_descrip;
	}
	
	public void setTick(String ticket_descrip) {
		this.ticket_descrip= ticket_descrip;
	}
	
	//Ticket Status 
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status= status;
	}
	
	
}
