<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>  

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSTL XML Tags</title>
</head>
<body>

    <h2>JSTL XML Tags</h2>

    <h3>1. x:parse and x:out</h3>

    <c:import var="bookInfo" url="books.xml" />

    <x:parse xml="${bookInfo}" var="output" />
    
    <p>First Book title: <x:out select="$output/books/book[1]/name" /></p>
    <p>First Book price: <x:out select="$output/books/book[1]/price" /></p>
    <p>Second Book title: <x:out select="$output/books/book[2]/name" /></p>
    <p>Second Book price: <x:out select="$output/books/book[2]/price" /></p>

    <br /><br />
    
    <h3>2. x:forEach</h3>
    <table border="2" cellspacing="2">
        <tr>
            <th>Book Name</th>
            <th>Author</th>
            <th>Price</th>
        </tr>
        <x:forEach select="$output/books/book" var="book">
            <tr>
                <td><x:out select="$book/name" /></td>
                <td><x:out select="$book/author" /></td>
                <td><x:out select="$book/price" /></td>
            </tr>
        </x:forEach>
    </table>
    
    <br />
    <br />
    <a href="FmtTags.jsp">Click Here for Fmt Tags</a>
</body>
</html>
