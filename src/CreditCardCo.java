import java.sql.*;
import oracle.jdbc.*;

public class CreditCardCo {

	public static final String URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	// insert username and password
	public static String USER = "";
	public static String PSWD = "";
	
	public CreditCardCo() {
	}
	
	public int getBalance(String c) {
		int bal = 0;
		String query = "SELECT BALANACE FROM AAHMED31.CREDITCARDCOMPANY WHERE CCN = "+c;
		try {// Load Oracle JDBC Driver 
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			 // Connect to Oracle Database 
			Connection conn = DriverManager.getConnection(URL, USER, PSWD);
			Statement st = conn.createStatement(); 
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();
			bal = rs.getInt(1);
			System.out.println(bal);
			conn.close();
		} catch (Exception ex) { 
			System.err.println(ex.getMessage()); 
		}
		return bal;
	}

}