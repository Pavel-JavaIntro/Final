<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lan}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="message.title" /></title>
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
                        <table>
                            <h3><fmt:message key="message.administer_books"/></h3>
                            <tr>
                                <form name="adminBookForm" method="POST" action="library">
                                    <input type="hidden" name="command" value="find_code"/>
                                    <br/><fmt:message key="message.findcode"/><br/>
                                    <input type="text" name="code" id="code" value=""/>
                                    <br/>
                                    <input type="submit" value="<fmt:message key="message.select" />"/>
                                </form>
                            </tr>
                            <tr>
                                <jsp:include page="${sessionScope.addition}"/>
                            </tr>
                        </table>
                    </td>
                    <td
                            rowspan="2"
                            style="width:80%">
                        <jsp:include page="${sessionScope.client.basket}"/>

                        <form name="selectForm" method="POST" action="library">
                            <fmt:message key="message.result"/><br/>

                            <select size="10" name="edition">
                                <c:forEach var="item" items="${sessionScope.editions}">
                                    <option value="${item.edition.id}">
                                            ${item}
                                    </option>
                                </c:forEach>
                            </select>

                            <br/>
                            <jsp:include page="${sessionScope.client.bookSelection}"/>
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
<script src="${pageContext.request.contextPath}/js/poster.js" type="text/javascript"></script>
</body>
</html>
