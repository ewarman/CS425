/*
CS 425 Homework 2 - Emily Warman, Ayesha Ahmed, Andrew Caron

4.	Write the program that will allow the manager to set or update the schedule of a worker. 
The program will not allow the manager to put two workers to work on the same thing in the same 
theatre location at the same time.
*/

import java.util.*;
import java.sql.*;
import oracle.jdbc.*;

public class Q4{
	public static final String URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	// insert username and password
	public static final String USER = "ewarman";
	public static final String PSWD = "A20317755";


	public static void main(String [] args) throws SQLException{
		Q4 q4 = new Q4();
		q4.run();
	}
	
	public void run() throws SQLException{	
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter worker ID:");
		int id = scan.nextInt();
		System.out.println("Enter date:");
		String date = scan.next();
		System.out.println("Enter time:");
		String time = scan.next();
		System.out.println("Enter job type:");
		String type = scan.next();
		System.out.println("Enter theater:");
		int theater = scan.nextInt();
		
		//if the shift can be scheduled, schedule it
		schedule(date, time, type, theater, id);
	}

	public void schedule(String date, String time, String type, int theater, int id) throws SQLException{
		Connection conn = null;
		CallableStatement cstmt = null;
		try {//connect to DB
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection(URL, USER, PSWD);
			cstmt = conn.prepareCall("{call Q4(?,?,?,?,?)}");
			cstmt.setString(1, date);	
			cstmt.setString(2, time);	
			cstmt.setString(3, type);
			cstmt.setInt(4, theater);
			cstmt.setInt(5, id);
		
			cstmt.execute();
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}finally{
		      //close resources
		      try{
		         if(cstmt!=null) cstmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null) conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		 }
	}
}