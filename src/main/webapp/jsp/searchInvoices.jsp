

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Customer Invoices</title>
</head>
<body>
<h2>Search Invoices by Customer Phone</h2>

<form action="${pageContext.request.contextPath}/searchInvoices" method="get">
    Enter Customer Phone Number: <input type="text" name="phoneNumber" required>
    <input type="submit" value="Search">
</form>

<%-- Display error or no results messages --%>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

</body>
</html>
