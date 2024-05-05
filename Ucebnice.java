
public class Ucebnice extends Kniha{
	int rocnik;
	
	public Ucebnice(String nazev, String autor, int rok, String typ, boolean dostupnost, int rocnik, int poradoveCislo) {
		super(nazev, autor, rok, typ, dostupnost, poradoveCislo);
		// TODO Auto-generated constructor stub
		this.rocnik = rocnik;
	}

	public int getRocnik() {
		return rocnik;
	}

	public void setRocnik(int rocnik) {
		this.rocnik = rocnik;
	}
	
}
