package src;
import java.sql.Date;

public class Showing {

	public static final String URL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	public static final String USER = "ewarman";
	public static final String PSWD = "A20317755";
	
	String title;
	double price;
	int theater;
	int mid;
	int rid;
	int sid;
	Date date;
	
	public Showing() {
	}
	
	public Showing(double p, int mi, int ri, Date d, int si) {
		price = p;
		mid = mi;
		rid = ri;
		sid = si;
		title = "";
		theater = 0;
		date = d;
	}
	
	public String toString() {
		return "price = "+price+", title = "+title+", theater="+theater+", date="+date.toString();
	}
	
}
