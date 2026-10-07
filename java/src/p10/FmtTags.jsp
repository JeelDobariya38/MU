<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSTL Format Tags</title>
</head>
<body>

<h2>JSTL Format Tags</h2>

<h3>1. fmt:formatDate</h3>
<%-- Create a Java Date object containing the current date and time. --%>
<c:set var="currentDate" value="<%= new java.util.Date() %>" />

<p>
    Formatted Time:
    <fmt:formatDate type="time" value="${currentDate}" />
</p>
<%-- The exact output depends on the server's locale and formatting configuration --%>
<p>
    Formatted Date:
    <fmt:formatDate type="date" value="${currentDate}" />
</p>

<hr>

<h3>2. fmt:parseDate</h3>
<%-- Store a date as a String. --%>
<c:set var="dateString" value="12-08-2016" />

<%-- Convert the String into a Java Date object using the given pattern. --%>
<fmt:parseDate value="${dateString}" var="parsedDate" pattern="dd-MM-yyyy" />

<p>
    Original String: ${dateString}
</p>
<p>
    Parsed Date:
    <fmt:formatDate value="${parsedDate}" pattern="dd-MM-yyyy" />
</p>

<hr>

<h3>3. fmt:setTimeZone</h3>
<%-- Set the timezone to Indian Standard Time (Asia/Kolkata). --%>
<fmt:setTimeZone value="Asia/Kolkata" />

<p>
    <b>Date and Time in Indian Standard Time (IST):</b>
    <fmt:formatDate value="${currentDate}"
                    type="both"
                    dateStyle="long"
                    timeStyle="long" />
</p>

<p>
    Time Zone Used: Asia/Kolkata
</p>

<hr>

<a href="XMLTags.jsp">Click Here for XML Tags</a>

</body>
</html>
