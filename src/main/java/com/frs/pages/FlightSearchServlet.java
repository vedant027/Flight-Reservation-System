package com.frs.pages;

import static com.frs.utils.DBUtils.closeConnection;
import static com.frs.utils.DBUtils.openConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.frs.dao.FlightDao;
import com.frs.dao.FlightDaoImpl;
import com.frs.pojos.Flight;
import com.frs.pojos.User;


@WebServlet("/FlightSearchServlet")
public class FlightSearchServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	
	private FlightDao flightDao;
	

	
	public void init(ServletConfig config) throws ServletException {
		
		try {
			openConnection();
			
			flightDao = new FlightDaoImpl();
		}
		catch(Exception e) {
				throw new ServletException("error in init of " + getClass(),e);
		}
	}


	public void destroy() {
		try {
			flightDao.cleanUp();
			closeConnection();
		}
		catch(Exception e) {
			throw new RuntimeException("err in destroy of " + getClass(), e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter()){
			String departure = request.getParameter("departure");
			String arrival = request.getParameter("arrival");
			Date date = Date.valueOf(request.getParameter("date"));
			
			List<Flight> f = flightDao.availableFlight(departure, arrival, date);
//			HttpSession session = request.getSession();
//			User userDetails = (User)session.getAttribute("user_details"); 
//			pw.print("<h5>Hello, " + userDetails.getFirstName() +" " + userDetails.getLastName() + "</h5>");
//			
			if(f.isEmpty()) {
				pw.print("<h5> No Flights available for your required search , " + "<a href='flightSearch.html'>Please try for another date</a></h5>");
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("flight_info", f);
//				pw.print("<h5>" + "Hello " +  + "</h5>");
				response.sendRedirect("available_flights");
			}
		}
		catch(Exception e) {
			throw new ServletException("err in do-post of " + getClass(), e);
		}
	}

}
