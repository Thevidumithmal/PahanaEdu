<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.business.user.dto.UserDTO" %>
<%
  UserDTO admin = (UserDTO) session.getAttribute("admin");
  if (admin == null) {
    response.sendRedirect("adminLogin.jsp");
    return;
  }

  UserDTO worker = (UserDTO) request.getAttribute("worker");
  if (worker == null && request.getAttribute("userInput") == null) {
    response.sendRedirect("viewShopWorkers");
    return;
  }

  // If redirected with errors, get userInput map (for preserving inputs)
  java.util.Map<String, String[]> inputMap = (java.util.Map<String, String[]>) request.getAttribute("userInput");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Shop Worker</title>
  <style>
    /* Your existing CSS styles here */
    body {
      margin: 0;
      padding: 0;
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(135deg, #00c6ff, #0072ff);
      color: #333;
    }
    .container {
      max-width: 500px;
      margin: 80px auto;
      background: #fff;
      padding: 40px 30px;
      border-radius: 12px;
      box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2);
    }
    h2 {
      text-align: center;
      color: #0072ff;
    }
    form {
      display: flex;
      flex-direction: column;
    }
    label {
      margin-top: 15px;
      font-weight: bold;
    }
    input[type="text"] {
      padding: 10px;
      margin-top: 5px;
      border-radius: 8px;
      border: 1px solid #ccc;
      font-size: 15px;
    }
    input[type="submit"] {
      margin-top: 25px;
      padding: 12px;
      background-color: #0072ff;
      color: white;
      border: none;
      border-radius: 25px;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    input[type="submit"]:hover {
      background-color: #004cbf;
    }
    .back-link {
      display: block;
      margin-top: 30px;
      text-align: center;
      color: #333;
      text-decoration: none;
      font-weight: bold;
    }
    .back-link:hover {
      text-decoration: underline;
    }
    .error-msg {
      color: red;
      font-size: 13px;
      margin-top: 4px;
    }
  </style>

  <script>
    function validateForm() {
      const nic = document.forms["workerForm"]["nicNo"].value.trim();
      const phone = document.forms["workerForm"]["phone"].value.trim();

      const nic10Pattern = /^\d{9}[vVxX]$/;
      const nic12Pattern = /^\d{12}$/;

      const phonePattern = /^\d{10}$/;

      let valid = true;
      let nicError = "";
      let phoneError = "";

      if (!(nic10Pattern.test(nic) || nic12Pattern.test(nic))) {
        nicError = "NIC must be 10 chars (9 digits + letter) or 12 digits.";
        valid = false;
      }
      if (!phonePattern.test(phone)) {
        phoneError = "Phone number must be exactly 10 digits.";
        valid = false;
      }

      document.getElementById("nicError").innerText = nicError;
      document.getElementById("phoneError").innerText = phoneError;

      return valid;
    }
  </script>
</head>
<body>
<div class="container">
  <h2>Edit Shop Worker</h2>

  <form name="workerForm" action="${pageContext.request.contextPath}/editShopWorker" method="post" onsubmit="return validateForm()">
    <input type="hidden" name="id" value="<%= worker != null ? worker.getId() : inputMap.get("id")[0] %>">

    <label>Username:</label>
    <input type="text" name="username" required value="<%=
      worker != null ? worker.getUsername() :
      (inputMap != null ? inputMap.get("username")[0] : "")
    %>">
    <div class="error-msg">${usernameError}</div>

    <label>Name:</label>
    <input type="text" name="name" required value="<%=
      worker != null ? worker.getName() :
      (inputMap != null ? inputMap.get("name")[0] : "")
    %>">
    <div class="error-msg">${nameError}</div>

    <label>NIC Number:</label>
    <input type="text" name="nicNo" required value="<%=
      worker != null ? worker.getNicNo() :
      (inputMap != null ? inputMap.get("nicNo")[0] : "")
    %>">
    <div id="nicError" class="error-msg">${nicError}</div>

    <label>Phone:</label>
    <input type="text" name="phone" required value="<%=
      worker != null ? worker.getPhone() :
      (inputMap != null ? inputMap.get("phone")[0] : "")
    %>">
    <div id="phoneError" class="error-msg">${phoneError}</div>

    <label>Address:</label>
    <input type="text" name="address" value="<%=
      worker != null ? worker.getAddress() :
      (inputMap != null ? inputMap.get("address")[0] : "")
    %>">

    <input type="submit" value="Update">
  </form>

  <a class="back-link" href="viewShopWorkers">← Back to Shop Workers</a>
</div>
</body>
</html>
