import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession sc = request.getSession();
        String usr = sc.getAttribute("user").toString();
        
        out.println("<html>");
        out.println("<head><title>Welcome Demo</title></head>");
        out.println("<body>");
        out.println("Welcome " + usr + "!!!");
        out.println("</body>");
        out.println("</html>");
    }
}
