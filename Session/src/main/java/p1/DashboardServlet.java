package p1;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DashboardServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Get existing session
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            String userid = (String) session.getAttribute("userid");
            
            if (userid != null) {
                out.println("<!DOCTYPE html>");
                out.println("<html><head>");
                out.println("<title>Dashboard</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 40px; background-color: #f0f0f0; }");
                out.println(".dashboard { background: white; padding: 30px; border-radius: 8px; max-width: 500px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
                out.println("h2 { color: #2196F3; }");
                out.println("a { color: #2196F3; text-decoration: none; margin-right: 20px; }");
                out.println("a:hover { text-decoration: underline; }");
                out.println(".logout-btn { background-color: #f44336; color: white; padding: 10px 20px; border-radius: 4px; }");
                out.println(".info-box { background: #e3f2fd; padding: 15px; border-radius: 4px; margin: 10px 0; }");
                out.println("</style>");
                out.println("</head><body>");
                out.println("<div class='dashboard'>");
                out.println("<h2>Dashboard</h2>");
                out.println("<div class='info-box'>");
                out.println("<p><strong>Logged in as:</strong> " + userid + "</p>");
                out.println("<p><strong>Session Active:</strong> Yes</p>");
                out.println("<p><strong>Last Accessed:</strong> " + new java.util.Date(session.getLastAccessedTime()) + "</p>");
                out.println("</div>");
                out.println("<h3>Session Management Demo</h3>");
                out.println("<p>This page demonstrates that session data is maintained across different servlet pages.</p>");
                out.println("<hr>");
                out.println("<a href='ProfileServlet'>Back to Profile</a>");
                out.println("<a href='Logout' class='logout-btn'>Logout</a>");
                out.println("</div>");
                out.println("</body></html>");
            } else {
                response.sendRedirect("login.html");
            }
        } else {
            response.sendRedirect("login.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
