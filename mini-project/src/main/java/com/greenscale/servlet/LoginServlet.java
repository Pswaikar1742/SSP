package com.greenscale.servlet;

import com.greenscale.db.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null) {
            req.getSession().setAttribute("loginError", "Missing credentials");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT id, password FROM users WHERE username = ?")) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String stored = rs.getString("password");
                    boolean auth = false;

                    // Try BCrypt if available (via reflection). Fallback to plaintext compare if not.
                    try {
                        Class<?> bc = Class.forName("org.mindrot.jbcrypt.BCrypt");
                        Method checkpw = bc.getMethod("checkpw", String.class, String.class);
                        auth = (Boolean) checkpw.invoke(null, password, stored);
                    } catch (ClassNotFoundException e) {
                        auth = password.equals(stored);
                    } catch (Exception e) {
                        auth = password.equals(stored);
                    }

                    if (auth) {
                        HttpSession session = req.getSession(true);
                        session.setAttribute("username", username);
                        session.setAttribute("userId", rs.getInt("id"));
                        resp.sendRedirect(req.getContextPath() + "/dashboard");
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        req.getSession().setAttribute("loginError", "Invalid username or password");
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
