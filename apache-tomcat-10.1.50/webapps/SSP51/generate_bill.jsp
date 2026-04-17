<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Detailed Bill</title>
</head>
<body>

<%!
    // Declaration Tag: Tax calculation method (GST at 18%)
    public double calculateTax(double amount) {
        return amount * 0.18;
    }
%>

<%
    // Implicit Objects: request & session
    String customerName = request.getParameter("customerName");
    if (customerName == null) customerName = "";
    session.setAttribute("customerName", customerName);
    
    String product = request.getParameter("product");
    double price = 0.0;
    int quantity = 0;
    try { price = Double.parseDouble(request.getParameter("price")); } catch(Exception e) { price = 0.0; }
    try { quantity = Integer.parseInt(request.getParameter("quantity")); } catch(Exception e) { quantity = 0; }
    
    // Edge Case: Redirect immediately if quantity or price is 0 using 'response'
    if(quantity <= 0 || price <= 0) {
        response.sendRedirect("thankyou.jsp?error=invalid");
        return; 
    }

    // Scriptlet Tag: Calculate total bill amount and discount
    double totalAmount = price * quantity;
    double discount = 0;
    
    if (totalAmount > 5000) {
        discount = totalAmount * 0.10; // 10% discount for orders > ₹5000
    }
    
    double amountAfterDiscount = totalAmount - discount;
    double tax = calculateTax(amountAfterDiscount);
    double finalAmount = amountAfterDiscount + tax;
    
    // Implicit Object 'application': Maintain customer visit counter
    Integer customerCount = (Integer) application.getAttribute("customerCount");
    if (customerCount == null) {
        customerCount = 1;
    } else {
        customerCount += 1;
    }
    application.setAttribute("customerCount", customerCount);
%>

<h2>Invoice for <%= session.getAttribute("customerName") %></h2>
<table border="1" cellpadding="5">
    <tr>
        <th>Product Name</th>
        <th>Quantity</th>
        <th>Total Price</th>
    </tr>
    <tr>
        <td><%= product %></td>
        <td><%= quantity %></td>
        <td>₹<%= totalAmount %></td>
    </tr>
</table>

<br>
<p><strong>Total Amount:</strong> ₹<%= totalAmount %></p>
<p><strong>Discount (10% on >₹5000):</strong> - ₹<%= discount %></p>
<p><strong>GST (18%):</strong> + ₹<%= tax %></p>
<h3><strong>Final Payable Amount:</strong> ₹<%= finalAmount %></h3>

<hr>
<p><strong>Total Customers Visited:</strong> <%= application.getAttribute("customerCount") %></p>

<form action="thankyou.jsp" method="post">
    <input type="submit" value="Confirm and Pay">
</form>

</body>
</html>
