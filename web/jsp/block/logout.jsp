<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.10.2020
  Time: 7:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<td bgcolor="#e6e6fa">
    <!--Абзац для ссылки на страницу сайта-->
    <p>
    <form name="logouForm" method="POST" action="library">
        <!--Ссылка на страницу сайта-->
        <h3>${sessionScope.client} Рады Вас приветствовать!<br/></h3>
        <h3>Выход из учетной записи<br/></h3>
        <br/>
        <form name="searchForm" method="POST" action="library">
            <input type="hidden" name="command" value="logout"/>
            <button type="submit" value="logout">Выйти</button>
        </form>
        <!--Закрываем абзац-->
    </form>
    </p>
    <p>
    <h3>Поиск книги</h3> <br/>
    <form name="searchForm" method="POST" action="library">
        <input type="hidden" name="command" value="search"/>
        <img src="./images/book.png" width="30" height="30" alt="">
        <input type="submit" value="Поиск книги">
    </form>
    </p>
    <!--Закрываем строку Меню-->
</td>
</body>
</html>
