<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.business.user.dto.UserDTO" %>
<%@ page import="java.util.List" %>
<%
  UserDTO admin = (UserDTO) session.getAttribute("admin");
  if (admin == null) {
    response.sendRedirect("adminLogin.jsp");
    return;
  }

  List<UserDTO> workers = (List<UserDTO>) request.getAttribute("shopWorkers");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Shop Workers</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(135deg, #ffecd2, #fcb69f);
      color: #333;
    }

    .container {
      max-width: 1000px;
      margin: 60px auto;
      background: #fff;
      padding: 30px 40px;
      border-radius: 12px;
      box-shadow: 0 15px 30px rgba(0, 0, 0, 0.15);
    }

    h2 {
      text-align: center;
      margin-bottom: 30px;
      color: #ff6f61;
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }

    th, td {
      padding: 12px 16px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }

    th {
      background-color: #ff6f61;
      color: white;
    }

    tr:hover {
      background-color: #f5f5f5;
    }

    .action-buttons a {
      margin-right: 10px;
      padding: 6px 12px;
      text-decoration: none;
      color: white;
      border-radius: 6px;
      font-size: 14px;
    }

    .edit-btn {
      background-color: #4caf50;
    }

    .edit-btn:hover {
      background-color: #388e3c;
    }

    .delete-btn {
      background-color: #f44336;
    }

    .delete-btn:hover {
      background-color: #c62828;
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

    @media (max-width: 600px) {
      table, thead, tbody, th, td, tr {
        display: block;
      }

      th {
        position: absolute;
        top: -9999px;
        left: -9999px;
      }

      td {
        position: relative;
        padding-left: 50%;
        margin-bottom: 10px;
      }

      td:before {
        position: absolute;
        top: 12px;
        left: 16px;
        width: 45%;
        white-space: nowrap;
        font-weight: bold;
      }

      td:nth-of-type(1):before { content: "ID"; }
      td:nth-of-type(2):before { content: "Username"; }
      td:nth-of-type(3):before { content: "Phone"; }
      td:nth-of-type(4):before { content: "Address"; }
      td:nth-of-type(5):before { content: "Actions"; }
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Shop Workers</h2>

  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Username</th>
      <th>Phone</th>
      <th>Address</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
      if (workers != null) {
        for (UserDTO u : workers) {
    %>
    <tr>
      <td><%= u.getId() %></td>
      <td><%= u.getUsername() %></td>
      <td><%= u.getPhone() %></td>
      <td><%= u.getAddress() %></td>
      <td class="action-buttons">
        <a class="edit-btn" href="editShopWorker?id=<%= u.getId() %>">Edit</a>
        <a class="delete-btn" href="deleteShopWorker?id=<%= u.getId() %>" onclick="return confirm('Are you sure you want to delete this worker?')">Delete</a>
      </td>
    </tr>
    <%
        }
      }
    %>
    </tbody>
  </table>

  <a class="back-link" href="${pageContext.request.contextPath}/jsp/adminDashboard.jsp">← Back to Dashboard</a>
</div>
</body>
</html>
