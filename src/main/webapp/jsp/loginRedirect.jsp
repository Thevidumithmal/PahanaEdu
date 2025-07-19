<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/19/2025
  Time: 1:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%
  String role = request.getParameter("role");
  if ("I am Admin".equals(role)) {
    response.sendRedirect(request.getContextPath() + "/jsp/adminLogin.jsp");
  } else {
    response.sendRedirect(request.getContextPath() + "/jsp/shopLogin.jsp");
  }
%>

