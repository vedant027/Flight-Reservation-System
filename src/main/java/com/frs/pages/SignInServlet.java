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

import com.frs.dao.UserDao;
import com.frs.dao.UserDaoImpl;
import com.frs.pojos.User;


@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;

	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("In init of " + getClass());
		
		try {
			openConnection();
			
			userDao = new UserDaoImpl();
			System.out.println(getClass() + " inited! ");
		}
		catch(Exception e) {
			throw new ServletException("Error in init of " + getClass(), e);
		}
	}

	
	public void destroy() {
		System.out.println("in destroy" + getClass());
		
		try {
			userDao.cleanUp();
			
			closeConnection();
		}
		catch(Exception e) {
			throw new RuntimeException("err in destroy of " + getClass(),e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			
			User user = userDao.signIn(email, password);
			if(user == null) {
				pw.print("<h5>Invalid Login, " + "Please <a href='signin.html'>Retry</a></h5>");
			}
			else {
//				Cookie c1 = new Cookie("user_details",user.getFirstName() + " " + user.getLastName());
//				response.addCookie(c1);
				HttpSession session = request.getSession();
				session.setAttribute("user_details", user);
				session.setAttribute("user_id", user.getUserId());
				response.sendRedirect("FlightSearch");
			}
		}
		catch(Exception e) {
			throw new ServletException("err in do-post of " + getClass(), e);
		}
	}

}
