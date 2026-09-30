<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL - Demo Page</title>
    </head>
    <body>
        <c:set var="userName" value="Jhon Doe" />
        <h2>Hello, <c:out value="${userName}" />!!!</h2>
        
        
        <c:set var="userName" value="Jhone Doeee" />
        <h2>Hello, <c:out value="${userName}" />!!!</h2>
        
        <c:remove var="userName" />
        <h2>Hello, <c:out value="${userName}" />!!!</h2>
        
        <c:import url="welcome.jsp" />
        
        <HR />
        <c:set var="fullName" value="Jhon Doe Lee" />
        <h2><c:out value="${fullName}" />!!!</h2>
        
        <h3><c:out value="${fn:toLowerCase(fullName)}" /></h3>
        
        <h3><c:out value="${fn:toUpperCase(fullName)}" /></h3>
        
        <h3><c:out value="${fn:substring(fullName, 0, 5)}" /></h3>
        
        <h3><c:out value="${fn:replace(fullName, 'Jhon', 'Josh')}" />!!!</h3>

    </body>
</html>
