<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/26/2025
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, com.pahanaedu.business.invoice.model.Invoice, com.pahanaedu.business.invioceItem.model.InvoiceItem" %>

<!DOCTYPE html>
<html>
<head>
  <title>Customer Invoices</title>
</head>
<body>
<h2>Invoices for Customer</h2>

<c:forEach var="invoice" items="${invoices}">
  <h3>Invoice ID: ${invoice.id}</h3>
  <p>Customer Name: ${invoice.customerName}</p>
  <p>Phone Number: ${invoice.phoneNumber}</p>
  <p>Total: ${invoice.total}</p>

  <table border="1" cellpadding="6" cellspacing="0">
    <tr>
      <th>Item Name</th><th>Quantity</th><th>Unit Price</th><th>Subtotal</th>
    </tr>
    <c:forEach var="item" items="${invoiceItemsMap[invoice.id]}">
      <tr>
        <td>${item.itemName}</td>
        <td>${item.quantity}</td>
        <td>${item.unitPrice}</td>
        <td>${item.subtotal}</td>
      </tr>
    </c:forEach>
  </table>
  <hr/>
</c:forEach>

<a href="${pageContext.request.contextPath}/jsp/searchInvoices.jsp">← Back to Search</a>
</body>
</html>

