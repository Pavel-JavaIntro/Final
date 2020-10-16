<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 21-Sep-20
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>ERROR</title>
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
                        <h2><fmt:message key="message.error1"/>
                            <a href="welcome.jsp"><fmt:message key="message.ret"/> </a></h2>
                        <h2><fmt:message key="message.error2"/></h2><br/>
                        ${pageContext.errorData.requestURI}
                        ${pageContext.errorData.servletName}
                        ${pageContext.errorData.statusCode}
                        ${pageContext.errorData.throwable.message}
                        ${pageContext.errorData.throwable.cause}
                        ${pageContext.errorData.throwable.stackTrace}

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
