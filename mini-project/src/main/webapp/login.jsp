<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <title>Login - GreenScale Lite</title>
    <style>
        body { font-family: Arial, Helvetica, sans-serif; background:#f7f9fb; color:#222; }
        main { max-width:420px; margin:48px auto; background:white; padding:24px; border-radius:6px; box-shadow:0 6px 18px rgba(0,0,0,0.08); }
        h1 { margin:0 0 16px; font-size:20px; }
        label { display:block; margin:8px 0; }
        input[type="text"], input[type="password"] { width:100%; padding:8px; box-sizing:border-box; }
        .error { background:#ffe6e6; color:#900; padding:8px; border-radius:4px; margin-bottom:12px; }
        button { margin-top:12px; padding:8px 12px; cursor:pointer; }
    </style>
</head>
<body>
    <main>
        <h1>GreenScale Lite — Login</h1>

        <c:if test="${not empty requestScope.error}">
            <div class="error">${requestScope.error}</div>
        </c:if>

        <c:if test="${not empty sessionScope.loginError}">
            <div class="error">${sessionScope.loginError}</div>
            <c:remove var="loginError" scope="session" />
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/login">
            <label>Username
                <input type="text" name="username" required autofocus />
            </label>
            <label>Password
                <input type="password" name="password" required />
            </label>
            <button type="submit">Log in</button>
        </form>
    </main>
</body>
</html>
