import java.sql.*;
import java.util.Scanner;

public class DeleteResult {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/new_student_db", "root", "1234");

            System.out.print("Enter Roll Number to Delete: ");
            int rollNo = sc.nextInt();

            // Delete from results table first (foreign key constraint)
            String deleteResults = "DELETE FROM results WHERE roll_no=?";
            PreparedStatement pst1 = con.prepareStatement(deleteResults);
            pst1.setInt(1, rollNo);
            pst1.executeUpdate();

            // Delete from students table
            String deleteStudent = "DELETE FROM students WHERE roll_no=?";
            PreparedStatement pst2 = con.prepareStatement(deleteStudent);
            pst2.setInt(1, rollNo);
            int rows = pst2.executeUpdate();

            if (rows > 0) {
                System.out.println("Record deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
