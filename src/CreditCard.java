import java.sql.Date;

public class CreditCard {

	String ccn, cvv, name, cardType, street1, street2, city, state, zip; 
	Date expDate;
	
	public CreditCard() {
		ccn = "";
		cvv = "";
		name = "";
		cardType = "";
		street1 = "";
		street2 = "";
		expDate = Date.valueOf("2000-01-01");
		city = "";
		state = "";
		zip = "";
	}
	
	public CreditCard(String cc, String cv, String n, String ct, Date e, String s1, String s2, String c, String st, String z) {
		ccn = cc;
		cvv = cv;
		name = n;
		cardType = ct;
		street1 = s1;
		street2 = s2;
		expDate = e;
		city = c;
		state = st;
		zip = z;
	}
}
