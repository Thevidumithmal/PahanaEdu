<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.business.user.dto.UserDTO" %>
<%
  UserDTO admin = (UserDTO) session.getAttribute("admin");
  if (admin == null) {
    response.sendRedirect("adminLogin.jsp");
    return;
  }

  UserDTO worker = (UserDTO) request.getAttribute("worker");
  if (worker == null) {
    response.sendRedirect("viewShopWorkers");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Shop Worker</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(135deg, #00c6ff, #0072ff);
      color: #333;
    }

    .container {
      max-width: 500px;
      margin: 80px auto;
      background: #fff;
      padding: 40px 30px;
      border-radius: 12px;
      box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2);
    }

    h2 {
      text-align: center;
      color: #0072ff;
    }

    form {
      display: flex;
      flex-direction: column;
    }

    label {
      margin-top: 15px;
      font-weight: bold;
    }

    input[type="text"] {
      padding: 10px;
      margin-top: 5px;
      border-radius: 8px;
      border: 1px solid #ccc;
      font-size: 15px;
    }

    input[type="submit"] {
      margin-top: 25px;
      padding: 12px;
      background-color: #0072ff;
      color: white;
      border: none;
      border-radius: 25px;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
      background-color: #004cbf;
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
  <h2>Edit Shop Worker</h2>

  <form action="${pageContext.request.contextPath}/editShopWorker" method="post">
    <input type="hidden" name="id" value="<%= worker.getId() %>">

    <label>Username:</label>
    <input type="text" name="username" value="<%= worker.getUsername() %>" required>

    <label>Phone:</label>
    <input type="text" name="phone" value="<%= worker.getPhone() %>">

    <label>Address:</label>
    <input type="text" name="address" value="<%= worker.getAddress() %>">

    <input type="submit" value="Update">
  </form>

  <a class="back-link" href="viewShopWorkers">← Back to Shop Workers</a>
</div>
</body>
</html>
