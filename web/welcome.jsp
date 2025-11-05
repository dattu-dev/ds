<%-- 
    Document   : welcome
    Created on : Apr 26, 2025, 8:58:34 AM
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
        <title>Welcome Page</title>
    </head>
    <body>
        <h2>Welcome, ${sessionScope.fullName}!</h2>
        <p>Role: ${sessionScope.role == 1 ? "Administrator" : "Member"}</p>

        <hr>

        <h3>Menu</h3>
        <ul>
            <li><a href="MainController?action=ActiveListings">View Active Listings</a></li>

            <c:if test="${sessionScope.role == 1}">
                <li><a href="reported-listings">View Reported Content</a></li>
            </c:if>

            <li><a href="logout">Logout</a></li>
        </ul>
    </body>
</html>
