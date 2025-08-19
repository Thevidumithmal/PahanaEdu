<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.business.item.dto.ItemDTO" %>
<%@ page import="com.pahanaedu.business.category.dto.CategoryDTO" %>
<%@ page import="java.util.List" %>
<%
    ItemDTO item = (ItemDTO) request.getAttribute("item");
    if (item == null) {
        response.sendRedirect("viewItems");
        return;
    }
    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");

%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Item</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #f6d365, #fda085);
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
            color: #f57c00;
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
            background-color: #f57c00;
            color: white;
            border: none;
            border-radius: 25px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #e65100;
        }

        select {
            padding: 10px;
            margin-top: 5px;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 15px;
            background-color: #fff;
            appearance: none; /* removes default arrow in some browsers */
            -webkit-appearance: none;
            -moz-appearance: none;
        }

        select:focus {
            outline: none;
            border-color: #f57c00;
            box-shadow: 0 0 5px rgba(245, 124, 0, 0.5);
        }

        .back-link {
            display: block;
            margin-top: 25px;
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
    <h2>Edit Item</h2>
    <form action="${pageContext.request.contextPath}/editItem" method="post">
        <input type="hidden" name="id" value="<%= item.getId() %>">

        <label>Name:</label>
        <input type="text" name="name" value="<%= item.getName() %>" required>

        <label>Price:</label>
        <input type="number" step="0.01" name="price" value="<%= item.getPrice() %>" required>

        <label>Category:</label>
        <select name="categoryId" required>
            <option value="">-- Select Category --</option>
            <%
                if (categories != null) {
                    for (CategoryDTO category : categories) {
                        String selected = category.getId() == item.getCategoryId() ? "selected" : "";
            %>
            <option value="<%= category.getId() %>" <%= selected %>><%= category.getName() %></option>
            <%
                    }
                }
            %>
        </select>

        <input type="submit" value="Update Item">
    </form>

    <a class="back-link" href="${pageContext.request.contextPath}/viewItems">← Back to Item List</a>
</div>
</body>
</html>
