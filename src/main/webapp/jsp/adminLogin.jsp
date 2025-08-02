<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Admin Login</title>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    />

    <style>
        /* Full-page background image with overlay */
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            height: 100vh;
            background:
                    linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
                    url('${pageContext.request.contextPath}/image/adminlogin.jpg') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-container {
            background: rgba(255, 255, 255, 0.5); /* white with transparency */
            padding: 40px 50px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
            max-width: 380px;
            width: 100%;
            text-align: center;
            color: #333;
        }

        h2 {
            margin-bottom: 30px;
            font-weight: 700;
            font-size: 28px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        input[type="text"],
        input[type="password"] {
            padding: 12px 15px;
            font-size: 16px;
            border: 1.5px solid #ccc;
            border-radius: 6px;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 6px rgba(0, 123, 255, 0.4);
        }

        input[type="submit"] {
            background-color: #007bff;
            border: none;
            color: white;
            font-weight: 600;
            font-size: 18px;
            padding: 14px;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            margin-top: 15px;
            color: #d93025;
            font-weight: 600;
            font-size: 14px;
            min-height: 18px;
            min-width: 100%;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2><i class="fas fa-user-shield" style="color:#b342f5; margin-right:8px;"></i>Admin Login</h2>
    <form action="${pageContext.request.contextPath}/adminLogin" method="post" autocomplete="off">
        <input type="text" name="username" placeholder="Username" required autocomplete="username" />
        <input type="password" name="password" placeholder="Password" required autocomplete="current-password" />
        <input type="submit" value="Login" />
    </form>

    <p class="error-message"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>

    <a href="login.jsp">← Back to User Selection</a>
</div>
</body>
</html>
