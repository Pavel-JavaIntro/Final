<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 02-Oct-20
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<td bgcolor="#e6e6fa">
<!--Абзац для ссылки на страницу сайта-->
<p>
    <!--Ссылка на страницу сайта-->
    <h3>Войдите в свою учетную запись<br/></h3>
    <br/>
    <input type="hidden" name="command" value="login"/>
    Фамилия:<br/>
    <input type="text" name="surname" value=""/>
    <br/>Имя:<br/>
    <input type="text" name="name" value=""/>
    <br/>Пароль:<br/>
    <input type="password" name="password" value=""/>
    <br/> ${errorLoginPassMessage} <br/>
    ${wrongAction} <br/>
    ${nullPage} <br/>
    <input type="submit" value="Войти"/>
    <!--Закрываем абзац-->
</p>
<p> Или воспользуйтесь поиском без регистрации
    <a href="library?command=search">
        <img src="./images/book.png" width="30" height="30">
        <span style="margin-left:5px;">Поиск книги</span></a>
</p>
<!--Закрываем строку Меню-->
</td>
</html>
