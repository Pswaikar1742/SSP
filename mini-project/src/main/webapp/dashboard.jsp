<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cluster" uri="http://greenscale/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>GreenScale Lite - Dashboard</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; vertical-align: top; }
        th { background-color: #f2f2f2; }
        .form-inline { display: inline-block; margin: 0; }
    </style>
    </head>
<body>
    <h1>Dashboard</h1>

    <c:if test="${not empty loginError}">
        <div style="color: red">${loginError}</div>
    </c:if>

    <c:if test="${not empty allocError}">
        <div style="color: red">${allocError}</div>
    </c:if>

    <table>
        <thead>
            <tr><th>Hostname</th><th>Specs</th><th>Status</th><th>Action</th></tr>
        </thead>
        <tbody>
            <c:forEach var="node" items="${nodes}">
                <tr>
                    <td>${node.hostname}</td>
                    <td><pre style="margin:0">${node.specs}</pre></td>
                    <td><cluster:HealthBadge status="${node.status}"/></td>
                    <td>
                        <c:if test="${node.status == 'Available'}">
                            <form class="form-inline" method="post" action="${pageContext.request.contextPath}/allocate">
                                <input type="hidden" name="node_id" value="${node.nodeId}" />
                                <button type="submit">Allocate</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
