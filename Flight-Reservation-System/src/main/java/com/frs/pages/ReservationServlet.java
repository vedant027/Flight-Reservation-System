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

import com.frs.dao.CardDao;
import com.frs.dao.CardDaoImpl;
import com.frs.dao.FlightDao;
import com.frs.dao.FlightDaoImpl;
import com.frs.dao.PassengersDao;
import com.frs.dao.PassengersDaoImpl;
import com.frs.dao.ReservationDao;
import com.frs.dao.ReservationDaoImpl;
import com.frs.dao.UserDao;
import com.frs.dao.UserDaoImpl;
import com.frs.pojos.Card;
import com.frs.pojos.Flight;
import com.frs.pojos.Passengers;
import com.frs.pojos.Reservation;
import com.frs.pojos.User;

@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	
	private ReservationDao reservationDao;
	private CardDao cardDao;
	private PassengersDao passengerDao;
	private FlightDao flightDao;
	private UserDao userDao;


	public void init(ServletConfig config) throws ServletException {
		
		try {
			openConnection();
			
			reservationDao = new ReservationDaoImpl();
			cardDao = new CardDaoImpl();
			passengerDao = new PassengersDaoImpl();
			flightDao = new FlightDaoImpl();
			userDao = new UserDaoImpl();
			
		}
		catch(Exception e) {
			throw new ServletException("error in init of " + getClass(),e);
		}
	}

	
	public void destroy() {
		
		try {
			reservationDao.cleanUp();
			cardDao.cleanup();
			passengerDao.cleanup();
			flightDao.cleanUp();
			userDao.cleanUp();
			
			closeConnection();
		}
		catch(Exception e) {
			throw new RuntimeException("err in destroy of " + getClass(), e);
		}
	}

	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}
	
	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		try (PrintWriter pw = response.getWriter()) {
			
	        HttpSession hs = request.getSession();
	        User u = (User) hs.getAttribute("user_details");
	        Flight f = (Flight) hs.getAttribute("flight_info");
	        Passengers p = (Passengers) hs.getAttribute("passenger");
	        Card c = (Card) hs.getAttribute("card_info");
	        Integer flightId = (Integer)hs.getAttribute("flight_id");
	        //Integer cardId = (Integer)hs.getAttribute("card_id");
	        Integer userId = (Integer)hs.getAttribute("user_id");
//	        Integer psgnrId = (Integer)hs.getAttribute("passenger_id");
	        
	        int cardId = cardDao.addCard(c);
	        int psgnrId = passengerDao.addPassengers(p);
	        
	        System.out.println("User ID from session: " + userId);
	        System.out.println("Flight ID from session: " + flightId);
	        System.out.println("Passenger ID from session: " + psgnrId);
	        System.out.println("Card ID from session: " + cardId);
	        
	        
//	        int rid = reservationDao.addReservation(f.getPrice(),flightId, psgnrId, userId, cardId);
	        int rid = reservationDao.addReservation(f.getPrice(),userId, flightId, psgnrId, cardId);
	        hs.setAttribute("rid", rid);
			System.out.println("rid "+rid); 
	        Reservation r = reservationDao.displayReservations(rid);
	        hs.invalidate();
	        

	        if(r == null) {
	        	pw.print("<h2>Booking failed</h2>");
	        }
			
	        else {
                // CSS Styles
                pw.println("<style>");
                pw.println("body { background-color: #f5f5f5; padding: 40px; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; }");
                pw.println(".boarding-pass { width: 900px; margin: 0 auto; background-color: white; box-shadow: 0 4px 24px rgba(0, 0, 0, 0.08); border-radius: 16px; overflow: hidden; }");
                pw.println(".header { background-color: #1a365d; color: white; padding: 24px 32px; display: flex; justify-content: space-between; align-items: center; border-bottom: 4px solid #2c5282; }");
                pw.println(".header h2 { margin: 0; font-size: 24px; font-weight: 600; letter-spacing: 1px; }");
                pw.println(".airline-logo { font-size: 22px; font-weight: 700; display: flex; align-items: center; gap: 8px; }");
                pw.println(".main-content { display: flex; padding: 32px; gap: 32px; }");
                pw.println(".section { flex: 1; padding: 0 16px; }");
                pw.println(".section:first-child { border-right: 1px dashed #e2e8f0; }");
                pw.println(".flight-info { display: flex; align-items: center; gap: 24px; margin: 24px 0; padding: 16px 0; border-top: 1px solid #edf2f7; border-bottom: 1px solid #edf2f7; }");
                pw.println(".city { flex: 1; }");
                pw.println(".city-code { font-size: 32px; font-weight: 700; color: #2d3748; letter-spacing: 1px; }");
                pw.println(".city-name { font-size: 14px; color: #718096; margin-top: 4px; }");
                pw.println(".flight-path { flex: 0 0 80px; text-align: center; position: relative; }");
                pw.println(".flight-path::before { content: '✈'; font-size: 24px; color: #2c5282; transform: rotate(90deg); display: inline-block; }");
                pw.println(".flight-path::after { content: ''; position: absolute; top: 50%; left: 0; right: 0; height: 1px; background: #e2e8f0; z-index: -1; }");
                pw.println(".field { margin-bottom: 20px; }");
                pw.println(".label { font-size: 12px; text-transform: uppercase; color: #718096; letter-spacing: 0.5px; margin-bottom: 6px; font-weight: 600; }");
                pw.println(".value { font-size: 16px; color: #2d3748; font-weight: 500; }");
                pw.println(".important .value { font-size: 20px; font-weight: 600; color: #1a365d; }");
                pw.println(".barcode { margin-top: 24px; text-align: center; }");
                pw.println(".barcode-img { height: 80px; background: repeating-linear-gradient(90deg, #000, #000 2px, #fff 2px, #fff 4px); width: 200px; margin: 0 auto; }");
                pw.println(".barcode-number { margin-top: 8px; font-size: 14px; color: #4a5568; font-family: monospace; }");
                pw.println(".footer { background-color: #f7fafc; padding: 16px 32px; border-top: 1px solid #e2e8f0; font-size: 13px; color: #718096; text-align: center; }");
                pw.println(".gate-info { display: flex; gap: 24px; margin-top: 24px; padding: 16px; background-color: #f7fafc; border-radius: 8px; }");
                pw.println(".gate-info .field { margin-bottom: 0; flex: 1; }");
                pw.println(".logout-btn { display: block; width: 200px; margin: 20px auto; padding: 10px; background-color: #1a365d; color: white; text-align: center; border-radius: 5px; text-decoration: none; font-weight: bold; }");
                pw.println(".logout-btn:hover { background-color: #2c5282; }");
                pw.println("</style>");

                // HTML Structure
                pw.println("<div class='boarding-pass'>");
                
                // Header
                pw.println("    <div class='header'>");
                pw.println("        <h2>BOARDING PASS</h2>");
                pw.println("        <div class='airline-logo'>"  + f.getAirline_name() + "</div>");
                pw.println("    </div>");
                
                // Main Content
                pw.println("    <div class='main-content'>");
                
                // Left Section
                pw.println("        <div class='section'>");
                pw.println("            <div class='field important'>");
                pw.println("                <div class='label'>Passenger Name</div>");
                pw.println("                <div class='value'>" + p.getPassengerName().toUpperCase() + "</div>");
                pw.println("            </div>");
                
                // Flight Info with Cities
                pw.println("            <div class='flight-info'>");
                pw.println("                <div class='city'>");
                pw.println("                    <div class='city-code'>" + getAirportCode(f.getDeparture_city()) + "</div>");
                pw.println("                    <div class='city-name'>" + f.getDeparture_city() + "</div>");
                pw.println("                </div>");
//                pw.println("                <div class='flight-path'></div>");
                pw.println("                <div class='city'>");
                pw.println("                    <div class='city-code'>" + getAirportCode(f.getArrival_city()) + "</div>");
                pw.println("                    <div class='city-name'>" + f.getArrival_city() + "</div>");
                pw.println("                </div>");
                pw.println("            </div>");

                pw.println("            <div class='field'>");
                pw.println("                <div class='label'>Flight</div>");
                pw.println("                <div class='value'>" + f.getAirline_name().substring(0, 2).toUpperCase() + " " + flightId + "</div>");
                pw.println("            </div>");

                // Gate Info Section
                pw.println("            <div class='gate-info'>");
                pw.println("                <div class='field'>");
                pw.println("                    <div class='label'>Gate</div>");
                pw.println("                    <div class='value'>B" + (rid % 20 + 1) + "</div>");
                pw.println("                </div>");
                pw.println("                <div class='field'>");
                pw.println("                    <div class='label'>Seat</div>");
                pw.println("                    <div class='value'>" + (rid % 30 + 1) + (char)('A' + (rid % 6)) + "</div>");
                pw.println("                </div>");
                pw.println("                <div class='field'>");
                pw.println("                    <div class='label'>Class</div>");
                pw.println("                    <div class='value'>Economy</div>");
                pw.println("                </div>");
                pw.println("            </div>");
                pw.println("        </div>");

                // Right Section
                pw.println("        <div class='section'>");
                pw.println("            <div class='field'>");
                pw.println("                <div class='label'>Date</div>");
                pw.println("                <div class='value'>" + f.getDeparture_date() + "</div>");
                pw.println("            </div>");
                
                pw.println("            <div class='field'>");
                pw.println("                <div class='label'>Departure Time</div>");
                pw.println("                <div class='value'>" + f.getDeparture_time() + "</div>");
                pw.println("            </div>");

                pw.println("            <div class='field'>");
                pw.println("                <div class='label'>Boarding Time</div>");
                pw.println("                <div class='value'>" + calculateBoardingTime(f.getDeparture_time().toString()	) + "</div>");
                pw.println("            </div>");

                pw.println("            <div class='field'>");
                pw.println("                <div class='label'>Booking Reference</div>");
                pw.println("                <div class='value'>" + String.format("%s%06d", f.getAirline_name().substring(0, 2).toUpperCase(), rid) + "</div>");
                pw.println("            </div>");

                // Barcode
                pw.println("            <div class='barcode'>");
                pw.println("                <div class='barcode-img'></div>");
                pw.println("                <div class='barcode-number'>" + String.format("%04d %04d %04d %04d", rid, flightId, psgnrId, cardId) + "</div>");
                pw.println("            </div>");
                pw.println("        </div>");
                pw.println("    </div>");

                // Footer
                pw.println("    <div class='footer'>");
                pw.println("        <p>Please be at the gate 45 minutes before departure. Boarding ends 15 minutes before departure.</p>");
                pw.println("    </div>");
                
             // Logout Button
                pw.println("</div>");
                pw.println("    <a href='index.html' class='logout-btn'>Logout</a>");
            }
        } catch(Exception e) {
            throw new ServletException("error in do-get of " + getClass(), e);
        }
    }

    // Helper method to calculate boarding time (45 minutes before departure)
    private String calculateBoardingTime(String departureTime) {
        try {
            String[] parts = departureTime.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            
            minutes -= 45;
            if (minutes < 0) {
                minutes += 60;
                hours -= 1;
                if (hours < 0) hours = 23;
            }
            
            return String.format("%02d:%02d", hours, minutes);
        } catch (Exception e) {
            return "N/A";
        }
    }

    // Helper method to generate airport code (simplified version)
    private String getAirportCode(String cityName) {
        if (cityName == null || cityName.length() < 3) return "XXX";
        return cityName.substring(0, 3).toUpperCase();
    }
}
	

