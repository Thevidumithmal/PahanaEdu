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
  <title>Admin Dashboard</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(135deg, #4e54c8, #8f94fb);
      color: #333;
    }

    .container {
      max-width: 800px;
      margin: 60px auto;
      background: #fff;
      padding: 40px;
      border-radius: 15px;
      box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
      text-align: center;
    }

    h2 {
      margin-bottom: 10px;
      font-size: 28px;
      color: #ee08c8;
    }

    h3 {
      margin-top: 30px;
      font-size: 22px;
      color: #333;
    }

    ul {
      list-style-type: none;
      padding: 0;
    }

    ul li {
      margin: 10px 0;
    }

    a.button {
      display: inline-block;
      padding: 10px 20px;
      margin: 5px;
      border-radius: 25px;
      background-color: #4e54c8;
      color: white;
      text-decoration: none;
      transition: background-color 0.3s ease;
    }

    a.button:hover {
      background-color: #2c2f91;
    }

    .logout-button {
      margin-top: 30px;
      padding: 10px 20px;
      border: none;
      border-radius: 25px;
      background-color: #ff4d4d;
      color: white;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .logout-button:hover {
      background-color: #cc0000;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Welcome, <%= admin.getUsername() %> 👋</h2>

  <h3>Manage Shop Workers</h3>
  <ul>
    <li><a class="button" href="addShopWorker.jsp">➕ Add New Shop Worker</a></li>
    <li><a class="button" href="${pageContext.request.contextPath}/viewShopWorkers">📋 View / Edit / Delete Shop Workers</a></li>
  </ul>

  <h3>Manage Items</h3>
  <ul>
    <li><a class="button" href="addItem.jsp">➕ Add New Item</a></li>
    <li><a class="button" href="${pageContext.request.contextPath}/viewItems">📋 View / Edit / Delete Items</a></li>
  </ul>

  <form action="logout.jsp" method="post">
    <input type="submit" class="logout-button" value="🚪 Logout">
  </form>
</div>
</body>
</html>
