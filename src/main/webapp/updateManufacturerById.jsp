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
    <form method ="get" action = "getManufacturerForUpdate">
     Enter Manufacturer Id: <input type ="number" name = "id" >
    <input type="submit" value="submit">
    </form>
    <% Manufacturer manufacturer = (Manufacturer) session.getAttribute("manufacturer"); %>
    <% if (null != manufacturer) { %>
        <h1>Replace some value If you want</h1>
        <form method = "get" action = "updateManufacturerById">
        <table>
            <tr>
                 <td>Id</td>
                 <td><input name = "id" value = "<%= manufacturer.getId() %>" readonly>
            </tr>
            <tr>
                <td>Name</td>
                <td> 
                    <input type ="text" name ="name" value = "<%= manufacturer.getName() %>">
                </td>
            </tr>
            
            <tr>
                <td>Company</td>
                <td> 
                    <input type ="text" name ="company" value = "<%= manufacturer.getCompany() %>">
                </td>
            </tr>
            
            <tr>
                <td>Investment</td>
                <td> 
                    <input type ="number" name ="investment" value = "<%= manufacturer.getInvestment() %>">
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