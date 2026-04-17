package p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        if (session == null) {
            out.println("<h2>Session Expired</h2>");
            return;
        }
        String user = (String) session.getAttribute("user");
        if (user == null) {
            out.println("<h2>Session Expired</h2>");
            return;
        }
        out.println("<h2>Welcome " + user + "</h2>");
        out.println("<a href='LogoutServlet'>Logout</a>");
    }
}
