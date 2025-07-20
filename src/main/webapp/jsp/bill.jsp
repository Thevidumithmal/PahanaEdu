<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/20/2025
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.pahanaedu.model.Item, com.pahanaedu.model.Customer" %>
<html>
<head><title>Billing Page</title></head>
<body>
<h2>Generate Invoice</h2>

<!-- Step 1: Search Customer -->
<form action="${pageContext.request.contextPath}/fetchCustomerForBill" method="post">
  Customer Phone No: <input type="text" name="phoneNumber" required>
  <input type="submit" value="Search">
</form>

<%
  Customer customer = (Customer) request.getAttribute("customer");
  List<Item> itemList = (List<Item>) request.getAttribute("items");
  if (customer != null && itemList != null) {
%>
<h3>Customer Info</h3>
Name: <%= customer.getName() %><br>
Phone: <%= customer.getPhone() %><br>
Address: <%= customer.getAddress() %><br><br>

<!-- Step 2: Select Items and Quantity -->
<form action="${pageContext.request.contextPath}/generateInvoice" method="post">
  <input type="hidden" name="phoneNumber" value="<%= customer.getPhone() %>">

  <table border="1">
    <tr>
      <th>Item Name</th>
      <th>Unit Price</th>
      <th>Quantity</th>
    </tr>
    <% for (Item item : itemList) { %>
    <tr>
      <td><%= item.getName() %></td>
      <td><%= item.getPrice() %></td>
      <td>
        <input type="number" name="quantity_<%= item.getId() %>" min="0" value="0">
        <input type="hidden" name="item_<%= item.getId() %>" value="<%= item.getName() %>">
      </td>
    </tr>
    <% } %>
  </table><br>

  <input type="submit" value="🧾 Generate Invoice PDF">
</form>
<% } else if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<a href="shopDashboard.jsp">← Back to Dashboard</a>
</body>
</html>
