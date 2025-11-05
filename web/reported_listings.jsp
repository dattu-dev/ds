<%-- 
    Document   : reported_listings
    Created on : Nov 5, 2025, 10:33:17 AM
    Author     : uydat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.account or sessionScope.role != 1}">
    <c:redirect url="welcome.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reported Page</title>
    </head>
    <body>
        <h2>User-Reported Content</h2>
        <a href="welcome.jsp">Back to Menu</a>

        <hr>

    <c:choose>
        <c:when test="${empty listings}">
            <p>No reported listings.</p>
        </c:when>
        <c:otherwise>
            <p>Total: <strong>${listings.size()}</strong> reported listings</p>

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
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="room" items="${listings}">
                    <tr style="background-color: #ffe6e6;">
                        <td>${room.id}</td>
                        <td>${room.title}</td>
                        <td><fmt:formatNumber value="${room.price}" type="number" groupingUsed="true"/></td>
                    <td>${room.location}</td>
                    <td>${room.description}</td>
                    <td>${room.username}</td>
                    <td><fmt:formatDate value="${room.postedDate}" pattern="dd/MM/yyyy HH:mm"/></td>
                    <td style="color: red; font-weight: bold;">REPORTED</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
