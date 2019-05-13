<%@ page  contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>



<div class="menu-container">

    <a href="${pageContext.request.contextPath}/">Home</a>
    |
    <security:authorize access="hasAnyRole('ROLE_CUSTOMER')">
    <a href="${pageContext.request.contextPath}/shoppingCart">
        Моя корзина
    </a>
    </security:authorize>
    |
    <security:authorize  access="hasAnyRole('ROLE_ADMIN')">
        <a href="${pageContext.request.contextPath}/orderList">
            Список заказов
        </a>
    </security:authorize>


</div>