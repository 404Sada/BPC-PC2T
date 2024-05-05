import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


import java.util.Scanner;

public class Databaze {
	public Databaze()
	{
		prvkyDatabaze=new TreeMap<String,Kniha>();
		sc=new Scanner(System.in);
	}
	
	public static int counter = 0;
	
	private ArrayList<Kniha> KnihyOdAutora(String autor) {
		ArrayList<Kniha> Seznam = new ArrayList<Kniha>();
		
		for(Map.Entry<String,Kniha> entry : prvkyDatabaze.entrySet()) {
			  String key = entry.getKey();
			  Kniha value = entry.getValue();
			  
			  if (value.getAutor().equals(autor)) {
			  Seznam.add(value);
			 }
		}	  
		return Seznam;
	}
	
	public void setKniha()
	{
		System.out.println("Zadejte nazev knihy");
		String nazev=kontrolaVstupu.prazdnyString(sc);
		System.out.println("Zadejte autora knihy");
		String autor=kontrolaVstupu.kontrolaStringu(sc);
		System.out.println("Zadejte rok vydani");
		int rok=kontrolaVstupu.pouzeCelaCisla(sc);
		System.out.println("Zadejte typ knihy");
		System.out.println("1.....Román");
		System.out.println("2.....Učebnice");
		int volba=kontrolaVstupu.pouzeCelaCisla(sc);
		if (volba < 1 || volba > 2) {
			System.out.println("Zvolte '1' pro Román nebo '2' pro Učebnici");
			volba=kontrolaVstupu.pouzeCelaCisla(sc);
		}
		
		switch(volba) {
			case 1:
				String typ = "Román";
				System.out.println("Zadejte zanr knihy");
				sc.nextLine();
				String zanr=kontrolaVstupu.kontrolaStringu(sc);
				prvkyDatabaze.put(nazev, new Roman(nazev,autor,rok,typ,true,zanr,counter));
				break;
			case 2:
				typ = "Učebnice";
				System.out.println("Zadejte, pro jaky rocnik je urcena");
				int rocnik=kontrolaVstupu.pouzeCelaCisla(sc);
					if (rocnik < 0 || rocnik > 9) {
						System.out.println("Rozsah rocniku je od 1 do 9, zadejte ho prosím znovu");
						rocnik=kontrolaVstupu.pouzeCelaCisla(sc);
					}
				prvkyDatabaze.put(nazev, new Ucebnice(nazev,autor,rok,typ,true,rocnik,counter));
				break;
				}
		counter++;
	}
	
