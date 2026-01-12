import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

/**
 * Servlet demonstrating the Servlet Life Cycle
 * Life Cycle Methods: init() -> service() -> destroy()
 */
public class LifeCycleServlet extends HttpServlet {
    
    private int initCount = 0;
    private int serviceCount = 0;
    
    /**
     * init() - Called once when servlet is first loaded
     * Used for one-time initialization
     */
    @Override
    public void init() throws ServletException {
        initCount++;
        System.out.println("=== INIT METHOD CALLED ===");
        System.out.println("Servlet is being initialized - Count: " + initCount);
        System.out.println("This method is called only ONCE during servlet lifecycle");
    }
    
    /**
     * service() - Called for each client request
     * Handles the actual request processing
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        serviceCount++;
        System.out.println("\n=== SERVICE METHOD CALLED ===");
        System.out.println("Processing request - Service Count: " + serviceCount);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Life Cycle Demo</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; background: #f0f0f0; }");
        out.println(".container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("h1 { color: #2c3e50; }");
        out.println(".lifecycle { background: #ecf0f1; padding: 15px; margin: 10px 0; border-left: 4px solid #3498db; }");
        out.println(".method { color: #e74c3c; font-weight: bold; }");
        out.println(".count { background: #3498db; color: white; padding: 5px 10px; border-radius: 5px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Servlet Life Cycle Demonstration</h1>");
        
        out.println("<div class='lifecycle'>");
        out.println("<h2>Life Cycle Phases:</h2>");
        out.println("<ol>");
        out.println("<li><span class='method'>init()</span> - Called when servlet is first loaded (one-time initialization)</li>");
        out.println("<li><span class='method'>service()</span> - Called for each request (request handling)</li>");
        out.println("<li><span class='method'>destroy()</span> - Called when servlet is unloaded (cleanup)</li>");
        out.println("</ol>");
        out.println("</div>");
        
        out.println("<div class='lifecycle'>");
        out.println("<h2>Current Status:</h2>");
        out.println("<p><span class='method'>init()</span> method called: <span class='count'>" + initCount + " time(s)</span></p>");
        out.println("<p><span class='method'>service()</span> method called: <span class='count'>" + serviceCount + " time(s)</span></p>");
        out.println("<p><em>Refresh this page to see service() count increase!</em></p>");
        out.println("</div>");
        
        out.println("<div class='lifecycle'>");
        out.println("<h3>Note:</h3>");
        out.println("<ul>");
        out.println("<li>init() is called only ONCE when the servlet is first loaded</li>");
        out.println("<li>service() is called for EVERY request</li>");
        out.println("<li>destroy() is called when the server shuts down or servlet is unloaded</li>");
        out.println("</ul>");
        out.println("</div>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    /**
     * destroy() - Called once when servlet is unloaded
     * Used for cleanup activities
     */
    @Override
    public void destroy() {
        System.out.println("\n=== DESTROY METHOD CALLED ===");
        System.out.println("Servlet is being destroyed");
        System.out.println("Final service count: " + serviceCount);
        System.out.println("Performing cleanup operations...");
        System.out.println("This method is called only ONCE when servlet is unloaded");
    }
}
