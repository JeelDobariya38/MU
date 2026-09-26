import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String usr = request.getParameter("username");
        String pwd = request.getParameter("password");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/authdb", "root", "");
            
            PreparedStatement pst = conn.prepareStatement("SELECT u_name from users WHERE u_name = ? AND u_pass = ?");
            
            pst.setString(1, usr);
            pst.setString(2, pwd);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                HttpSession sc = request.getSession();
                sc.setAttribute("user", usr);
                response.sendRedirect("welcome");
            } else {
                request.getRequestDispatcher("index.html").include(request, response);
                                
                out.println("<p style='color:red;'>Invalid Username or Password</p>");
                out.println("Correct Credentials: <span style='color:lightblue;'>MU, JAVA</span>!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
