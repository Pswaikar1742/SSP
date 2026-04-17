package com.greenscale.servlet;

import com.greenscale.dao.NodeDao;
import com.greenscale.model.NodeBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NodeDao dao = new NodeDao();
        try {
            List<NodeBean> nodes = dao.getAllNodes();
            // move any session error into the request for display
            HttpSession session = req.getSession(false);
            if (session != null) {
                Object allocError = session.getAttribute("allocError");
                if (allocError != null) {
                    req.setAttribute("allocError", allocError);
                    session.removeAttribute("allocError");
                }
                Object loginError = session.getAttribute("loginError");
                if (loginError != null) {
                    req.setAttribute("loginError", loginError);
                    session.removeAttribute("loginError");
                }
            }

            req.setAttribute("nodes", nodes);
            req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
