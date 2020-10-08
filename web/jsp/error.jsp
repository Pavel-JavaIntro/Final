<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 21-Sep-20
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}

<br/><a href="library?command=welcome">Вернитесь назад</a><br/>
</body>
</html>
