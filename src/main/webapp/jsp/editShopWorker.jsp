<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.dto.UserDTO" %>
<%
  UserDTO admin = (UserDTO) session.getAttribute("admin");
  if (admin == null) {
    response.sendRedirect("adminLogin.jsp");
    return;
  }

  UserDTO worker = (UserDTO) request.getAttribute("worker");
  if (worker == null) {
    response.sendRedirect("viewShopWorkers");
    return;
  }
%>
<html>
<head>
  <title>Edit Shop Worker</title>
</head>
<body>
<h2>Edit Shop Worker</h2>
<form action="${pageContext.request.contextPath}/editShopWorker" method="post">
  <input type="hidden" name="id" value="<%= worker.getId() %>">

  Username: <input type="text" name="username" value="<%= worker.getUsername() %>" required><br><br>
  Phone: <input type="text" name="phone" value="<%= worker.getPhone() %>"><br><br>
  Address: <input type="text" name="address" value="<%= worker.getAddress() %>"><br><br>

  <input type="submit" value="Update">
</form>

<br>
<a href="viewShopWorkers">← Back</a>
</body>
</html>
