<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forwarding Logic</title>
</head>
<body>
<%
    String user = request.getParameter("user");
    if (user == null || user.isEmpty()) {
%>
        <jsp:forward page="error.jsp" />
<%
    } else {
%>
        <jsp:forward page="success.jsp">
            <jsp:param name="user" value="<%= user %>" />
        </jsp:forward>
<%
    }
%>
</body>
</html>