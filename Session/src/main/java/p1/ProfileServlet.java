package p1;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProfileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Get existing session (don't create new one if not exists)
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // Get userid from session
            String userid = (String) session.getAttribute("userid");
            
            if (userid != null) {
                out.println("<!DOCTYPE html>");
                out.println("<html><head>");
                out.println("<title>Profile Page</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 40px; background-color: #f0f0f0; }");
                out.println(".profile-card { background: white; padding: 30px; border-radius: 8px; max-width: 400px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
                out.println("h2 { color: #4CAF50; }");
                out.println("a { color: #2196F3; text-decoration: none; margin-right: 20px; }");
                out.println("a:hover { text-decoration: underline; }");
                out.println(".logout-btn { background-color: #f44336; color: white; padding: 10px 20px; border-radius: 4px; }");
                out.println("</style>");
                out.println("</head><body>");
                out.println("<div class='profile-card'>");
                out.println("<h2>Welcome to Profile Page</h2>");
                out.println("<p><strong>User ID:</strong> " + userid + "</p>");
                out.println("<p><strong>Session ID:</strong> " + session.getId() + "</p>");
                out.println("<p><strong>Session Created:</strong> " + new java.util.Date(session.getCreationTime()) + "</p>");
                out.println("<hr>");
                out.println("<a href='DashboardServlet'>Go to Dashboard</a>");
                out.println("<a href='Logout' class='logout-btn'>Logout</a>");
                out.println("</div>");
                out.println("</body></html>");
            } else {
                response.sendRedirect("login.html");
            }
        } else {
            // No session exists, redirect to login
            response.sendRedirect("login.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
