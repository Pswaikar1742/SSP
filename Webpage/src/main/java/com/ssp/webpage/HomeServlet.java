package com.ssp.webpage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "HomeServlet", urlPatterns = {"/", "/home"})
public class HomeServlet extends HttpServlet {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String topic = request.getParameter("topic");

        if (name == null || name.isBlank()) {
            name = "Guest";
        }
        if (topic == null || topic.isBlank()) {
            topic = "Dynamic Web Project";
        }

        request.setAttribute("name", name.trim());
        request.setAttribute("topic", topic.trim());
        request.setAttribute("timestamp", LocalDateTime.now().format(FORMATTER));
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
