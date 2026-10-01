import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "error", urlPatterns = {"/error"})
public class ErrorHandlerServelt extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
            
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Welcome</title>");
        out.println("<style>");
        out.println("  * { box-sizing: border-box; margin: 0; padding: 0; }");
        out.println("  body { padding: 6rem;");
        out.println("  min-height: 100vh; font-family: 'Segoe UI', Verdana, sans-serif; background: #000000; color: #333; }");
        out.println("  h1 { font-size: 3.5rem; color: #ffffffaa; margin-bottom: 0.5rem; }");
        out.println("  p { font-size: 2rem; color: #dd3748ee; margin-bottom: 1.8rem; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("  <div class='card'>");
        out.println("       <h1>Error Page</h1>");
        out.println("       <p>" + request.getAttribute("errorMessage") + "</p>");
        out.println("  </div>");
        out.println("</body>");
        out.println("</html>");
                                
    }
}
