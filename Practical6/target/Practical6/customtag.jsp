<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="custom" uri="http://example.com/customtags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Custom Tag Demo</title>
</head>
<body>
    <h1>Custom Tag Example</h1>
    <h2>HelloTag Output</h2>
    <custom:hellotag />
    <h2>AddTag Output</h2>
    <p>Example literal attributes:</p>
    <custom:addtag num1="12" num2="30" description="Sum is:" />

    <p>Example with expression values (request params):</p>
    <form method="get">
        <label>Num1: <input name="n1" value="" /></label>
        <label>Num2: <input name="n2" value="" /></label>
        <button type="submit">Compute</button>
    </form>
    <custom:addtag num1="${param.n1}" num2="${param.n2}" description="From params:" />
</body>
</html>
