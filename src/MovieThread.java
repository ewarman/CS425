package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class MovieThread {
	
	public static final String URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	public static final String USER = "ewarman";
	public static final String PSWD = "A20317755";
	
	ArrayList<Comment> comments;
	int id;
	String username, movie, star, director, text;
	
	public MovieThread(int i, String u, String m, String s, String d, String t) {
		id = i;
		username = u;
		movie = m;
		star = s;
		director = d;
		text = t;
		comments = new ArrayList<Comment>();
		try {// Load Oracle JDBC Driver 
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			 // Connect to Oracle Database 
			Connection conn = DriverManager.getConnection(URL, USER, PSWD);
			Statement st = conn.createStatement();
			st.executeQuery("SELECT COMMENT_NUMBER, USERNAME, TEXT FROM AAHMED31.MOVIECOMMENTS WHERE THREAD_ID="+i);
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				Comment comment = new Comment(rs.getInt(1), rs.getString(2), rs.getString(3));
				comments.add(comment);
			}
			conn.close(); 
		} catch (Exception ex) { 
			System.err.println(ex.getMessage()); 
		}
	}

}