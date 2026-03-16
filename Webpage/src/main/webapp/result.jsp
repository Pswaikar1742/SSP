<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dynamic Response</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<div class="container">
    <h1>Welcome, ${name}!</h1>

    <div class="card">
        <p><strong>Selected Topic:</strong> ${topic}</p>
        <p><strong>Server Time:</strong> ${timestamp}</p>
        <p><strong>Context Path:</strong> ${pageContext.request.contextPath}</p>
    </div>

    <a class="btn" href="${pageContext.request.contextPath}/home">Back to Home</a>
</div>
</body>
</html>
