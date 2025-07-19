<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/19/2025
  Time: 9:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Change Password</title>
</head>
<body>
<h2>Reset Shop Worker Password</h2>

<form action="${pageContext.request.contextPath}/changePassword" method="post">
  Username: <input type="text" name="username" required><br><br>
  Phone No: <input type="text" name="phone" required><br><br>
  New Password: <input type="password" name="newPassword" required><br><br>
  <input type="submit" value="Change Password">
</form>

<p style="color:green;"><%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %></p>
<p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>

<a href="${pageContext.request.contextPath}/jsp/shopLogin.jsp">← Back to Login</a>
</body>
</html>

