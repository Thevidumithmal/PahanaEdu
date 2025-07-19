<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.model.Item" %>
<%@ page import="java.util.List" %>
<%
  com.pahanaedu.model.User admin = (com.pahanaedu.model.User) session.getAttribute("admin");
  if (admin == null) {
    response.sendRedirect("adminLogin.jsp");
    return;
  }

  List<Item> items = (List<Item>) request.getAttribute("items");
%>
<html>
<head>
  <title>View Items</title>
</head>
<body>
<h2>Item List</h2>

<table border="1" cellpadding="8">
  <tr>
    <th>ID</th><th>Name</th><th>Price</th><th>Actions</th>
  </tr>
  <%
    if (items != null && !items.isEmpty()) {
      for (Item item : items) {
  %>
  <tr>
    <td><%= item.getId() %></td>
    <td><%= item.getName() %></td>
    <td><%= item.getPrice() %></td>
    <td>
      <a href="editItem?id=<%= item.getId() %>">Edit</a> |
      <a href="deleteItem?id=<%= item.getId() %>" onclick="return confirm('Are you sure?')">Delete</a>
    </td>
  </tr>
  <%
    }
  } else {
  %>
  <tr>
    <td colspan="4">No items found.</td>
  </tr>
  <%
    }
  %>
</table>

<br>
<a href="${pageContext.request.contextPath}/jsp/adminDashboard.jsp">← Back to Dashboard</a>
</body>
</html>
