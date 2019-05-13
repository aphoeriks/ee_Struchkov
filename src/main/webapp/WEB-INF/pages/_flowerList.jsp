<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page contentType="text/html;charset=utf-8" %>

<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">Каталог цветов</div>


<table style="text-align:left;">
    <tr>
        <td>Название </td>
        <td>Цена </td>
        <td>В наличии </td>
    </tr>
<c:forEach items="${paginationFlowers.list}" var="flowerInfo">

        <tr>
            <td>${flowerInfo.name}</td>
            <td><fmt:formatNumber value="${flowerInfo.price}" type="currency"/></td>
            <td>${flowerInfo.inStock}</td>

        </tr>

</c:forEach>
</table>
<br/>


<c:if test="${paginationFlowers.totalPages > 1}">
    <div class="page-navigator">
        <c:forEach items="${paginationFlowers.navigationPages}" var = "page">
            <c:if test="${page != -1 }">
                <a href="flowerList?page=${page}" class="nav-item">${page}</a>
            </c:if>
            <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
            </c:if>
        </c:forEach>

    </div>
</c:if>