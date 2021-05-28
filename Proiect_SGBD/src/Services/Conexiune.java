
package Services;

import java.sql.*;

public class Conexiune {
    
    private static Conexiune conexiune;
    private Connection connection;
    
    private String utilizator;
    private String parola;
    private final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
      
    private Conexiune() {}
    
    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, utilizator, parola);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return connection;
    }
    
    public static Conexiune getInstance() {
        if (conexiune == null) {
            conexiune = new Conexiune();
        }
        return conexiune;
    }
  
    public boolean logare(String utilizator, String parola) {
        this.utilizator = utilizator;
        this.parola = parola;
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, utilizator, parola);
            return true;
        } catch (Exception e) {
            return false;
        } 
    }
}
