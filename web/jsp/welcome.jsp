<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 29-Sep-20
  Time: 08:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <jsp:include page="header.jsp"/>

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
                        <h2>Добро пожаловать!</h2>
                        <!--Начинаем абзац с красной строки-->
                        <p style="text-indent:20px">
                            Здравствуйте уважаемые настоящие и будущие читатели!
                            Наша библиотека рада приветствовать Вас на своём сайте.
                            Этот сайт первый, который я разработал самостоятельно,
                            а до этого умел только входить в интернет.</p>

                        <p style="text-indent:20px">
                            Наша библиотека насчитывает уже ${count} книг
                            и обслуживает ${applicationScope.users} пользователей.
                            И эти числа постоянно растут!</p>

                        <p style="text-indent:20px">Вы можете использовать базовую функцию
                            библиотеки - поиск книг - без регистрации, нажав на кнопку "Поиск книги".
                            Но для того, чтобы полностью использовать имеющийся функционал,
                            вам следует войти, используя форму справа вверху,
                            по логину (по умолчанию это ваши фамилия и имя) и паролю.</p>
                        <p style="text-indent:20px">
                            Приятного чтения!</p>
                            <!--Закрываем ячейку-->
                    </td>

                    <!--САЙДБАР-->

                    <!--Создаём ячейку сайдбара-->

                    <jsp:include page="menu.jsp"/>
                </tr>
                <!--Создаём строку с дополнительной информацией-->
                <tr>
                    <!--Ячейка с дополнительной информацией-->
                    <jsp:include page="extra.jsp"/>
                </tr>
            </table>

            <!--ПОДВАЛ-->

            <!--Создаём таблицу подвала-->
            <jsp:include page="footer.jsp"/>
            <!--Закрываем таблицу контейнера-->
        </td>
    </tr>
</table>
</body>
</html>