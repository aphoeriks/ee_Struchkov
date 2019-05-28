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

<h3> Заказы</h3>

<c:if test="${paginationOrders.totalRecords > 0}">
    <table style="text-align:left;">

        <tr>
            <td></td>
            <td><button onclick= 'location.href="${pageContext.request.contextPath}/orderList?sortType=sortByDate"' >Сортировка по дате</button></td>
            <td><button onclick= 'location.href="${pageContext.request.contextPath}/orderList?sortType=sortByStatus"' >Сортировка по статусу</button></td>
        </tr>
        <tr>
            <td>Цена  </td>
            <td>Дата создания</td>
            <td>Статус</td>
        </tr>
        <c:forEach items="${paginationOrders.list}" var="order">

            <tr>
                <td>&#8381 ${order.price}</td>
                <td>${order.date} </td>
                <td>${order.status}</td>
                <c:if test="${order.status eq ('Оплачен')}"  >
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/closeOrder?OrderId=${order.id}">
                            <input type="submit" value="Закрыть">
                        </form>
                    </td>
                </c:if>
            </tr>

        </c:forEach>
    </table>
    <br/>


    <c:if test="${paginationOrders.totalPages > 1}">
        <div class="page-navigator">
            <c:forEach items="${paginationOrders.navigationPages}" var = "page">
                <c:if test="${page != -1 }">
                    <a href="${pageContext.request.contextPath}/?page=${page}" class="nav-item">${page}</a>
                </c:if>
                <c:if test="${page == -1 }">
                    <span class="nav-item"> ... </span>
                </c:if>
            </c:forEach>

        </div>
    </c:if>
</c:if>
<jsp:include page="_footer.jsp" />


</body>
</html>