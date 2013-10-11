package com.sunnyd.peerpen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunnyd.peerpen.domain.User;
import com.sunnyd.peerpen.manager.SessionManager;
import com.sunnyd.peerpen.manager.UserManager;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet(urlPatterns = { "/","/Login"})
public class Login extends HttpServlet {
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
		command = getCommand(request);
		command.init(getServletContext(), request, response);
		command.processForward();
	}

	private FrontCommand getCommand(HttpServletRequest request) {
		try {
			return (FrontCommand) getCommandClass(request).newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Class<?> getCommandClass(HttpServletRequest request) {
		Class<?> result;
		final String commandClassName = "com.sunnyd.peerpen.manager."
				+"LoginManager";
		try {
			result = Class.forName(commandClassName);
		} catch (ClassNotFoundException e) {
			result = UnknownCommand.class;
		}
		return result;
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