<%@page import="com.ideas2It.model.Dealer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form method = "post" action = "getDealerById">
    Enter Dealer Id: <input type ="number" name = "id" >
    <input type="submit" value="submit">
    </form>
    <% Dealer dealer = (Dealer) session.getAttribute("dealer"); %>
    <% if (null != dealer) { %>
    <h1>YOUR DEALER DEATAILS</h1>
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
    <% } %>
</body>
</html>