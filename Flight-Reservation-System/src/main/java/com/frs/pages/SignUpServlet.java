package com.frs.pages;

import static com.frs.utils.DBUtils.closeConnection;
import static com.frs.utils.DBUtils.openConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frs.dao.UserDao;
import com.frs.dao.UserDaoImpl;
import com.frs.pojos.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("In init of " + getClass());
		
		try {
			openConnection();
			
			userDao = new UserDaoImpl();
			System.out.println(getClass() + "inited !");
		}
		catch(Exception e) {
			throw new ServletException("error in init of " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("in destroy" + getClass());
		
		try {
			userDao.cleanUp();
			closeConnection();
		}
		catch(Exception e) {
			throw new RuntimeException("err in destroy " + getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			String firstname = request.getParameter("fn");
			String lastname = request.getParameter("ln");
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			Date dateofbirth = Date.valueOf(request.getParameter("dob"));
			User u = new User(firstname,lastname,email,password,dateofbirth);
			userDao.signUp(u);
			
			if(u == null)
				pw.print("<h5>Invalid Details,  " + "Please <a href='login.html'>Try again...</a></h5>");
			else {
				response.sendRedirect("signup_success");
			}
		}
		catch(Exception e) {
			throw new ServletException("error in do-post of " + getClass(), e);
		}
	}

}
