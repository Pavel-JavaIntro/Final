<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lan}"/>
<fmt:setBundle basename="messages"/>
<td
        bgcolor="#e6e6fa"
        align="center">
    <h3><fmt:message key="message.admin"/></h3>
    <form action="library" method="post">
        <a href="#" onclick="postTo('show_orders');"><fmt:message key="message.users_orders" /></a><br/>
        <a href="#" onclick="postTo('show_books');"><fmt:message key="message.dispatch" /></a>
    </form>

</td>
<script src="${pageContext.request.contextPath}/js/poster.js" type="text/javascript"></script>