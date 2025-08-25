import java.sql.*;
import java.util.Scanner;

public class SearchResult {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // DB Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/new_student_db", "root", "1234");

            // Input roll number
            System.out.print("Enter Roll Number to Search: ");
            int rollNo = sc.nextInt();

            // SQL Query
            String query = "SELECT s.roll_no, s.name, s.maths, s.science, s.english, " +
                           "r.total, r.percentage, r.grade, r.status " +
                           "FROM students s " +
                           "JOIN results r ON s.roll_no = r.roll_no " +
                           "WHERE s.roll_no = ?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, rollNo);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Student Result ---");
                System.out.println("Roll No : " + rs.getInt("roll_no"));
                System.out.println("Name    : " + rs.getString("name"));
                System.out.println("Maths   : " + rs.getInt("maths"));
                System.out.println("Science : " + rs.getInt("science"));
                System.out.println("English : " + rs.getInt("english"));
                System.out.println("Total   : " + rs.getInt("total"));
                System.out.println("Percent : " + rs.getFloat("percentage"));
                System.out.println("Grade   : " + rs.getString("grade"));
                System.out.println("Status  : " + rs.getString("status"));
            } else {
                System.out.println("No record found for Roll No: " + rollNo);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
