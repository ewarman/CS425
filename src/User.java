import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class User {

	public static final String URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	public static final String USER = "ewarman";
	public static final String PSWD = "A20317755";
	
	String username, password, name, phone, email, ccn, rewardLevel;
	int curPoints, totPoints;
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
	
	public void getPointsInfo() {
		totPoints = 0;
		curPoints = 0;
		rewardLevel = "Beginner";
		try {// Load Oracle JDBC Driver 
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			 // Connect to Oracle Database 
			Connection conn = DriverManager.getConnection(URL, USER, PSWD);
			Statement st = conn.createStatement();
			st.executeQuery("SELECT TOTAL_POINTS, CURRENT_POINTS FROM AAHMED31.POINTS WHERE USERNAME='"+username+"'");
			ResultSet rs = st.getResultSet();
			if (rs.next()) {
				totPoints = rs.getInt(1);
				curPoints = rs.getInt(2);
				Statement st2 = conn.createStatement();
				st2.executeQuery("SELECT LEVEL_NAME FROM AAHMED31.POINTLEVEL WHERE LEVEL_BOUNDARY < "+totPoints+ "ORDER BY LEVEL_BOUNDARY DESC");
				ResultSet rs2 = st2.getResultSet();
				if (rs2.next()) rewardLevel=rs2.getString(1);
			}
			conn.close(); 
		} catch (Exception ex) { 
			System.err.println(ex.getMessage()); 
		}
	}
	
	public void update(String pswd, String cc, String ph, String eml) {
		try {// Load Oracle JDBC Driver 
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			 // Connect to Oracle Database 
			Connection conn = DriverManager.getConnection(URL, USER, PSWD);
			Statement st = conn.createStatement();
			st.executeUpdate("UPDATE AAHMED31.THEATERUSERS SET PASSWORD='"+pswd+"',CCN='"+ccn+"',PHONE='"+ph+"',EMAIL='"+email+"' WHERE USERNAME='"+username+"'");
			conn.close(); 
		} catch (Exception ex) { 
			System.err.println(ex.getMessage()); 
		}
	}
	
	public boolean purchaseTicket(int price) {
		CreditCardCo auth = new CreditCardCo();
		int bal = auth.getBalance(ccn);
		if (bal<price) return true;
		else return false;
	}
}
