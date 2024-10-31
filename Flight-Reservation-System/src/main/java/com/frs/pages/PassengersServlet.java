package com.frs.pages;

import static com.frs.utils.DBUtils.closeConnection;
import static com.frs.utils.DBUtils.openConnection;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.frs.dao.PassengersDao;
import com.frs.dao.PassengersDaoImpl;
import com.frs.pojos.Passengers; 

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/PassengersServlet")
public class PassengersServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	
	private PassengersDao passengersDao;

	public void init(ServletConfig config) throws ServletException {
		
		try{
			openConnection();
			
			passengersDao = new PassengersDaoImpl();
		}
		catch(Exception e) {
			throw new ServletException("error in init of " + getClass(),e);
		}
	}

	public void destroy() {
		try {
			passengersDao.cleanup();
			
			closeConnection();
		}
		catch(Exception e) {
			throw new RuntimeException("err in destroy of " + getClass(), e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter()){
			String pname = request.getParameter("name");
			String pemail = request.getParameter("email");
			String phone = request.getParameter("phone");
			
			Passengers p = new Passengers(pname,pemail,phone);
//			passengersDao.addPassengers(p);
			
			HttpSession hs = request.getSession();
			hs.setAttribute("passenger", p);
			hs.setAttribute("passenger_id", p.getPassengerid());
			response.sendRedirect("carddetails.html");
		}
		catch(Exception e) {
			throw new ServletException("error in do-post of " + getClass(),e);
		}
	}

}
