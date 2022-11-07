<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form method = "post" action = "deleteDealerById">
    Enter Dealer Id: <input type ="number" name = "id" >
    <input type="submit" value="submit">
    </form>
    <% if (null != session.getAttribute("found")) { %>
        <% boolean found = (boolean) session.getAttribute("found"); %>
        <% if (found) {%>
            <%= "Deleted successfully" %>
    <%} } %> 
</body>
</html>