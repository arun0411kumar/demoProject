<%@page import="com.ideas2It.model.Dealer"%>
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
     <h1>YOUR DEALERS LIST</h1></br>
    <% List<Dealer> dealers = (List<Dealer>) session.getAttribute("dealers"); %>
    <% if (null != dealers) { %>
    <% for (Dealer dealer: dealers) {%>
    <table>
        <tr>
            <td>Id</td>
            <td><%= dealer.getId() %></td>
        </tr>
        
        <tr>
            <td>Company</td>
            <td><%= dealer.getCompany() %></td>
        </tr>
    
        <tr>
            <td>Stock Available</td>
            <td><%= dealer.getStockAvailable() %></td>
        </tr>

        <tr>
            <td>City</td>
            <td><%= dealer.getCity() %></td>
        </tr> 
    </table>  
    <% } } %>
</body>
</html>