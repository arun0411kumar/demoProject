 package com.ideas2It.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2It.model.Dealer;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.service.DealerService;
import com.ideas2It.service.ManufacturerService;
import com.ideas2It.service.impl.DealerServiceImpl;
import com.ideas2It.service.impl.ManufacturerServiceImpl;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.logger.VehicleManagementLogger;

@WebServlet(urlPatterns = {"/insertDealer", "/getDealers", "/getDealerById",
		"/deleteDealerById", "/updateDealerById", "/getDealerForUpdate", "/assignDealer"})
public class DealerServlet extends HttpServlet {
    DealerService dealerService = new DealerServiceImpl();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getServletPath();
		PrintWriter printWriter = response.getWriter();
		switch (action) {
		case "/insertDealer":
			createDealer(request, response);
			break;
			
		case "/getDealerById":
			getDealerById(request, response, action);
			break;

		case "/deleteDealerById":
			deleteDealerById(request, response);
			break;

		default:
			printWriter.print("choose valid option");
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getServletPath();
		switch (action) {
		case "/getDealers":
			getDealers(request, response, action);
			break;
			
		case "/assignDealer":
			getDealers(request, response, action);
			break;
			
		case "/getDealerForUpdate":
			getDealerById(request, response, action);
			break;
			
		case "/updateDealerById":
			updateDealerById(request, response);
			break;
				
	    }
	}	
	
	/**
	 * Gets the attributes of dealer
	 *  
	 * @param request
	 * @param response
	 */
	private void createDealer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			Dealer dealer = dealerService.createDealer(request.getParameter("company"),
					Integer.parseInt(request.getParameter("stockAvailable")), request.getParameter("city"));
			HttpSession session = request.getSession();
			session.setAttribute("dealer", dealer);
			response.sendRedirect("insertDealer.jsp");
		} catch (NumberFormatException | VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}

	/**
	 * Display the dealers list
	 *  
	 * @param request
	 * @param response
	 */
	private void getDealers(HttpServletRequest request, HttpServletResponse response, String action) {
		try {
			List<Dealer> dealers = dealerService.getDealers();
			HttpSession session = request.getSession();
			session.setAttribute("dealers", dealers);
			if (action.equals("/assignDealer")) {
				response.sendRedirect("insertTwoWheeler.jsp");		
			} else {
			    response.sendRedirect("getDealers.jsp");
			}    
		} catch (VehicleManagementException | IOException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}

	/**
	 * Gets a dealerId from the user and display the dealer
	 *  
	 * @param request
	 * @param response
	 */
	private void getDealerById(HttpServletRequest request, HttpServletResponse response, String action) {
		try {
			Dealer dealer = dealerService.getDealerById(
					Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("dealer", dealer);
			if (action.equals("/getDealerForUpdate")) {
				response.sendRedirect("updateDealerById.jsp");
			} else if (action.equals("/assignDealerId")) {
				response.sendRedirect("assignDealer.jsp");				
			} else {
				response.sendRedirect("getDealerById.jsp");		
			}
		} catch (VehicleManagementException | IOException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}

	/**
	 * Gets a dealerId from the user and marking deleted the dealer
	 *  
	 * @param request
	 * @param response
	 */
	private void deleteDealerById(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean found = dealerService.deleteDealerById(
					Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("found", found);
			response.sendRedirect("deleteDealerById.jsp");
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
	private void updateDealerById(HttpServletRequest request, HttpServletResponse response) {
		try {
			Dealer dealer = dealerService.getDealerById(
					Integer.parseInt(request.getParameter("id")));
			dealer.setCity(request.getParameter("city"));
			dealer.setCompany(request.getParameter("company"));
			dealer.setStockAvailable(Integer.parseInt(request.getParameter("stockAvailable")));
			boolean found = dealerService.updateDealer(dealer);
			HttpSession session = request.getSession();
			session.setAttribute("found", found);
			response.sendRedirect("updateDealerById.jsp");
		} catch (VehicleManagementException | IOException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	}
}
