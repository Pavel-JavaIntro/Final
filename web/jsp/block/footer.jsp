<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 02-Oct-20
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
            <h3>Адрес</h3>
            <p>г.Минск, ул. Библиотеки Павла Касичева</p>
            <h3>Распорядок работы</h3>
            <p>Только в рабочее время</p>
            <h3>Сайт разработан Павлом Касичевым</h3>
            <p> email: ${applicationScope.email}</p>
            <!--Закрываем таблицу подвала. При желании в подвале можно
            сделать несколько строк и столбцов-->
        </th>
    </tr>
</table>
</html>
