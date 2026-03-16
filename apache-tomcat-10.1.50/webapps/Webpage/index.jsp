<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Webpage Dynamic Project</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <h1> Demonstration of @page directive</h1>
    <h2> Current Date and time </h2>
    <font color = "blue" size = 18>
    <%= new Date() %>
    </font>
    <%@ include file="include.jsp" %>

</body>
</html>
