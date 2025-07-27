<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.dto.UserDTO" %>

<%
    UserDTO admin = (UserDTO) session.getAttribute("admin");
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

<h2>Welcome, Admin <%= admin.getUsername() %>!</h2>
<h3>Add New Shop Worker</h3>

<form action="${pageContext.request.contextPath}/addShopWorker" method="post">
    Username: <input type="text" name="username" required><br><br>
    Password: <input type="password" name="password" required><br><br>
    Phone: <input type="text" name="phone"><br><br>
    Address: <input type="text" name="address"><br><br>
    <input type="submit" value="Add Worker">
</form>

<p style="color:green;">${success}</p>
<p style="color:red;">${error}</p>

<br>
<a href="${pageContext.request.contextPath}/jsp/adminDashboard.jsp">← Back to Dashboard</a>

</body>
</html>
