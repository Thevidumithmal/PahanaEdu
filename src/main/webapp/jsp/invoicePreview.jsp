<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.text.DecimalFormat, java.math.BigDecimal" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Invoice Preview</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet" />
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #e0eafc 0%, #cfdef3 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding: 40px 15px;
            font-size: 13px;
        }

        .container {
            max-width: 880px;
            background: rgba(255, 255, 255, 0.9);
            backdrop-filter: blur(8px);
            padding: 30px 40px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.1);
        }

        h2 {
            font-size: 1.4rem;
            font-weight: 600;
            text-align: center;
            color: #2c3e50;
            margin-bottom: 25px;
        }

        h3 {
            font-size: 1.05rem;
            font-weight: 500;
            color: #2c3e50;
            margin-bottom: 10px;
        }

        .table {
            font-size: 0.9rem;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
        }

        thead {
            background-color: #667eea;
            color: #fff;
        }

        td, th {
            text-align: center;
            vertical-align: middle;
        }

        .btn-download {
            margin-top: 20px;
            background-color: #28a745;
            color: #fff;
            padding: 10px 20px;
            font-size: 0.9rem;
            font-weight: 600;
            border-radius: 10px;
            border: none;
            transition: all 0.3s ease;
        }

        .btn-download:hover {
            background-color: #218838;
            box-shadow: 0 6px 12px rgba(40, 167, 69, 0.4);
        }

        .alert-danger {
            font-size: 0.95rem;
            margin-top: 20px;
        }

        .back-link {
            display: inline-block;
            margin-top: 30px;
            font-size: 0.9rem;
            padding: 10px 22px;
            background: #ced4da;
            border-radius: 10px;
            text-decoration: none;
            color: #2c3e50;
            transition: background-color 0.3s ease;
        }

        .back-link:hover {
            background: #a0aec0;
            color: #1a202c;
        }
    </style>
</head>
<body>
<div class="container">
    <h2><i class="fa-solid fa-file-invoice-dollar me-2"></i>Invoice Preview</h2>

    <%
        String phoneNumber = (String) request.getAttribute("phoneNumber");
        String customerName = (String) request.getAttribute("customerName");
        List<Map<String, Object>> billItems = (List<Map<String, Object>>) request.getAttribute("billItems");
        BigDecimal total = (BigDecimal) request.getAttribute("total");
        DecimalFormat df = new DecimalFormat("0.00");
    %>

    <% if (billItems != null && !billItems.isEmpty()) { %>
    <div class="mb-3">
        <h3><i class="fa-solid fa-user me-2"></i>Customer Name: <strong><%= customerName %></strong></h3>
        <h3><i class="fa-solid fa-phone me-2"></i>Customer Phone: <strong><%= phoneNumber %></strong></h3>
    </div>

    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th><i class="fa-solid fa-box"></i> Item</th>
            <th><i class="fa-solid fa-dollar-sign"></i> Unit Price (Rs.)</th>
            <th><i class="fa-solid fa-hashtag"></i> Quantity</th>
            <th><i class="fa-solid fa-coins"></i> Subtotal (Rs.)</th>
        </tr>
        </thead>
        <tbody>
        <% for (Map<String, Object> item : billItems) { %>
        <tr>
            <td><%= item.get("itemName") %></td>
            <td><%= df.format((BigDecimal) item.get("unitPrice")) %></td>
            <td><%= item.get("quantity") %></td>
            <td><%= df.format((BigDecimal) item.get("subtotal")) %></td>
        </tr>
        <% } %>
        <tr class="table-light">
            <td colspan="3" class="text-end"><strong>Total</strong></td>
            <td><strong><%= df.format(total) %></strong></td>
        </tr>
        </tbody>
    </table>

    <form action="${pageContext.request.contextPath}/downloadAndSaveInvoice" method="post">
        <input type="hidden" name="phoneNumber" value="<%= phoneNumber %>">
        <input type="hidden" name="customerName" value="<%= customerName %>">
        <button type="submit" class="btn-download">
            <i class="fa-solid fa-download me-1"></i>Download PDF Invoice & Save
        </button>
    </form>

    <% } else { %>
    <div class="alert alert-danger">
        <i class="fa-solid fa-circle-exclamation me-1"></i> No items found for invoice.
    </div>
    <% } %>

    <a href="${pageContext.request.contextPath}/jsp/shopDashboard.jsp" class="back-link">
        <i class="fa-solid fa-arrow-left me-1"></i>Back to Dashboard
    </a>
</div>
</body>
</html>
