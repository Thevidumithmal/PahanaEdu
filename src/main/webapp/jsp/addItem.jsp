<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.business.user.dto.UserDTO" %>
<%
    UserDTO admin = (UserDTO) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Item</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #fddb92, #d1fdff);
        }

        .container {
            max-width: 500px;
            margin: 80px auto;
            background: #fff;
            padding: 40px 30px;
            border-radius: 12px;
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.15);
        }

        h2 {
            text-align: center;
            color: #ff9800;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-top: 15px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"] {
            padding: 10px;
            margin-top: 5px;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 15px;
        }

        input[type="submit"] {
            margin-top: 25px;
            padding: 12px;
            background-color: #ff9800;
            color: white;
            border: none;
            border-radius: 25px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #e68900;
        }

        .message-success {
            margin-top: 20px;
            color: green;
            text-align: center;
        }

        .message-error {
            margin-top: 20px;
            color: red;
            text-align: center;
        }

        .back-link {
            display: block;
            margin-top: 30px;
            text-align: center;
            font-weight: bold;
            color: #333;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Add New Item</h2>

    <form action="${pageContext.request.contextPath}/addItem" method="post">
        <label>Item Name:</label>
        <input type="text" name="name" required>

        <label>Price:</label>
        <input type="number" step="0.01" name="price" required>

        <input type="submit" value="Add Item">
    </form>

    <div class="message-success">
        <%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %>
    </div>
    <div class="message-error">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </div>

    <a class="back-link" href="${pageContext.request.contextPath}/jsp/adminDashboard.jsp">← Back to Dashboard</a>
</div>
</body>
</html>
