<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form method = "get" action = "getTwoWheelers">
    <table>
    <tr>
        <td>
            <h1>TWO WHEELER CHOICE</h1>
        </td>
    </tr>
    <tr>
        <td>
        	<a href="insertTwoWheeler.jsp"> <input type="button"
		    value="insertTwoWheeler">
	        </a>
        </td>
    </tr>
    
    <tr>
        <td>
        	<a href = "getTwoWheelers"> <input type="button"
		     value="getTwoWheelers">
	        </a></br>
        </td>
    </tr>
    
    <tr>
        <td>
        	<a href="getTwoWheelerById.jsp"> <input type="button"
		    value="getTwoWheelerById">
	        </a></br>
        </td>
    </tr>
    
    <tr>
        <td>
        	<a href="deleteTwoWheelerById.jsp"> <input type="button"
		     value="deleteTwoWheelerById">
	        </a></br>
        </td>
    </tr>
    
    <tr>
        <td>
        	<a href="updateTwoWheelerById.jsp"> <input type="button"
		     value="updateTwoWheelerById">
	        </a></br>
        </td>
    </tr>

    <tr>
        <td>
            <a href="searchTwoWheelers.jsp"> <input type="button"
		    value="searchTwoWheelers">
	        </a></br>
        </td>
    </tr>
    
    <tr>
        <td>
            <a href="retriveVehiclesInRange.jsp"> <input type="button"
		    value="retriveVehiclesInRange">
	        </a></br>
        </td>
    </tr>
    
    <tr>
        <td>
        	<a href="getChoice.jsp"> <input type="button"
		    value="retriveTwoWheelerInCodes">
	        </a>
        </td>
    </tr>                        
    </table>			
	</form>
</body>
</html>