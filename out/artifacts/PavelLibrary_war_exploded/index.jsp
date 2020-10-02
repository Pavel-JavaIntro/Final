<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Login</title></head>
<body>
<form name="loginForm" method="POST" action="library">
    <h1>Библиотека Павла Касичева<br/></h1>
    <br/>
    <h2>Войдите в свою учетную запись<br/></h2>
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
</form>
<hr/>
<jsp:include page="jsp/menu.jsp"/>
</body>
</html>