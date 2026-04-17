package p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String user = request.getParameter("username");
        String pwd = request.getParameter("password");
        HttpSession session = request.getSession();
        if (pwd != null && pwd.equals("admin")) {
            session.setAttribute("user", user);
            response.sendRedirect("WelcomeServlet");
        } else {
            response.sendRedirect("login.html");
        }
    }
}
