package com.frs.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.frs.pojos.User;

@WebServlet("/FlightSearch")
public class FlightSearch extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		try(PrintWriter pw = resp.getWriter()){
			HttpSession session = req.getSession();
			User user = (User)session.getAttribute("user_details");
			
			pw.print("<html>");
			pw.print("<head><title>Flight Search</title></head>");
			pw.print("<body style=\""
			        + "background-color: #f4f4f4;"
			        + "font-family: Arial, sans-serif;"
			        + "display: flex;"
			        + "align-items: center;"
			        + "justify-content: center;"
			        + "min-height: 100vh;"
			        + "margin: 0;"
			        + "\">");

			pw.print("<div style=\""
			        + "background-color: #fff;"
			        + "padding: 30px 40px;"
			        + "border-radius: 10px;"
			        + "box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);"
			        + "width: 400px;"
			        + "\">");

			pw.print("<h1 style=\""
			        + "text-align: center;"
			        + "color: #333;"
			        + "font-size: 1.8rem;"
			        + "margin-bottom: 0.5rem;"
			        + "\">Hello " + user.getFirstName() + " " + user.getLastName() + ", </h1>");

			pw.print("<h3 style=\""
			        + "text-align: center;"
			        + "text-decoration: underline;"
			        + "color: #555;"
			        + "margin-bottom: 1.5rem;"
			        + "\">Search your Flights</h3>");

			pw.print("<form action=\"FlightSearchServlet\" method=\"post\">");

			pw.print("<table style=\"width: 100%; color: #333;\">");

			pw.print("<tr>"
			        + "<td style=\"padding: 8px 0; text-align: left;\">From:</td>"
			        + "<td style=\"padding: 8px 0;\"><input type=\"text\" name=\"departure\" style=\""
			        + "width: 100%;"
			        + "padding: 8px;"
			        + "border: 1px solid #ccc;"
			        + "border-radius: 4px;"
			        + "box-sizing: border-box;"
			        + "\" /></td>"
			        + "</tr>");

			pw.print("<tr>"
			        + "<td style=\"padding: 8px 0; text-align: left;\">To:</td>"
			        + "<td style=\"padding: 8px 0;\"><input type=\"text\" name=\"arrival\" style=\""
			        + "width: 100%;"
			        + "padding: 8px;"
			        + "border: 1px solid #ccc;"
			        + "border-radius: 4px;"
			        + "box-sizing: border-box;"
			        + "\" /></td>"
			        + "</tr>");

			pw.print("<tr>"
			        + "<td style=\"padding: 8px 0; text-align: left;\">Departure Date:</td>"
			        + "<td style=\"padding: 8px 0;\"><input type=\"date\" name=\"date\" style=\""
			        + "width: 100%;"
			        + "padding: 8px;"
			        + "border: 1px solid #ccc;"
			        + "border-radius: 4px;"
			        + "box-sizing: border-box;"
			        + "\" /></td>"
			        + "</tr>");

			pw.print("<tr>"
			        + "<td colspan=\"2\" style=\"padding-top: 20px;\">"
			        + "<input type=\"submit\" value=\"Search Flight\" style=\""
			        + "width: 100%;"
			        + "padding: 10px;"
			        + "background-color: #007bff;"
			        + "color: white;"
			        + "border: none;"
			        + "border-radius: 4px;"
			        + "font-size: 1rem;"
			        + "cursor: pointer;"
			        + "\" />"
			        + "</td>"
			        + "</tr>");

			pw.print("</table>");
			pw.print("</form>");
			pw.print("</div>");
			pw.print("</body>");
			pw.print("</html>");

			
		}
		catch(Exception e) {
			throw new ServletException("err in do-get of " + getClass(),e);
		}
	}
}
