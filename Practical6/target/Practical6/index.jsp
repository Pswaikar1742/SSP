<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Practical6 JSP Page</title>
    <style>
        body {
            margin: 0;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #f7f9fc 0%, #d8e2f3 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #1f2937;
        }

        .card {
            background: #ffffff;
            width: min(92vw, 700px);
            border-radius: 14px;
            padding: 2rem;
            box-shadow: 0 12px 30px rgba(15, 23, 42, 0.12);
        }

        h1 {
            margin-top: 0;
            color: #0f172a;
        }

        .meta {
            background: #f8fafc;
            border: 1px solid #e2e8f0;
            border-radius: 10px;
            padding: 1rem;
            line-height: 1.8;
        }

        .label {
            display: inline-block;
            width: 180px;
            color: #334155;
            font-weight: 600;
        }
    </style>
</head>
<body>
<div class="card">
    <h1>Dynamic JSP Page</h1>
    <p>This page is rendered by JSP and shows values from the current HTTP request.</p>
    <p><a href="customtag.jsp">Open Custom Tag Demo</a></p>

    <div class="meta">
        <div><span class="label">Current Server Time:</span> <%= new java.util.Date() %></div>
        <div><span class="label">Request Method:</span> <%= request.getMethod() %></div>
        <div><span class="label">Request URI:</span> <%= request.getRequestURI() %></div>
        <div><span class="label">Context Path:</span> <%= request.getContextPath() %></div>
        <div><span class="label">Client IP:</span> <%= request.getRemoteAddr() %></div>
    </div>
</div>
</body>
</html>
