package p1;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Logout() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Get existing session
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // Invalidate the session
            session.invalidate();
        }
        
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<title>Logout</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f0f0f0; }");
        out.println(".logout-card { background: white; padding: 30px; border-radius: 8px; text-align: center; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("h2 { color: #4CAF50; }");
        out.println("a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #2196F3; color: white; text-decoration: none; border-radius: 4px; }");
        out.println("a:hover { background-color: #1976D2; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='logout-card'>");
        out.println("<h2>You have been successfully logged out!</h2>");
        out.println("<p>Your session has been terminated.</p>");
        out.println("<a href='login.html'>Login Again</a>");
        out.println("</div>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
