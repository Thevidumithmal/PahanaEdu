<%@ page import="com.pahanaedu.business.customer.dto.CustomerDTO" %>
<%
    CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
%>
<html>
<head>
    <title>Edit Customer</title>
    <style>
        .validation-error {
            color: red;
            margin-bottom: 15px;
            font-weight: bold;
        }
        .message-error {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<h2>Edit Customer</h2>

<div class="validation-error">
    <%
        String validationErrors = (String) request.getAttribute("validationErrors");
        if (validationErrors != null) {
            out.print(validationErrors);
        }
    %>
</div>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<div class="message-error"><%= error %></div>
<%
    }
%>

<form action="${pageContext.request.contextPath}/editCustomer" method="post">
    <input type="hidden" name="id" value="<%= customer.getId() %>"><br>

    Name: <input type="text" name="name" value="<%= customer.getName() %>" required><br><br>
    Phone: <input type="text" name="phone" value="<%= customer.getPhone() %>" required><br><br>
    NIC No: <input type="text" name="nicNo" value="<%= customer.getNicNo() %>" required><br><br>
    Address: <input type="text" name="address" value="<%= customer.getAddress() != null ? customer.getAddress() : "" %>"><br><br>

    <input type="submit" value="Update Customer">
</form>

<a href="${pageContext.request.contextPath}/viewCustomer?phoneNumber=<%= customer.getPhone() %>">← Back to Customer Details</a>
</body>
</html>
