<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.business.customer.dto.CustomerDTO" %>
<%
  List<CustomerDTO> customers = (List<CustomerDTO>) request.getAttribute("customers");
  String notFound = (String) request.getAttribute("notFound");
  String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Search Customer</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet" />
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap');

    body {
      font-family: 'Poppins', sans-serif;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: flex-start;
      padding: 40px 15px;
      font-size: 13px; /* smaller base font */
      color: #2c3e50;
    }
    .container {
      max-width: 800px;
      background: #fff;
      padding: 30px 35px;
      border-radius: 16px;
      box-shadow: 0 15px 40px rgba(50, 50, 93, 0.1);
      transition: transform 0.3s ease;
    }
    .container:hover {
      transform: translateY(-6px);
    }
    h2 {
      font-size: 1.4rem;
      margin-bottom: 25px;
      font-weight: 600;
      text-align: center;
      color: #34495e;
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 8px;
    }
    h3 {
      font-size: 1.1rem;
      font-weight: 600;
      text-align: center;
      color: #34495e;
      margin-bottom: 20px;
    }
    .alert {
      font-size: 0.9rem;
      padding: 12px 16px;
      border-radius: 10px;
    }
    form.d-flex {
      gap: 12px;
      margin-bottom: 30px;
      flex-wrap: wrap;
      justify-content: center;
    }
    form label {
      font-size: 0.95rem;
      font-weight: 500;
      min-width: 110px;
      align-self: center;
      color: #34495e;
    }
    input[type="text"] {
      flex: 1 1 220px;
      padding: 10px 14px;
      font-size: 0.9rem;
      border-radius: 10px;
      border: 1.8px solid #ced4da;
    }
    input[type="text"]:focus {
      border-color: #5a67d8;
      box-shadow: 0 0 6px rgba(90, 103, 216, 0.4);
    }
    input[type="submit"] {
      font-size: 0.95rem;
      font-weight: 600;
      padding: 10px 22px;
      border-radius: 12px;
      background: #5a67d8;
      color: white;
      border: none;
      transition: 0.3s;
    }
    input[type="submit"]:hover {
      background: #434bbd;
      box-shadow: 0 6px 15px rgba(90, 103, 216, 0.4);
    }
    table {
      width: 100%;
      border-radius: 10px;
      overflow: hidden;
      font-size: 0.85rem;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    }
    thead {
      background: #5a67d8;
      color: #fff;
      font-weight: 600;
    }
    th, td {
      padding: 12px 16px;
    }
    tbody tr:hover {
      background-color: #eef2ff;
      transition: background-color 0.3s;
    }
    a.edit-link {
      font-size: 0.85rem;
      font-weight: 500;
      color: #5a67d8;
    }
    a.edit-link:hover {
      color: #434bbd;
      text-decoration: underline;
    }
    .back-link {
      font-size: 0.9rem;
      padding: 10px 24px;
      border-radius: 12px;
      background: #d1d5db;
      color: #2c3e50;
      margin-top: 35px;
      display: inline-block;
      transition: 0.3s ease;
    }
    .back-link:hover {
      background: #9ca3af;
      color: #1a252f;
    }
    @media (max-width: 576px) {
      form label {
        min-width: 100%;
        text-align: left;
      }
      form.d-flex {
        justify-content: flex-start;
      }
      input[type="text"], input[type="submit"] {
        flex: 1 1 100%;
      }
      .back-link {
        width: 100%;
        text-align: center;
      }
    }
  </style>

</head>
<body>
<div class="container shadow-sm">
  <h2><i class="fa-solid fa-magnifying-glass"></i> Search Customer</h2>

  <% if ("updated".equals(msg)) { %>
  <div class="alert alert-success" role="alert">
    <i class="fa-solid fa-circle-check"></i> Customer updated successfully.
  </div>
  <% } %>

  <% if (notFound != null) { %>
  <div class="alert alert-danger" role="alert">
    <i class="fa-solid fa-triangle-exclamation"></i> <%= notFound %>
  </div>
  <% } %>

  <!-- Search Form -->
  <form action="${pageContext.request.contextPath}/viewCustomer" method="get" class="d-flex align-items-center gap-3 mb-4">
    <label for="phoneNumber" class="mb-0"><i class="fa-solid fa-phone"></i> Phone Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required placeholder="Enter phone number" />
    <input type="submit" value="Search" />
  </form>

  <!-- Results Table -->
  <% if (customers != null && !customers.isEmpty()) { %>
  <h3>Customer Details</h3>
  <table class="table table-striped table-hover rounded">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Phone</th>
      <th>NIC No</th>
      <th>Address</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% for (CustomerDTO customer : customers) { %>
    <tr>
      <td><%= customer.getId() %></td>
      <td><%= customer.getName() %></td>
      <td><%= customer.getPhone() %></td>
      <td><%= customer.getNicNo() %></td>
      <td><%= customer.getAddress() %></td>
      <td>
        <a href="${pageContext.request.contextPath}/editCustomer?id=<%= customer.getId() %>" class="edit-link" title="Edit Customer">
          <i class="fa-solid fa-pen-to-square"></i> Edit
        </a>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <% } %>

  <a href="${pageContext.request.contextPath}/jsp/shopDashboard.jsp" class="back-link">
    <i class="fa-solid fa-arrow-left"></i> Back to Dashboard
  </a>
</div>
</body>
</html>
