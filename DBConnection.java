import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static final String URL = "jdbc:mysql://localhost:3306/bankdb";
    static final String USER = "root"; 
    static final String PASS = "root123"; // your password

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } 
        catch (Exception e) {
            System.out.println("Database Connection Error!");
            e.printStackTrace();
            return null;
        }
    }
}
