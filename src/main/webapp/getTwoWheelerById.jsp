<%@page import="com.ideas2It.model.Dealer"%>
<%@page import="com.ideas2It.model.TwoWheeler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form method = "post" action = "getTwoWheelerById">
    Enter vehicle code: <input type ="text" name = "code" >
    <input type="submit" value="submit">
    </form>
    <% TwoWheeler twoWheeler = (TwoWheeler) session.getAttribute("twoWheeler"); %>
    <% if (null != twoWheeler) {%>
    <h1>YOUR TWO WHEELER DEATAILS</h1>
    <table>
        <tr>
            <td>Id</td>
            <td><%= twoWheeler.getId() %></td>
        </tr>
        
        <tr>
            <td>VehicleCode</td>
            <td><%= twoWheeler.getVehicleCode() %></td>
        </tr>
    
        <tr>
            <td>BrandName</td>
            <td><%= twoWheeler.getBrandName() %></td>
        </tr>

        <tr>
            <td>FuelType</td>
            <td><%= twoWheeler.getFuelType() %></td>
        </tr>  
        
        <tr>
            <td>Mileage</td>
            <td><%= twoWheeler.getMileage() %></td>
        </tr>  
        
        <tr>
            <td>Colour</td>
            <td><%= twoWheeler.getColour() %></td>
        </tr>  
        
        <tr>
            <td>DateOfManufacture</td>
            <td><%= twoWheeler.getDateOfManufacture() %></td>
        </tr>
        
        <tr>
            <td>NoOfStroke</td>
            <td><%= twoWheeler.getNoOfStroke() %></td>
        </tr>  
        
        <tr>
            <td>Type</td>
            <td><%= twoWheeler.getType() %></td>
        </tr>
        
        <tr>
            <td>LifeTime</td>
            <td><%= twoWheeler.getAge(twoWheeler.getDateOfManufacture()) %></td>
        </tr>  
        
        <tr>
            <td>Manufacturer</td>
            <td><%= twoWheeler.getManufacturer() %></td>
        </tr>  
        
        <tr>
            <% Dealer dealer = twoWheeler.getDealer(); %>
            <% if (null != dealer) { %>
                <td>Dealer</td>
                <td>
                    <%= dealer %>
                </td>
           <% } %>    
        </tr>                                     
    </table>
    <% } else { %>
     <%= "Choose vaild two Wheeler from the list" %>
    <% } %>
</body>
</html>