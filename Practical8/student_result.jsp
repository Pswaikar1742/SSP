<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Result</title>
</head>
<body>

<%! 
    // Declaration Tag: Method to calculate grade
    public String calculateGrade(double average) {
        if (average >= 80) return "A";
        else if (average >= 60) return "B";
        else if (average >= 40) return "C";
        else return "Fail";
    }
%>

<%
    // Scriptlet Tag: Retrieve form data using implicit object 'request'
    String name = request.getParameter("name");
    String roll = request.getParameter("roll");
    String course = request.getParameter("course");
    
    int m1 = Integer.parseInt(request.getParameter("m1"));
    int m2 = Integer.parseInt(request.getParameter("m2"));
    int m3 = Integer.parseInt(request.getParameter("m3"));
    
    // Perform total and average calculation
    int total = m1 + m2 + m3;
    double average = total / 3.0;
    
    // Implicit Object 'session': Store student name
    session.setAttribute("studentName", name);
    
    // Implicit Object 'application': Maintain visit counter
    Integer visitCount = (Integer) application.getAttribute("visitCount");
    if (visitCount == null) {
        visitCount = 1;
    } else {
        visitCount += 1;
    }
    application.setAttribute("visitCount", visitCount);
%>

<h2>Student Information Result</h2>
<p><strong>Name:</strong> <%= name %></p>
<p><strong>Roll No:</strong> <%= roll %></p>
<p><strong>Course:</strong> <%= course %></p>
<p><strong>Total Marks:</strong> <%= total %></p>
<p><strong>Average Marks:</strong> <%= String.format("%.2f", average) %></p>
<p><strong>Grade:</strong> <%= calculateGrade(average) %></p>

<br>
<% 
    // Implicit Object 'out': Print output directly
    out.println("<i>Status: Results processed successfully.</i>"); 
%>

<hr>
<p><strong>Total Students Visited:</strong> <%= application.getAttribute("visitCount") %></p>

</body>
</html>
