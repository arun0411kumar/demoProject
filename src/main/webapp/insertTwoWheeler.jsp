<%@page import="java.util.List"%>
<%@page import="com.ideas2It.model.Dealer"%>
<%@page import="com.ideas2It.model.Manufacturer"%>
<%@page import="com.ideas2It.model.TwoWheeler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
Manufacturer manufacturer = null;
Dealer dealer = null;
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>FILL THE BELOW CHOICES</h1>
	<form method="get" action="assignDealer">
	</form>
	<form method="get" action="assignManufacturer">
	</form>
	<form method = "post" action = "insertTwoWheeler">
        <table>
            <tr>
                <td>BrandName</td>
                <td> 
                    <input type ="radio" name ="brandName" value = "HONDA">Honda
                    <input type ="radio" name ="brandName" value = "DUCATI">Ducati
                    <input type ="radio" name ="brandName" value = "KAWASAKI">kawasaki
                    <input type ="radio" name ="brandName" value = "PULSER">pulser
                </td>
            </tr>
            
            <tr>
                <td>FuelType</td>
                <td> 
                    <input type ="radio" name ="fuelType" value = "PETROL">Petrol
                    <input type ="radio" name ="fuelType" value = "DIESEL">diesel
                    <input type ="radio" name ="fuelType" value = "BATTERY">battery
                </td>
            </tr>
            
            <tr>
                <td>mileage</td>
                <td> 
                    <input type ="number" name ="mileage" pattern ="[11-79]{1}" 
                     title = "number must within the 11 - 78">
                </td>
            </tr>
            
            <tr>
                <td>colour</td>
                <td> 
                    <input type ="radio" name ="colour" value = "BLACK">Black
                    <input type ="radio" name ="colour" value = "BLUE">Blue
                    <input type ="radio" name ="colour" value = "WHITE">White
                    <input type ="radio" name ="colour" value = "RED">Red
                    <input type ="radio" name ="colour" value = "YELLOW">Yellow
                </td>
            </tr>  
            
            <tr>
                <td>DateOfManufacture</td>
                <td> 
                    <input type ="date" name ="dateOfManufacture">
                </td>
            </tr> 
            
            <tr>
                <td>NoOfStroke</td>
                <td> 
                    <input type ="tel" name ="noOfStroke" pattern ="[2]|[4]" 
                     title = "Enter number 2 or 4">
                </td>
            </tr>
            
            <tr>
                <td>Type</td>
                <td> 
                    <input type ="radio" name ="type" value = "GEAR">Gear
                    <input type ="radio" name ="type" value = "NONGEAR">Non-Gear
                    <input type ="radio" name ="type" value = "SCOTTER">Scotter
                </td>
            </tr>  
            
            <tr>
                 <td>Dealer</td>   
                 <td> 
                     <a href="assignDealer"> <input type="button" value="assign-Dealer">
		             </a>
	                 </br>
	                 <%List<Dealer> dealers = (List<Dealer>) session.getAttribute("dealers");  %>
	                 <% if (null != dealers) { %>
	                 <select name = "dealerId" required>
	                  	   <% for (int index = 0; index < dealers.size(); index++) { %>
	                  	       <% dealer = dealers.get(index); %>
                     	       <option value = "<%= dealer.getId() %>"><%= dealer %></option>
	                  	   <% } %>
	                 </select>
	                 <% } %>  
                 </td>         
            </tr>
            
             <tr>
             <td>Manufacturer</td>   
                 <td> 
                     <a href="assignManufacturer"><input type="button"
			            value="assign-Manufacturer"> </a>
	                 </br>
	                 <%List<Manufacturer> manufacturers = (List<Manufacturer>) session.getAttribute("manufacturers");  %>
	                 <% if (null != manufacturers) { %>
	                 <select name = "manufacturerId" required>
	                 	   <% for (int index = 0; index < manufacturers.size(); index++) { %>
	                     	   <% manufacturer = manufacturers.get(index); %>
    	                  	   <option value = "<%= manufacturer.getId() %>"><%= manufacturer %></option>
                      	   <% } %>
	                 </select>
	                 <% } %>
                 </td>         
            </tr>
            
            <tr>  
                 <td> 
                   <form method = "get" action = "fgdfgdf"> 
	               </form>
                 </td>         
            </tr>
            
            <tr>
                <td> 
                    <input type ="submit" value = "submit">
                </td>
            </tr>                     
        </table>
     </form>
</body>
    <% TwoWheeler twoWheeler = (TwoWheeler) session.getAttribute("twoWheeler"); %>
    <% if (null != twoWheeler) {%>
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
            <% dealer = twoWheeler.getDealer(); %>
            <% if (null != dealer) { %>
                <td>Dealer</td>
                <td>
                    <%= dealer %>
                </td>
           <% } %>    
        </tr> 
    <% } %>
</html>