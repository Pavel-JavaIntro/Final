<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 02-Oct-20
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<table
        border="1"
        background="./images/book.png"
        bgcolor="#7FFFD4"
        cellpadding="10"
        style="width:100%; border-radius:5px;">
    <!--Создаём строку таблицы-->
    <tr>
        <!--Создаём столбец таблицы-->
        <th>
            <!--Содержание ячейки столбца-->
            <h1>Библиотека Павла Касичева</h1>
            <h3>Лучшая онлайн библиотека в моем гитхаб репозитории</h3>
            <!--Закрываем таблицу-->
        </th>
        <th>
            <form name="selectLang" method="POST" action="library">
            <img src="./images/rus.png" width="30" height="30" alt="">
            <br/>
                <input type="submit" value="RUS">
            </form>
        </th>
        <th>
            <form name="selectLang" method="POST" action="library">
            <img src="./images/eng.png" width="30" height="30" alt="">
            <br/>
                <input type="submit" value="ENG">
            </form>
        </th>
    </tr>
</table>
</html>
