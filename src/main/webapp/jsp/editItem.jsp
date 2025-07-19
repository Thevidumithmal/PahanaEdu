<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.model.Item" %>
<%
    Item item = (Item) request.getAttribute("item");
    if (item == null) {
        response.sendRedirect("viewItems");
        return;
    }
%>
<html>
<head>
    <title>Edit Item</title>
</head>
<body>
<h2>Edit Item</h2>
<form action="${pageContext.request.contextPath}/editItem" method="post">
    <input type="hidden" name="id" value="<%= item.getId() %>">
    Name: <input type="text" name="name" value="<%= item.getName() %>" required><br><br>
    Price: <input type="number" step="0.01" name="price" value="<%= item.getPrice() %>" required><br><br>
    <input type="submit" value="Update Item">
</form>
</body>
</html>
