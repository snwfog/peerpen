package com.sunnyd.peerpen.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.sunnyd.peerpen.Controller.FrontCommand;
import com.sunnyd.peerpen.Model.User;

public class UserManager extends FrontCommand {

	public void processForward() throws ServletException, IOException {
		forward("/Views/Login.jsp");
	}

	public void processRedirect() throws ServletException, IOException {
		redirect("/User/new");

	}

	public void createPeerPage() throws ServletException, IOException {
		forward("/Views/NewUser.jsp");
	}

	public void viewUser(String id) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
//		session.setAttribute("user", user);

		forward("/Views/Profile.jsp");
	}

	/**
	 * Retrieves 'user' from session
	 * 
	 */
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

	/**
	 * Excepts user info create object to be create, then set 'user' to Session
	 * to be viewed when redirect to profile page
	 * 
	 */
	public void creationUser(String first_name, String last_name, String sex,
			String website, String user_name, String email, String password)
			throws ServletException, IOException {
		User user = new User(first_name, last_name, sex, website, user_name,
				email, password);

		HttpSession session = request.getSession(true);
		session.setAttribute("user", user);

		redirect("/User/Profile");
	}

	public void editUser(String first_name, String last_name, String sex,
			String website, String user_name, String email, String password)
			throws ServletException, IOException {
		User user = new User(first_name, last_name, sex, website, user_name,
				email, password);

		HttpSession session = request.getSession(true);
		session.setAttribute("user", user);

		redirect("/User/Profile");
	}
}
