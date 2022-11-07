package com.ideas2It.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2It.model.Dealer;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.model.TwoWheeler;
import com.ideas2It.service.DealerService;
import com.ideas2It.service.ManufacturerService;
import com.ideas2It.service.VehicleService;
import com.ideas2It.service.impl.DealerServiceImpl;
import com.ideas2It.service.impl.ManufacturerServiceImpl;
import com.ideas2It.service.impl.VehicleServiceImpl;
import com.ideas2It.util.DateUtil;
import com.ideas2It.util.constant.Constants;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.enumeration.Brand;
import com.ideas2It.util.enumeration.Colours;
import com.ideas2It.util.enumeration.FuelType;
import com.ideas2It.util.enumeration.Type;
import com.ideas2It.util.logger.VehicleManagementLogger;

@WebServlet(urlPatterns = {"/insertTwoWheeler", "/getTwoWheelers", 
		"/getTwoWheelerById", "/getTwoWheelerForUpdate", "/deleteTwoWheelerById", "/updateTwoWheelerById",
		"/searchTwoWheelers", "/range", "/In"})
public class VehicleServlet extends HttpServlet {
    VehicleService vehicleService = new VehicleServiceImpl();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
        String action = request.getServletPath();		
        PrintWriter printWriter = response.getWriter();
        switch(action) {
            case "/insertTwoWheeler":
            	createTwoWheeler(request, response);
            	break;
            	
            case "/getTwoWheelerById":
            	getTwoWheelerByCode(request, response, action);
            	break;
            	
            case "/deleteTwoWheelerById":
            	deleteTwoWheelerByCode(request, response);
            	break;
            	
            case "/searchTwoWheelers":
            	searchTwoWheelers(request, response);
            	break;
            	
            case "/range":
            	retriveTwoWheelersInRange(request, response);
            	break;
            	
            default :
            	printWriter.print("choose valid option");
        }
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
        String action = request.getServletPath();		
        switch(action) {
            case "/getTwoWheelers": 
            	getTwoWheelers(request, response);
            	break;
            	
            case "/getTwoWheelerForUpdate":
            	getTwoWheelerByCode(request, response, action);
            	break;
            	
            case "/updateTwoWheelerById":
            	updateTwoWheelerByCode(request, response);
            	break;
            	
            case "/In":
            	displayTwoWheelerByCodes(request, response);
            	break;
            	
        }
	}
	
	/**
	 * This method gets the attributes of two wheeler
	 * 
	 * @param request
	 * @param response
	 */
	private void createTwoWheeler(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			String date = request.getParameter("dateOfManufacture");
            ManufacturerService manufacturerService = new ManufacturerServiceImpl();
            DealerService dealerService = new DealerServiceImpl();
            Manufacturer manufacturer = manufacturerService.getManufacturerById(
            		Integer.parseInt(request.getParameter("manufacturerId")));
            String dealerId = request.getParameter("dealerId");
            Dealer dealer = null;
			if (null != dealerId) { 
				dealer = dealerService.getDealerById(Integer.parseInt(dealerId)); 
		    } 
			TwoWheeler twoWheeler = vehicleService.createTwoWheeler(Brand.valueOf(request.getParameter("brandName")),
					FuelType.valueOf(request.getParameter("fuelType")), Byte.parseByte(request.getParameter("mileage")),
					Colours.valueOf(request.getParameter("colour")), DateUtil.getDate(date),
					Byte.parseByte(request.getParameter("noOfStroke")), Type.valueOf(request.getParameter("type")),
					manufacturer, dealer);
			HttpSession session = request.getSession();
			session.setAttribute("twoWheeler", twoWheeler);
			response.sendRedirect("insertTwoWheeler.jsp");
		} catch (NumberFormatException | VehicleManagementException | ParseException e) {
			//VehicleManagementLogger.displayVehicleError(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Display the two wheeler list
	 * 
	 * @param request
	 * @param response
	 */
	private void getTwoWheelers(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<TwoWheeler> twoWheelers = vehicleService.getTwoWheelers();
			HttpSession session = request.getSession();
			session.setAttribute("twoWheelers", twoWheelers);
			response.sendRedirect("getTwoWheelers.jsp");
		} catch (VehicleManagementException | IOException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}
	
	/**
	 * Gets a vehicleCode from the user and display the two wheeler
	 *  
	 * @param request
	 * @param response
	 */
	private void getTwoWheelerByCode(HttpServletRequest request, HttpServletResponse response, String action) {
		try {
			TwoWheeler twoWheeler = vehicleService.getTwoWheelerByCode(request.getParameter("code"));
			HttpSession session = request.getSession();
			session.setAttribute("twoWheeler", twoWheeler);
			if (action.equals("/getTwoWheelerForUpdate")) {
				response.sendRedirect("updateTwoWheelerById.jsp");
			} else {		
			    response.sendRedirect("getTwoWheelerById.jsp");
			}    
		} catch (VehicleManagementException | IOException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}
	
	/**
	 * Gets a vehicleCode from the user and delete the two wheeler
	 *  
	 * @param request
	 * @param response
	 */
	private void deleteTwoWheelerByCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean found = vehicleService.deleteTwoWheelerByCode(request.getParameter("code"));
			HttpSession session = request.getSession();
			session.setAttribute("found", found);
			response.sendRedirect("deleteTwoWheelerById.jsp");
		} catch (VehicleManagementException | IOException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}
	
	/**
	 * Based on the user Input it's update that value
	 *
 
	 * @param request
	 * @param response
	 */
	private void updateTwoWheelerByCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			TwoWheeler twoWheeler = vehicleService.getTwoWheelerByCode(request.getParameter("code"));;
			String date = request.getParameter("dateOfManufacture");
			twoWheeler.setBrandName(Brand.valueOf(request.getParameter("brandName")));
			twoWheeler.setFuelType(FuelType.valueOf(request.getParameter("fuelType")));
			twoWheeler.setMileage(Byte.parseByte(request.getParameter("mileage")));
			twoWheeler.setColour(Colours.valueOf(request.getParameter("colour")));
			twoWheeler.setDateOfManufacture(DateUtil.getDate(date));
			twoWheeler.setNoOfStroke(Byte.parseByte(request.getParameter("noOfStroke")));
			twoWheeler.setType(Type.valueOf(request.getParameter("type")));
			boolean found = vehicleService.updateVehicle(twoWheeler);
			HttpSession session = request.getSession();
			session.setAttribute("found", found);
			response.sendRedirect("updateTwoWheelerById.jsp");
		} catch ( IOException | ParseException | VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}
	
	/**
	 * This method display twowheeler list based on the user given value
	 */
	private void searchTwoWheelers(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<TwoWheeler> twoWheelers = vehicleService.searchTwoWheeler(
					request.getParameter("letter"));
			HttpSession session = request.getSession();
			session.setAttribute("twoWheelers", twoWheelers);
			response.sendRedirect("searchTwoWheelers.jsp");
		} catch (VehicleManagementException | IOException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}
	
	/**
	 * This method display twowheeler list based on the user given range
	 *  
	 * @param request
	 * @param response
	 */
	private void retriveTwoWheelersInRange(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<TwoWheeler> twoWheelers = null;
			HttpSession session = request.getSession();
			twoWheelers = vehicleService.retriveVehiclesInRange(1, 
					request.getParameter("start"), request.getParameter("end"));
			session.setAttribute("twoWheelers", twoWheelers);
			try {
				new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("start"));
				response.sendRedirect("retriveBetweenDate.jsp");
			} catch (ParseException  e) {
				response.sendRedirect("retriveBetweenMileage.jsp");
			}
		} catch (VehicleManagementException| IOException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}
	
	/**
	 * This method display the value of given no of codes
	 *  
	 * @param request
	 * @param response
	 */
	public void displayTwoWheelerByCodes(HttpServletRequest request, HttpServletResponse response) {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			int index = Constants.CHOICE_ZERO;
			int choice = Integer.parseInt(request.getParameter("choice"));
			String codes[] = new String[choice];
			do {
				codes[index] = request.getParameter(
						stringBuffer.append("code-").append(index + 1).toString());
				stringBuffer.setLength(0);
				index++;
			} while (--choice > 0);
			List<TwoWheeler> twoWheelers = vehicleService.getTwoWheelerByCodes(codes);
			HttpSession session = request.getSession();
			session.setAttribute("twoWheelers", twoWheelers);
			response.sendRedirect("retriveTwoWheelerInCodes.jsp");
		} catch (VehicleManagementException | IOException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}
}

