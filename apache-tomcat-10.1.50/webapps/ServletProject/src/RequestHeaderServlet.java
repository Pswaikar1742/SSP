import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

/**
 * Servlet for displaying HTTP Request Header Information
 * Demonstrates how to access and display various request headers
 */
public class RequestHeaderServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Request Header Information</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; background: #f4f4f4; }");
        out.println(".container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("h1 { color: #2c3e50; border-bottom: 3px solid #3498db; padding-bottom: 10px; }");
        out.println("h2 { color: #34495e; margin-top: 20px; }");
        out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
        out.println("th { background: #3498db; color: white; padding: 12px; text-align: left; }");
        out.println("td { padding: 10px; border: 1px solid #ddd; }");
        out.println("tr:nth-child(even) { background: #f9f9f9; }");
        out.println("tr:hover { background: #e8f4f8; }");
        out.println(".header-name { font-weight: bold; color: #2980b9; width: 30%; }");
        out.println(".info-box { background: #ecf0f1; padding: 15px; margin: 15px 0; border-left: 4px solid #e74c3c; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>HTTP Request Header Information</h1>");
        
        // Display Request Method and URI
        out.println("<div class='info-box'>");
        out.println("<p><strong>Request Method:</strong> " + request.getMethod() + "</p>");
        out.println("<p><strong>Request URI:</strong> " + request.getRequestURI() + "</p>");
        out.println("<p><strong>Protocol:</strong> " + request.getProtocol() + "</p>");
        out.println("<p><strong>Remote Address:</strong> " + request.getRemoteAddr() + "</p>");
        out.println("<p><strong>Remote Host:</strong> " + request.getRemoteHost() + "</p>");
        out.println("</div>");
        
        // Display all request headers
        out.println("<h2>All Request Headers</h2>");
        out.println("<table>");
        out.println("<tr><th>Header Name</th><th>Header Value</th></tr>");
        
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            out.println("<tr>");
            out.println("<td class='header-name'>" + headerName + "</td>");
            out.println("<td>" + headerValue + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        
        // Display specific important headers
        out.println("<h2>Common HTTP Headers</h2>");
        out.println("<table>");
        out.println("<tr><th>Header Name</th><th>Value</th><th>Description</th></tr>");
        
        String[][] commonHeaders = {
            {"User-Agent", request.getHeader("User-Agent"), "Browser/client information"},
            {"Accept", request.getHeader("Accept"), "Acceptable content types"},
            {"Accept-Language", request.getHeader("Accept-Language"), "Preferred languages"},
            {"Accept-Encoding", request.getHeader("Accept-Encoding"), "Acceptable encodings"},
            {"Connection", request.getHeader("Connection"), "Connection type"},
            {"Host", request.getHeader("Host"), "Server host and port"},
            {"Referer", request.getHeader("Referer"), "Previous page URL"},
            {"Cookie", request.getHeader("Cookie"), "Cookies sent by client"}
        };
        
        for (String[] header : commonHeaders) {
            out.println("<tr>");
            out.println("<td class='header-name'>" + header[0] + "</td>");
            out.println("<td>" + (header[1] != null ? header[1] : "<em>Not available</em>") + "</td>");
            out.println("<td>" + header[2] + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        
        // Additional request information
        out.println("<h2>Additional Request Information</h2>");
        out.println("<table>");
        out.println("<tr><th>Property</th><th>Value</th></tr>");
        out.println("<tr><td class='header-name'>Content Type</td><td>" + 
                    (request.getContentType() != null ? request.getContentType() : "N/A") + "</td></tr>");
        out.println("<tr><td class='header-name'>Content Length</td><td>" + request.getContentLength() + "</td></tr>");
        out.println("<tr><td class='header-name'>Character Encoding</td><td>" + 
                    (request.getCharacterEncoding() != null ? request.getCharacterEncoding() : "N/A") + "</td></tr>");
        out.println("<tr><td class='header-name'>Server Name</td><td>" + request.getServerName() + "</td></tr>");
        out.println("<tr><td class='header-name'>Server Port</td><td>" + request.getServerPort() + "</td></tr>");
        out.println("<tr><td class='header-name'>Context Path</td><td>" + request.getContextPath() + "</td></tr>");
        out.println("<tr><td class='header-name'>Servlet Path</td><td>" + request.getServletPath() + "</td></tr>");
        out.println("</table>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
