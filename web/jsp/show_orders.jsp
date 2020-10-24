<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Библиотека Павла Касичева</title>
    <fmt:setLocale value="${sessionScope.lan}"/>
    <fmt:setBundle basename="messages"/>
</head>
<body>
<table
        border="1"
        align="center"
        rules="rows"
        style="width:70%;">
    <tr>
        <td>
            <jsp:include page="block/header.jsp"/>
            <table
                    border="1"
                    bgcolor="#e6e6fa"
                    cellpadding="10"
                    style="width:100%; border-radius:5px;">
                <tr>
                    <td
                            rowspan="2"
                            style="width:80%">
                        <h2><fmt:message key="message.users_orders" /></h2>
                        <form name="orderForm" method="POST" action="library">
                            <select size="10" name="book">
                                <c:forEach var="item" items="${sessionScope.orders}">
                                    <c:forEach var="edition" items="${item.editionInfoSet}">
                                        <option value="${item.userId}">${item.userId} &lt;&lt; ${edition}</option>
                                    </c:forEach>
                                </c:forEach>
                            </select>
                            <br/>
                            <input type="hidden" name="command" value="order_book"/>
                            <button name="order" value="unselect"><fmt:message key="message.unselect"/> </button><br/>
                            <button name="order" value="order"><fmt:message key="message.order"/></button>
                        </form>
                    </td>
                    <jsp:include page="${sessionScope.client.entrance}"/>
                </tr>
                <tr>
                    <jsp:include page="${sessionScope.client.adminSection}"/>
                </tr>
            </table>
            <jsp:include page="block/footer.jsp"/>
        </td>
    </tr>
</table>

</body>
</html>


