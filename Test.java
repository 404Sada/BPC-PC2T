import java.util.Scanner;


public class Test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean run = true;
		Scanner sc = new Scanner(System.in);
		Databaze novaDatabaze = new Databaze();
		String nazev;
		while(run==true){
			
			
			System.out.println("");
			System.out.println("*****************************");
			System.out.println("*    P&P - Library index    *");
			System.out.println("*****************************");
			System.out.println("");
			System.out.println("1.....Vytvořit databázi");
			System.out.println("2.....Přidat knihu");
			System.out.println("3.....Upravit knihu");
			System.out.println("4.....Smazat knihu");
			System.out.println("5.....Výpis knihy");
			System.out.println("6.....Výpis všech knih");
			System.out.println("7.....Výpis všech vypůjčených knih");
			System.out.println("8.....Výpis knih daného žánru");
			System.out.println("9.....Výpis knih v chronologickém pořadí");
			System.out.println("10....Uložení knihy do souboru");
			System.out.println("11....Vypsání knihy ze souboru");
			System.out.println("12....Ukonceni programu");
			
						
			
			int volba=kontrolaVstupu.pouzeCelaCisla(sc);
			switch(volba) {
				case 1:
					novaDatabaze=new Databaze();
					SQL.nactiDatabazi();
					System.out.println("Databáze byla vytvořena");
					break;
				case 2:		
					novaDatabaze.setKniha();
					break;
				case 3:
					System.out.println("Zadejte nazev knihy, kterou chcete upravit");
					nazev=sc.next();
					boolean vysledek = novaDatabaze.upravKnihu(nazev);
					if (vysledek == true) {
					System.out.println("Uprava byla provedena");
					} else 
					System.out.println("Uprava nebyla provedena");
					break;
				case 4:
					System.out.println("Zadejte nazev knihy, kterou chcete smazat");
					nazev = sc.next();
					novaDatabaze.delKniha(nazev);
					break;
				case 5:
					System.out.println("Zadejte nazev knihy");
					nazev=sc.next();
					Kniha info = null;
					try 
					{	
						info=novaDatabaze.getKniha(nazev);
						if (info instanceof Roman) {
							System.out.println("Nazev: " + info.getNazev() + ", autor: " + info.getAutor() + ", rok vydani: " + info.getRok() + ", typ: " + info.getTyp()+", dostupnost: " + info.isDostupnost()+", zanr: "+ ((Roman) info).getZanr());
						} else { 
							System.out.println("Nazev: " + info.getNazev() + ", autor: " + info.getAutor() + ", rok vydani: " + info.getRok() + ", typ: " + info.getTyp()+", dostupnost: " + info.isDostupnost()+", rocnik: "+ ((Ucebnice) info).getRocnik());
						}
													
					} 
					catch(ArrayIndexOutOfBoundsException e)
					{
						System.out.println("Vybrana polozka mimo rozsah databaze");
					}
					catch (NullPointerException e)
					{
						System.out.println("Vybrana polozka neexistuje");
					}
					break;
				
				case 6:
					novaDatabaze.vypisVsechKnih();
					break;
					
				case 7:
					novaDatabaze.vypisVsechVypujcenychKnih();
					break;
			
				case 8:
					System.out.println("Zadejte žánr");
					sc.nextLine();
					String hledanyZanr=kontrolaVstupu.kontrolaStringu(sc);
					novaDatabaze.vypisDleAutora(hledanyZanr);
					break;
					
				case 9:
					System.out.println("Zadejte autora");
					sc.nextLine();
					String hledanyAutor=kontrolaVstupu.kontrolaStringu(sc);
					novaDatabaze.chronologickePoradi(hledanyAutor);
					break;
					
				case 10:
					System.out.println("Zadejte  název knihy, kterou chcete uložit do souboru");
					String hledanyNazev=sc.next();
					Kniha nactenaKniha = novaDatabaze.getKniha(hledanyNazev);
					String nazevKnihy = nactenaKniha.getNazev();
					String autor = nactenaKniha.getAutor();
					int rok = nactenaKniha.getRok();
					String typ = nactenaKniha.getTyp();
					boolean dostupnost = nactenaKniha.isDostupnost();
					if (nactenaKniha instanceof Roman) {
						String zanr = ((Roman)nactenaKniha).getZanr();
						novaDatabaze.ulozeniKnihyDoSouboru(nazevKnihy, autor, rok, typ, dostupnost, zanr, 0);
					}
					if (nactenaKniha instanceof Ucebnice) {
						int rocnik = ((Ucebnice)nactenaKniha).getRocnik();
						novaDatabaze.ulozeniKnihyDoSouboru(nazevKnihy, autor, rok, typ, dostupnost, null, rocnik);
					}
					break;
					
				case 11:
					System.out.println("Zadejte  název knihy, kterou chcete načíst ze souboru");
					hledanyNazev=sc.next();
					novaDatabaze.nacteniKnihyZeSouboru(hledanyNazev);
					break;
					
				case 12:
					SQL.ulozDatabazi();
					System.out.println("Program ukoncen");
					return;
			
			}
		}
	}

}
