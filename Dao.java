//Author: Anthony Irizarry
//Date: 4/16/2020
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Dao {
    //Declare DB objects 
    DBConnect conn = null;
    Statement stmt = null;

    // constructor
    public Dao() { //create db object instance
        conn = new DBConnect();
    }


    public void createTable() {
        try {
            // Open a connection
            System.out.println("Connecting to a selected database to create Table...");
            System.out.println("Connected database successfully...");

            // Execute create query
            System.out.println("Creating table in given database...");

            stmt = conn.connect().createStatement();

            String sql = "CREATE TABLE aIriz_tickets1" +
                    "(tid INTEGER not NULL AUTO_INCREMENT, " +
                    " ticket_descrip VARCHAR (255), " +
                    " start_date DATE, " +
                    " end_date DATE, " +
                    "status VARCHAR (20), " + 
                    " PRIMARY KEY ( tid ))";

        	String sql2 = "CREATE TABLE aIriz_users" +
        				" (username VARCHAR (20), " +
        				" password VARCHAR (20))";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            System.out.println("Created table in given database...");
            conn.connect().close(); //close db connection 
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }

    // INSERT INTO METHOD
    public void insertRecords(ArrayList <Tickets> ticks) {
        try {
            // Execute a query
        	System.out.println("Connecting to a selected database for Inserts");
        	System.out.println("Connected database successfully...");
            System.out.println("Inserting records into the table...");
            stmt = conn.connect().createStatement();
            String sql = null;
            
            // Include all object data to the database table
            for (int i = 0; i < ticks.size(); ++i) {

                // finish string assignment to insert all object data 
               

                sql = "INSERT INTO aIriz_tickets1(tid,start_Date, end_Date, ticket_descrip) " +
                	       "VALUES (' "+ticks.get(i).gettid()+" ', ' "+ticks.get(i).getsDate()+" ', ' "+ticks.get(i).geteDate()+" ',' "+ticks.get(i).getTick()+" ' )";
               
                stmt.executeUpdate(sql);
            }
            
            
            System.out.println("Records inserted into the table...");
            conn.connect().close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void insertRecordsT(ArrayList <LoginT> login) {
    	try {
    	System.out.println("Connecting to a selected database for Inserts");
    	System.out.println("Connected database successfully...");
        System.out.println("Inserting records into the table...");
        stmt = conn.connect().createStatement();
        String sql2 = null;
    	
        for (int i = 0; i < login.size(); ++i) {

    	 sql2= "INSERT INTO aIriz_users(username,password)" +
         		"VALUES (' "+ login.get(i).getusername()+" ',' "+login.get(i).getpassword()+" ')";
    	
    	 stmt.executeUpdate(sql2);
        }
    	 System.out.println("Records inserted into the table...");
         conn.connect().close();
     } catch(SQLException se) {
         se.printStackTrace();
     }
    
    }
    

    public ResultSet retrieveRecords() {
        ResultSet rs = null;
        System.out.println("Connecting to a selected database for Record retrievals...");
        try {
        	stmt = conn.connect().createStatement();
			System.out.println("Connected to database sucessfully...");

        	
        }
        catch (SQLException e){
        	e.printStackTrace();
        	
        }
        
        String sql = "SELECT id, income, pep from a_Iriz_tab11 order by pep desc";
        
        try {
        	rs = stmt.executeQuery(sql);
        	System.out.println("Records retrieved from the database...");
        	System.out.println("Creating Select statement...");
        }
        catch (SQLException e) {
        	e.printStackTrace();
        }
        
        try {
        	conn.connect().close();	
        }
        
        catch (SQLException e) {
        	
        	e.printStackTrace();
        }
        
        return rs;
    }

    
    public void deleteRecords (int tid) {
    	try {
    
    	// Execute delete  query
        System.out.println("Creating statement...");
        stmt = conn.connect().createStatement();
        String sql = "DELETE FROM aIriz_tickets1  " +
                     "WHERE tid = " + tid ;
      
       int response = JOptionPane.showConfirmDialog(null, "Delete ticket # " + tid + "?",
                                 "Confirm",  JOptionPane.YES_NO_OPTION, 
                                 JOptionPane.QUESTION_MESSAGE);
       if (response == JOptionPane.NO_OPTION) {
         System.out.println("No record deleted");
      } else if (response == JOptionPane.YES_OPTION) {
        stmt.executeUpdate(sql);
        System.out.println("Record deleted");
      } else if (response == JOptionPane.CLOSED_OPTION) {
        System.out.println("Request cancelled");
      }

} catch (SQLException e) {
	
	e.printStackTrace();
}

    }
    
    
public void updateRecords(String ticket_descrip) {
	try {
	    
    // Execute update  query
    System.out.println("Creating update statement...");
    stmt = conn.connect().createStatement();
    String sql = "UPDATE airiz_tickets1" +
                 "SET ticket_descrip = ‘Can’t win ‘’em all’ WHERE tid in (10, 11)";
    stmt.executeUpdate(sql);

	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}





public ResultSet checkUser(String password, String username) {
	try {
	stmt = conn.connect().createStatement();
    String queryString = "SELECT username, password, Role FROM aIriz_users where username=? and password=?";
	PreparedStatement ps;
	ResultSet results = null;
	
		// set up prepared statements to execute query string cleanly and safely
		ps = (PreparedStatement)conn.connect().prepareStatement(queryString);
		ps.setString(1, username);
		ps.setString(2, password);
		results = ps.executeQuery();
		return results;
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	return null;
}


}
