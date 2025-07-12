<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/12/2025
  Time: 7:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Edit Customer</title></head>
<body>
<h2>Edit Customer</h2>
<form action="customers" method="post">
    <input type="hidden" name="action" value="edit" />
    Account Number: <input type="number" name="accountNumber" value="${customer.accountNumber}" readonly /><br><br>
    Name: <input type="text" name="name" value="${customer.name}" required /><br><br>
    Address: <input type="text" name="address" value="${customer.address}" /><br><br>
    Phone Number: <input type="text" name="phoneNumber" value="${customer.phoneNumber}" /><br><br>
    Units Consumed: <input type="number" name="unitsConsumed" value="${customer.unitsConsumed}" /><br><br>
    <input type="submit" value="Update Customer" />
</form>
<br>
<a href="customers">Back to List</a>
</body>
</html>

