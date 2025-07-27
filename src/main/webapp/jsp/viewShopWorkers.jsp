<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.dto.UserDTO" %>
<%@ page import="java.util.List" %>
<%
  UserDTO admin = (UserDTO) session.getAttribute("admin");
  if (admin == null) {
    response.sendRedirect("adminLogin.jsp");
    return;
  }

  List<UserDTO> workers = (List<UserDTO>) request.getAttribute("shopWorkers");
%>
<html>
<head>
  <title>Shop Workers</title>
</head>
<body>
<h2>Shop Workers</h2>

<table border="1" cellpadding="8">
  <tr>
    <th>ID</th><th>Username</th><th>Phone</th><th>Address</th><th>Actions</th>
  </tr>
  <%
    if (workers != null) {
      for (UserDTO u : workers) {
  %>
  <tr>
    <td><%= u.getId() %></td>
    <td><%= u.getUsername() %></td>
    <td><%= u.getPhone() %></td>
    <td><%= u.getAddress() %></td>
    <td>
      <a href="editShopWorker?id=<%= u.getId() %>">Edit</a> |
      <a href="deleteShopWorker?id=<%= u.getId() %>" onclick="return confirm('Are you sure?')">Delete</a>
    </td>
  </tr>
  <%
      }
    }
  %>
</table>

<br>
<a href="${pageContext.request.contextPath}/jsp/adminDashboard.jsp">← Back to Dashboard</a>

</body>
</html>
