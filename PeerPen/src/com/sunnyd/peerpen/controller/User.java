package com.sunnyd.peerpen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunnyd.peerpen.manager.UserManager;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet(urlPatterns = { "/User", "/User/new" })
public class User extends HttpServlet {
	final static String UserURI = "/PeerPen/User";
	final static String UserNewURI = "/PeerPen/User/new";

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if(request.getRequestURI().contentEquals(UserNewURI)){
//			show create user page
			UserManager um = new UserManager();
			um.init(getServletContext(), request, response);
			um.createUser();
		}
		else if(request.getRequestURI().contentEquals(UserURI)){
//			show user page?
//			get user stuff id and show stuff
		}
		
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		System.out.println(request.getParameter("user"));

//		FrontCommand command;
//		command = getCommand(request);
//		command.init(getServletContext(), request, response);
//		command.process();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("halalalalalalalalalal!!!!!!!!!!!!!!!!!!!!!!!!!!!! powpowpowpowpowpowpowpowpowpow!!!");

	}

}