	public void ulozeniKnihyDoSouboru(String nazev,String autor,int rok,String typ,boolean dostupnost, String zanr,int rocnik) {
		FileWriter fw = null; BufferedWriter out = null;
		try {
			fw = new FileWriter(nazev + ".txt");
			out = new BufferedWriter(fw);
			out.write(nazev + ";" + autor + ";" + rok + ";" + typ + ";" + dostupnost + ";" + zanr + ";" + rocnik + ";");
			out.close(); fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void nacteniKnihyZeSouboru(String nazev) {
		FileReader fr = null; BufferedReader in = null;
		try {
			fr = new FileReader(nazev + ".txt");
			in = new BufferedReader(fr);
			
			String celyText = in.readLine();
			String[] casti = celyText.split(";");
			nazev = casti [0];
			String autor = casti [1];
			int rok = Integer.parseInt(casti[2]);
			String typ = casti[3];
			boolean dostupnost;
			String zanr = null;
			int rocnik;
			if (casti[4] == "true") {
				dostupnost = true;
			}
			else {
				dostupnost = false;
			}
			if (typ == "Roman") {
				zanr = casti [5];
				prvkyDatabaze.put(nazev, new Roman(nazev,autor,rok,typ,true,zanr,counter));
			}
			else
			{
				rocnik = Integer.parseInt(casti[6]);
				prvkyDatabaze.put(nazev, new Ucebnice(nazev,autor,rok,typ,true,rocnik,counter));
			}
			counter++;
			
			
			
		} catch (IOException e) {
			System.out.println("Neplatný název souboru");
		}
		
	}
			
		
	
	public void vypisVsechVypujcenychKnih() {
		
		for(Map.Entry<String,Kniha> entry : prvkyDatabaze.entrySet()) {
			  String key = entry.getKey();
			  Kniha value = entry.getValue();
			  
			  if (value.isDostupnost() == false) {
				    System.out.println("Nazev:       " + value.getNazev());
				  	System.out.println("Autor:       " + value.getAutor());
					System.out.println("Rok vydani:  " + value.getRok());
					System.out.println("Typ:         " + value.getTyp());
					System.out.println("Dostupnost:  " + value.isDostupnost());
					System.out.println("");			
			}
		}
	}
	
	public void chronologickePoradi(String autor) {
		
		Kniha hledanyAutor = prvkyDatabaze.get(autor); 
		if (hledanyAutor == null) {
			System.out.println("Zadaný autor není v knihovně evidován");
			return;
		} 
				
		ArrayList<Kniha> Poradi = KnihyOdAutora(autor);
		Collections.sort(Poradi);
		
		for(int i = 0; i < Poradi.size(); i++ ) {
			Kniha value = Poradi.get(i);
				System.out.println("Nazev:       " + value.getNazev());
			  	System.out.println("Autor:       " + value.getAutor());
				System.out.println("Rok vydani:  " + value.getRok());
				System.out.println("Typ:         " + value.getTyp());
				System.out.println("Dostupnost:  " + value.isDostupnost());
				System.out.println("");	
			}
		}
	
		
	public Kniha getKniha(String nazev) 
	{
		return prvkyDatabaze.get(nazev);
	}
	
	public void delKniha(String nazev) 
	{
		Kniha smaz = prvkyDatabaze.get(nazev); 
		if (smaz == null) {
			System.out.println("Zadaná kniha neexistuje");
			return;
		} 
		prvkyDatabaze.remove(nazev);
		System.out.println("Kniha byla smazána");
			
	}
	
	public void vypisDleAutora(String hledanyZanr) {
		
		Kniha autor = prvkyDatabaze.get(hledanyZanr); 
		if (autor == null) {
			System.out.println("Zadaný žánr se nevyskytuje v knihovně");
			return;
		} 
		
		for(Map.Entry<String,Kniha> entry : prvkyDatabaze.entrySet()) {
			  String key = entry.getKey();
			  Kniha value = entry.getValue();
			  
		if (value instanceof Roman && ((Roman) value).getZanr().equals(hledanyZanr)) { 
			System.out.println("Knihy zanru " + hledanyZanr + ":");
				System.out.println("Nazev:       " + value.getNazev());
				System.out.println("Autor:       " + value.getAutor());
				System.out.println("Rok vydani:  " + value.getRok());
				System.out.println("Typ:         " + value.getTyp());
				System.out.println("Dostupnost:  " + value.isDostupnost());
				System.out.println("");
			}
		}
	}
	
	public void vypisVsechKnih() {
		for(Map.Entry<String,Kniha> entry : prvkyDatabaze.entrySet()) {
			  String key = entry.getKey();
			  Kniha value = entry.getValue();
			  
			  if (value instanceof Roman) {
				    System.out.println("Nazev:       " + value.getNazev());
					System.out.println("Autor:       " + value.getAutor());
					System.out.println("Rok vydani:  " + value.getRok());
					System.out.println("Typ:         " + value.getTyp());
					System.out.println("Dostupnost:  " + value.isDostupnost());
					System.out.println("Zanr:        "+ ((Roman) value).getZanr());
					System.out.println("");
				} else { 
					System.out.println("Nazev:       " + value.getNazev());
					System.out.println("Autor:       " + value.getAutor());
					System.out.println("Rok vydani:  " + value.getRok());
					System.out.println("Typ:         " + value.getTyp());
					System.out.println("Dostupnost:  " + value.isDostupnost());
					System.out.println("Rocnik:      "+ ((Ucebnice) value).getRocnik());
					System.out.println("");
				}
			}
		}
	
	public boolean upravKnihu(String nazev) 
	{
		Kniha uprav = prvkyDatabaze.get(nazev); 
		if (uprav == null) {
			System.out.println("Zadaná kniha neexistuje");
			return false;
		} 
		System.out.println("Zadejte co chcete v knize upravit");
		System.out.println("1.....Upravit autora");
		System.out.println("2.....Upravit rok vydání");
		System.out.println("3.....Upravit stav dostupnosti");
		System.out.println("4.....Vypůjčit knihu");
		System.out.println("5.....Vrátit knihu");
		int volba=kontrolaVstupu.pouzeCelaCisla(sc);
		switch (volba) {
		case 1: 
			System.out.println("Zadejte noveho autora");
			sc.nextLine();
			String autor=kontrolaVstupu.kontrolaStringu(sc);
			uprav.setAutor(autor); 
		break;
		
		case 2: 
			System.out.println("Zadejte novy rok vydani");
			int rocnik=kontrolaVstupu.pouzeCelaCisla(sc);
			uprav.setRok(rocnik);
		break;
		
		case 3: 
		 	boolean dostupnost = uprav.zmenaDostupnosti();
			System.out.println("Změnili jste stav na " + dostupnost); 
			break;
			
		case 4: 
			if (uprav.isDostupnost() == false) {
				System.out.println("Kniha je již vypůjčená");
				break;
			} 
			uprav.setDostupnost(false);
			break;
		
		case 5: 
			if (uprav.isDostupnost() == true) {
				System.out.println("Kniha je dostupná");
				break;
			} 
			uprav.setDostupnost(true);
		break;
			
		}
		return true;
	}
	
	private Scanner sc;
	public static TreeMap<String,Kniha>  prvkyDatabaze;
}