import java.util.Scanner;
import java.sql.*;

public class P2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "");
        setupDB(conn);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter id: ");
        int id = sc.nextInt();

        CallableStatement cst = conn.prepareCall("");

        cst.setInt(1, id);
        cst.registerOutParameter(2, Types.VARCHAR);
        cst.execute();

        System.out.println("ID=" + id + ", City=" + cst.getString(2));

        sc.close();
        conn.close();
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

        // 2. Drop stored procedure if it already exists
        st.execute("DROP PROCEDURE IF EXISTS get_city");

        // 3. Create stored procedure (No DELIMITER keywords needed)
        st.execute(
            """
            DELIMITER //

            CREATE PROCEDURE get_city(
                IN p_empid INT,
                OUT p_city VARCHAR(50)
            )
            BEGIN
                SELECT city INTO p_city 
                FROM emp 
                WHERE id = p_empid;
            END //

            DELIMITER ;
            """
        );
    }
}
