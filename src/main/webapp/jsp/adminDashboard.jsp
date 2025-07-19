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
  <title>Admin Dashboard</title>
</head>
<body>
<h2>Welcome, Admin <%= admin.getUsername() %>!</h2>

<h3>Manage Shop Workers</h3>
<ul>
  <li><a href="addShopWorker.jsp">Add New Shop Worker</a></li>
  <li><a href="${pageContext.request.contextPath}/viewShopWorkers">View / Edit / Delete Shop Workers</a></li>

</ul>

<h3>Manage Items</h3>
<ul>
  <li><a href="addItem.jsp">Add New Item</a></li>
  <li><a href="viewItems.jsp">View / Edit / Delete Items</a></li>
</ul>

<form action="logout.jsp" method="post">
  <input type="submit" value="Logout">
</form>
</body>
</html>

