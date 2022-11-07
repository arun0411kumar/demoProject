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
    <form method ="get" action = "getDealerForUpdate">
     Enter Dealer Id: <input type ="number" name = "id" >
    <input type="submit" value="submit">
    </form>
    <% Dealer dealer = (Dealer) session.getAttribute("dealer"); %>
    <% if (null != dealer) { %>
        <h1>Replace some value If you want</h1>
        <form method = "get" action = "updateDealerById">
        <table>
            <tr>
                 <td>Id</td>
                 <td><input name = "id" value = "<%= dealer.getId() %>" readonly>
            </tr>        
            <tr>
                <td>City</td>
                <td> 
                    <input type ="text" name ="city" value = "<%= dealer.getCity() %>">
                </td>
            </tr>
            
            <tr>
                <td>Company</td>
                <td> 
                    <input type ="text" name ="company" value = "<%= dealer.getCompany() %>">
                </td>
            </tr>
            
            <tr>
                <td>StockAvailable</td>
                <td> 
                    <input type ="number" name ="stockAvailable" value = "<%= dealer.getStockAvailable() %>">
                </td>
            </tr>  
            
            <tr>
                <td> 
                    <input type ="submit" value = "save">
                </td>
            </tr>          
        </table>
    </form>
    <% } %>
    <% if (null != session.getAttribute("found")) { %>
        <% boolean found = (boolean) session.getAttribute("found"); %>
        <% if (found) {%>
            <%= "updated successfully" %>
    <%} } %>  
</body>
</html>