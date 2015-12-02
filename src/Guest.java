import java.sql.Date;


public class Guest {

	CreditCard cc;
	
	public Guest() {
		cc = new CreditCard();
	}
	public Guest(String ccn, String cv, String n, String ct, Date e, String s1, String s2, String c, String st, String z) {
		cc = new CreditCard(ccn, cv, n, ct, e, s1, s2, c, st, z);
	}
	
	public boolean purchaseTicket(int price) {
		CreditCardCo auth = new CreditCardCo();
		int bal = auth.getBalance(cc.ccn);
		if (bal<price) return true;
		else return false;
	}
}
