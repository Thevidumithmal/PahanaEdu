<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/19/2025
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Shop Worker Login</title>
</head>
<body>
<h2>Shop Worker Login</h2>

<form action="${pageContext.request.contextPath}/shopLogin" method="post">
  Username: <input type="text" name="username" required><br><br>
  Password: <input type="password" name="password" required><br><br>
  <input type="submit" value="Login">
</form>

<p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>

<br>
<a href="${pageContext.request.contextPath}/jsp/changePassword.jsp">Forgot Password? Change here</a><br>
<a href="login.jsp">← Back to User Selection</a>
</body>
</html>

