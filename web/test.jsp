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
    <meta charset="utf-8" />
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
            <!--ШАПКА САЙТА-->

            <!--В ячейке строки создаём ещё одну таблицу для шапки сайта.
            Оформление:
            border="1" - двойная рамка толщиной в 1px
            background="images/168.png" - картинка в шапке сайта, если требуется.
            Адрес картинки вы должны вставить свой.
            bgcolor="#7FFFD4" - фоновый цвет в шапке, если нет картинки.
            cellpadding="10" - отступ содержимого от рамки не менее 10px.
            style="width:100%; border-radius:5px;" - добавляем "резиновость"
            и закругляем уголки рамки-->
            <table
                    border="1"
                    background="images/book.png"
                    bgcolor="#7FFFD4"
                    cellpadding="10"
                    style="width:100%; border-radius:5px;">
                <!--Создаём строку таблицы-->
                <tr>
                    <!--Создаём столбец таблицы-->
                    <th>
                        <!--Содержание ячейки столбца-->
                        <h1>Библиотека Павла Касичева</h1>
                        <h3>Лучшая онлайн библиотека</h3>
                        <!--Закрываем таблицу-->
                    </th>
                </tr>
            </table>

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

                        <p style="text-indent:20px">Вы можете изучить базовые функции
                            библиотеки - поиск книг - без подписки.
                            Но для того, чтобы полностью использовать имеющийся функционал,
                            вам следует войти по ссылке справа по логину (по умолчанию
                            это ваши фамилия и имя) и паролю.</p>
                        <!--Закрываем ячейку-->
                    </td>

                    <!--САЙДБАР-->

                    <!--Создаём ячейку сайдбара-->
                    <td bgcolor="#e6e6fa">
                        <h3>Меню</h3>
                        <!--Абзац для ссылки на страницу сайта-->
                        <p>
                            <!--Ссылка на страницу сайта-->
                            <a href="">
                                <!--Картинка маркера перед названием страницы-->
                                <img src="images/book.png" width="30" height="30">
                                <!--Название страницы
                                style="margin-left:5px;" - отступ названия от маркера-->
                                <span style="margin-left:5px;">Войти</span></a>
                            <!--Закрываем абзац-->
                        </p>
                        <p>
                            <a href="">
                                <img src="images/book.png" width="30" height="30">
                                <span style="margin-left:5px;">Страница 1</span></a>
                        </p>
                        <p>
                            <a href="">
                                <img src="images/book.png" width="30" height="30">
                                <span style="margin-left:5px;">Страница 2</span></a>
                        </p>
                        <!--Закрываем строку Меню-->
                    </td>
                </tr>
                <!--Создаём строку с дополнительной информацией-->
                <tr>
                    <!--Ячейка с дополнительной информацией-->
                    <td
                            bgcolor="#e6e6fa"
                            align="center">
                        <h3>Адрес</h3>
                        <p>г.Минск, ул. Библиотеки Павла Касичева</p>
                        <h3>Распорядок работы</h3>
                        <p>Только в рабочее время</p>
                        <!--Закрываем ячейку с общей информацией
                        и таблицу основного контента-->
                    </td>
                </tr>
            </table>

            <!--ПОДВАЛ-->

            <!--Создаём таблицу подвала-->
            <table
                    border="1"
                    bgcolor="#7FFFD4"
                    height="100"
                    cellpadding="10"
                    style="width:100%; border-radius:5px;">
                <!--Создаём строку.-->
                <tr>
                    <!--Создаём столбец-->
                    <th>
                        <h3>Сайт разработан Павлом Касичевым</h3>
                        <p> email: ${applicationScope.email}</p>
                        <!--Закрываем таблицу подвала. При желании в подвале можно
                        сделать несколько строк и столбцов-->
                    </th>
                </tr>
            </table>
            <!--Закрываем таблицу контейнера-->
        </td>
    </tr>
</table>
</body>
</html>
