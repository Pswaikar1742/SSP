import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String address = request.getParameter("address");

        // Validation
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty() ||
            address == null || address.trim().isEmpty()) {
            response.sendRedirect("register.html?error=3");
            return;
        }

        if (!password.equals(confirmPassword)) {
            response.sendRedirect("register.html?error=2");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            if (conn != null) {
                // Check if username already exists
                String checkSQL = "SELECT * FROM users WHERE username = ?";
                pstmt = conn.prepareStatement(checkSQL);
                pstmt.setString(1, username);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    // Username already exists
                    response.sendRedirect("register.html?error=1");
                } else {
                    // Insert new user
                    String insertSQL = "INSERT INTO users (username, password, address) VALUES (?, ?, ?)";
                    pstmt = conn.prepareStatement(insertSQL);
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    pstmt.setString(3, address);

                    int result = pstmt.executeUpdate();

                    if (result > 0) {
                        // Registration successful
                        response.sendRedirect("login.html?registered=1");
                    } else {
                        // Registration failed
                        response.sendRedirect("register.html?error=1");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            response.sendRedirect("register.html?error=1");
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
        response.sendRedirect("register.html");
    }
}
