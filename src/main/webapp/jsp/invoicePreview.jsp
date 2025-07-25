<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.text.DecimalFormat, java.math.BigDecimal" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Invoice Preview</title>
</head>
<body>

<h2>🧾 Invoice Preview</h2>

<%
    String phoneNumber = (String) request.getAttribute("phoneNumber");
    String customerName = (String) request.getAttribute("customerName");
    List<Map<String, Object>> billItems = (List<Map<String, Object>>) request.getAttribute("billItems");
    BigDecimal total = (BigDecimal) request.getAttribute("total");
    DecimalFormat df = new DecimalFormat("0.00");
%>

<% if (billItems != null && !billItems.isEmpty()) { %>
<h3>👤 Customer Name: <%= customerName %></h3>
<h3>📞 Customer Phone: <%= phoneNumber %></h3>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>🛒 Item</th>
        <th>💵 Unit Price</th>
        <th>🔢 Quantity</th>
        <th>💰 Subtotal</th>
    </tr>
    <% for (Map<String, Object> item : billItems) { %>
    <tr>
        <td><%= item.get("itemName") %></td>
        <td><%= df.format((BigDecimal) item.get("unitPrice")) %></td>
        <td><%= item.get("quantity") %></td>
        <td><%= df.format((BigDecimal) item.get("subtotal")) %></td>
    </tr>
    <% } %>
    <tr>
        <td colspan="3" align="right"><strong>Total</strong></td>
        <td><strong><%= df.format(total) %></strong></td>
    </tr>
</table>
<br>

<!-- ✅ Download PDF Form -->
<form action="${pageContext.request.contextPath}/downloadAndSaveInvoice" method="post">
    <input type="hidden" name="phoneNumber" value="<%= phoneNumber %>">
    <input type="hidden" name="customerName" value="<%= customerName %>">

    <input type="submit" value="⬇ Download PDF Invoice & Save to DB">
</form>

<% } else { %>
<p style="color: red;">❌ No items found for invoice.</p>
<% } %>

<br><a href="${pageContext.request.contextPath}/jsp/shopDashboard.jsp">← Back to Dashboard</a>


</body>
</html>
