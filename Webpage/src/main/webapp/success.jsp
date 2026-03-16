<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Success</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <div class="container">
        <h1>Login Successful</h1>
        <p>Welcome, <strong><%= request.getParameter("user") %></strong>!</p>
        <a href="forward.html" class="btn">Go Back</a>
    </div>
</body>
</html>