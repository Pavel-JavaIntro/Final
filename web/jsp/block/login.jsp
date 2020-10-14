
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lan}"/>
<fmt:setBundle basename="messages"/>
<body>
<td bgcolor="#e6e6fa">
    <p>
    <form name="loginForm" method="POST" action="library" onsubmit="return validateLogin();">
        <h3><fmt:message key="login"/><br/></h3>
        <br/>
        <input type="hidden" name="command" value="login"/>
    <fmt:message key="usersurname"/>:<br/>
        <input type="text" name="surname" id="surname" value=""/>
        <br/><fmt:message key="username"/>:<br/>
        <input type="text" name="name" id="name" value=""/>
        <br/><fmt:message key="userpassword"/>:<br/>
        <input type="password" name="password" id="password" value=""/>
    <br/> <font color="red">${errorLoginPassMessage} </font> <br/>
        ${wrongAction} <br/>
        ${nullPage} <br/>
        <input type="submit" value="<fmt:message key="entrance"/>"/>
    </form>
    </p>
    <p> <fmt:message key="unregistered"/> <br/>
    <form name="searchForm" method="POST" action="library">
        <input type="hidden" name="command" value="search"/>
        <img src="./images/book.png" width="30" height="30" alt="">
        <input type="submit" value="Поиск книги">
    </form>
    </p>
</td>
<script src="${pageContext.request.contextPath}/js/validator.js" type="text/javascript"></script>
</body>

