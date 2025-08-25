import java.sql.*;
import java.util.*;

public class StudentSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/new_student_db";
        String user = "root";   // apna MySQL username
        String pass = "1234";   // apna MySQL password

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            System.out.println("✅ Connected to Database!");

            while (true) {
                System.out.println("\n==== Student Management System ====");
                System.out.println("1. Add Student");
                System.out.println("2. Enter Marks");
                System.out.println("3. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();

                if (choice == 1) {
                    // Add Student
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    sc.nextLine(); // buffer clear
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    String sql = "INSERT INTO students(roll_no, name) VALUES (?, ?)";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setInt(1, roll);
                    pst.setString(2, name);
                    pst.executeUpdate();
                    System.out.println("🎉 Student added successfully!");

                } else if (choice == 2) {
                    // Enter Marks
                    System.out.print("Enter Roll No of student: ");
                    int roll = sc.nextInt();

                    System.out.print("Enter Maths Marks: ");
                    int maths = sc.nextInt();
                    System.out.print("Enter Science Marks: ");
                    int science = sc.nextInt();
                    System.out.print("Enter English Marks: ");
                    int english = sc.nextInt();

                    String sql = "UPDATE students SET maths=?, science=?, english=? WHERE roll_no=?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setInt(1, maths);
                    pst.setInt(2, science);
                    pst.setInt(3, english);
                    pst.setInt(4, roll);

                    int rows = pst.executeUpdate();
                    if (rows > 0) {
                        System.out.println("✅ Marks updated successfully!");
                    } else {
                        System.out.println("⚠️ Student not found!");
                    }

                } else if (choice == 3) {
                    System.out.println("👋 Exiting...");
                    break;
                } else {
                    System.out.println("❌ Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
