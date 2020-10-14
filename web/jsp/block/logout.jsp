
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lan}"/>
<fmt:setBundle basename="messages"/>
<td bgcolor="#e6e6fa">
    <p>
    <form name="logouForm" method="POST" action="library">
        <h3>${sessionScope.client} <fmt:message key="salute"/><br/></h3>
        <h3><fmt:message key="quit"/><br/></h3>
        <br/>
        <form name="searchForm" method="POST" action="library">
            <input type="hidden" name="command" value="logout"/>
            <button type="submit" value="logout"><fmt:message key="logout"/></button>
        </form>
    </form>
    </p>
    <p>
    <h3><fmt:message key="search"/></h3> <br/>
    <form name="searchForm" method="POST" action="library">
        <input type="hidden" name="command" value="search"/>
        <img src="./images/book.png" width="30" height="30" alt="">
        <input type="submit" value="<fmt:message key="search"/>">
    </form>
    </p>
</td>


