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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".login-input").on('change', function postinput(){
            var loginValue = $(this).val();
            $.ajax({
                url: '${pageContext.request.contextPath}/rest/checkLogin/'+loginValue,
                async: true,
                type: 'get',
                success: function(responseData) {
                    var messageElement = document.getElementById("login-error");
                    var submitButton = document.getElementById("registration_form_submit")
                    if(responseData.length < 20) {
                        messageElement.setAttribute("class", "valid-message");
                        submitButton.removeAttribute("disabled");
                    }else{
                        messageElement.setAttribute("class", "error-message");
                        submitButton.setAttribute("disabled","disabled");
                    }
                    messageElement.innerText = responseData;
                    console.log('Done: ', responseData);
                },
                error: function(responseData)  {
                console.log('error: ', responseData);
            }
            });
    })})

</script>
<body>

<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<div class="page-title">Registration</div>
<c:if test="${not empty error }">
    <div class="error-message">
            ${error}
    </div>
</c:if>


<form:form modelAttribute="registrationForm" method="POST" enctype="form-data" action="${pageContext.request.contextPath}/registrationCheck">
    <table style="text-align:left;">


        <tr>
            <td>Логин *</td>
            <td> <form:input path="login" maxlength="20" class="login-input"/></td>
            <td><form:errors path="login" class="error-message" />
                <span id="login-error"> </span>
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
            <td><input type="submit" disabled id="registration_form_submit"/> <input type="reset"/></td>
        </tr>
    </table>
</form:form>




<jsp:include page="_footer.jsp" />

</body>
</html>