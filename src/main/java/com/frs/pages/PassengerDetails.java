package com.frs.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.frs.pojos.Flight;
import com.frs.pojos.User;

@WebServlet("/PassengerDetails")
public class PassengerDetails extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
	    resp.setContentType("text/html");
	    try(PrintWriter pw = resp.getWriter()){
	        
	        HttpSession session = req.getSession();
	        User user = (User)session.getAttribute("user_details");
	        
	        int fId = Integer.parseInt(req.getParameter("flightId"));
	            
	        List<Flight> flights = new ArrayList<Flight>((List<Flight>)session.getAttribute("flight_info"));
	        Flight selectFlight = null;
	        for(Flight f: flights) {
	            if(f.getAirline_id() == fId) {
	                selectFlight = f;
	                break;
	            }
	        }
	        
	        // CSS for the flight details table and passenger details form
	        pw.print("<style>");
	        pw.print("body { font-family: Arial, sans-serif; background-color: #f0f0f0; color: #333; }");
	        pw.print(".flight-table { width: 80%; border-collapse: collapse; margin: auto; border-radius: 10px; overflow: hidden; }");
	        pw.print(".flight-table th, .flight-table td { border: 1px solid #ccc; padding: 10px; text-align: center; }");
	        pw.print(".flight-table th { background-color: #B9E5E8; color: #002147; }");
	        pw.print(".flight-table tr { background-color: #f9f9f9; transition: background-color 0.3s; }");
	        pw.print(".flight-table tr:hover { background-color: #e1f5fe; }"); // Light blue on hover

	        // Styling for the form
	        pw.print(".form-container { display: flex; justify-content: center; align-items: center; margin: 2rem; }");
	        pw.print(".form-table td { border: none; }");
	        pw.print("form { width: 100%; max-width: 400px; background-color: #ffffff; padding: 1.5rem; border-radius: 10px; box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1); }");
	        pw.print("input[type='text'], input[type='email'] { width: 100%; padding: 0.5rem; border-radius: 5px; border: 1px solid #ccc; }");
	        pw.print("input[type='submit'] { width: 100%; padding: 0.6rem; background-color: #4CAF50; color: white; border: none; border-radius: 5px; font-size: 1rem; cursor: pointer; transition: background-color 0.3s; }");
	        pw.print("input[type='submit']:hover { background-color: #45a049; }"); // Darker green on hover
	        pw.print("</style>");

	        // Display selected flight details table
	        pw.print("<h1 align='center' style='text-decoration: underline; margin-bottom: 2.5rem;'>Passenger Details Form</h1>");
	        pw.print("<h2 align='center'>Hello " + user.getFirstName() + " " + user.getLastName() + ",</h2>");
	        pw.print("<h3 align='center'>You have Chosen:</h3>");
	        pw.print("<table class='flight-table'>");
	        pw.print("<tr><th>Flight Number</th><th>Airline Name</th><th>Departure City</th>"
	                + "<th>Arrival City</th><th>Departure Time</th><th>Arrival Time</th><th>Ticket Price</th>"
	                + "<th>Departure Date</th><th>Available Seats</th></tr>");
	        pw.print("<tr>");
	        pw.print("<td>" + selectFlight.getFlight_number() + "</td>");
	        pw.print("<td>" + selectFlight.getAirline_name() + "</td>");
	        pw.print("<td>" + selectFlight.getDeparture_city() + "</td>");
	        pw.print("<td>" + selectFlight.getArrival_city() + "</td>");
	        pw.print("<td>" + selectFlight.getDeparture_time() + "</td>");
	        pw.print("<td>" + selectFlight.getArrival_time() + "</td>");
	        pw.print("<td>" + selectFlight.getPrice() + "</td>");
	        pw.print("<td>" + selectFlight.getDeparture_date() + "</td>");
	        pw.print("<td>" + selectFlight.getAvailable_seats() + "</td>");
	        pw.print("</tr>");
	        pw.print("</table>");

	        // Display passenger details form
	        pw.print("<h2 align='center'>Enter Passenger Details</h2>");
	        pw.print("<div class='form-container'>");
	        pw.print("<form action='PassengersServlet' method='post'>");
	        pw.print("<h2 style='color: #333; margin-bottom: 1rem;'>Passenger Information</h2>");

	        pw.print("<table class='form-table' style='width: 100%; border-collapse: separate; border-spacing: 0 1rem;'>");

	        // Passenger Name Field
	        pw.print("<tr>");
	        pw.print("<td style='text-align: left; font-weight: bold; color: #333;'>Passenger Name:</td>");
	        pw.print("<td><input type='text' name='name' required /></td>");
	        pw.print("</tr>");

	        // Email Field
	        pw.print("<tr>");
	        pw.print("<td style='text-align: left; font-weight: bold; color: #333;'>Email:</td>");
	        pw.print("<td><input type='email' name='email' required /></td>");
	        pw.print("</tr>");

	        // Phone Field
	        pw.print("<tr>");
	        pw.print("<td style='text-align: left; font-weight: bold; color: #333;'>Phone:</td>");
	        pw.print("<td><input type='text' name='phone' required /></td>");
	        pw.print("</tr>");

	        // Submit Button
	        pw.print("<tr>");
	        pw.print("<td colspan='2' style='text-align: center;'>");
	        pw.print("<input type='submit' value='Enter Details' />");
	        pw.print("</td>");
	        pw.print("</tr>");

	        pw.print("</table>");
	        pw.print("</form>");
	        pw.print("</div>");

	        session.setAttribute("flight_info", selectFlight);
	        session.setAttribute("flight_id", fId);
	    }
	    catch(Exception e) {
	        throw new ServletException("Error in doPost of " + getClass(), e);
	    }
	}

}
