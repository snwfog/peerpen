package com.sunnyd.peerpen.Controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunnyd.peerpen.Manager.SessionManager;
import com.sunnyd.peerpen.Manager.UserManager;
import com.sunnyd.peerpen.Model.User;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet(urlPatterns = { "/","/Login","/Logout"})
public class Login extends SuperBase {
	final static String LoginURI = "/PeerPen/Login";
	final static String LogoutURI = "/PeerPen/Logout";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FrontCommand command;
		if(!sessionExists(request))
			command = getCommand("Login");
		else if((request.getRequestURI().contentEquals(LogoutURI)))
			command = getCommand("Logout");
		else
			command = getCommand("Login");
		command.init(getServletContext(), request, response);
		command.processForward();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		try
		{	    
			 User user = new User();
			 user.setUserName(request.getParameter("un"));
			 user.setPassword(request.getParameter("pw"));
			 
			//		     user = UserDAO.login(user);
			 user.setValid(true);
			   		    
			 if (user.isValid())
			 {
				 
				 
				 HttpSession session = request.getSession(true);
				 session.setMaxInactiveInterval(60*60*24*14);
				 System.out.println("default timeout period for sessions : " + session.getMaxInactiveInterval());
				 session.setAttribute("user",user); 
				 response.sendRedirect("http://localhost:8080/PeerPen/FrontPage");
				
		     }
			        
		     else 
		    	 request.getRequestDispatcher("/Views/missingData.jsp").forward(request, response);//error page 
		} 
				
				
		catch (Throwable theException) 	    
		{
		     System.out.println(theException); 
		}
	}

}