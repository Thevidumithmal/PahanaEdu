<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/14/2025
  Time: 10:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.model.User" %>
<%
    User admin = (User) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    }
%>
<html>
<head>
    <title>Add New Item</title>
</head>
<body>
<h2>Add Item</h2>

<form action="${pageContext.request.contextPath}/addItem" method="post">
    Item Name: <input type="text" name="name" required><br><br>
    Price: <input type="number" step="0.01" name="price" required><br><br>
    <input type="submit" value="Add Item">
</form>

<p style="color:green;"><%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %></p>
<p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>

<br>
<a href="${pageContext.request.contextPath}/jsp/adminDashboard.jsp">← Back to Dashboard</a>
</body>
</html>


