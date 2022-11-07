<%@page import="com.ideas2It.model.Manufacturer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form method = "post" action = "getManufacturerById">
    Enter Manufacturer Id: <input type ="number" name = "id" >
    <input type="submit" value="submit">
    </form>
    <% Manufacturer manufacturer = (Manufacturer) session.getAttribute("manufacturer"); %>
    <% if (null != manufacturer) { %>
    <h1>YOUR MANUFACTURER DEATAILS</h1>
    <table>
        <tr>
            <td>Id</td>
            <td><%= manufacturer.getId() %></td>
        </tr>
        
        <tr>
            <td>Name</td>
            <td><%= manufacturer.getName() %></td>
        </tr>
    
        <tr>
            <td>Company</td>
            <td><%= manufacturer.getCompany() %></td>
        </tr>

        <tr>
            <td>Investment</td>
            <td><%= manufacturer.getInvestment() %></td>
        </tr> 
    </table>  
     <% session.setAttribute("manufacturer", manufacturer); %>  
    <% } else {%>
        <%= "Enter correct Id" %>
    <% } %>
    <a href="Manufacturer.jsp"> <input type="button"
		value="Back">
	</a>
</body>
</html>