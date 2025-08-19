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
<html lang="en">
<head>
  <title>Admin Dashboard</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link
          rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
  />
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: 'Poppins', sans-serif;
      background: linear-gradient(135deg, #8862c5, #6e9def);
      color: #fff;
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .dashboard {
      max-width: 1100px;
      width: 95%;
      padding: 40px;
      border-radius: 20px;
      background: rgba(255, 255, 255, 0.05);
      backdrop-filter: blur(12px);
      box-shadow: 0 15px 35px rgba(0, 0, 0, 0.3);
      animation: fadeIn 1s ease-in-out;
    }

    .dashboard-header {
      text-align: center;
      margin-bottom: 30px;
    }

    .dashboard-header h2 {
      font-size: 28px;
      font-weight: 600;
      margin: 0;
      color: #fff;
    }

    .cards {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
      gap: 25px;
    }

    .card {
      background: #ffffff;
      padding: 25px;
      border-radius: 16px;
      text-align: center;
      transition: transform 0.3s ease, box-shadow 0.3s ease;
      box-shadow: 0 6px 15px rgba(0, 0, 0, 0.15);
      color: #333;
    }

    .card:hover {
      transform: translateY(-5px);
      box-shadow: 0 12px 25px rgba(0, 0, 0, 0.25);
    }

    .card i {
      font-size: 40px;
      margin-bottom: 12px;
      color: #6a11cb;
    }

    .card h3 {
      margin-bottom: 15px;
      font-size: 20px;
      font-weight: 600;
      color: #222;
    }

    .card a {
      display: block;
      margin: 8px 0;
      padding: 10px;
      border-radius: 8px;
      background: linear-gradient(45deg, #6a11cb, #2575fc);
      color: #fff;
      text-decoration: none;
      font-weight: 500;
      transition: all 0.3s ease;
    }

    .card a:hover {
      opacity: 0.9;
      transform: scale(1.02);
    }

    .logout-container {
      text-align: center;
      margin-top: 30px;
    }

    .logout-button {
      padding: 12px 24px;
      border: none;
      border-radius: 30px;
      background: #ff4d4d;
      color: white;
      font-size: 16px;
      font-weight: 500;
      cursor: pointer;
      transition: all 0.3s ease;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
    }

    .logout-button:hover {
      background-color: #cc0000;
      transform: translateY(-2px);
    }
  </style>

</head>
<body>
<div class="dashboard">
  <div class="dashboard-header">
    <h2><i class="fas fa-user-shield"></i> Welcome, <%= admin.getUsername() %> 👋</h2>
  </div>

  <div class="cards">
    <!-- Shop Workers -->
    <div class="card">
      <i class="fas fa-users"></i>
      <h3>Manage Shop Workers</h3>
      <a href="addShopWorker.jsp">➕ Add New Shop Worker</a>
      <a href="${pageContext.request.contextPath}/viewShopWorkers">📋 View / Edit / Delete</a>
    </div>

    <!-- Items -->
    <div class="card">
      <i class="fas fa-book"></i>
      <h3>Manage Items</h3>
      <a href="${pageContext.request.contextPath}/addItem">➕ Add New Item</a>
      <a href="${pageContext.request.contextPath}/viewItems">📋 View / Edit / Delete</a>
    </div>

    <!-- Categories -->
    <div class="card">
      <i class="fas fa-tags"></i>
      <h3>Manage Categories</h3>
      <a href="addCategory.jsp">➕ Add New Category</a>
      <a href="${pageContext.request.contextPath}/viewCategories">📋 View / Delete</a>
    </div>
  </div>

  <div class="logout-container">
    <form action="logout.jsp" method="post">
      <input type="submit" class="logout-button" value="🚪 Logout">
    </form>
  </div>
</div>
</body>
</html>
