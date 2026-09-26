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

        String usr = request.getParameter("username").toLowerCase();
        String pwd = request.getParameter("password").toLowerCase();
        
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
                throw new ServletException("Invalid Credentials: Failed to login!!!");
            }
        } catch (ServletException e) {
            request.getRequestDispatcher("index.html").include(request, response);
                                
            out.println("<p style='color:red;'>" + e.getMessage() + "</p>");
            out.println("Correct Credentials: <span style=\"font-style:underline;color: cornflowerblue;font-size: 1.3rem;letter-spacing: 3px;margin: 0px 10px;font-family: monospace;font-weight: 900;\">MU, JAVA</span>!!!");
        } catch (SQLException e) {
            out.println("<body style='background-color:black;'>");
            out.println("<h1 style='color:red;'>SQL SEVER ERROR</h1>");
            out.println("<p style='color:red;'>" + e.getMessage() + "</p>");
            out.println("</body>");
        } catch (IOException | ClassNotFoundException e) {
            out.println("<body style='background-color:black;'>");
            out.println("<h1 style='color:red;'>ERROR</h1>");
            out.println("<p style='color:red;'>" + e.getMessage() + "</p>");
            out.println("</body>");
        }
    }
}
