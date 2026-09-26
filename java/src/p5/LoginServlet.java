import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
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

        if (usr.toLowerCase().equals("mu") && pwd.toLowerCase().equals("java")) {
            HttpSession sc = request.getSession();
            sc.setAttribute("user", usr);
            response.sendRedirect("welcome");
        } else {
            request.getRequestDispatcher("index.html").include(request, response);
            out.println("Sorry Your Credentials Are Wrong!!! <br/>");
            out.println("Correct Credentials: MU, JAVA!!!");
        }
    }
}
