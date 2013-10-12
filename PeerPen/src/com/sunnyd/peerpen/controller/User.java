package com.sunnyd.peerpen.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunnyd.peerpen.Manager.UserManager;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet(urlPatterns = { "/User", "/User/new", "/User/Profile",
		"/User/EditProfile" })
public class User extends SuperBase {
	final static String UserProfileURI = "/PeerPen/User/Profile";
	final static String UserNewURI = "/PeerPen/User/new";
	final static String UserEditURI = "/PeerPen/User/EditProfile";

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public User() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println((String) request.getParameter("command"));
		if ((String) request.getParameter("command") != null) {
//			make method to JUMP controller from the command
//			 FrontCommand command;
//			 command = getCommand(request.getParameter("command"));
//			 command.init(getServletContext(), request, response);
//			 command.processForward();

//			FrontCommand login;
//			login = getCommand("FrontPage");
//			login.init(getServletContext(), request, response);
//			login.processRedirect();
		}

		UserManager usermanager = new UserManager();
		usermanager.init(getServletContext(), request, response);
		if (request.getRequestURI().contentEquals(UserNewURI)) {
			usermanager.createPeerPage();
		} else if (request.getRequestURI().contentEquals(UserProfileURI)) {
			usermanager.viewUser("INSERT USERNAME OR ID HERE FROM SESSION");
		} else if (request.getRequestURI().contentEquals(UserEditURI)) {
			usermanager.editUserView();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String method = request.getParameter("method");

		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String sex = request.getParameter("sex");
		String website = request.getParameter("personal_website");
		String user_name = request.getParameter("user_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserManager usermanager = new UserManager();
		usermanager.init(getServletContext(), request, response);
		if (method != null) {
			usermanager.editUser(first_name, last_name, sex, website,
					user_name, email, password);
		} else {
			usermanager.creationUser(first_name, last_name, sex, website,
					user_name, email, password);
		}
	}
}