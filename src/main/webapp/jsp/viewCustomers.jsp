<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.dto.CustomerDTO" %>
<%
  List<CustomerDTO> customers = (List<CustomerDTO>) request.getAttribute("customers");
  String notFound = (String) request.getAttribute("notFound");
%>
<html>
<head><title>Search Customer</title></head>
<body>
<h2>Search Customer by Phone Number</h2>

<!-- 🔍 Search Form -->
<form action="${pageContext.request.contextPath}/viewCustomer" method="get">
  Enter Phone Number: <input type="text" name="phoneNumber" required>
  <input type="submit" value="Search">
</form>

<br>

<!-- ✅ Display Result -->
<% if (customers != null && !customers.isEmpty()) { %>
<h3>Customer Details:</h3>
<table border="1">
  <tr><th>ID</th><th>Name</th><th>Phone</th><th>Address</th></tr>
  <% for (CustomerDTO customer : customers) { %>
  <tr>
    <td><%= customer.getId() %></td>
    <td><%= customer.getName() %></td>
    <td><%= customer.getPhone() %></td>
    <td><%= customer.getAddress() %></td>
  </tr>
  <% } %>
</table>
<% } else if (notFound != null) { %>
<p style="color:red;"><%= notFound %></p>
<% } %>

<br>
<a href="${pageContext.request.contextPath}/jsp/shopDashboard.jsp">← Back to Dashboard</a>

</body>
</html>
