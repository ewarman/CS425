import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Admin {

	public static final String URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	public static final String USER = "ewarman";
	public static final String PSWD = "A20317755";
	
	int id, theater;
	String type, username, password, name, address, phone, ssn;
	
	public Admin() {
		type = "";
		username = "";
		password = "";
		name = "";
		address = "";
		phone = "";
		ssn = "";
		id = 0;
		theater = 0;
	}
	
	public Admin(int i, int th, String t, String u, String p, String n, String a, String ph, String s) {
		type = t;
		username = u;
		password = p;
		name = n;
		address = a;
		phone = ph;
		ssn = s;
		id = i;
		theater = th;
	}
	
	public String toString() {
		return "Logged in as "+username+
				"\n password: " +password+
				"\n name: " +name+
				"\n address: "+address+
				"\n phone: "+phone+
				"\n ssn: "+ssn+
				"\n ID: "+id+
				"\n theater: "+theater;
	}
	
	public boolean login(String usr, String pswd) {
		try {// Load Oracle JDBC Driver 
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			 // Connect to Oracle Database 
			Connection conn = DriverManager.getConnection(URL, USER, PSWD);
			Statement st = conn.createStatement();
			st.executeQuery("SELECT * FROM AAHMED31.MANAGEMENT WHERE USERNAME = '"+usr+"'");
			ResultSet rs = st.getResultSet();
			//if no user with that username returned
			if (!rs.next()) {
				return false;
			}
			else {
				id = rs.getInt(1);
				theater = rs.getInt(2);
				type = rs.getString(3);
				username = rs.getString(4);
				password = rs.getString(5);
				name = rs.getString(6);
				address = rs.getString(7);
				phone = rs.getString(8);
				ssn = rs.getString(9);
			}
			conn.close(); 
		} catch (Exception ex) { 
			System.err.println(ex.getMessage()); 
		}
		if (username.equals(usr) && password.equals(pswd)) {
			return true;
		}
		else {
			return false;
		}
	}
}
