<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, com.pahanaedu.business.invoice.model.Invoice, com.pahanaedu.business.invioceItem.model.InvoiceItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Customer Invoices</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet" />
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

    body {
      font-family: 'Poppins', sans-serif;
      background: linear-gradient(135deg, #dbe6f6 0%, #c5796d 100%);
      min-height: 100vh;
      padding: 40px 15px;
      font-size: 13px;
      display: flex;
      justify-content: center;
      align-items: flex-start;
    }

    .container {
      max-width: 900px;
      background: rgba(255, 255, 255, 0.95);
      backdrop-filter: blur(8px);
      padding: 30px 35px;
      border-radius: 18px;
      box-shadow: 0 15px 40px rgba(0, 0, 0, 0.1);
    }

    h2 {
      font-size: 1.4rem;
      font-weight: 600;
      text-align: center;
      color: #2c3e50;
      margin-bottom: 25px;
    }

    .search-bar {
      margin-bottom: 25px;
    }

    .form-control {
      font-size: 0.9rem;
      border-radius: 10px;
    }

    h3 {
      font-size: 1.1rem;
      font-weight: 600;
      margin-top: 25px;
      color: #34495e;
    }

    p {
      font-size: 0.9rem;
      margin-bottom: 5px;
    }

    .invoice-table {
      width: 100%;
      font-size: 0.85rem;
      border-radius: 10px;
      overflow: hidden;
      margin-top: 10px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    }

    .invoice-table th {
      background: #667eea;
      color: white;
      text-align: center;
      padding: 10px;
    }

    .invoice-table td {
      padding: 10px;
      text-align: center;
    }

    hr {
      margin: 30px 0;
      border: 0;
      height: 1px;
      background: #ddd;
    }

    .back-link {
      display: inline-block;
      margin-top: 30px;
      padding: 10px 22px;
      font-size: 0.9rem;
      background: #cbd5e0;
      color: #2c3e50;
      border-radius: 10px;
      text-decoration: none;
      transition: background-color 0.3s ease;
    }

    .back-link:hover {
      background: #a0aec0;
      color: #1a202c;
    }

    .invoice-summary {
      background: #f6f8fa;
      padding: 10px 16px;
      border-radius: 12px;
      margin-top: 10px;
    }

    @media (max-width: 576px) {
      .invoice-table th,
      .invoice-table td {
        font-size: 0.75rem;
        padding: 6px;
      }
    }
  </style>
</head>
<body>
<div class="container">
  <h2><i class="fa-solid fa-file-invoice me-2"></i>Invoices for Customer</h2>

  <!-- 🔍 Search by Invoice ID -->
  <div class="input-group search-bar">
    <span class="input-group-text bg-light"><i class="fa-solid fa-search"></i></span>
    <input type="text" class="form-control" id="invoiceSearch" placeholder="Search by Invoice ID...">
  </div>

  <!-- Invoice Display -->
  <c:forEach var="invoice" items="${invoices}">
    <div class="invoice-block" data-invoice-id="${invoice.id}">
      <h3><i class="fa-solid fa-receipt me-1"></i>Invoice ID: ${invoice.id}</h3>
      <div class="invoice-summary">
        <p><i class="fa-solid fa-user me-1"></i>Customer Name: <strong>${invoice.customerName}</strong></p>
        <p><i class="fa-solid fa-phone me-1"></i>Phone Number: <strong>${invoice.phoneNumber}</strong></p>
        <p><i class="fa-solid fa-coins me-1"></i>Total: <strong>Rs. ${invoice.total}</strong></p>
      </div>

      <table class="table table-bordered table-striped invoice-table mt-3">
        <thead>
        <tr>
          <th>Item Name</th>
          <th>Quantity</th>
          <th>Unit Price (Rs.)</th>
          <th>Subtotal (Rs.)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${invoiceItemsMap[invoice.id]}">
          <tr>
            <td>${item.itemName}</td>
            <td>${item.quantity}</td>
            <td>${item.unitPrice}</td>
            <td>${item.subtotal}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <hr/>
    </div>
  </c:forEach>

  <a href="${pageContext.request.contextPath}/jsp/searchInvoices.jsp" class="back-link">
    <i class="fa-solid fa-arrow-left me-1"></i>Back to Search
  </a>
</div>

<!-- 🔎 JS: Filter Invoice Blocks by Invoice ID -->
<script>
  document.getElementById('invoiceSearch').addEventListener('input', function () {
    const filter = this.value.toLowerCase();
    const blocks = document.querySelectorAll('.invoice-block');

    blocks.forEach(block => {
      const invoiceId = block.getAttribute('data-invoice-id').toLowerCase();
      block.style.display = invoiceId.includes(filter) ? 'block' : 'none';
    });
  });
</script>
</body>
</html>
