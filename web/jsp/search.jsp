<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 02-Oct-20
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        В поле "Автор" ставьте только фамилию

                        <p style="text-indent:20px">
                            <input type="hidden" name="command" value="search"/>
                            <br/>Название:<br/>
                            <input type="text" name="title" value=""/>
                            <br/>Автор:<br/>
                            <input type="text" name="author1" value=""/>
                            <br/>Автор:<br/>
                            <input type="text" name="author2" value=""/>
                            <br/>Год издания:<br/>
                            <input type="text" name="year" value=""/>
                            <br/> ${errorLoginPassMessage} <br/>
                            ${wrongAction} <br/>
                            ${nullPage} <br/>
                            <input type="submit" value="Искать"/></p>
                        <p style="text-indent:20px">
                            <select size="8">
                                <option>Пункт 1</option>
                                <option>Пункт 2</option>
                                <option>Пункт 3</option>
                            </select>
                        </p>


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
</body>
</html>

