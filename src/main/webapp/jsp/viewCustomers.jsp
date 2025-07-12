<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/12/2025
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Customers List</title></head>
<body>
<h2>Customers</h2>
<a href="customers?action=add">Add New Customer</a><br><br>
<table border="1" cellpadding="5" cellspacing="0">
  <tr>
    <th>Account Number</th>
    <th>Name</th>
    <th>Address</th>
    <th>Phone</th>
    <th>Units Consumed</th>
    <th>Actions</th>
  </tr>
  <c:forEach var="customer" items="${customers}">
    <tr>
      <td>${customer.accountNumber}</td>
      <td>${customer.name}</td>
      <td>${customer.address}</td>
      <td>${customer.phoneNumber}</td>
      <td>${customer.unitsConsumed}</td>
      <td>
        <a href="customers?action=edit&accountNumber=${customer.accountNumber}">Edit</a> |
        <a href="customers?action=delete&accountNumber=${customer.accountNumber}" onclick="return confirm('Are you sure?');">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>
<br>
<a href="logout.jsp">Logout</a>
</body>
</html>

