//Author: Anthony Irizarry
//Date: 4/16/2020
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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




}
