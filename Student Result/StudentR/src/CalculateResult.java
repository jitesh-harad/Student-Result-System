import java.sql.*;

public class CalculateResult {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/new_student_db";
        String user = "root";       // Apna MySQL username
        String pass = "1234";       // Apna MySQL password

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement()) {

            System.out.println("✅ Connected to Database!");

            // Fetch all students with marks
            String query = "SELECT roll_no, maths, science, english FROM students";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int roll = rs.getInt("roll_no");
                int maths = rs.getInt("maths");
                int science = rs.getInt("science");
                int english = rs.getInt("english");

                int total = maths + science + english;
                float percentage = total / 3.0f;

                // Grade and Status calculation
                String grade;
                String status;
                if (percentage >= 90) { grade = "A+"; status = "Pass"; }
                else if (percentage >= 75) { grade = "A"; status = "Pass"; }
                else if (percentage >= 60) { grade = "B"; status = "Pass"; }
                else if (percentage >= 40) { grade = "C"; status = "Pass"; }
                else { grade = "F"; status = "Fail"; }

                // Insert/Update results table
                String sql = "REPLACE INTO results (roll_no, total, percentage, grade, status) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, roll);
                pst.setInt(2, total);
                pst.setFloat(3, percentage);
                pst.setString(4, grade);
                pst.setString(5, status);
                pst.executeUpdate();
            }

            System.out.println("🎉 Result calculation completed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

