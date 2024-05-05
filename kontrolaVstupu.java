import java.util.Scanner;

public class kontrolaVstupu {
	
	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
			
		}
		catch(Exception e)
		{
			System.out.println("Zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}

	public static boolean jeCislo(String volba){
	    try{
	        Double.parseDouble(volba);
	        return true;
	    } catch (NumberFormatException e){
	        return false;
	    }
	}

	public static String kontrolaStringu(Scanner sc){
	    String volba = sc.nextLine();
	    while(jeCislo(volba) || volba.isEmpty()){
	        System.out.println("Neplatny vstup, zadejte znovu");
	        volba = sc.nextLine();
	    }
	    return volba;

	}
	
	
	public static String prazdnyString(Scanner sc) {
		
		String volba;
		do {
			
			try 
			{
				volba = sc.nextLine();
				if (volba.isEmpty()) {
				System.out.println("Tato položka nesmí být prázdná");
				} else {
					return volba;
				}
			} catch (IllegalArgumentException e) {
	            System.out.println("Nastala vyjimka." + e.getMessage()); 
			}
			
		} while (true);

	}
	

}


