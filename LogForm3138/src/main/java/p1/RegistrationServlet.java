package p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");
        String contact = request.getParameter("contact");
        String city = request.getParameter("city");
        String gender = request.getParameter("gender");

        if (username == null || username.trim().isEmpty()) {
            out.println("Username is required");
            return;
        }

        if (password == null || !password.equals(confirmpassword)) {
            out.println("Password does not match");
            return;
        }

        String checkSql = "SELECT 1 FROM users WHERE username = ?";
        String insertSql = "INSERT INTO users(username,password,address,dob,contact,city,gender) VALUES(?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkSql)) {

            checkStmt.setString(1, username);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    out.println("Username already exists<br>");
                    out.println("<a href='registration.html'>Try Again</a>");
                    return;
                }
            }

            try (PreparedStatement ps = con.prepareStatement(insertSql)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, address);
                ps.setString(4, dob);
                ps.setString(5, contact);
                ps.setString(6, city);
                ps.setString(7, gender);

                ps.executeUpdate();
            }

            response.sendRedirect("login.html");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Registration failed");
        }
    }
}
