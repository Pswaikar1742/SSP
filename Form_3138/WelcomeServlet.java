import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);
        String username = session == null ? null : (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("login.html");
            return;
        }

        String address = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT address FROM users WHERE username = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    address = rs.getString("address");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">\n<head>\n<meta charset=\"UTF-8\">\n");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        out.println("<title>Welcome</title>\n");
        out.println("<style>body{font-family:Arial,sans-serif;margin:30px;} .box{width:420px;border:1px solid #444;padding:16px;} h1{font-size:18px;margin:0 0 12px 0;} .row{margin:8px 0;} .label{font-weight:bold;}</style>\n");
        out.println("</head><body>");
        out.println("<div class=\"box\">\n<h1>Welcome</h1>");
        out.println("<div class=\"row\"><span class=\"label\">Username:</span> " + escapeHtml(username) + "</div>");
        out.println("<div class=\"row\"><span class=\"label\">Address:</span> " + escapeHtml(address) + "</div>");
        out.println("<div class=\"row\"><a href=\"LogoutServlet\">Logout</a></div>");
        out.println("</div></body></html>");
    }

    private String escapeHtml(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
