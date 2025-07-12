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
<h2>Add New Customer</h2>
<form action="customers" method="post">
  <input type="hidden" name="action" value="add" />
  Account Number: <input type="number" name="accountNumber" required /><br><br>
  Name: <input type="text" name="name" required /><br><br>
  Address: <input type="text" name="address" /><br><br>
  Phone Number: <input type="text" name="phoneNumber" /><br><br>
  Units Consumed: <input type="number" name="unitsConsumed" /><br><br>
  <input type="submit" value="Add Customer" />
</form>
<br>
<a href="customers">Back to List</a>
</body>
</html>

