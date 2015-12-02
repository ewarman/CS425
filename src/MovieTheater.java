import java.util.*;
import java.sql.*;

public class MovieTheater {
	public static final String DBURL =
			"jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
			 public static final String DBUSER = "";
			 public static final String DBPASS = "";

			 double age;
			 double discount;

			 double total;
			 double partial;

			 public MovieTheater(){
			 age = -1.0;
			 discount = 0.0;

			 total = 464.0;
			 partial = 140.0;
			 }
			 public static void main(String[] args){
			 MovieTheater mt = new MovieTheater();
			 mt.run();
			 }

			 public void run(){
			 System.out.println("\t\t------ Recreation Center Revenue Calculator ------");
			 System.out.println("Please enter age group, e.g. 50 means 50+: ");
			 Scanner sc = new Scanner(System.in);
			 while(true){
			 try{
			 age = sc.nextDouble();
			 if(age<0){
			 throw new NumberFormatException("Age should be a positive interger.");
			 }else{
			 break;
			 }
			 }catch(Exception e){
			 System.out.println(e);
			 System.out.println("Please try again:");
			 sc.nextLine();
			 }
			 }

			 System.out.println("Please enter discount percentage, e.g. 20 means 20% off: ");
			 while(true){
			 try{
			 discount = sc.nextDouble();
			 if(discount<0){
			 throw new NumberFormatException("Discount should be a positive interger.");
			 }else{
			 break;
			 }
			 }catch(Exception e){
			 System.out.println(e);
			 System.out.println("Please try again:");
			 sc.nextLine();
			 }
			 }

			 getInfo(age);
			 calculate(discount);
			 }

			 public void getInfo(double age){
			 Connection con = null;
			 Statement statement = null;

			 try{
			 // Load Oracle JDBC Driver
			 DriverManager.registerDriver(new
			oracle.jdbc.driver.OracleDriver());

			 // Connect to Oracle Database
			 con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

			 statement = con.createStatement();

			 // Execute a SELECT query on Oracle Dummy DUAL Table. Useful for retrieving system values
			 // Enables us to retrieve values as if querying from a table
			 CallableStatement cstmt = con.prepareCall("{call Q6(?,?,?)}");
			 cstmt.setDouble(1,age);
			 cstmt.registerOutParameter(2,Types.DOUBLE);
			 cstmt.registerOutParameter(3,Types.DOUBLE);
			 cstmt.execute();

			 total = cstmt.getDouble(2);
			 partial = cstmt.getDouble(3);

			 cstmt.close();
			 statement.close();
			 con.close();
			 }catch(SQLException se){
			 //Handle errors for JDBC
			 se.printStackTrace();
			 }catch(Exception e){
			 //Handle errors for Class.forName
			 e.printStackTrace();
			 }finally{
			 //finally block used to close resources
			 try{
			 if(statement!=null)
			 statement.close();
			 }catch(SQLException se2){
			 }// nothing we can do
			 try{
			 if(con!=null)
			 con.close();
			 }catch(SQLException se){
			 se.printStackTrace();
			 }//end finally try
			 }//end try
			 }

			 public void calculate(double discount){
			 double discountamount = partial * (discount) / 100.0;
			 double finalvalue = total - discountamount;
			 System.out.println("The revenue will decrease from "+total+" to "+finalvalue+". We make $"+discountamount+" less.");
			 }

}
