<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
        }

        .box {
            width: 380px;
            border: 1px solid #444;
            padding: 16px;
        }

        h1 {
            font-size: 18px;
            margin: 0 0 12px 0;
        }

        .row {
            display: flex;
            align-items: center;
            margin: 8px 0;
        }

        .row label {
            width: 90px;
        }

        .row input {
            width: 160px;
            padding: 4px;
        }

        .btn {
            margin-top: 10px;
            padding: 4px 10px;
        }

        .link {
            margin-top: 8px;
        }

        .error-message {
            color: #c00;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="box">
        <h1>Login form</h1>

        <% 
            String error = request.getParameter("error");
            if (error != null && error.equals("1")) {
        %>
            <div class="error-message">Invalid username or password!</div>
        <% 
            }
        %>

        <form action="LoginServlet" method="post">
            <div class="row">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="row">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">submit</button>
        </form>
        <div class="link">
            New User? <a href="register.jsp">Register</a>
        </div>
    </div>
</body>
</html>
