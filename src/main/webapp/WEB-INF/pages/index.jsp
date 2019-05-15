<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">

</head>
<body>


<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />


<jsp:include page="_flowerList.jsp"/>

<jsp:include page="_cartList.jsp"/>
<jsp:include page="_footer.jsp" />

</body>
</html>