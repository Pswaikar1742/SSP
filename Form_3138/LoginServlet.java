import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            if (conn != null) {
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);

                rs = pstmt.executeQuery();

                if (rs.next()) {
                    // Login successful
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("userId", rs.getInt("id"));

                    // Display welcome page
                    response.sendRedirect("WelcomeServlet");
                } else {
                    // Login failed - redirect back to login with error
                    response.sendRedirect("login.html?error=1");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            response.sendRedirect("login.html?error=1");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
