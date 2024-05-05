import java.sql.*;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SQL {

	public static void vytvorDatabazi() {
        
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:knihovna.db");

            String sql = """
                    CREATE TABLE IF NOT EXISTS Ucebnice(
                    nazev VARCHAR(50),
                    autor VARCHAR(20),
                    rok INT,
                    typ VARCHAR(20), 
                    dostupnost BOOLEAN,
                    rocnik INT,
                    poradoveCislo INT PRIMARY KEY
                    );""";
            Statement statement = conn.createStatement();
            statement.execute(sql);
            sql = """
                    CREATE TABLE IF NOT EXISTS Romany(
                    nazev VARCHAR(50),
                    autor VARCHAR(20),
                    rok INT,
                    typ VARCHAR(20),
                    dostupnost BOOLEAN,
                    zanr VARCHAR(30),
                    poradoveCislo INT PRIMARY KEY
                    );""";
            statement.execute(sql);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
	
	public static void ulozDatabazi() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:knihovna.db");
            TreeMap<String,Kniha> knihovna = Databaze.prvkyDatabaze;
            
            for (Entry<String, Kniha> kniha : knihovna.entrySet()) {
                String sql = null;
                if (kniha.getValue() instanceof Ucebnice)
                    sql = "INSERT INTO Ucebnice (nazev, autor, rok, typ, dostupnost, rocnik, poradoveCislo) VALUES (?,?,?,?,?,?,?)";
                else if (kniha.getValue() instanceof Roman)
                    sql = "INSERT INTO Romany (nazev, autor, rok, typ, dostupnost, zanr, poradoveCislo) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement prStatement = conn.prepareStatement(sql);
                prStatement.setString(1, kniha.getValue().getNazev());
                prStatement.setString(2, kniha.getValue().getAutor());
                prStatement.setInt(3, kniha.getValue().getRok());
                prStatement.setString(4, kniha.getValue().getTyp());
                prStatement.setBoolean(5, kniha.getValue().isDostupnost());
                if (kniha.getValue() instanceof Ucebnice) prStatement.setInt(6, ((Ucebnice) kniha.getValue()).getRocnik());
                else if (kniha.getValue() instanceof Roman) prStatement.setString(6, ((Roman) kniha.getValue()).getZanr());
                prStatement.setInt(7, kniha.getValue().getPoradoveCislo());
                prStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
	
	 public static void nactiDatabazi() {
		 	SQL.vytvorDatabazi();
	        try {
	            Class.forName("org.sqlite.JDBC");
	            Connection conn = DriverManager.getConnection("jdbc:sqlite:knihovna.db");

	            String sql;
	            for (int i = 0; i <= 1; i++) {
	                if (i == 0)
	                    sql = "SELECT * FROM Ucebnice";
	                else
	                    sql = "SELECT * FROM Romany";

	                PreparedStatement pstmt = conn.prepareStatement(sql);
	                ResultSet rs = pstmt.executeQuery();
	                while (rs.next()) {
	                    String nazev = rs.getString("nazev");
	                    String autor = rs.getString("autor");
	                    int rok = rs.getInt("rok");
	                    String typ = rs.getString("typ");
	                    boolean dostupnost = rs.getBoolean("dostupnost");
	                    int counter = rs.getInt("poradoveCislo");
	                    if (i == 0)
	                    	Databaze.prvkyDatabaze.put(nazev, new Ucebnice(nazev, autor, rok, typ, dostupnost, rs.getInt("rocnik"), counter));
	                    else	
	                    	Databaze.prvkyDatabaze.put(nazev, new Roman(nazev, autor, rok, typ, dostupnost, rs.getString("zanr"), counter));
	                    
	                }
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            throw new RuntimeException(e);
	      }
	 }
}



