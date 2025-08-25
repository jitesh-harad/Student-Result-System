import java.sql.*;
import java.util.Scanner;

public class UpdateResult {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // DB Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/new_student_db", "root", "1234");

            // Input Roll Number
            System.out.print("Enter Roll Number to Update: ");
            int rollNo = sc.nextInt();

            // Check if student exists
            String check = "SELECT * FROM students WHERE roll_no=?";
            PreparedStatement pstCheck = con.prepareStatement(check);
            pstCheck.setInt(1, rollNo);
            ResultSet rs = pstCheck.executeQuery();

            if (!rs.next()) {
                System.out.println("Student not found!");
                return;
            }

            // New Marks
            System.out.print("Enter New Maths Marks: ");
            int maths = sc.nextInt();
            System.out.print("Enter New Science Marks: ");
            int science = sc.nextInt();
            System.out.print("Enter New English Marks: ");
            int english = sc.nextInt();

            int total = maths + science + english;
            float percentage = total / 3.0f;
            String grade = (percentage >= 90) ? "A+" :
                           (percentage >= 75) ? "A" :
                           (percentage >= 60) ? "B" :
                           (percentage >= 40) ? "C" : "F";
            String status = (percentage >= 40) ? "Pass" : "Fail";

            // Update students table
            String updateStudent = "UPDATE students SET maths=?, science=?, english=? WHERE roll_no=?";
            PreparedStatement pst1 = con.prepareStatement(updateStudent);
            pst1.setInt(1, maths);
            pst1.setInt(2, science);
            pst1.setInt(3, english);
            pst1.setInt(4, rollNo);
            pst1.executeUpdate();

            // Update results table
            String updateResult = "UPDATE results SET total=?, percentage=?, grade=?, status=? WHERE roll_no=?";
            PreparedStatement pst2 = con.prepareStatement(updateResult);
            pst2.setInt(1, total);
            pst2.setFloat(2, percentage);
            pst2.setString(3, grade);
            pst2.setString(4, status);
            pst2.setInt(5, rollNo);
            pst2.executeUpdate();

            System.out.println("Result updated successfully!");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
