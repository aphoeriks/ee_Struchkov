<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=utf-8" %>

<fmt:setLocale value="en_US" scope="session"/>

<c:if test="${cartError.length()>1}">
    <h3>Не удалось добавить заказ по причине:</h3>
    <h3>"${cartError}"</h3>
</c:if>
<c:if test="${accountData.isInitialized()}">
    <c:if test="${!accountData.getCartInfo().getCartLines().isEmpty()}">
        <h3>Товары в корзине:</h3>
        <table>
            <tr>
                <td>название</td>
                <td>цена</td>
                <td>количество</td>
            </tr>
            <c:forEach items="${accountData.getCartInfo().cartLines}" var="cartLine">
                <tr>
                    <td>${cartLine.name}</td>
                    <td>&#8381 ${cartLine.price}</td>
                    <td>${cartLine.quantity}</td>
                </tr>
            </c:forEach>
            <tr>
                <td>Общая цена:</td>
                <td>&#8381 ${accountData.getCartPrice()}</td>
                <td></td>
                <td><button onclick= 'location.href="${pageContext.request.contextPath}/CreateNewOrder"' >Создать заказ</button></td>
            </tr>
        </table>
    </c:if>
</c:if>