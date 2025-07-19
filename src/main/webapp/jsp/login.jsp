<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Welcome - Login</title>
</head>
<body>
<h2>Welcome to Pahana Edu</h2>
<form action="${pageContext.request.contextPath}/jsp/loginRedirect.jsp" method="get">
  <input type="submit" name="role" value="I am Admin"/>
  <input type="submit" name="role" value="I am Shop Worker"/>
</form>
</body>
</html>
