<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/19/2025
  Time: 1:05 PM
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
    <title>Add Shop Worker</title>
</head>
<body>
<h2>Add New Shop Worker</h2>
<form action="${pageContext.request.contextPath}/addShopWorker" method="post">
    Username: <input type="text" name="username" required><br><br>
    Password: <input type="password" name="password" required><br><br>
    Phone: <input type="text" name="phone"><br><br>
    Address: <input type="text" name="address"><br><br>
    <input type="submit" value="Add Worker">
</form>

<p style="color:green;"><%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %></p>
<p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>

<br>
<a href="adminDashboard.jsp">← Back to Dashboard</a>
</body>
</html>

