import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

/**
 * Servlet for Processing Student Information Form
 * Demonstrates form handling and data processing
 */
public class StudentFormServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Retrieve form data
        String rollNo = request.getParameter("rollno");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String course = request.getParameter("course");
        String gender = request.getParameter("gender");
        String[] hobbies = request.getParameterValues("hobbies");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        
        // Build hobbies string
        String hobbiesStr = "";
        if (hobbies != null && hobbies.length > 0) {
            hobbiesStr = String.join(", ", hobbies);
        } else {
            hobbiesStr = "None selected";
        }
        
        // Generate HTML response
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Registration - Confirmation</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); }");
        out.println(".container { background: white; padding: 40px; border-radius: 15px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); max-width: 800px; margin: 0 auto; }");
        out.println("h1 { color: #2c3e50; text-align: center; border-bottom: 3px solid #3498db; padding-bottom: 15px; }");
        out.println(".success { background: #d4edda; color: #155724; padding: 15px; border-radius: 5px; margin: 20px 0; border-left: 5px solid #28a745; }");
        out.println(".info-table { width: 100%; margin: 20px 0; }");
        out.println(".info-table td { padding: 12px; border-bottom: 1px solid #ecf0f1; }");
        out.println(".label { font-weight: bold; color: #34495e; width: 35%; background: #f8f9fa; }");
        out.println(".value { color: #2c3e50; }");
        out.println(".btn-container { text-align: center; margin-top: 30px; }");
        out.println(".btn { background: #3498db; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; display: inline-block; transition: 0.3s; }");
        out.println(".btn:hover { background: #2980b9; transform: translateY(-2px); box-shadow: 0 5px 15px rgba(0,0,0,0.2); }");
        out.println(".header-icon { font-size: 50px; text-align: center; margin-bottom: 20px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<div class='header-icon'>&#127891;</div>");
        out.println("<h1>Student Registration Successful</h1>");
        
        out.println("<div class='success'>");
        out.println("<strong>✓ Success!</strong> Student information has been processed successfully.");
        out.println("</div>");
        
        out.println("<h2 style='color: #34495e; margin-top: 30px;'>Student Details</h2>");
        out.println("<table class='info-table'>");
        
        out.println("<tr><td class='label'>Roll Number</td><td class='value'>" + rollNo + "</td></tr>");
        out.println("<tr><td class='label'>Full Name</td><td class='value'>" + name + "</td></tr>");
        out.println("<tr><td class='label'>Email Address</td><td class='value'>" + email + "</td></tr>");
        out.println("<tr><td class='label'>Phone Number</td><td class='value'>" + phone + "</td></tr>");
        out.println("<tr><td class='label'>Course</td><td class='value'>" + course + "</td></tr>");
        out.println("<tr><td class='label'>Gender</td><td class='value'>" + gender + "</td></tr>");
        out.println("<tr><td class='label'>Hobbies</td><td class='value'>" + hobbiesStr + "</td></tr>");
        out.println("<tr><td class='label'>Address</td><td class='value'>" + address + "</td></tr>");
        out.println("<tr><td class='label'>City</td><td class='value'>" + city + "</td></tr>");
        out.println("<tr><td class='label'>State</td><td class='value'>" + state + "</td></tr>");
        
        out.println("</table>");
        
        out.println("<div class='btn-container'>");
        out.println("<a href='student-form.html' class='btn'>Register Another Student</a>");
        out.println("</div>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        
        // Console output for server-side logging
        System.out.println("\n=== Student Registration ===");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("========================\n");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html><body>");
        out.println("<h2>Error: Invalid Request Method</h2>");
        out.println("<p>Please submit the form using POST method.</p>");
        out.println("<a href='student-form.html'>Go to Student Registration Form</a>");
        out.println("</body></html>");
    }
}
