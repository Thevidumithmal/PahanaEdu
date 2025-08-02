<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Welcome - Login</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: url('${pageContext.request.contextPath}/image/Bookshop.jpg') no-repeat center center fixed;
      background-size: cover;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      color: #fff;
      position: relative;
      overflow: hidden;
    }

    body::before {
      content: "";
      position: absolute;
      top: 0;
      left: 0;
      height: 100%;
      width: 100%;
      background-color: rgba(0, 0, 0, 0.7); /* Dark transparent overlay */
      z-index: 0;
    }

    .container {
      position: relative;
      z-index: 1;
      background-color: rgba(0, 0, 0, 0.6);
      padding: 40px;
      border-radius: 15px;
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.4);
      text-align: center;
    }

    h2 {
      margin-bottom: 30px;
      font-size: 28px;
    }

    input[type="submit"] {
      background-color: #007bff;
      color: white;
      border: none;
      padding: 15px 25px;
      margin: 10px;
      border-radius: 10px;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Welcome to Pahana Edu</h2>
  <form action="${pageContext.request.contextPath}/jsp/loginRedirect.jsp" method="get">
    <input type="submit" name="role" value="I am Admin" />
    <input type="submit" name="role" value="I am Shop Worker" />
  </form>
</div>
</body>
</html>
