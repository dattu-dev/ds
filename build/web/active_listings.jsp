<%-- 
    Document   : active_listings
    Created on : Nov 5, 2025, 10:27:05 AM
    Author     : uydat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.account}">
    <c:redirect url="login"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Active Page</title>
    </head>
    <body>
        <h2>Active Room Listings</h2>
        <a href="welcome.jsp">Back to Menu</a>

        <hr>

    <c:choose>
        <c:when test="${empty listings}">
            <p>No active listings available.</p>
        </c:when>
        <c:otherwise>
            <p>Total: <strong>${listings.size()}</strong> listings</p>

            <table border="1" cellpadding="5" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Price (VNĐ/month)</th>
                        <th>Location</th>
                        <th>Description</th>
                        <th>Posted By</th>
                        <th>Posted Date</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="room" items="${listings}">
                    <tr>
                        <td>${room.id}</td>
                        <td>${room.title}</td>
                        <td><fmt:formatNumber value="${room.price}" type="number" groupingUsed="true"/></td>
                    <td>${room.location}</td>
                    <td>${room.description}</td>
                    <td>${room.username}</td>
                    <td><fmt:formatDate value="${room.postedDate}" pattern="dd/MM/yyyy HH:mm"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
