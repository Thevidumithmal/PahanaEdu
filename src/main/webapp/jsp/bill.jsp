<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.pahanaedu.business.item.model.Item, com.pahanaedu.business.customer.dto.CustomerDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Generate Invoice</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet"/>
  <style>
    body {
      background: linear-gradient(to right, #d7d2cc, #304352);
      font-size: 0.9rem;
      font-family: 'Segoe UI', sans-serif;
    }
    .container {
      max-width: 900px;
      margin-top: 50px;
      background: rgba(255, 255, 255, 0.85);
      padding: 30px;
      border-radius: 12px;
      box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
    }
    .form-control, .form-select {
      font-size: 0.9rem;
    }
    .table th, .table td {
      vertical-align: middle;
      text-align: center;
    }
    .btn {
      font-size: 0.85rem;
    }
    .header-icon {
      font-size: 1.4rem;
      margin-right: 10px;
    }
    .back-link {
      text-decoration: none;
      color: #333;
    }
    .back-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <h4 class="mb-4 text-center">
    <i class="fa-solid fa-file-invoice header-icon"></i>Generate Invoice
  </h4>

  <!-- Step 1: Search Customer -->
  <form class="row g-3 mb-4" action="${pageContext.request.contextPath}/fetchCustomerForBill" method="post">
    <div class="col-md-9">
      <label for="phoneNumber" class="form-label">Customer Phone No:</label>
      <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" required>
    </div>
    <div class="col-md-3 d-flex align-items-end">
      <button type="submit" class="btn btn-dark w-100">
        <i class="fa fa-search me-1"></i>Search
      </button>
    </div>
  </form>

  <%
    CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
    List<Item> itemList = (List<Item>) request.getAttribute("items");
    if (customer != null && itemList != null) {
  %>
  <hr>
  <div class="mb-4">
    <h6 class="fw-bold"><i class="fa-solid fa-user me-2"></i>Customer Information</h6>
    <p class="mb-1">👤 <strong>Name:</strong> <%= customer.getName() %></p>
    <p class="mb-1">📞 <strong>Phone:</strong> <%= customer.getPhone() %></p>
    <p class="mb-1">🏠 <strong>Address:</strong> <%= customer.getAddress() %></p>
    <p class="mb-0">🆔 <strong>NIC No:</strong> <%= customer.getNicNo() %></p>
  </div>

  <!-- Step 2: Select Items & Quantity -->
  <form action="${pageContext.request.contextPath}/generateInvoiceCustomer" method="post">
    <input type="hidden" name="phoneNumber" value="<%= customer.getPhone() %>">

    <table class="table table-bordered table-hover table-sm">
      <thead class="table-dark">
      <tr>
        <th>Item Name</th>
        <th>Unit Price (Rs.)</th>
        <th>Quantity</th>
      </tr>
      </thead>
      <tbody>
      <% for (Item item : itemList) { %>
      <tr>
        <td><%= item.getName() %></td>
        <td><%= item.getPrice() %></td>
        <td>
          <input type="number" class="form-control" name="quantity_<%= item.getId() %>" min="0" value="0">
          <input type="hidden" name="item_<%= item.getId() %>" value="<%= item.getName() %>">
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>

    <button type="submit" class="btn btn-success w-100 mt-3">
      <i class="fa-solid fa-file-invoice-dollar me-2"></i>Generate Invoice PDF
    </button>
  </form>

  <% } else if (request.getAttribute("error") != null) { %>
  <div class="alert alert-danger mt-3" role="alert">
    <%= request.getAttribute("error") %>
  </div>
  <% } %>

  <hr>
  <a href="${pageContext.request.contextPath}/jsp/shopDashboard.jsp" class="back-link">
    ← Back to Dashboard
  </a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
