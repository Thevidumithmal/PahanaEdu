<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/12/2025
  Time: 7:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  session.invalidate();
  response.sendRedirect("login.jsp");
%>


