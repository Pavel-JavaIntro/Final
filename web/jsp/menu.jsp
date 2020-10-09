<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 02-Oct-20
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<td bgcolor="#e6e6fa">
    <!--Абзац для ссылки на страницу сайта-->
    <p>
    <form name="loginForm" method="POST" action="library" onsubmit="return validateLogin();">
        <!--Ссылка на страницу сайта-->
        <h3>Войдите в свою учетную запись<br/></h3>
        <br/>
        <input type="hidden" name="command" value="login"/>
        Фамилия:<br/>
        <input type="text" name="surname" id="surname" value=""/>
        <br/>Имя:<br/>
        <input type="text" name="name" id="name" value=""/>
        <br/>Пароль:<br/>
        <input type="password" name="password" id="password" value=""/>
        <br/> ${errorLoginPassMessage} <br/>
        ${wrongAction} <br/>
        ${nullPage} <br/>
        <input type="submit" value="Войти"/>
        <!--Закрываем абзац-->
    </form>
    </p>
    <p> Или воспользуйтесь поиском без регистрации <br/>
    <form name="searchForm" method="POST" action="library">
        <input type="hidden" name="command" value="search"/>
        <img src="./images/book.png" width="30" height="30" alt="">
        <input type="submit" value="Поиск книги">
    </form>
    </p>
    <!--Закрываем строку Меню-->
</td>
<script src="${pageContext.request.contextPath}/js/validator.js" type="text/javascript"></script>
</body>
</html>
