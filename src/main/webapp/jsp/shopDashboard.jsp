<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.business.user.model.User" %>
<%
  User shopworker = (User) session.getAttribute("shopworker");
  if (shopworker == null) {
    response.sendRedirect("shopLogin.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Shop Worker Dashboard</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet" />
  <style>
    /* Background Image and Overlay */
    body {
      margin: 0;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      min-height: 100vh;
      display: flex;
      flex-direction: column;
      align-items: center;
      color: #2c3e50;
      background: url('https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=1470&q=80') no-repeat center center fixed;
      background-size: cover;
      position: relative;
    }
    /* Overlay for better contrast */
    body::before {
      content: "";
      position: fixed;
      top: 0; left: 0; right: 0; bottom: 0;
      background: rgba(240, 244, 248, 0.8);
      z-index: 0;
    }

    /* Navbar */
    .navbar {
      position: relative;
      width: 100%;
      background: rgba(44, 62, 80, 0.85);
      color: #ecf0f1;
      box-shadow: 0 4px 15px rgba(44, 62, 80, 0.6);
      padding: 0.75rem 1.5rem;
      display: flex;
      justify-content: space-between;
      align-items: center;
      z-index: 1;
    }

    .navbar-brand {
      font-weight: 700;
      font-size: 1.6rem;
      letter-spacing: 0.05em;
      user-select: none;
      color: #ecf0f1;
    }

    .user-info {
      display: flex;
      align-items: center;
      gap: 12px;
      font-weight: 600;
      color: #dcdde1;
    }

    .user-info img {
      width: 38px;
      height: 38px;
      border-radius: 50%;
      object-fit: cover;
      border: 2px solid #ecf0f1;
      box-shadow: 0 0 6px rgba(236, 240, 241, 0.8);
    }

    .btn-logout {
      background: #e74c3c;
      border: none;
      color: white;
      padding: 7px 16px;
      border-radius: 10px;
      font-weight: 600;
      cursor: pointer;
      transition: background-color 0.3s ease, box-shadow 0.3s ease;
      box-shadow: 0 3px 10px rgba(231, 76, 60, 0.6);
    }

    .btn-logout:hover {
      background: #c0392b;
      box-shadow: 0 6px 20px rgba(192, 57, 43, 0.8);
    }

    /* Dashboard Container */
    .dashboard-container {
      position: relative;
      background: rgba(255, 255, 255, 0.95);
      border-radius: 16px;
      margin-top: 45px;
      padding: 30px 30px;
      width: 360px;
      box-shadow: 0 10px 35px rgba(44, 62, 80, 0.25);
      text-align: center;
      z-index: 1;
    }

    .dashboard-container h2 {
      font-weight: 700;
      font-size: 1.5rem;
      margin-bottom: 30px;
      color: #2c3e50;
      user-select: none;
    }

    /* Buttons */
    .dashboard-btn {
      display: flex;
      align-items: center;
      justify-content: center;
      gap: 12px;
      background: linear-gradient(135deg, #2980b9, #3498db);
      border: none;
      color: white;
      padding: 12px 18px;
      margin-bottom: 16px;
      font-size: 1.05rem;
      font-weight: 600;
      border-radius: 14px;
      text-decoration: none;
      box-shadow: 0 6px 18px rgba(41, 128, 185, 0.5);
      transition: transform 0.25s ease, box-shadow 0.25s ease;
      cursor: pointer;
      user-select: none;
    }

    .dashboard-btn:hover {
      background: linear-gradient(135deg, #3498db, #2980b9);
      box-shadow: 0 12px 28px rgba(41, 128, 185, 0.7);
      transform: translateY(-4px);
      color: #e0f2ff;
      text-decoration: none;
    }

    .dashboard-btn i {
      font-size: 1.3rem;
      color: white;
      text-shadow: 0 0 5px rgba(255,255,255,0.7);
    }

    .dashboard-btn:focus-visible {
      outline: 3px solid #55aaff;
      outline-offset: 3px;
    }

    /* Footer */
    footer {
      margin-top: auto;
      padding: 18px 0;
      font-size: 0.9rem;
      color: #555;
      user-select: none;
      z-index: 1;
    }

    @media (max-width: 400px) {
      .dashboard-container {
        width: 90%;
        padding: 25px 20px;
      }
    }
  </style>
</head>
<body>

<nav class="navbar">
  <div class="navbar-brand">📚 Bookshop Dashboard</div>
  <div class="user-info">
    <img src="https://i.pravatar.cc/40?u=<%=shopworker.getUsername()%>" alt="User" />
    <span><%= shopworker.getUsername() %></span>
    <form action="${pageContext.request.contextPath}/jsp/logout.jsp" method="post" style="margin:0 0 0 10px;">
      <button type="submit" class="btn-logout" aria-label="Logout">
        <i class="fas fa-sign-out-alt"></i>
      </button>
    </form>
  </div>
</nav>

<div class="dashboard-container" role="main" aria-label="Shop Worker Dashboard">
  <h2>Welcome, <%= shopworker.getUsername() %>!</h2>

  <a href="addCustomer.jsp" class="dashboard-btn" role="button" tabindex="0">
    <i class="fas fa-user-plus"></i> Add New Customer
  </a>
  <a href="viewCustomers.jsp" class="dashboard-btn" role="button" tabindex="0">
    <i class="fas fa-users"></i> View Customers
  </a>
  <a href="bill.jsp" class="dashboard-btn" role="button" tabindex="0">
    <i class="fas fa-file-invoice"></i> Generate Bill / Invoice
  </a>
  <a href="searchInvoices.jsp" class="dashboard-btn" role="button" tabindex="0">
    <i class="fas fa-search"></i> Search Invoice
  </a>

  <p style="margin-top: 25px; font-size: 0.9rem; color: #555;">PahanaEdu Book Shop, Panadura</p>
</div>

<footer>
  &copy; <%= java.time.Year.now() %> PahanaEdu Book Shop
</footer>

</body>
</html>
