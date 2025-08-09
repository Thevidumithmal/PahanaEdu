<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Customer Invoices</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet" />
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #dfe9f3 0%, #ffffff 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding: 40px 15px;
            font-size: 13px;
        }

        .container {
            max-width: 600px;
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            padding: 30px;
            border-radius: 16px;
            box-shadow: 0 12px 35px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            font-size: 1.4rem;
            font-weight: 600;
            margin-bottom: 25px;
            color: #2c3e50;
        }

        .form-label {
            font-weight: 500;
            color: #34495e;
        }

        .form-control {
            font-size: 0.9rem;
            border-radius: 10px;
        }

        .btn-search {
            background: #667eea;
            color: white;
            font-weight: 600;
            border: none;
            padding: 10px 20px;
            border-radius: 10px;
            transition: background-color 0.3s ease;
        }

        .btn-search:hover {
            background: #5a67d8;
            box-shadow: 0 6px 12px rgba(90, 103, 216, 0.4);
        }

        .alert {
            font-size: 0.9rem;
            border-radius: 10px;
            padding: 12px 16px;
        }

        .back-link {
            display: inline-block;
            margin-top: 25px;
            font-size: 0.9rem;
            padding: 10px 20px;
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
    </style>
</head>
<body>
<div class="container">
    <h2><i class="fa-solid fa-file-invoice me-2"></i>Search Invoices</h2>

    <form action="${pageContext.request.contextPath}/searchInvoices" method="get" class="mb-3">
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">
                <i class="fa-solid fa-phone me-1"></i>Enter Customer Phone Number:
            </label>
            <input type="text" name="phoneNumber" id="phoneNumber" class="form-control" required placeholder="e.g. 0771234567">
        </div>
        <button type="submit" class="btn btn-search w-100">
            <i class="fa-solid fa-magnifying-glass me-1"></i>Search
        </button>
    </form>

    <!-- Display error message if any -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">
            <i class="fa-solid fa-circle-exclamation me-1"></i> ${error}
        </div>
    </c:if>

    <a href="${pageContext.request.contextPath}/jsp/shopDashboard.jsp" class="back-link">
        <i class="fa-solid fa-arrow-left me-1"></i>Back to Dashboard
    </a>
</div>
</body>
</html>
