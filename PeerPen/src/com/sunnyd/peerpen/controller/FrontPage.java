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
@WebServlet(urlPatterns = { "/FrontPage"})
public class FrontPage extends SuperBass {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FrontCommand login;
		login = getCommand(request);
		login.init(getServletContext(), request, response);
		if(sessionExists(request)){
			login.processForward();
		}
		else{
			login.processRedirect();
		}
	
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
				+ "SessionManager";
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
		
		
	}

}