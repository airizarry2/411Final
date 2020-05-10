//Author: Anthony Irizarry
//Date: 5/9/2020
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

    //Constructor
    public Dao() { //create DB object instance
        conn = new DBConnect();
    }

    //Method creates tables in the database
    public void createTable() {
        try {
            // Open a connection
            System.out.println("Connecting to a selected database to create Table...");
            System.out.println("Connected database successfully...");

            // Execute create query
            System.out.println("Creating table in given database...");

            stmt = conn.connect().createStatement();

            //Ticket table creation
            String sql = "CREATE TABLE aIriz_tickets1" +
                    "(tid INTEGER not NULL AUTO_INCREMENT, " +
                    " ticket_descrip VARCHAR (255), " +
                    " start_date DATE, " +
                    " end_date DATE, " +
                    "status VARCHAR (20), " + 
                    " PRIMARY KEY ( tid ))";
            
            //User table creation
        	String sql2 = "CREATE TABLE aIriz_users" +
        				" (username VARCHAR (20), " +
        				" password VARCHAR (20))";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            System.out.println("Created table in given database...");
            conn.connect().close(); //close DB connection 
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }

    //Method inserts ticket records into respective database
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
         
                sql = "INSERT INTO aIriz_tickets1(tid,start_Date, end_Date, ticket_descrip, status) " +
                	       "VALUES (' "+ticks.get(i).gettid()+" ', ' "+ticks.get(i).getsDate()+" ', ' "+ticks.get(i).geteDate()+" ',' "+ticks.get(i).getTick()+" ',' "+ticks.get(i).getStatus()+" ')";
                stmt.executeUpdate(sql);
            }
            
            
            System.out.println("Records inserted into the table...");
            conn.connect().close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    //Method inserts Users into database with username and password (not in use currently)
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
    
    //Method retrieves records from database to display onto console
    public ResultSet retrieveRecords() {
        ResultSet rs1 = null;
        System.out.println("Connecting to a selected database for Record retrievals...");
        try {
        	stmt = conn.connect().createStatement();
			System.out.println("Connected to database sucessfully...");

        	
        }
        catch (SQLException e){
        	e.printStackTrace();
        	
        }
        
        String sql = "SELECT tid,start_Date, end_Date, ticket_descrip, status from aIriz_tickets1 order by tid asc";
        
        try {
        	rs1 = stmt.executeQuery(sql);
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
        
        return rs1;
    }

    //Method deletes records from respective database
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
    
//Method updates ticket status (admin only)
public void updateStatus (String status, int tid) {
    	try {
    		// Execute update  query
    	    System.out.println("Creating update statement...");
    	    stmt = conn.connect().createStatement();
    	    String sql = "UPDATE airiz_tickets1 SET status = '"+status+"' WHERE tid=?";
    	    PreparedStatement ps1;
    	    ps1 = (PreparedStatement)conn.connect().prepareStatement(sql);
    	    ps1.setInt(1, tid);
    	    ps1.executeUpdate();
    	}
    	
    	catch(SQLException e) {
    		e.printStackTrace();
    	
    	}
    }
    
//Method updates ticket description (admin only)
public void updateRecords(String ticket_descrip, int tid) {
	try {
	    
    // Execute update  query
    System.out.println("Creating update statement...");
    stmt = conn.connect().createStatement();
    String sql = "UPDATE airiz_tickets1 SET ticket_descrip = '"+ticket_descrip+"' WHERE tid=?";
    PreparedStatement ps1;
    ps1 = (PreparedStatement)conn.connect().prepareStatement(sql);
    ps1.setInt(1, tid);
    ps1.executeUpdate();

	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}




//Method checks to see if user is in the User database and allows for authentication 
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


//Method displays results of database onto console
public static void displayResults(ResultSet rs1, ArrayList<String> ticks) throws SQLException {
    ArrayList<String> headerFormat = new ArrayList<String>();
    System.out.print("\n");
    for(int i = 0; i < ticks.size(); i++) {
        headerFormat.add("%-" + (ticks.get(i).length() + 8) + "s");
        System.out.format(headerFormat.get(i), ticks.get(i));
    }
    System.out.print("\n");

    while (rs1.next()) {
        for(int i = 0; i < ticks.size(); i++) {
            System.out.format(headerFormat.get(i), rs1.getString(ticks.get(i)));
        }
        System.out.print("\n");
    }
    System.out.print("\n");
}

}
