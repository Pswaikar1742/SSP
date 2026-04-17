<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Thank You</title>
</head>
<body>
    <%
        // Checking for the redirect error from generate_bill.jsp
        String error = request.getParameter("error");
        if("invalid".equals(error)) {
    %>
        <h2 style="color:red;">Invalid Order</h2>
        <p>You cannot purchase 0 items. Please go back and try again.</p>
    <%
        } else {
    %>
        <h2 style="color:green;">Thank You, <%= session.getAttribute("customerName") %>!</h2>
        <p>Your payment has been received and your order is confirmed.</p>
    <%
        }
    %>
</body>
</html>
