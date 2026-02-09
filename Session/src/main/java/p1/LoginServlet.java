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
            // Create a new session
            HttpSession session = request.getSession();
            
            // Store userid in session
            session.setAttribute("userid", userid);
            
            // Set session timeout (30 minutes)
            session.setMaxInactiveInterval(30 * 60);
            
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
