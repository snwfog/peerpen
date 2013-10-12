package com.sunnyd.peerpen.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.sunnyd.peerpen.Controller.FrontCommand;
import com.sunnyd.peerpen.Model.User;

public class UserManager extends FrontCommand {

	// @Override
	public void processForward() throws ServletException, IOException {
		// User user = User.findUser("bob", "hilo");//
		// request.getParameter("name")
		// request.setAttribute("user", new User(user));
		forward("/Views/Login.jsp");

	}

	public void processRedirect() throws ServletException, IOException {

	}

	public void createPeerPage() throws ServletException, IOException {
		forward("/Views/NewUser.jsp");
	}

	public void viewUser(String id) throws ServletException, IOException {

		User user = new User("John", "Smith", "Shemale", "www.peerpen.com",
				"i_like_turtles", "steve.jobs@apple.com", "root");

		HttpSession session = request.getSession(true);
		session.setAttribute("user", user);

		forward("/Views/Profile.jsp");
	}

	public void editUserView() throws ServletException, IOException {
		forward("/Views/EditUser.jsp");
	}

	public User createPeer(String first_name, String last_name, String sex,
			String website, String user_name, String email, String password)
			throws ServletException, IOException {

		User user = new User(first_name, last_name, sex, website, user_name,
				email, password);

		return user;
	}

	public void creationUser(String first_name, String last_name, String sex,
			String website, String user_name, String email, String password)
			throws ServletException, IOException {
		System.out.println("show user");
		User user = new User(first_name, last_name, sex, website, user_name,
				email, password);

		HttpSession session = request.getSession(true);
		session.setAttribute("user", user);

		String redirectURL = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + "/" + "PeerPen/User/Profile";

		response.sendRedirect(redirectURL);
	}

	public void editUser(String first_name, String last_name, String sex,
			String website, String user_name, String email, String password)
			throws ServletException, IOException {
		User user = new User(first_name, last_name, sex, website, user_name,
				email, password);

		HttpSession session = request.getSession(true);
		session.setAttribute("user", user);

		String redirectURL = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + "/" + "PeerPen/User/Profile";

		response.sendRedirect(redirectURL);

	}
}
