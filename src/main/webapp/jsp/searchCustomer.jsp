<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/20/2025
  Time: 6:28 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head><title>Search Customers</title></head>
<body>
<h2>Search Customers by Phone</h2>
<form action="${pageContext.request.contextPath}/viewCustomers" method="post">
    Phone: <input type="text" name="phone" required>
    <input type="submit" value="Search">
</form>

<a href="${pageContext.request.contextPath}/jsp/shopDashboard.jsp">← Back to Dashboard</a>
</body>
</html>

