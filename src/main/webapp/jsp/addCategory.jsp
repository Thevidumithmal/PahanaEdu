<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Category</title>
    <style>
        body { font-family:'Segoe UI',sans-serif; background:linear-gradient(135deg,#fddb92,#d1fdff); margin:0; padding:0; }
        .container { max-width:400px; margin:80px auto; background:#fff; padding:30px; border-radius:12px; box-shadow:0 12px 30px rgba(0,0,0,0.15);}
        h2 { text-align:center; color:#ff9800; }
        form { display:flex; flex-direction:column; }
        input[type="text"] { padding:10px; margin-top:10px; border-radius:8px; border:1px solid #ccc; }
        input[type="submit"] { margin-top:20px; padding:12px; background:#ff9800; color:white; border:none; border-radius:25px; cursor:pointer; }
        input[type="submit"]:hover { background:#e68900; }
        .message { margin-top:20px; text-align:center; color:green; }
        .back-link { display:block; text-align:center; margin-top:30px; font-weight:bold; color:#333; text-decoration:none; }
        .back-link:hover { text-decoration:underline; }
    </style>
</head>
<body>
<div class="container">
    <h2>Add New Category</h2>
    <form action="${pageContext.request.contextPath}/addCategory" method="post">
        <input type="text" name="name" placeholder="Category Name" required>
        <input type="submit" value="Add Category">
    </form>
    <div class="message">
        <%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %>
    </div>
    <a class="back-link" href="${pageContext.request.contextPath}/jsp/adminDashboard.jsp">← Back to Dashboard</a>

</div>
</body>
</html>
