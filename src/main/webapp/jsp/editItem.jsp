<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/14/2025
  Time: 10:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    com.pahanaedu.model.Item item = (com.pahanaedu.model.Item) request.getAttribute("item");
%>
<html>
<head>
    <title>Edit Item</title>
</head>
<body>
<h2>Edit Item</h2>
<form action="items" method="post">
    <input type="hidden" name="action" value="edit"/>
    <input type="hidden" name="id" value="<%= item.getId() %>" />

    <label>Name:</label><br/>
    <input type="text" name="name" value="<%= item.getName() %>" required/><br/><br/>

    <label>Price:</label><br/>
    <input type="number" name="price" step="0.01" value="<%= item.getPrice() %>" required/><br/><br/>

    <label>Description:</label><br/>
    <textarea name="description"><%= item.getDescription() %></textarea><br/><br/>

    <input type="submit" value="Update Item"/>
</form>
<br/>
<a href="items">Back to Item List</a>
</body>
</html>

