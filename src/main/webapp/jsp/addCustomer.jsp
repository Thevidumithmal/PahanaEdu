<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Add Customer | Bookshop</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap Icons -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

  <style>
    body {
      background: linear-gradient(rgba(25, 25, 30, 0.6), rgba(10, 10, 15, 0.6)),
      url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=1350&q=80') no-repeat center center fixed;
      background-size: cover;
      font-family: 'Segoe UI', sans-serif;
      color: #f0f0f0;
    }

    .card {
      background: rgba(255, 255, 255, 0.05); /* transparent */
      border-radius: 16px;
      padding: 30px;
      border: 1px solid rgba(255, 255, 255, 0.15);
      backdrop-filter: blur(10px);
      box-shadow:
              0 8px 30px rgba(0, 0, 0, 0.5),
              inset 0 0 0.8px rgba(255, 255, 255, 0.1);
      color: #eaeaea;
    }

    .form-heading {
      font-weight: 700;
      text-shadow: 0 1px 3px #000;
      color: #ffffff;
    }

    .form-label {
      color: #cccccc;
      font-weight: 500;
    }

    .form-control {
      background: rgba(255, 255, 255, 0.07);
      border: 1px solid rgba(255, 255, 255, 0.2);
      color: #ffffff;
      border-radius: 6px;
      backdrop-filter: blur(4px);
    }

    .form-control:focus {
      background: rgba(255, 255, 255, 0.15);
      color: #ffffff;
      border-color: #66afe9;
      box-shadow: 0 0 8px rgba(102, 175, 233, 0.4);
    }

    .btn-custom {
      background: linear-gradient(145deg, #4bc3af, #2ea18c);
      color: #fff;
      border: none;
      font-weight: bold;
      border-radius: 6px;
      transition: background 0.3s;
    }

    .btn-custom:hover {
      background: linear-gradient(145deg, #67e0c4, #349d89);
    }

    .back-link {
      color: #b0c4de;
      font-size: 14px;
      font-weight: 500;
      text-decoration: none;
    }

    .back-link:hover {
      text-decoration: underline;
      color: #ffffff;
    }

    .alert {
      border-radius: 8px;
      background-color: rgba(245, 245, 245, 0.1);
      color: #f0f0f0;
      border-left: 4px solid #888;
    }

    .alert-success {
      border-left-color: #28a745;
    }

    .alert-danger {
      border-left-color: #dc3545;
    }

    .alert-warning {
      border-left-color: #ffc107;
    }

    .btn-close-white {
      filter: invert(1);
    }
  </style>


</head>
<body>

<div class="container py-5">
  <div class="row justify-content-center">
    <div class="col-md-7 col-lg-6">
      <div class="card">
        <h3 class="text-center mb-4 form-heading"><i class="bi bi-person-plus-fill me-2"></i>Add New Customer</h3>

        <% String msg = request.getParameter("msg");
          String error = request.getParameter("error");
          String validationErrors = (String) request.getAttribute("validationErrors");
        %>

        <% if ("added".equals(msg)) { %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
          <i class="bi bi-check-circle-fill me-2"></i>Customer added successfully!
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% } else if ("true".equals(error)) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
          <i class="bi bi-x-circle-fill me-2"></i>Failed to add customer. Please try again.
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% } %>

        <% if (validationErrors != null) { %>
        <div class="alert alert-warning" role="alert">
          <i class="bi bi-exclamation-triangle-fill me-2"></i><%= validationErrors %>
        </div>
        <% } %>

        <form action="${pageContext.request.contextPath}/addCustomer" method="post">
          <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control" required
                   value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>">
          </div>
          <div class="mb-3">
            <label class="form-label">Phone</label>
            <input type="text" name="phone" class="form-control" required
                   value="<%= request.getAttribute("phone") != null ? request.getAttribute("phone") : "" %>">
          </div>
          <div class="mb-3">
            <label class="form-label">NIC No</label>
            <input type="text" name="nicNo" class="form-control" required
                   value="<%= request.getAttribute("nicNo") != null ? request.getAttribute("nicNo") : "" %>">
          </div>
          <div class="mb-3">
            <label class="form-label">Address</label>
            <input type="text" name="address" class="form-control"
                   value="<%= request.getAttribute("address") != null ? request.getAttribute("address") : "" %>">
          </div>
          <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" name="email" class="form-control" required
                   value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>">
          </div>

          <div class="d-grid">
            <button type="submit" class="btn btn-custom">
              <i class="bi bi-plus-circle me-1"></i> Add Customer
            </button>
          </div>
        </form>


        <div class="text-center mt-4">
          <a href="${pageContext.request.contextPath}/jsp/shopDashboard.jsp" class="back-link">
            <i class="bi bi-arrow-left-circle me-1"></i>Back to Dashboard
          </a>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
