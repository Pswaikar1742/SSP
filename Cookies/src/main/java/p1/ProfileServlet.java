package p1;

import java.io.*;
import java.util.Date;
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
            out.println("<title>Profile Page - Cookie Based</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 40px; background-color: #f0f0f0; }");
            out.println(".profile-card { background: white; padding: 30px; border-radius: 8px; max-width: 400px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
            out.println("h2 { color: #ff9800; }");
            out.println(".cookie-badge { background: #fff3e0; color: #e65100; padding: 5px 10px; border-radius: 15px; font-size: 12px; }");
            out.println("a { color: #2196F3; text-decoration: none; margin-right: 20px; }");
            out.println("a:hover { text-decoration: underline; }");
            out.println(".logout-btn { background-color: #f44336; color: white; padding: 10px 20px; border-radius: 4px; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='profile-card'>");
            out.println("<h2>Welcome to Profile Page</h2>");
            out.println("<span class='cookie-badge'>🍪 Cookie-Based Auth</span>");
            out.println("<p><strong>User ID:</strong> " + userid + "</p>");
            out.println("<p><strong>Login Time:</strong> " + loginTimeStr + "</p>");
            out.println("<p><strong>Auth Method:</strong> HTTP Cookies</p>");
            out.println("<hr>");
            out.println("<a href='DashboardServlet'>Go to Dashboard</a>");
            out.println("<a href='Logout' class='logout-btn'>Logout</a>");
            out.println("</div>");
            out.println("</body></html>");
        } else {
            // No valid cookies, redirect to login
            response.sendRedirect("login.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
