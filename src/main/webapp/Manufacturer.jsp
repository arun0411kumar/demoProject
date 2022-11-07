<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form method = "get" action = "getManufacturers">
    <table>
         <tr>
         <td><h1>MANUFACTURER CHOICE</h1></td>
         </tr>
         
         <tr>
             <td>
             	<a href="insertManufacturer.jsp"> <input type="button"
		        value="insertManufacturer">
	            </a>
             </td>
         </tr>
         
         <tr>
             <td>
             	<a href="getManufacturers"> <input type="button"
		        value="getManufacturers">
	            </a>
             </td>
         </tr>
         
         <tr>
             <td>
             	<a href="getManufacturerById.jsp"> <input type="button"
		        value="getManufacturerById">
	            </a>
             </td>
         </tr>
         
         <tr>
             <td>
             	<a href="deleteManufacturerById.jsp"> <input type="button"
		        value="deleteManufacturerById">
	            </a>
             </td>
         </tr>
         
         <tr>
             <td>
             	<a href="updateManufacturerById.jsp"> <input type="button"
		        value="updateManufacturerById">
	            </a>
             </td>
         </tr>
    </table>
	</form>
</body>
</html>