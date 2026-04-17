<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="p1" uri="http://example.com/courses" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Result</title>
</head>
<body>

<jsp:useBean id="student" class="p1.Student" scope="request" />
<jsp:setProperty name="student" property="name" param="name" />
<jsp:setProperty name="student" property="roll" param="roll" />

<%
    // Retrieve selected courses
    String[] selected = request.getParameterValues("courses");
    String coursesCsv = "";
    if (selected != null) {
        // join into comma-separated string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selected.length; i++) {
            sb.append(selected[i]);
            if (i < selected.length - 1) sb.append(",");
        }
        coursesCsv = sb.toString();
    }
    // store courses in bean
    ((p1.Student)request.getAttribute("student")).setCourses(coursesCsv);
%>

<h2>Course Registration Confirmation</h2>
<p><strong>Name:</strong> <%= request.getAttribute("student") != null ? ((p1.Student)request.getAttribute("student")).getName() : "" %></p>
<p><strong>Roll:</strong> <%= request.getAttribute("student") != null ? ((p1.Student)request.getAttribute("student")).getRoll() : "" %></p>
<p><strong>Selected Courses:</strong> <%= coursesCsv %></p>

<!-- Use custom tag to calculate and display total fee; it also sets page attribute 'totalFee' -->
<p1:courseFee courses="<%= coursesCsv %>" />

<%
    // Retrieve computed total from tag
    Double totalFee = (Double) pageContext.getAttribute("totalFee");
    if (totalFee == null) totalFee = 0.0;
    // store in bean
    ((p1.Student)request.getAttribute("student")).setTotalFee(totalFee);

    // Persist registration via JDBC (SQLite)
    java.sql.Connection conn = null;
    java.sql.PreparedStatement ps = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String dbPath = "/home/psw/Projects/SSP/db/ssp61.db";
        conn = java.sql.DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        java.sql.Statement st = conn.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS registrations (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, roll TEXT, courses TEXT, total REAL)");
        ps = conn.prepareStatement("INSERT INTO registrations(name, roll, courses, total) VALUES (?,?,?,?)");
        ps.setString(1, ((p1.Student)request.getAttribute("student")).getName());
        ps.setString(2, ((p1.Student)request.getAttribute("student")).getRoll());
        ps.setString(3, ((p1.Student)request.getAttribute("student")).getCourses());
        ps.setDouble(4, totalFee);
        ps.executeUpdate();
        out.println("<p><em>Registration saved to database.</em></p>");
    } catch (Exception e) {
        out.println("<p style='color:red;'>Error saving registration: " + e.getMessage() + "</p>");
    } finally {
        try { if (ps != null) ps.close(); } catch(Exception _e) {}
        try { if (conn != null) conn.close(); } catch(Exception _e) {}
    }
%>

<p><a href="registration_form.html">Register another student</a></p>

</body>
</html>
