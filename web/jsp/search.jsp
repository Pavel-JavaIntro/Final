
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Библиотека Павла Касичева</title>
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
                        <h2>Поиск книги</h2>
                        <br/>
                        <h3>Введите название книги и / или фамилию автора(-ов)<br/>
                            Фамилия должна быть написана без пробелов, без имени или инициалов<br/>
                            Если вы осуществляете поиск по авторам для книг с более, чем одним автором,
                            внесите в поле поиска любого<br/></h3>
                        <form name="searchForm" method="POST" action="library" onsubmit="return validateAuthor();">
                            <input type="hidden" name="command" value="find_edition"/>
                            <br/>Название:<br/>
                            <input type="text" name="title" id="title" value=""/>
                            <br/>Автор:<br/>
                            <input type="text" name="author" id="author" value=""/>
                            <br/><br/>
                            <input type="submit" value="Искать"/>
                        </form>
                    </td>
                    <td
                            rowspan="2"
                            style="width:80%">
                        <h2>В вашей корзине ... книг<br/></h2>
                        Результаты поиска будут отображены здесь:<br/>
                        <select size="10">
                            <c:forEach var="item" items="${sessionScope.editions}">
                                <option>${item}</option>
                            </c:forEach>
                        </select>
                        <br/>
                        <form name="orderForm" method="POST" action="library">
                            <input type="checkbox" name="subscribe" id="subscribe">Абонемент</input><br/>
                            <input type="submit" value="Заказать"/>
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

<script src="${pageContext.request.contextPath}/js/validator.js" type="text/javascript"></script>
</body>
</html>

