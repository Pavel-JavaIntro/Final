
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <h2><fmt:message key="welcome" /></h2>
                        <p style="text-indent:20px">
                            <fmt:message key="maintext1" /></p>

                        <p style="text-indent:20px">
                            <fmt:message key="maintext2" /> ${applicationScope.books}
                            <fmt:message key="maintext3" /> ${applicationScope.users}
                            <fmt:message key="maintext4" /></p>

                        <p style="text-indent:20px"><fmt:message key="maintext5" />
                        <form action="library" method="post">
                            <a href="#" onclick="parentNode.submit();"><fmt:message key="search" /></a>
                            <input type="hidden" name="command" value="search"/>
                        </form>
                        <p><fmt:message key="maintext6" /></p>
                        <p style="text-indent:20px">
                            <fmt:message key="maintext20" /></p>
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
