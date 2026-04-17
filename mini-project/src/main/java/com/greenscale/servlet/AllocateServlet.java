package com.greenscale.servlet;

import com.greenscale.dao.NodeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/allocate")
public class AllocateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeIdParam = req.getParameter("node_id");
        HttpSession session = req.getSession(true);

        if (nodeIdParam == null) {
            session.setAttribute("allocError", "Missing node_id parameter");
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        int nodeId;
        try {
            nodeId = Integer.parseInt(nodeIdParam);
        } catch (NumberFormatException e) {
            session.setAttribute("allocError", "Invalid node_id parameter");
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        try {
            // RMI lookup
            com.greenscale.rmi.NodeMonitor monitor = (com.greenscale.rmi.NodeMonitor)
                    java.rmi.Naming.lookup("rmi://localhost/Node1");
            double temp = monitor.getTemperature();

            if (temp > 85.0) {
                session.setAttribute("allocError", "Node temperature too high: " + temp);
            } else {
                NodeDao dao = new NodeDao();
                boolean ok = dao.allocateNode(nodeId);
                if (!ok) {
                    session.setAttribute("allocError", "Failed to allocate node " + nodeId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("allocError", "Allocation error: " + e.getMessage());
        }

        resp.sendRedirect(req.getContextPath() + "/dashboard");
    }
}
