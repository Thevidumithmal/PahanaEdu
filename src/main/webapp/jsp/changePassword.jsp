<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Reset Shop Worker Password</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

    body {
      font-family: 'Poppins', sans-serif;
      margin: 0;
      overflow: hidden;
      height: 100vh;
      position: relative;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .bg-image-layer {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: url('${pageContext.request.contextPath}/image/resetpassword.jpg') no-repeat center center fixed;
      background-size: cover;
      opacity: 0.5; /* dim background for better contrast */
      z-index: -1;
    }

    .glass-container {
      background: rgba(255, 255, 255, 0.75); /* brighter glass */
      backdrop-filter: blur(12px);
      border: 1px solid rgba(255, 255, 255, 0.3);
      padding: 40px 30px;
      border-radius: 20px;
      box-shadow: 0 12px 30px rgba(0, 0, 0, 0.3);
      max-width: 420px;
      width: 100%;
      color: #1a1a1a; /* dark text for readability */
    }

    h2 {
      font-size: 1.5rem;
      font-weight: 600;
      text-align: center;
      margin-bottom: 25px;
    }

    label {
      font-size: 0.9rem;
      font-weight: 500;
      margin-bottom: 5px;
      color: #2c2c2c;
    }

    .form-control {
      background-color: rgba(255, 255, 255, 0.9);
      border: 1px solid #ccc;
      color: #1a1a1a;
    }

    .form-control::placeholder {
      color: #666;
    }

    .form-control:focus {
      background-color: #fff;
      border-color: #6c63ff;
      box-shadow: 0 0 8px rgba(108, 99, 255, 0.4);
    }

    .btn-metal {
      background: linear-gradient(145deg, #6e6e6e, #494949);
      color: white;
      font-weight: 600;
      border: none;
      padding: 10px;
      border-radius: 12px;
      transition: 0.3s;
      width: 100%;
    }

    .btn-metal:hover {
      background: linear-gradient(145deg, #494949, #1f1f1f);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
    }

    .message {
      text-align: center;
      margin-top: 15px;
      font-size: 0.9rem;
    }

    .success {
      color: #1e8e3e;
    }

    .error {
      color: #d43f3f;
    }

    .back-link {
      display: block;
      margin-top: 20px;
      text-align: center;
      font-size: 0.9rem;
      color: #333;
      text-decoration: underline;
    }

    .back-link:hover {
      color: #111;
    }

    @media (max-width: 500px) {
      .glass-container {
        padding: 25px 20px;
      }
    }
  </style>
</head>
<body>

<div class="bg-image-layer"></div>

<div class="glass-container">
  <h2><i class="fa-solid fa-lock me-2"></i>Reset Password</h2>

  <form action="${pageContext.request.contextPath}/changePassword" method="post">
    <div class="mb-3">
      <label for="username" class="form-label"><i class="fa-solid fa-user me-1"></i>Username</label>
      <input type="text" class="form-control" id="username" name="username" required placeholder="Enter your username">
    </div>

    <div class="mb-3">
      <label for="phone" class="form-label"><i class="fa-solid fa-phone me-1"></i>Phone Number</label>
      <input type="text" class="form-control" id="phone" name="phone" required placeholder="Enter your phone number">
    </div>

    <div class="mb-3">
      <label for="newPassword" class="form-label"><i class="fa-solid fa-key me-1"></i>New Password</label>
      <input type="password" class="form-control" id="newPassword" name="newPassword" required placeholder="Enter new password">
    </div>

    <button type="submit" class="btn btn-metal mt-3"><i class="fa-solid fa-rotate me-1"></i>Change Password</button>
  </form>

  <div class="message">
    <p class="success"><%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %></p>
    <p class="error"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>
  </div>

  <a href="${pageContext.request.contextPath}/jsp/shopLogin.jsp" class="back-link">
    <i class="fa-solid fa-arrow-left me-1"></i>Back to Login
  </a>
</div>

</body>
</html>
