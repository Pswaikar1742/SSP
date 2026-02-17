<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
        }

        .box {
            width: 420px;
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
            width: 140px;
        }

        .row input,
        .row textarea {
            width: 180px;
            padding: 4px;
        }

        textarea {
            height: 50px;
        }

        .btn {
            margin-top: 10px;
            padding: 4px 10px;
        }

        .link {
            margin-top: 8px;
        }

        .success-message {
            color: #060;
            margin-bottom: 8px;
        }

        .error-message {
            color: #c00;
            margin-bottom: 8px;
        }
    </style>
</head>
<body>
    <div class="box">
        <h1>Student Registration Form</h1>

        <% 
            String success = request.getParameter("success");
            String error = request.getParameter("error");
            if (success != null && success.equals("1")) {
        %>
            <div class="success-message">Registration successful! Please <a href="login.jsp">login</a> to continue.</div>
        <% 
            }
            if (error != null && error.equals("1")) {
        %>
            <div class="error-message">Username already exists! Please choose another username.</div>
        <% 
            }
            if (error != null && error.equals("2")) {
        %>
            <div class="error-message">Passwords do not match!</div>
        <% 
            }
            if (error != null && error.equals("3")) {
        %>
            <div class="error-message">Please fill all fields!</div>
        <% 
            }
        %>

        <form action="RegisterServlet" method="post">
            <div class="row">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="row">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="row">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            <div class="row">
                <label for="address">Address</label>
                <textarea id="address" name="address" required></textarea>
            </div>
            <button type="submit" class="btn">Register</button>
            <button type="reset" class="btn">Clear</button>
        </form>
        <div class="link">
            <a href="login.jsp">Login</a>
        </div>
    </div>
</body>
</html>
