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
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

    body {
      font-family: 'Poppins', sans-serif;
      background: linear-gradient(135deg, #cfd9df 0%, #e2ebf0 100%);
      color: #2c3e50;
      font-size: 13px;
      padding: 40px 15px;
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: flex-start;
    }

    .container {
      max-width: 880px;
      background: rgba(255, 255, 255, 0.92);
      backdrop-filter: blur(8px);
      border-radius: 20px;
      padding: 30px 35px;
      box-shadow: 0 15px 40px rgba(0, 0, 0, 0.1);
      transition: all 0.3s ease;
    }

    .container:hover {
      transform: translateY(-6px);
    }

    h2, h3 {
      text-align: center;
      color: #2c3e50;
    }

    h2 {
      font-size: 1.4rem;
      font-weight: 600;
      margin-bottom: 25px;
    }

    h3 {
      font-size: 1.1rem;
      margin-top: 30px;
      margin-bottom: 20px;
    }

    .alert {
      font-size: 0.9rem;
      border-radius: 10px;
    }

    form.d-flex {
      gap: 12px;
      flex-wrap: wrap;
      justify-content: center;
      margin-bottom: 30px;
    }

    form label {
      font-weight: 500;
      color: #34495e;
      min-width: 100px;
      align-self: center;
    }

    input[type="text"] {
      flex: 1 1 220px;
      padding: 10px 14px;
      font-size: 0.9rem;
      border-radius: 10px;
      border: 2px solid #ced4da;
      transition: border-color 0.3s ease, box-shadow 0.3s ease;
    }

    input[type="text"]:focus {
      border-color: #667eea;
      box-shadow: 0 0 8px rgba(102, 126, 234, 0.5);
      outline: none;
    }

    input[type="submit"] {
      background: #667eea;
      color: white;
      border: none;
      padding: 10px 22px;
      font-weight: 600;
      border-radius: 12px;
      transition: all 0.3s;
    }

    input[type="submit"]:hover {
      background: #5a67d8;
      box-shadow: 0 6px 12px rgba(90, 103, 216, 0.4);
    }

    table {
      width: 100%;
      border-radius: 12px;
      overflow: hidden;
      font-size: 0.85rem;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    }

    thead {
      background: #667eea;
      color: #fff;
    }

    th, td {
      padding: 12px 16px;
      vertical-align: middle;
    }

    tbody tr:hover {
      background-color: #f1f5ff;
      transition: background-color 0.3s ease;
    }

    .edit-link {
      font-size: 0.85rem;
      font-weight: 500;
      color: #5a67d8;
      text-decoration: none;
    }

    .edit-link:hover {
      color: #434bbd;
      text-decoration: underline;
    }

    .back-link {
      display: inline-block;
      margin-top: 35px;
      padding: 10px 24px;
      background: #cbd5e0;
      color: #2c3e50;
      border-radius: 12px;
      text-decoration: none;
      font-size: 0.9rem;
      transition: 0.3s ease;
    }

    .back-link:hover {
      background: #a0aec0;
      color: #1a202c;
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
<div class="container">
  <h2><i class="fa-solid fa-magnifying-glass me-2"></i>Search Customer</h2>

  <% if ("updated".equals(msg)) { %>
  <div class="alert alert-success">
    <i class="fa-solid fa-circle-check me-1"></i> Customer updated successfully.
  </div>
  <% } %>

  <% if (notFound != null) { %>
  <div class="alert alert-danger">
    <i class="fa-solid fa-triangle-exclamation me-1"></i> <%= notFound %>
  </div>
  <% } %>

  <!-- Search Form -->
  <form action="${pageContext.request.contextPath}/viewCustomer" method="get" class="d-flex align-items-center">
    <label for="phoneNumber"><i class="fa-solid fa-phone me-1"></i>Phone Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required placeholder="Enter phone number" />
    <input type="submit" value="Search" />
  </form>

  <!-- Results Table -->
  <% if (customers != null && !customers.isEmpty()) { %>
  <h3><i class="fa-solid fa-users me-1"></i>Customer Details</h3>
  <table class="table table-hover table-striped">
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
        <a href="${pageContext.request.contextPath}/editCustomer?id=<%= customer.getId() %>" class="edit-link">
          <i class="fa-solid fa-pen-to-square me-1"></i>Edit
        </a>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <% } %>

  <a href="${pageContext.request.contextPath}/jsp/shopDashboard.jsp" class="back-link">
    <i class="fa-solid fa-arrow-left me-1"></i>Back to Dashboard
  </a>
</div>
</body>
</html>
