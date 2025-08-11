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
    <title>Add Shop Worker</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
        }

        .container {
            max-width: 500px;
            margin: 80px auto;
            background: #fff;
            padding: 40px 30px;
            border-radius: 12px;
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2);
        }

        h2, h3 {
            text-align: center;
            color: #4e54c8;
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
        input[type="password"] {
            padding: 10px;
            margin-top: 5px;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 15px;
        }

        input[type="submit"] {
            margin-top: 25px;
            padding: 12px;
            background-color: #4e54c8;
            color: white;
            border: none;
            border-radius: 25px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #3c3fa1;
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
            color: #333;
            text-decoration: none;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Welcome, <%= admin.getUsername() %> 👋</h2>
    <h3>Add New Shop Worker</h3>

    <form name="workerForm" action="${pageContext.request.contextPath}/addShopWorker" method="post" onsubmit="return validateForm()">

        <label>Name:</label>
        <input type="text" name="name" required value="${param.name != null ? param.name : ''}">
        <div style="color:red;">${nameError}</div>

        <label>NIC Number:</label>
        <input type="text" name="nicNo" required value="${param.nicNo != null ? param.nicNo : ''}">
        <div id="nicError" style="color:red;">${nicError}</div>

        <label>Username:</label>
        <input type="text" name="username" required>

        <label>Password:</label>
        <input type="password" name="password" required>

        <label>Phone:</label>
        <input type="text" name="phone" required value="${param.phone != null ? param.phone : ''}">
        <div id="phoneError" style="color:red;">${phoneError}</div>

        <label>Address:</label>
        <input type="text" name="address">

        <input type="submit" value="Add Worker">
    </form>

    <div class="message-success">${success}</div>
    <div class="message-error">${error}</div>

    <a class="back-link" href="${pageContext.request.contextPath}/jsp/adminDashboard.jsp">← Back to Dashboard</a>
</div>
</body>
<script>
    function validateForm() {
        const nic = document.forms["workerForm"]["nicNo"].value.trim();
        const phone = document.forms["workerForm"]["phone"].value.trim();

        const nic10Pattern = /^\d{9}[vVxX]$/;
        const nic12Pattern = /^\d{12}$/;

        const phonePattern = /^\d{10}$/;

        let valid = true;
        let nicError = "";
        let phoneError = "";

        if (!(nic10Pattern.test(nic) || nic12Pattern.test(nic))) {
            nicError = "NIC must be 10 chars (9 digits + letter) or 12 digits.";
            valid = false;
        }
        if (!phonePattern.test(phone)) {
            phoneError = "Phone number must be exactly 10 digits.";
            valid = false;
        }

        document.getElementById("nicError").innerText = nicError;
        document.getElementById("phoneError").innerText = phoneError;

        return valid;
    }
</script>
</html>
