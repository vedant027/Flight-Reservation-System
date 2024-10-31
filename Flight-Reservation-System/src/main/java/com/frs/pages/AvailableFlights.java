package com.frs.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.frs.pojos.Flight;

@WebServlet("/available_flights")
public class AvailableFlights extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
	    resp.setContentType("text/html");
	    try(PrintWriter pw = resp.getWriter()){
	        
	        HttpSession session = req.getSession();
	        List<Flight> flightDetails = (List<Flight>)session.getAttribute("flight_info");
	        
	        if(flightDetails != null && !flightDetails.isEmpty()) {
	            
	            pw.print("<style>");
	            pw.print("table { width: 80%; border-collapse: collapse; margin: auto; border-radius: 10px; overflow: hidden; }");
	            pw.print("th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }");
	            pw.print("th { background-color: #B9E5E8; color: #002147; }"); // Navy blue text for header
	            pw.print("tr { background-color: #f9f9f9; transition: background-color 0.3s; }"); // Smooth transition for row color
	            pw.print("tr:hover { background-color: #e1f5fe; }"); // Light blue on hover
	            pw.print("input[type='submit'] { padding: 8px 15px; background-color: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer; transition: background-color 0.3s; }");
	            pw.print("input[type='submit']:hover { background-color: #0056b3; }"); // Darker blue on hover for button
	            pw.print("</style>");
	            
	            pw.print("<h1 align='center' style='color:#CC2B52'>Available Flights</h1>");
	            pw.print("<table>");
	            pw.print("<tr><th>Flight Number</th><th>Airline Name</th><th>Departure City</th>"
	                    + "<th>Arrival City</th><th>Departure Time</th><th>Arrival Time</th><th>Ticket Price</th>"
	                    + "<th>Departure Date</th><th>Available Seats</th><th>Book Now</th></tr>");
	            
	            for(Flight f : flightDetails) {
	                pw.print("<tr>");
	                pw.print("<td>" + f.getFlight_number() + "</td>");
	                pw.print("<td>" + f.getAirline_name() + "</td>");
	                pw.print("<td>" + f.getDeparture_city() + "</td>");
	                pw.print("<td>" + f.getArrival_city() + "</td>");
	                pw.print("<td>" + f.getDeparture_time() + "</td>");
	                pw.print("<td>" + f.getArrival_time() + "</td>");
	                pw.print("<td>" + f.getPrice() + "</td>");
	                pw.print("<td>" + f.getDeparture_date() + "</td>");
	                pw.print("<td>" + f.getAvailable_seats() + "</td>");

	                pw.print("<td>");
	                pw.print("<form action='PassengerDetails' method='post'>");
	                pw.print("<input type='hidden' name='flightId' value='" + f.getAirline_id() +"' />");
	                pw.print("<input type='submit' value='Select' /></form>");
	                pw.print("</td>");
	                pw.print("</tr>");
	            }
	            pw.print("</table>");
	            
	        } else {
	            pw.print("<h5>No Flights Available! Please try again later.</h5>");
	        }
	    } catch(Exception e) {
	        throw new ServletException("Error in doGet of " + getClass(), e);
	    }
	}

}
