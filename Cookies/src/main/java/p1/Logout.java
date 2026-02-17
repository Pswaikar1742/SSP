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
        
        // Delete cookies by setting max age to 0
        Cookie userCookie = new Cookie("userid", "");
        Cookie authCookie = new Cookie("authenticated", "");
        Cookie loginTimeCookie = new Cookie("loginTime", "");
        
        // Set max age to 0 to delete the cookies
        userCookie.setMaxAge(0);
        authCookie.setMaxAge(0);
        loginTimeCookie.setMaxAge(0);
        
        // Set path to match the original cookies
        userCookie.setPath("/");
        authCookie.setPath("/");
        loginTimeCookie.setPath("/");
        
        // Add cookies to response (this will delete them)
        response.addCookie(userCookie);
        response.addCookie(authCookie);
        response.addCookie(loginTimeCookie);
        
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<title>Logout - Cookie Based</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f0f0f0; }");
        out.println(".logout-card { background: white; padding: 30px; border-radius: 8px; text-align: center; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("h2 { color: #ff9800; }");
        out.println(".cookie-badge { background: #fff3e0; color: #e65100; padding: 5px 10px; border-radius: 15px; font-size: 12px; }");
        out.println("a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #ff9800; color: white; text-decoration: none; border-radius: 4px; }");
        out.println("a:hover { background-color: #f57c00; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='logout-card'>");
        out.println("<h2>You have been successfully logged out!</h2>");
        out.println("<span class='cookie-badge'>🍪 Cookies Cleared</span>");
        out.println("<p>Your authentication cookies have been deleted.</p>");
        out.println("<a href='login.html'>Login Again</a>");
        out.println("</div>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
