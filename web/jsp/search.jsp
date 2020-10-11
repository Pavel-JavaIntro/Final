<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 02-Oct-20
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Библиотека Павла Касичева</title>
</head>
<body>
<!--Создаём таблицу контейнер, которой задаём следующее
оформление:
border="1" - рамка вокруг контейнера. Увеличив число, можно увеличить толщину рамки.
align="center" - размещаем контейнер по центру экрана.
rules="rows" - убираем двойную рамку.
style="width:70%;" - добавляем стилевое свойства, делающее
контейнер и весь сайт "резиновым".
Сделать полноценный адаптивный дизайн, этим способом невозможно.-->
<table
        border="1"
        align="center"
        rules="rows"
        style="width:70%;">
    <!--Создаём строку-->
    <tr>
        <!--Создаём ячейку строки-->
        <td>
            <jsp:include page="block/header.jsp"/>

            <!--ОСНОВНОЙ КОНТЕНТ-->

            <!--В этой же ячейке контейнера создаём ещё одну таблицу
            для основного контента.
            Оформление как и в предыдущей таблице-->

            <table
                    border="1"
                    bgcolor="#e6e6fa"
                    cellpadding="10"
                    style="width:100%; border-radius:5px;">
                <!--Создаём строку-->
                <tr>
                    <!--Создаём ячейку
                    Оформление:
                    rowspan="2" - объединяем две ячейки в одну.
                    Число объединяемых ячеек по числу ячеек в сайдбаре.
                    style="width:80%" - основной контент занимает 80% всей площади,
                    оставшиеся 20% для сайдбара-->
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
                            <input type="hidden" name="command" value="find_book"/>
                            <br/>Название:<br/>
                            <input type="text" name="title" id="title" value=""/>
                            <br/>Автор:<br/>
                            <input type="text" name="author" id="author" value=""/>
                            <br/><br/>
                            <input type="submit" value="Искать"/>
                        </form>
                        <!--Закрываем ячейку-->
                    </td>
                    <td
                            rowspan="2"
                            style="width:80%">
                        <h2>В вашей корзине ... книг<br/></h2>
                        Результаты поиска будут отображены здесь:<br/>
                        <select size="10">
                            <c:forEach var="item" items="${sessionScope.books}">
                                <option>${item}</option>
                            </c:forEach>
                        </select>
                        <br/>
                        <form name="orderForm" method="POST" action="library">
                            <input type="checkbox" name="subscribe" id="subscribe">Абонемент</input><br/>
                            <input type="submit" value="Заказать"/>
                        </form>
                    </td>

                    <!--САЙДБАР-->

                    <!--Создаём ячейку сайдбара-->

                    <%--                    <jsp:include page="block/login.jsp"/>--%>
                    <jsp:include page="${sessionScope.client.entrance}"/>
                </tr>
                <!--Создаём строку с дополнительной информацией-->
                <tr>
                    <!--Ячейка с дополнительной информацией-->
                    <%--                    <jsp:include page="block/latin.jsp"/>--%>
                    <jsp:include page="${sessionScope.client.adminSection}"/>
                </tr>
            </table>

            <!--ПОДВАЛ-->

            <!--Создаём таблицу подвала-->
            <jsp:include page="block/footer.jsp"/>
            <!--Закрываем таблицу контейнера-->
        </td>
    </tr>
</table>

<script src="${pageContext.request.contextPath}/js/validator.js" type="text/javascript"></script>
</body>
</html>

