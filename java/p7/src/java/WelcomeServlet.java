import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "welcome", urlPatterns = { "/welcome" })
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession sc = request.getSession();
        String usr = sc.getAttribute("user").toString();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Welcome</title>");
        out.println("<style>");
        out.println("  * { box-sizing: border-box; margin: 0; padding: 0; }");
        out.println("  body { display: flex; justify-content: center; align-items: center;");
        out.println("  min-height: 100vh; font-family: 'Segoe UI', Verdana, sans-serif; background: #667eea88; color: #333; }");
        out.println("  h1 { font-size: 3.5rem; color: #2d3748; margin-bottom: 0.5rem; }");
        out.println("  p { font-size: 2rem; color: #2d3748ee; margin-bottom: 1.8rem; }");
        out.println("  .btn { display: inline-block; padding: 0.75rem 1.5rem;");
        out.println("    background-color: #667eea; color: #ffffff;");
        out.println("    text-decoration: none; border-radius: 8px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("  <div class='card'>");
        out.println("    <h1>Welcome, " + usr.toUpperCase() + "! &#128075;</h1>");
        out.println("    <p>Have a wonderful and productive day!</p>");
        out.println("    <a href='index.html' class='btn'>Back to Homepage</a>");
        out.println("  </div>");
        out.println("</body>");
        out.println("</html>");
    }
}
