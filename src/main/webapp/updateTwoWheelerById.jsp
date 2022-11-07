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
    <form method ="get" action = "getTwoWheelerForUpdate">
     Enter vehicle Code: <input type ="text" name = "code" >
    <input type="submit" value="submit">
    </form>
    <% TwoWheeler twoWheeler = (TwoWheeler) session.getAttribute("twoWheeler"); %>
     <form method ="get" action = "updateTwoWheelerById">
         <% if (twoWheeler != null) {%>
         <h1>Replace some value If you want</h1>
         <table>
            <tr>
                 <td>VehicleCode</td>
                 <td><input name = "code" value = "<%= twoWheeler.getVehicleCode() %>" readonly>
            </tr>
            <tr>
                <td>BrandName</td>
                <% String option = twoWheeler.getBrandName().toString(); %>                
                <td> 
                    <input type ="radio" name ="brandName" value = "HONDA"
                    <% if(option.equals("HONDA")) {%> <%= "checked" %> <% }%> >Honda
                    <input type ="radio" name ="brandName" value = "DUCATI"
                    <% if(option.equals("DUCATI")) {%> <%= "checked" %> <% }%> >Ducati
                    <input type ="radio" name ="brandName" value = "KAWASAKI"
                    <% if(option.equals("KAWASAKI")) {%> <%= "checked" %> <% }%> >kawasaki
                    <input type ="radio" name ="brandName" value = "PULSER"
                    <% if(option.equals("PULSER")) {%> <%= "checked" %> <% }%> >pulser
                </td>
            </tr>
            
            <tr>
                <td>FuelType</td>
                <% option = twoWheeler.getFuelType().toString(); %>
                <td> 
                    <input type ="radio" name ="fuelType" value = "PETROL" 
                    <% if(option.equals("PETROL")) {%> <%= "checked" %> <% }%> >Petrol
                    <input type ="radio" name ="fuelType" value = "DIESEL"
                    <% if(option.equals("DIESEL")) {%> <%= "checked" %> <% }%> >diesel
                    <input type ="radio" name ="fuelType" value = "BATTERY" 
                    <% if(option.equals("BATTERY")) {%> <%= "checked" %> <% }%> >battery
                </td>
            </tr>
            
            <tr>
                <td>mileage</td>
                <td> 
                    <input type ="number" name ="mileage" value = "<%= twoWheeler.getMileage() %>" pattern ="[11-79]{1}" 
                     title = "number must within the 11 - 78">
                </td>
            </tr>
            
            <tr>
                <td>colour</td>
                <% option = twoWheeler.getColour().toString(); %>
                <td> 
                    <input type ="radio" name ="colour" value = "BLACK"
                    <% if(option.equals("BLACK")) {%> <%= "checked" %> <% }%> >Black
                    <input type ="radio" name ="colour" value = "BLUE"
                    <% if(option.equals("BLUE")) {%> <%= "checked" %> <% }%> >Blue
                    <input type ="radio" name ="colour" value = "WHITE"
                    <% if(option.equals("WHITE")) {%> <%= "checked" %> <% }%> >White
                    <input type ="radio" name ="colour" value = "RED"
                    <% if(option.equals("RED")) {%> <%= "checked" %> <% }%> >Red
                    <input type ="radio" name ="colour" value = "YELLOW"
                    <% if(option.equals("YELLOW")) {%> <%= "checked" %> <% }%> >Yellow
                </td>
            </tr>  
            
            <tr>
                <td>DateOfManufacture</td>
                <td> 
                    <input type ="datetime" name ="dateOfManufacture" value = "<%= twoWheeler.getDateOfManufacture() %>">
                </td>
            </tr> 
            
            <tr>
                <td>NoOfStroke</td>
                <td> 
                    <input type ="tel" name ="noOfStroke" value = "<%= twoWheeler.getNoOfStroke() %>" pattern ="[2]|[4]" 
                     title = "Enter number 2 or 4"> 
                </td>
            </tr>
            
            <tr>
                <td>Type</td>
                <% option = twoWheeler.getType().toString(); %>
                <td> 
                    <input type ="radio" name ="type" value = "GEAR"
                    <% if(option.equals("GEAR")) {%> <%= "checked" %> <% }%> >Gear
                    <input type ="radio" name ="type" value = "NONGEAR"
                    <% if(option.equals("NONGEAR")) {%> <%= "checked" %> <% }%> >Non-Gear
                    <input type ="radio" name ="type" value = "SCOTTER"
                    <% if(option.equals("SCOTTER")) {%> <%= "checked" %> <% }%> >Scotter
                </td>
            </tr> 
            
            <tr>
                 <td><input type="submit" value="save"></td>
            </tr>
        </table>
        <% if (null != session.getAttribute("found")) { %>
        <% boolean found = (boolean) session.getAttribute("found"); %>
        <% if (found) {%>
            <%= "updated successfully" %>
        <%} } %>  
        <% } %>   
    </form>
</body>
</html>