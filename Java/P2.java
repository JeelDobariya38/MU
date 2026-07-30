import java.util.Scanner;
import java.sql.*;

public class P2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter id: ");
        int id = sc.nextInt();

        CallableStatement cst = conn.prepareCall("{call get_city(?,?)}");

        cst.setInt(1, id);
        cst.registerOutParameter(2, Types.VARCHAR);
        cst.execute();

        System.out.println("ID=" + id + ", City=" + cst.getString(2));
        System.out.println();

        sc.close();
        conn.close();
    }
}
