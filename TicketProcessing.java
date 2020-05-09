//Author: Anthony Irizarry
//Date: 4/16/2020
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner; 


public class TicketProcessing extends Tickets{
	
	public static void main(String[] args) {
	
	
	ArrayList <Tickets> ticks = new ArrayList <>();
	
	
	Scanner sc = new Scanner(System.in); 	
		
	System.out.println("Ticket Description: ");
	// String input 
    String description = sc.nextLine(); 

    Tickets obj1 = new Tickets();
    obj1.setTick(description);
    ticks.add(obj1);
    obj1.setsDate(new Date(2020,5,8));
    obj1.seteDate(new Date(2020,5,10));
    
	//Tickets br= new Tickets();
	Dao dao = new Dao();
	//dao.createTable();
	dao.insertRecords(ticks); //perform inserts
	//ResultSet rs= dao.retrieveRecords();//fill result set object
	
	

	}
	
}
