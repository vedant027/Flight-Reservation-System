package com.frs.pages;

import static com.frs.utils.DBUtils.closeConnection;
import static com.frs.utils.DBUtils.openConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.frs.dao.CardDao;
import com.frs.dao.CardDaoImpl;
import com.frs.pojos.Card;


@WebServlet("/CardDetailsServlet")
public class CardDetailsServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	
	private CardDao cardDao;


	public void init(ServletConfig config) throws ServletException {
		try {
			openConnection();
			cardDao = new CardDaoImpl();
		}
		catch(Exception e) {
			throw new ServletException("error in init of " + getClass(),e);
		}
	}

	
	public void destroy() {
		try {
			cardDao.cleanup();
			closeConnection();
		}
		catch(Exception e) {
			throw new RuntimeException("err in destroy of " + getClass(), e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			String cardNo = request.getParameter("cardno");
			String cardHolderName = request.getParameter("cardholdername");
			Date expDate = Date.valueOf(request.getParameter("expdate"));
			String cvv = request.getParameter("cvv");
			
			Card c = new Card(cardNo,cardHolderName,expDate,cvv);
//			cardDao.addCard(c);
			
			HttpSession hs = request.getSession();
			hs.setAttribute("card_info", c);
			hs.setAttribute("card_id", c.getCardId());
			response.sendRedirect("ReservationServlet");		
			
		}
		catch(Exception e) {
			throw new ServletException("err in do-post of " + getClass(), e);
		}
	}

}
