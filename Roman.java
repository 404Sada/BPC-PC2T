
public class Roman extends Kniha {
	String zanr;
	
	public Roman(String nazev, String autor, int rok,String typ, boolean dostupnost, String zanr, int poradoveCislo) {
		super(nazev, autor, rok, typ, dostupnost, poradoveCislo);
		// TODO Auto-generated constructor stub
		this.zanr = zanr;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	
	
}
