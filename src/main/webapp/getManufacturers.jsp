<%@page import="com.ideas2It.model.Manufacturer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <h1>YOUR MANUFACTURER LIST</h1></br>
    <% List<Manufacturer> manufacturers = (List<Manufacturer>) session.getAttribute("manufacturers"); %>
    <% if (null != manufacturers) { %>
    <% for (Manufacturer manufacturer: manufacturers) {%>
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
    <% }  }%>
         <% session.setAttribute("manufacturers", manufacturers); %>  
</body>
</html>