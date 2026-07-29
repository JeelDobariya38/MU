import java.util.Scanner;
import java.sql.*;


public class P1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "");

        setupDB(conn);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("--- Menu ---");
            System.out.println("1. Insert");
            System.out.println("2. Display");
            System.out.print("> ");

            int input = sc.nextInt();

            if (input == 1) {
                System.out.print("name> ");
                String name = sc.next();
                System.out.print("age> ");
                int age = sc.nextInt();
                System.out.print("city> ");
                String city = sc.next();
                insertRecords(conn, name, age, city);
            } else if(input == 2) {
                displayRecords(conn);
            } else {
                System.out.println("Error: Invaild Input!!!");
                sc.close();
                conn.close();
                return;
            }

            System.out.println();
        }
    }

    private static void setupDB(Connection conn) throws SQLException {
        Statement st = conn.createStatement();

        // 1. Create table
        st.execute(
            """
            CREATE TABLE IF NOT EXISTS emp (
                id INTEGER AUTO_INCREMENT PRIMARY KEY, 
                name VARCHAR(50), 
                age INTEGER, 
                city VARCHAR(50)
            );
            """
        );
    }

    private static void displayRecords(Connection conn) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select * FROM emp");

        System.out.println("--- Records ---");
        while (rs.next()) {
            System.out.println(rs.getString("name") + ", " + rs.getString("age") + ", " + rs.getString("city"));
        }
    }

    private static void insertRecords(Connection conn, String name, Integer age, String city) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO emp (name, age, city) VALUES (?,?,?)");
        pst.setString(1, name);
        pst.setInt(2, age);
        pst.setString(3, city);

        int rowAffected = pst.executeUpdate();
        System.out.println(rowAffected + " rows affected!!");
    }
}
