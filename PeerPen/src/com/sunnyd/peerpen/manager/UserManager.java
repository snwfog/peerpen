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

	public void createPeerPage() throws ServletException, IOException {
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

	
	public void createPeer(String first_name, String last_name, String sex,
			String website, String user_name, String email, String password) throws ServletException, IOException{
		
		forward("dsfads");
		
	}
}
