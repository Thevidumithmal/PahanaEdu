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
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Poppins', sans-serif;
            height: 100vh;
            background:
                    linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)),
                    url('${pageContext.request.contextPath}/image/adminlogin.jpg') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-container {
            background: rgba(255, 255, 255, 0.15);
            padding: 40px 50px;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            max-width: 380px;
            width: 100%;
            text-align: center;
            color: #fff;
            backdrop-filter: blur(12px);
            animation: fadeIn 1s ease-in-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h2 {
            margin-bottom: 25px;
            font-weight: 600;
            font-size: 26px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 18px;
        }

        .input-field {
            position: relative;
        }

        .input-field i {
            position: absolute;
            top: 50%;
            left: 12px;
            transform: translateY(-50%);
            color: #888;
        }

        .input-field input {
            width: 250px;
            padding: 12px 12px 12px 38px;
            font-size: 15px;
            border: none;
            border-radius: 8px;
            outline: none;
            background: rgba(255, 255, 255, 0.2);
            color: #fff;
            transition: 0.3s ease;
        }

        .input-field input::placeholder {
            color: #ddd;
        }

        .input-field input:focus {
            background: rgba(255, 255, 255, 0.3);
            box-shadow: 0 0 8px rgba(0, 123, 255, 0.6);
        }

        input[type="submit"] {
            background: linear-gradient(45deg, #6a11cb, #2575fc);
            width: 200px;   /* same as input field */
            margin: 0 auto;
            display: block;
            border: none;
            color: white;
            font-weight: 600;
            font-size: 17px;
            padding: 14px;
            border-radius: 8px;
            cursor: pointer;
            transition: transform 0.2s ease, opacity 0.3s ease;
        }

        input[type="submit"]:hover {
            transform: translateY(-2px);
            opacity: 0.9;
        }

        .error-message {
            margin-top: 15px;
            color: #ff4d4d;
            font-weight: 500;
            font-size: 14px;
            min-height: 18px;
        }

        .back-link {
            display: inline-block;
            margin-top: 18px;
            color: #ddd;
            text-decoration: none;
            font-size: 14px;
            transition: color 0.3s;
        }

        .back-link:hover {
            color: #fff;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2><i class="fas fa-user-shield" style="margin-right:8px;"></i>Admin Login</h2>
    <form action="${pageContext.request.contextPath}/adminLogin" method="post" autocomplete="off">
        <div class="input-field">
            <i class="fas fa-user"></i>
            <input type="text" name="username" placeholder="Username" required autocomplete="username" />
        </div>
        <div class="input-field">
            <i class="fas fa-lock"></i>
            <input type="password" name="password" placeholder="Password" required autocomplete="current-password" />
        </div>
        <input type="submit" value="Login" />
    </form>

    <p class="error-message"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>

    <a href="${pageContext.request.contextPath}/jsp/login.jsp" class="back-link">← Back to User Selection</a>
</div>
</body>
</html>
