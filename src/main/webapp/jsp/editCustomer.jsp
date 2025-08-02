<%@ page import="com.pahanaedu.business.customer.dto.CustomerDTO" %>
<%
    CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
%>
<html>
<head><title>Edit Customer</title></head>
<body>
<h2>Edit Customer</h2>

<form action="${pageContext.request.contextPath}/editCustomer" method="post">
    <input type="hidden" name="id" value="<%= customer.getId() %>">
    Name: <input type="text" name="name" value="<%= customer.getName() %>" required><br>
    Phone: <input type="text" name="phone" value="<%= customer.getPhone() %>" required><br>
    NIC No: <input type="text" name="nicNo" value="<%= customer.getNicNo() %>" required><br> <!-- New -->
    Address: <input type="text" name="address" value="<%= customer.getAddress() %>"><br>
    <input type="submit" value="Update Customer">
</form>

<a href="${pageContext.request.contextPath}/viewCustomer?phoneNumber=<%= customer.getPhone() %>">← Back to Customer Details</a>
</body>
</html>
