<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="utf-8"/>
<fmt:setLocale value="${sessionScope.lan}"/>
<fmt:setBundle basename="messages"/>
<form name="bookAddingForm" method="POST" action="library">
    <br/>
    <input type="hidden" name="command" value="login"/>
    <fmt:message key="message.genre"/>:<br/>
    <input type="number" name="genre" id="genre" value=""/>
    <br/><fmt:message key="message.authors"/>:<br/>
    <label for="surname1"><fmt:message key="message.usersurname"/></label><input type="text" name="surname1"
                                                                                 id="surname1" value=""/>
    <label for="name1"><fmt:message key="message.username"/></label><input type="text" name="name1"
                                                                           id="name1" value=""/>
    <label for="secondname1"><fmt:message key="message.secondname"/></label><input type="text" name="secondname1"
                                                                                   id="secondname1" value=""/>
    <br/><label for="surname2"><fmt:message key="message.usersurname"/></label><input type="text" name="surname2"
                                                                                 id="surname2" value=""/>
    <label for="name2"><fmt:message key="message.username"/></label><input type="text" name="name2"
                                                                           id="name2" value=""/>
    <label for="secondname2"><fmt:message key="message.secondname"/></label><input type="text" name="secondname2"
                                                                                   id="secondname2" value=""/>
    <br/><label for="surname3"><fmt:message key="message.usersurname"/></label><input type="text" name="surname3"
                                                                                 id="surname3" value=""/>
    <label for="name3"><fmt:message key="message.username"/></label><input type="text" name="name3"
                                                                           id="name3" value=""/>
    <label for="secondname3"><fmt:message key="message.secondname"/></label><input type="text" name="secondname3"
                                                                                   id="secondname3" value=""/>
    <br/><fmt:message key="message.booktitle"/><br/>
    <input type="text" name="booktitle" id="booktitle" value=""/>
    <br/><fmt:message key="message.year"/>:<br/>
    <input type="number" name="bookyear" id="bookyear" value=""/>
    <br/><fmt:message key="message.booklocation"/>:<br/>
    <input type="number" name="booklocation" id="booklocation" value=""/>
    <br/><input type="submit" value="<fmt:message key="message.addbook"/>"/>
</form>
