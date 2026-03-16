<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <div class="container">
        <h1>Error</h1>
        <p style="color: red;">Username cannot be empty. Please go back and enter a valid username.</p>
        <a href="forward.html" class="btn">Go Back</a>
    </div>
</body>
</html>