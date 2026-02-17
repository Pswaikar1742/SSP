package p1;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Get parameters from login form
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        
        // Validate credentials (using "admin" as password for demo)
        if (password.equals("admin")) {
            // Create cookies to store user information
            Cookie userCookie = new Cookie("userid", userid);
            Cookie authCookie = new Cookie("authenticated", "true");
            Cookie loginTimeCookie = new Cookie("loginTime", String.valueOf(System.currentTimeMillis()));
            
            // Set cookie max age (30 minutes = 1800 seconds)
            userCookie.setMaxAge(30 * 60);
            authCookie.setMaxAge(30 * 60);
            loginTimeCookie.setMaxAge(30 * 60);
            
            // Set cookie path to make them accessible across the application
            userCookie.setPath("/");
            authCookie.setPath("/");
            loginTimeCookie.setPath("/");
            
            // Add cookies to response
            response.addCookie(userCookie);
            response.addCookie(authCookie);
            response.addCookie(loginTimeCookie);
            
            // Redirect to profile page
            response.sendRedirect("ProfileServlet");
        } else {
            out.println("<html><body>");
            out.println("<h2 style='color:red;'>Invalid Password! Please try again.</h2>");
            out.println("<a href='login.html'>Back to Login</a>");
            out.println("</body></html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
