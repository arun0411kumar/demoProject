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
    <form method = "post" action = "insertManufacturer">
                <h1>FILL THE BELOW CHOICES</h1>
        <table>        
            <tr>
                <td>Name</td>
                <td> 
                    <input type ="text" name ="name" >
                </td>
            </tr>
            
            <tr>
                <td>Company</td>
                <td> 
                    <input type ="text" name ="company" >
                </td>
            </tr>
            
            <tr>
                <td>Investment</td>
                <td> 
                    <input type ="number" name ="investment">
                </td>
            </tr>  
            <tr>
                <td> 
                    <input type ="submit" value = "submit">
                </td>
            </tr>           
        </table>
    </form>
    <% Manufacturer manufacturer = (Manufacturer) session.getAttribute("manufacturer"); %>
    <% if (null != manufacturer) { %>
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
    <% } %>
</body>
</html>