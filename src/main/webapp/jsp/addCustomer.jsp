<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add Customer</title>
  <style>
    .message-success {
      color: green;
      font-weight: bold;
      margin-bottom: 15px;
    }
    .message-error {
      color: red;
      font-weight: bold;
      margin-bottom: 15px;
    }
  </style>
</head>
<body>
<h2>Add Customer</h2>

<%
  String msg = request.getParameter("msg");
  String error = request.getParameter("error");

  if ("added".equals(msg)) {
%>
<div class="message-success">Customer added successfully!</div>
<%
} else if ("true".equals(error)) {
%>
<div class="message-error">Failed to add customer. Please try again.</div>
<%
  }
%>

<form action="${pageContext.request.contextPath}/addCustomer" method="post">
  Name: <input type="text" name="name" required><br><br>
  Phone: <input type="text" name="phone" required><br><br>
  NIC No: <input type="text" name="nicNo" required><br><br>
  Address: <input type="text" name="address"><br><br>
  <input type="submit" value="Add">
</form>
<a href="${pageContext.request.contextPath}/jsp/shopDashboard.jsp">← Back to Dashboard</a>
</body>
</html>
