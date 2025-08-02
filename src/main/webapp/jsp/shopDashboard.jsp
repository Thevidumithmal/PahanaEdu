<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/20/2025
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.business.user.model.User" %>
<%
  User shopworker = (User) session.getAttribute("shopworker");
  if (shopworker == null) {
    response.sendRedirect("shopLogin.jsp");
    return;
  }
%>
<html>
<head>
  <title>Shop Worker Dashboard</title>
</head>
<body>
<h2>Welcome, <%= shopworker.getUsername() %> (Shop Worker)</h2>

<ul>
  <li><a href="addCustomer.jsp">➕ Add New Customer</a></li>
  <li><a href="viewCustomers.jsp">🔍 View Customer by Phone No</a></li>
  <li><a href="bill.jsp">🧾 Generate Bill / Invoice</a></li>
  <li><a href="searchInvoices.jsp">🧾 search Invoice</a></li>

  <li>  <a href="${pageContext.request.contextPath}/jsp/logout.jsp">🚪 Logout</a></li>
</ul>

</body>
</html>

