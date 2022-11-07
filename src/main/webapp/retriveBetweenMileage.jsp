<%@page import="com.ideas2It.model.Dealer"%>
<%@page import="java.util.List"%>
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
    <form action ="range" method = "post">
    <table>
        <tr>
            <td>StartMileage</td>
            <td><input type ="number" name ="start"></td>            
        </tr>
        <tr>
            <td>endMileage</td>
            <td><input type ="number" name ="end"></td>            
        </tr>
        <tr>
            <td><input type ="submit" name ="submit"></td>            
        </tr>
    </table>
    </form>
    <% List<TwoWheeler> twoWheelers = (List<TwoWheeler>) session.getAttribute("twoWheelers"); %>
    <% if (null != twoWheelers) {%>
    <h1>YOUR TWO WHEELER LIST</h1></br>
    <% for (TwoWheeler twoWheeler: twoWheelers)  {%>
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
    <% } } %>    
</body>
</html>