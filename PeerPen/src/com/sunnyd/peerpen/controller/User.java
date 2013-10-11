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
@WebServlet(urlPatterns = { "/User", "/User/new", "/User/Profile", "/User/EditProfile" })
public class User extends SuperBass {
	final static String UserProfileURI = "/PeerPen/User/Profile";
	final static String UserNewURI = "/PeerPen/User/new";
	final static String UserEditURI = "/PeerPen/User/EditProfile";

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

		UserManager usermanager = new UserManager();
		usermanager.init(getServletContext(), request, response);
		if (request.getRequestURI().contentEquals(UserNewURI)) {
			usermanager.createPeerPage();
		} else if (request.getRequestURI().contentEquals(UserProfileURI)) {
			usermanager.viewUser("INSERT USERNAME OR ID HERE FROM SESSION");
		} else if (request.getRequestURI().contentEquals(UserEditURI)) {
			usermanager.editUser();
		}

//		System.out.println(request.getRequestURL());
//		System.out.println(request.getRequestURI());
//		System.out.println(request.getParameter("user"));
//		System.out.println(request.getProtocol());
//
//		System.out.println(request.getServerName() + ":"
//				+ request.getServerPort() + "/");
		System.out.println("--------------------");
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
		com.sunnyd.peerpen.domain.User user = um.createPeer(first_name,
				last_name, sex, website, user_name, email, password);

		um.creationUser("1");
	}


}