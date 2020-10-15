<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lan}"/>
<fmt:setBundle basename="messages"/>
<form name="orderForm" method="POST" action="library">
    <input type="checkbox" name="subscribe" id="subscribe"><fmt:message key="message.subscriber" /></input><br/>
    <input type="submit" value="<fmt:message key="message.order" />"/>
</form>
