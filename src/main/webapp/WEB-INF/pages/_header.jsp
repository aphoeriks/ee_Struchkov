<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="header-container">

    <div class="site-name">Магазин цветов</div>

    <div class="header-bar">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            Привет
            <a href="${pageContext.request.contextPath}/accountInfo">
                    ${pageContext.request.userPrincipal.name} </a>
            &nbsp;|&nbsp;
            <a href="${pageContext.request.contextPath}/logout">Выход</a>

        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <a href="${pageContext.request.contextPath}/login">Вход</a>
            |
            <a href="${pageContext.request.contextPath}/registration">Зарегистрироваться</a>
        </c:if>
    </div>
</div>