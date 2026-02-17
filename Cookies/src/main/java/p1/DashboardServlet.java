package p1;

import java.io.*;
import java.util.Date;
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
        
        // Get cookies from request
        Cookie[] cookies = request.getCookies();
        String userid = null;
        String authenticated = null;
        String loginTime = null;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userid")) {
                    userid = cookie.getValue();
                } else if (cookie.getName().equals("authenticated")) {
                    authenticated = cookie.getValue();
                } else if (cookie.getName().equals("loginTime")) {
                    loginTime = cookie.getValue();
                }
            }
        }
        
        // Check if user is authenticated via cookies
        if (userid != null && "true".equals(authenticated)) {
            String loginTimeStr = "N/A";
            if (loginTime != null) {
                try {
                    long time = Long.parseLong(loginTime);
                    loginTimeStr = new Date(time).toString();
                } catch (NumberFormatException e) {
                    loginTimeStr = "Invalid";
                }
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<title>Dashboard - Cookie Based</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 40px; background-color: #f0f0f0; }");
            out.println(".dashboard { background: white; padding: 30px; border-radius: 8px; max-width: 500px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
            out.println("h2 { color: #ff9800; }");
            out.println(".cookie-badge { background: #fff3e0; color: #e65100; padding: 5px 10px; border-radius: 15px; font-size: 12px; }");
            out.println("a { color: #2196F3; text-decoration: none; margin-right: 20px; }");
            out.println("a:hover { text-decoration: underline; }");
            out.println(".logout-btn { background-color: #f44336; color: white; padding: 10px 20px; border-radius: 4px; }");
            out.println(".info-box { background: #fff3e0; padding: 15px; border-radius: 4px; margin: 10px 0; }");
            out.println(".cookie-list { background: #fafafa; padding: 10px; border-radius: 4px; margin: 10px 0; font-family: monospace; font-size: 12px; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='dashboard'>");
            out.println("<h2>Dashboard</h2>");
            out.println("<span class='cookie-badge'>🍪 Cookie-Based Auth</span>");
            out.println("<div class='info-box'>");
            out.println("<p><strong>Logged in as:</strong> " + userid + "</p>");
            out.println("<p><strong>Cookie Active:</strong> Yes</p>");
            out.println("<p><strong>Login Time:</strong> " + loginTimeStr + "</p>");
            out.println("</div>");
            out.println("<h3>Cookies in Use</h3>");
            out.println("<div class='cookie-list'>");
            out.println("<p>• userid=" + userid + "</p>");
            out.println("<p>• authenticated=" + authenticated + "</p>");
            out.println("<p>• loginTime=" + loginTime + "</p>");
            out.println("</div>");
            out.println("<h3>Cookie Management Demo</h3>");
            out.println("<p>This page demonstrates that cookie data is maintained across different servlet pages.</p>");
            out.println("<hr>");
            out.println("<a href='ProfileServlet'>Back to Profile</a>");
            out.println("<a href='Logout' class='logout-btn'>Logout</a>");
            out.println("</div>");
            out.println("</body></html>");
        } else {
            response.sendRedirect("login.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
