<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.business.customer.dto.CustomerDTO" %>
<%
    CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
    String validationErrors = (String) request.getAttribute("validationErrors");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet" />
    <style>
        body {
            background: linear-gradient(to right, #667eea, #764ba2);
            font-family: 'Poppins', sans-serif;
            padding: 50px 15px;
            color: #333;
        }

        .form-container {
            background: #fff;
            padding: 35px 40px;
            max-width: 600px;
            margin: auto;
            border-radius: 16px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.15);
        }

        .form-container h2 {
            font-size: 1.6rem;
            margin-bottom: 25px;
            font-weight: 600;
            text-align: center;
            color: #333;
        }

        .form-label {
            font-weight: 500;
            margin-bottom: 6px;
        }

        .form-control {
            border-radius: 10px;
            font-size: 0.95rem;
        }

        .btn-update {
            background-color: #5a67d8;
            color: white;
            font-weight: 600;
            border-radius: 12px;
            padding: 10px 24px;
            width: 100%;
            transition: 0.3s;
        }

        .btn-update:hover {
            background-color: #434bbd;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 25px;
            font-size: 0.9rem;
            color: #444;
            text-decoration: underline;
        }

        .back-link:hover {
            color: #222;
        }

        .alert-custom {
            font-size: 0.9rem;
            border-radius: 10px;
            padding: 12px 16px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2><i class="fa-solid fa-pen-to-square me-2"></i>Edit Customer</h2>

    <% if (validationErrors != null) { %>
    <div class="alert alert-danger alert-custom" role="alert">
        <i class="fa-solid fa-circle-exclamation me-1"></i> <%= validationErrors %>
    </div>
    <% } %>

    <% if (error != null) { %>
    <div class="alert alert-warning alert-custom" role="alert">
        <i class="fa-solid fa-triangle-exclamation me-1"></i> <%= error %>
    </div>
    <% } %>

    <form action="${pageContext.request.contextPath}/editCustomer" method="post">
        <input type="hidden" name="id" value="<%= customer.getId() %>">

        <div class="mb-3">
            <label for="name" class="form-label"><i class="fa-solid fa-user me-1"></i>Name</label>
            <input type="text" class="form-control" id="name" name="name" value="<%= customer.getName() %>" required>
        </div>

        <div class="mb-3">
            <label for="phone" class="form-label"><i class="fa-solid fa-phone me-1"></i>Phone</label>
            <input type="text" class="form-control" id="phone" name="phone" value="<%= customer.getPhone() %>" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label"><i class="fa-solid fa-envelope me-1"></i>Email</label>
            <input type="email" class="form-control" id="email" name="email" value="<%= customer.getEmail() != null ? customer.getEmail() : "" %>" required>
        </div>

        <div class="mb-3">
            <label for="nicNo" class="form-label"><i class="fa-solid fa-id-card me-1"></i>NIC No</label>
            <input type="text" class="form-control" id="nicNo" name="nicNo" value="<%= customer.getNicNo() %>" required>
        </div>

        <div class="mb-3">
            <label for="address" class="form-label"><i class="fa-solid fa-location-dot me-1"></i>Address</label>
            <input type="text" class="form-control" id="address" name="address" value="<%= customer.getAddress() != null ? customer.getAddress() : "" %>">
        </div>

        <button type="submit" class="btn btn-update"><i class="fa-solid fa-check me-1"></i>Update Customer</button>
    </form>

    <a href="${pageContext.request.contextPath}/viewCustomer?phoneNumber=<%= customer.getPhone() %>" class="back-link">
        <i class="fa-solid fa-arrow-left me-1"></i>Back to Customer Details
    </a>
</div>

</body>
</html>
