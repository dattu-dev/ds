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
        <title>Active Listings</title>
    </head>
    <body>
        <h2>Welcome ${sessionScope.fullName} !</h2>
        <h3>Active Room Listings</h3>
        <a href="welcome.jsp">Back to Menu</a> | <a href="logout">Logout</a>

        <hr>

        <!-- Filter and Sort Form -->
        <form action="active-listings" method="get">
            <label>Price Range:</label>
            <input type="number" name="minPrice" placeholder="Min Price" value="${minPrice}" step="0.01">
            to
            <input type="number" name="maxPrice" placeholder="Max Price" value="${maxPrice}" step="0.01">
            
            <label>Sort by Price:</label>
            <select name="sortOrder">
                <option value="">-- Select --</option>
                <option value="ASC" ${sortOrder == 'ASC' ? 'selected' : ''}>Ascending</option>
                <option value="DESC" ${sortOrder == 'DESC' ? 'selected' : ''}>Descending</option>
            </select>
            
            <button type="submit">Apply Filters</button>
            <a href="active-listings">Clear Filters</a>
        </form>

        <hr>

    <c:choose>
        <c:when test="${empty listings}">
            <p>No matching data found!</p>
        </c:when>
        <c:otherwise>
            <p>Total: <strong>${listings.size()}</strong> listings</p>

            <table border="1" cellpadding="5" cellspacing="0">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Location</th>
                        <th>Posted Date</th>
                        <th>Price (VNĐ/month)</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="room" items="${listings}">
                    <tr>
                        <td>${room.id}</td>
                        <td>${room.title}</td>
                        <td>${room.location}</td>
                        <td><fmt:formatDate value="${room.postedDate}" pattern="dd/MM/yyyy"/></td>
                        <td><fmt:formatNumber value="${room.price}" type="number" groupingUsed="true"/></td>
                        <td>
                            <form action="active-listings" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="expire">
                                <input type="hidden" name="roomId" value="${room.id}">
                                <input type="hidden" name="minPrice" value="${minPrice}">
                                <input type="hidden" name="maxPrice" value="${maxPrice}">
                                <input type="hidden" name="sortOrder" value="${sortOrder}">
                                <button type="submit">Expired</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
