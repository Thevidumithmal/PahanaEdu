<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.business.user.dto.UserDTO" %>
<%@ page import="com.pahanaedu.business.item.dto.ItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.business.category.dto.CategoryDTO" %>
<%
  UserDTO admin = (UserDTO) session.getAttribute("admin");
  if (admin == null) {
    response.sendRedirect("adminLogin.jsp");
    return;
  }

  List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("items");
  List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
  String selectedCategoryId = request.getParameter("categoryId") != null ? request.getParameter("categoryId") : "";
%>
<!DOCTYPE html>
<html>
<head>
  <title>Item List</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(135deg, #a1c4fd, #c2e9fb);
      margin: 0;
      padding: 0;
    }

    .container {
      max-width: 900px;
      margin: 60px auto;
      background: #fff;
      padding: 30px 40px;
      border-radius: 12px;
      box-shadow: 0 15px 30px rgba(0, 0, 0, 0.15);
    }

    h2 {
      text-align: center;
      color: #1976d2;
      margin-bottom: 30px;
    }

    .action-buttons a {
      padding: 6px 12px;
      text-decoration: none;
      border-radius: 6px;
      font-size: 14px;
      margin-right: 8px;
      color: white;
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

    .no-data {
      text-align: center;
      padding: 20px;
      color: #555;
      font-style: italic;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Item List</h2>
  <!-- Category Filter -->
  <form action="${pageContext.request.contextPath}/viewItems" method="get" class="mb-3 d-flex gap-2">
    <select name="categoryId" class="form-select w-25">
      <option value="">All Items</option>
      <%
        if (categories != null) {
          for (CategoryDTO cat : categories) {
            String selected = String.valueOf(cat.getId()).equals(selectedCategoryId) ? "selected" : "";
      %>
      <option value="<%= cat.getId() %>" <%= selected %>><%= cat.getName() %></option>
      <%
          }
        }
      %>
    </select>
    <button type="submit" class="btn btn-primary">Filter</button>
  </form>

  <!-- Search Field -->
  <div class="mb-2">
    <input type="text" id="itemSearch" class="form-control form-control-sm w-50" placeholder="Search items by name or price...">
  </div>

  <!-- Item Table -->
  <table class="table table-bordered table-hover">
    <thead class="table-primary">
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Price</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody id="itemTable">
    <%
      if (items != null && !items.isEmpty()) {
        for (ItemDTO item : items) {
    %>
    <tr>
      <td><%= item.getId() %></td>
      <td><%= item.getName() %></td>
      <td>Rs. <%= item.getPrice() %></td>
      <td class="action-buttons">
        <a class="edit-btn" href="editItem?id=<%= item.getId() %>">Edit</a>
        <a class="delete-btn" href="deleteItem?id=<%= item.getId() %>" onclick="return confirm('Are you sure you want to delete this item?')">Delete</a>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="4" class="no-data">No items found.</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>

  <a class="back-link" href="${pageContext.request.contextPath}/jsp/adminDashboard.jsp">← Back to Dashboard</a>
</div>

<!-- Bootstrap JS + Search Filter Script -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
  document.getElementById('itemSearch').addEventListener('keyup', function () {
    const filter = this.value.toLowerCase();
    const rows = document.querySelectorAll('#itemTable tr');

    rows.forEach(row => {
      const name = row.cells[1]?.textContent.toLowerCase();
      const price = row.cells[2]?.textContent.toLowerCase();

      if (name.includes(filter) || price.includes(filter)) {
        row.style.display = '';
      } else {
        row.style.display = 'none';
      }
    });
  });
</script>
</body>
</html>
