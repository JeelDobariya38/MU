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
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            String usr = request.getParameter("username").toLowerCase();
            String pwd = request.getParameter("password").toLowerCase();
            
            
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
        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
            request.setAttribute("errorMessage", e.toString());
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
            dispatcher.forward(request, response);
        }
    }
}
