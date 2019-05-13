<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">

</head>
<body>

<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<div class="page-title">Registration</div>



<form:form modelAttribute="registrationForm" method="POST" enctype="form-data" action="${pageContext.request.contextPath}/registrationCheck">
    <table style="text-align:left;">


        <tr>
            <td>Логин *</td>
            <td><form:input path="login" /></td>
            <td><form:errors path="login" class="error-message" />
                <c:if test="${not empty message }">
                    <div class="error-message">
                            ${message}
                    </div>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Пароль *</td>
            <td><form:input path="password" type="password"/></td>
            <td><form:errors path="password" class="error-message" /></td>
        </tr>
        <tr>
            <td>Повторите пароль*</td>
            <td><form:input path="matchingPassword" type="password"/></td>
            <td>
                <form:errors path="matchingPassword" class="error-message" />
                <form:errors path="equalPasswords" class="error-message" />
            </td>
        </tr>
        <tr>
            <td>Адрес *</td>
            <td><form:input path="address" /></td>
            <td><form:errors path="address" class="error-message" /></td>
        </tr>
        <tr>
            <td>Телефон *</td>
            <td><form:input path="phone" /></td>
            <td><form:errors path="phone" class="error-message" /></td>
        </tr>
        <tr>
            <td>Имя *</td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" class="error-message" /></td>
        </tr>
        <tr>
            <td>Фамилия *</td>
            <td><form:input path="surname" /></td>
            <td><form:errors path="surname" class="error-message" /></td>
        </tr>
        <tr>
            <td>Отчество *</td>
            <td><form:input path="patronymic" /></td>
            <td><form:errors path="patronymic" class="error-message" /></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" /> <input type="reset"/></td>
        </tr>
    </table>
</form:form>




<jsp:include page="_footer.jsp" />

</body>
</html>