package com.ideas2It.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2It.model.Dealer;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.model.TwoWheeler;
import com.ideas2It.service.ManufacturerService;
import com.ideas2It.service.VehicleService;
import com.ideas2It.service.impl.ManufacturerServiceImpl;
import com.ideas2It.service.impl.VehicleServiceImpl;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.enumeration.Brand;
import com.ideas2It.util.enumeration.Colours;
import com.ideas2It.util.enumeration.FuelType;
import com.ideas2It.util.enumeration.Type;
import com.ideas2It.util.logger.VehicleManagementLogger;

@WebServlet(urlPatterns = {"/insertManufacturer", "/getManufacturers", 
		"/getManufacturerById", "/deleteManufacturerById", "/updateManufacturerById", 
		"/assignManufacturer", "/getManufacturerForUpdate"})
public class ManufacturerServlet extends HttpServlet {
    ManufacturerService manufacturerService = new ManufacturerServiceImpl();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getServletPath();
		PrintWriter printWriter = response.getWriter();
		switch (action) {
		case "/insertManufacturer":
			createManufacturer(request, response);
			break;

		case "/getManufacturerById":
			getManufacturerById(request, response, action);
			break;

		case "/deleteManufacturerById":
			deleteManufacturerById(request, response);
			break;

		default:
			printWriter.print("choose valid option");
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getServletPath();
		switch (action) {
		case "/getManufacturers":
			getManufacturers(request, response, action);
			break;
			
		case "/assignManufacturer":
			getManufacturers(request, response, action);
			break;
			
		case "/getManufacturerForUpdate":
			getManufacturerById(request, response, action);
			break;
			
		case "/updateManufacturerById":
			updateManufacturerById(request, response);
			break;
		}		
	}
	
	/**
	 * This method was create the manufacturer details and print that object
	 * 
	 * @param request
	 * @param response
	 */
	private void createManufacturer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			Manufacturer manufacturer = manufacturerService.createManufacturer(request.getParameter("name"),
					request.getParameter("company"), Double.parseDouble(request.getParameter("investment")));
			HttpSession session = request.getSession();
			session.setAttribute("manufacturer", manufacturer);
			response.sendRedirect("insertManufacturer.jsp");
		} catch (NumberFormatException | VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}
	
	/**
	 * This method was print the manufacturer details
	 *  
	 * @param request
	 * @param response
	 */
	private void getManufacturers(HttpServletRequest request, HttpServletResponse response, String action) {
		try {
			List<Manufacturer> manufacturers = manufacturerService.getManufacturers();
			HttpSession session = request.getSession();
			session.setAttribute("manufacturers", manufacturers);
			if (action.equals("/assignManufacturer")) {
			    response.sendRedirect("insertTwoWheeler.jsp");	
			} else
			    response.sendRedirect("getManufacturers.jsp");
		} catch (VehicleManagementException | IOException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}

	/**
	 * Gets a manufacturerId from the user and display the manufacturer
	 *  
	 * @param request
	 * @param response
	 */
	private void getManufacturerById(HttpServletRequest request, HttpServletResponse response, String action) {
		try {
			Manufacturer manufacturer = manufacturerService.getManufacturerById(
					Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("manufacturer", manufacturer);
			if (action.equals("/assignManufacturerId")) {
			    response.sendRedirect("assignManufacturer.jsp");
			} else if (action.equals("/getManufacturerforUpdate")) {
			    response.sendRedirect("updateManufacturerById.jsp");
			} else {
				response.sendRedirect("getManufacturerById.jsp");
			}    
		} catch (VehicleManagementException | IOException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}

	/**
	 * Gets a manufacturerId from the user and delete the manufacturer
	 *  
	 * @param request
	 * @param response
	 */
	private void deleteManufacturerById(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean found = manufacturerService.deleteManufacturerById(
					Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("found", found);
			response.sendRedirect("deleteManufacturerById.jsp");
		} catch (VehicleManagementException | IOException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}
	
	/**
	 * This method get's user given id and get user given choice and update specific
	 * field
	 * 
	 * @param request
	 * @param response
	 */
	private void updateManufacturerById(HttpServletRequest request, HttpServletResponse response) {
		try {
			Manufacturer manufacturer = manufacturerService.getManufacturerById(
					Integer.parseInt(request.getParameter("id")));
			manufacturer.setCompany(request.getParameter("company"));
			manufacturer.setName(request.getParameter("name"));
			manufacturer.setInvestment(Double.parseDouble(request.getParameter("investment")));
			boolean found = manufacturerService.updateManufacturer(manufacturer);
			HttpSession session = request.getSession();
			session.setAttribute("found", found);
			response.sendRedirect("updateManufacturerById.jsp");
		} catch (VehicleManagementException | IOException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}
}
