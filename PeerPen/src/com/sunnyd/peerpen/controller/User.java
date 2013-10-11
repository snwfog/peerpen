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
public class User extends SuperBass {
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

		if (request.getRequestURI().contentEquals(UserNewURI)) {
			// show create user page
			UserManager um = new UserManager();
			um.init(getServletContext(), request, response);
			um.createPeerPage();
		} else if (request.getRequestURI().contentEquals(UserURI)) {
			// show user page?
			// get user stuff id and show stuff
		}

		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		System.out.println(request.getParameter("user"));
		System.out.println(request.getProtocol());

		System.out.println(request.getServerName() + ":"
				+ request.getServerPort() + "/");
		System.out.println("--------------------");

		// FrontCommand command;
		// command = getCommand(request);
		// command.init(getServletContext(), request, response);
		// command.process();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String sex = request.getParameter("sex");
		String website = request.getParameter("personal_website");
		String user_name = request.getParameter("user_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserManager um = new UserManager();
		um.init(getServletContext(), request, response);
		boolean status = um.createPeer(first_name, last_name, sex, website,
				user_name, email, password);

		FrontCommand command;
		command = getCommand("FrontPage");
		command.init(getServletContext(), request, response);
		command.process();

	}

	private FrontCommand getCommand(String manager) {
		try {
			return (FrontCommand) getCommandClass(manager).newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Class<?> getCommandClass(String controller) {
		Class<?> result;
		final String commandClassName = "com.sunnyd.peerpen.manager."
				+ controller + "Manager";
		try {
			result = Class.forName(commandClassName);
		} catch (ClassNotFoundException e) {
			result = UnknownCommand.class;
		}
		return result;
	}

}