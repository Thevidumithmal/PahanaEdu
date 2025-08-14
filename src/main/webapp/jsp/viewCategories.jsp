<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.business.category.dto.CategoryDTO" %>
<%@ page import="java.util.List" %>
<%
    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
%>
<!DOCTYPE html>
<html>
<head>
    <title>View Categories</title>
    <style>
        body { font-family:'Segoe UI',sans-serif; background:linear-gradient(135deg,#a1c4fd,#c2e9fb); margin:0; padding:0;}
        .container { max-width:600px; margin:60px auto; background:#fff; padding:30px; border-radius:12px; box-shadow:0 15px 30px rgba(0,0,0,0.15);}
        h2 { text-align:center; color:#1976d2; margin-bottom:20px; }
        table { width:100%; border-collapse:collapse; }
        th,td { border:1px solid #ccc; padding:10px; text-align:left; }
        th { background:#1976d2; color:white; }
        .delete-btn { padding:5px 10px; background:#f44336; color:white; border:none; border-radius:5px; text-decoration:none; }
        .delete-btn:hover { background:#c62828; }
        .back-link { display:block; text-align:center; margin-top:20px; font-weight:bold; color:#333; text-decoration:none; }
        .back-link:hover { text-decoration:underline; }
    </style>
</head>
<body>
<div class="container">
    <h2>Category List</h2>
    <table>
        <tr><th>ID</th><th>Name</th><th>Action</th></tr>
        <%
            if (categories != null && !categories.isEmpty()) {
                for (CategoryDTO c : categories) {
        %>
        <tr>
            <td><%= c.getId() %></td>
            <td><%= c.getName() %></td>
            <td><a class="delete-btn" href="deleteCategory?id=<%= c.getId() %>" onclick="return confirm('Delete this category?')">Delete</a></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="3" style="text-align:center;">No categories found.</td></tr>
        <%
            }
        %>
    </table>
    <a class="back-link" href="${pageContext.request.contextPath}/jsp/adminDashboard.jsp">← Back to Dashboard</a>
</div>
</body>
</html>

