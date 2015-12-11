package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Location {

	public static final String URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	public static final String USER = "ewarman";
	public static final String PSWD = "A20317755";
	
	ArrayList<HashMap<String,Integer>> roomArray = new ArrayList<HashMap<String, Integer>>();
	String th_address, th_city, th_state, th_zip, th_name;
	int th_id, room_id, room_num, room_cap;
	//TheaterInfo;
	
	public Location() {
		th_id = 0;
		th_address = "";
		th_city = "";
		th_state = "";
		th_zip = "";
		th_name = "";
	}
	
	public Location(int tid, String tadd, String c, String s, String z, String n) {
		th_id = tid;
		th_address = tadd;
		th_city = c;
		th_state = s;
		th_zip = z;
		th_name = n;
	}
	
	public boolean th_loc(int tid) {
		try {// Load Oracle JDBC Driver 
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			 // Connect to Oracle Database 
			Connection conn = DriverManager.getConnection(URL, USER, PSWD);
			//Location info
			Statement st = conn.createStatement();
			st.executeQuery("SELECT * FROM AAHMED31.LOCATIONS WHERE THEATER_ID='"+tid+"'");
			ResultSet rs = st.getResultSet();
			//Room info
			Statement room_st = conn.createStatement();
			room_st.executeQuery("SELECT * FROM AAHMED31.THEATERINFO WHERE THEATER_ID='"+tid+"'");
			ResultSet rooms = room_st.getResultSet();
			
			//if no location with that id returned
			if (!rs.next()) {
				return false;
			}
			else {
				String rid = "room_id", rn = "room_num", rc = "room_cap";
				th_id = rs.getInt(1);
				th_address = rs.getString(2);
				th_city = rs.getString(3);
				th_state = rs.getString(4);
				th_zip = rs.getString(5);
				th_name = rs.getString(6);
				while(rooms.next()){
					HashMap<String, Integer> hm = new HashMap<String, Integer>();
					room_id = rooms.getInt(1);
					room_num = rooms.getInt(3);
					room_cap = rooms.getInt(4);
					hm.put(rid, room_id);
					hm.put(rn, room_num);
					hm.put(rc, room_cap);
					roomArray.add(hm);
				}
			}
			conn.close(); 
		} catch (Exception ex) { 
			System.err.println(ex.getMessage()); 
		}
		if (th_id == tid) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	public void getRoomInfo(int rid) {
		room_num = 0;
		room_cap = 0;
		try {// Load Oracle JDBC Driver 
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			 // Connect to Oracle Database 
			Connection conn = DriverManager.getConnection(URL, USER, PSWD);
			Statement st = conn.createStatement();
			st.executeQuery("SELECT ROOM_NUM, CAPACITY FROM AAHMED31.THEATERINFO WHERE ROOM_ID='"+rid+"'");
			ResultSet rs = st.getResultSet();
			if (rs.next()) {
				room_num = rs.getInt(1);
				room_cap = rs.getInt(2);
			}
			conn.close(); 
		} catch (Exception ex) { 
			System.err.println(ex.getMessage()); 
		}
	}
	*/
}
