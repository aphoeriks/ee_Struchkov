<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=utf-8" %>

<fmt:setLocale value="en_US" scope="session"/>

<h3>Каталог цветов</h3>


<table style="text-align:left;">
    <form method="POST"
          action="${pageContext.request.contextPath}/">
        <div style=".flower-preview-container ">
        <tr>
            <input type="submit" value="Поиск"/>  по названию: <input name="name" type="text"/>
        </tr>
        <tr>
            с ценой от: <input name="priceMin" /> до: <input name="priceMax"/>
        </tr>
        </div>
    </form>

    <tr>
        <td>Название </td>
        <td>Цена </td>
        <td>В наличии </td>
        <td>Добавить в заказ</td>
    </tr>
<c:forEach items="${paginationFlowers.list}" var="flowerInfo">

    <tr>
        <td>${flowerInfo.name}</td>
        <td>&#8381 ${flowerInfo.price} ></td>
        <td>${flowerInfo.inStock}</td>
        <security:authorize access="hasRole('ROLE_CUSTOMER')">
            <td><form:form method="POST" modelAttribute="flowerCartFormLine" enctype="form-data"
                           action="${pageContext.request.contextPath}/newCartItem">>
                <form:input path="quantity" />
                <form:hidden path="price" value="${flowerInfo.price}"/>
                <form:hidden path="name" value="${flowerInfo.name}"/>
                <input type="submit" value="Добавить"/>
            </form:form> </td>
        </security:authorize>
    </tr>

</c:forEach>
</table>
<br/>


<c:if test="${paginationFlowers.totalPages > 1}">
    <div class="page-navigator">
        <c:forEach items="${paginationFlowers.navigationPages}" var = "page">
            <c:if test="${page != -1 }">
                <a href="${pageContext.request.contextPath}/?page=${page}" class="nav-item">${page}</a>
            </c:if>
            <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
            </c:if>
        </c:forEach>

    </div>
</c:if>
