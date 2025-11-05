<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.account}">
    <c:redirect url="login"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User-reported content Page</title>
    </head>
    <body>
        <h2>Welcome ${sessionScope.fullName} !</h2>
        <h3>User-reported content</h3>
        <a href="welcome.jsp">Back to Menu</a> | <a href="logout">Logout</a>
        
        <hr>
        
        <!--your code here-->
        <p>This page is under construction.</p>
    </body>
</html>
