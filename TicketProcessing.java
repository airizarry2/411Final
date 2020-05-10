//Author: Anthony Irizarry
//Date: 4/16/2020
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 


public class TicketProcessing extends JFrame implements ActionListener{
	
	Container contentPane = getContentPane();
	JPanel pnl = new JPanel();
	JPanel grid = new JPanel(new GridLayout(6,2));
	
	JButton b1, b2;
	
	public TicketProcessing() {
		
	b1= new JButton("Delete Tickets");
	b2= new JButton ("Close Tickets");
		
	
	add(b1);
	add(b2);
	
	
	 b1.addActionListener(this);
	 b2.addActionListener(this);
	 setSize(750,400);
	 setLayout(new GridLayout(2,2));
	}
	public void actionPerformed(ActionEvent ae)
	  {
		if(ae.getSource() == b1) {
			
		}
		if(ae.getSource()==b2) {
			
		}
	  }
	
	public static void main(String[] args) {
	Dao dao = new Dao();
	ArrayList <Tickets> ticks = new ArrayList <>();
	
	ArrayList <LoginT> login = new ArrayList<>();
	
   Tickets obj1 = new Tickets();
    LoginT obj2 = new LoginT();
	Scanner sc = new Scanner(System.in); 	
	
	String message = "Welcome to the Ticket System";
	JOptionPane.showMessageDialog(null,  message,
			"Welcome!", JOptionPane.PLAIN_MESSAGE);
	//Dialog Boxes
	String user = 
			JOptionPane.showInputDialog(null, "Enter Your Username: ");
	String pass = 
			JOptionPane.showInputDialog(null, "Enter Your Password: ");
	
	ResultSet rs= dao.checkUser(pass, user);
	
	
	boolean admin = false; 
	
	
    ArrayList<String> array1= new ArrayList<>();	
    array1.add("tid");
    array1.add("start_Date");
    array1.add("end_Date");
    array1.add("ticket_descrip");
    array1.add("status");
    
   
    ResultSet rs1= dao.retrieveRecords();
    
	try {
		
		
		
		
		
	if (rs.next()) {
		admin = rs.getBoolean("Role");
		
		// verify if a record match exists
		JOptionPane.showMessageDialog(null, "Username and Password exists");

		if (admin) {
			
			int question = 
					JOptionPane.showConfirmDialog(null, "Do you want to delete a ticket?", "Close?",  JOptionPane.YES_NO_OPTION);
			if (question == JOptionPane.YES_OPTION)
			{
				String num = 
						JOptionPane.showInputDialog(null, "Enter the ticket ID you wish to delete: ");
				dao.deleteRecords(Integer.parseInt(num));
			}
			
			int question5 = 
					JOptionPane.showConfirmDialog(null, "Do you want to update a ticket status?", "Close?",  JOptionPane.YES_NO_OPTION);
			if (question5 == JOptionPane.YES_OPTION) {
				String ticketnum1 = 
						JOptionPane.showInputDialog(null, "Enter the ticket id of such ticket:  ");
				String string2 = JOptionPane.showInputDialog(null, "Enter updated ticket status (Closed, open, reopened): ");
				dao.updateStatus(string2, Integer.parseInt(ticketnum1));
			}
			
			
			int question1 = 
					JOptionPane.showConfirmDialog(null, "Do you want to update a ticket?", "Close?",  JOptionPane.YES_NO_OPTION);
			if (question1 == JOptionPane.YES_OPTION) {
				String ticketnum = 
						JOptionPane.showInputDialog(null, "Enter the ticket id of such ticket:  ");
						
				String string1 = JOptionPane.showInputDialog(null, "Enter updated ticket description: ");
					dao.updateRecords(string1, Integer.parseInt(ticketnum));
			}
			int question3 = 
					JOptionPane.showConfirmDialog(null, "Do you want to view the records in a table in the console?", "Close?",  JOptionPane.YES_NO_OPTION);
			if (question3 == JOptionPane.YES_OPTION) {
				dao.retrieveRecords();
				dao.displayResults(rs1, array1 );
			}
			
			
		}else {
			String ticket = 
					JOptionPane.showInputDialog(null, "What seems to be the issue today?:  ");
			
			obj1.setTick(ticket);
			obj1.setStatus("Open");
			dao.insertRecords(ticks);

		}
		
		
	} 
	else {
		JOptionPane.showMessageDialog(null, "Please check Username and Password ");
	}

	}
	catch(SQLException e1) {
		e1.printStackTrace();
	} 
		

    
    ticks.add(obj1);
    obj1.setsDate(new Date(	System.currentTimeMillis()));
    obj1.seteDate(new Date(	System.currentTimeMillis()+604800000));
    login.add(obj2);
    obj2.setusername(user);
    obj2.setpassword(pass);
    
	//Tickets br= new Tickets();
	
	//dao.createTable();
	//dao.insertRecordsT(login);
	//perform inserts
	//ResultSet rs= dao.retrieveRecords();//fill result set object
	
	

	}



}
