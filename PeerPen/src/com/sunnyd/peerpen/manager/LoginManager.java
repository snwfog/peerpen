package com.sunnyd.peerpen.Manager;

import java.io.IOException;
import javax.servlet.ServletException;

import com.sunnyd.peerpen.Controller.FrontCommand;

public class LoginManager extends FrontCommand {

//	@Override
	public void processForward() throws ServletException, IOException {
//		User user = User.findUser("bob", "hilo");// request.getParameter("name")
//		request.setAttribute("user", new User(user));
		forward("/Views/Login.jsp");

	}
	public void processRedirect() throws ServletException, IOException {
		
	
	}
}
