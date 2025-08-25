import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void main(String[] args) {
        // MySQL credentials
        String url = "jdbc:mysql://localhost:3306/student_result_system"; 
        String user = "root";  // apna MySQL username
        String password = "1234"; // apna MySQL password

        try {
            // JDBC Driver load
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connection establish
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database Connected Successfully!");

            // Close connection
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
    }
}
