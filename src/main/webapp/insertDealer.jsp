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
    <form method = "post" action = "insertDealer">
        <h1>FILL THE BELOW CHOICES</h1>
        <table>
            <tr>
                <td>City</td>
                <td> 
                    <input type ="text" name ="city" >
                </td>
            </tr>
            
            <tr>
                <td>Company</td>
                <td> 
                    <input type ="text" name ="company">
                </td>
            </tr>
            
            <tr>
                <td>StockAvailable</td>
                <td> 
                    <input type ="number" name ="stockAvailable" >
                </td>
            </tr>  
            
            <tr>
                <td> 
                    <input type ="submit" value = "submit">
                </td>
            </tr>          
        </table>
    </form>
    <% Dealer dealer = (Dealer) session.getAttribute("dealer"); %>
    <% if (null != dealer) { %>
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