package com.sunnyd.peerpen.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import com.sunnyd.peerpen.controller.FrontCommand;

public class UserManager extends FrontCommand {

	// @Override
	public void process() throws ServletException, IOException {
//		 User user = User.findUser("bob", "hilo");//
//		 request.getParameter("name")
//		 request.setAttribute("user", new User(user));
		forward("/Views/Login.jsp");

	}

	public void createUser() throws ServletException, IOException {
		System.out.println("create new user");
		forward("/Views/NewUser.jsp");
	}
	
	public void viewUser(String id) throws ServletException, IOException {
		System.out.println("show user");
//		 User user = User.findUser("bob", "hilo");//
//		 request.getParameter("name")
//		 request.setAttribute("user", new User(user));
		forward("/Views/User.jsp");
	}
}
