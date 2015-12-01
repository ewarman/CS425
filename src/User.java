import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class User {

	public static final String URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	public static final String USER = "ewarman";
	public static final String PSWD = "A20317755";
	
	String username, password, name, phone, email, ccn;
	//CreditCard cc;
	
	public User() {
		username = "";
		password = "";
		name = "";
		ccn = "";
		phone = "";
		email = "";
	}
	
	public User(String u, String p, String n, String c, String ph, String e) {
		username = u;
		password = p;
		name = n;
		ccn = c;
		phone = ph;
		email = e;
	}
	
	public boolean login(String usr, String pswd) {
		try {// Load Oracle JDBC Driver 
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			 // Connect to Oracle Database 
			Connection conn = DriverManager.getConnection(URL, USER, PSWD);
			Statement st = conn.createStatement();
			st.executeQuery("SELECT * FROM AAHMED31.THEATERUSERS WHERE USERNAME='"+usr+"'");
			ResultSet rs = st.getResultSet();
			//if no user with that username returned
			if (!rs.next()) {
				return false;
			}
			else {
				username = rs.getString(1);
				password = rs.getString(2);
				name = rs.getString(3);
				ccn = rs.getString(4);
				phone = rs.getString(5);
				email = rs.getString(6);
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
