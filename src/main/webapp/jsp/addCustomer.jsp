<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/12/2025
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head><title>Add Customer</title></head>
<body>
<h2>Add Customer</h2>
<form action="${pageContext.request.contextPath}/addCustomer" method="post">
  Name: <input type="text" name="name" required><br>
  Phone: <input type="text" name="phone" required><br>
  Address: <input type="text" name="address"><br>
  <input type="submit" value="Add">
</form>
</body>
</html>



