package com.sunnyd.peerpen.manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import com.sunnyd.peerpen.controller.FrontCommand;
import com.sunnyd.peerpen.domain.User;

public class FrontPageManager extends FrontCommand {

	public void process() throws ServletException, IOException {
		// User user = User.findUser("bob", "hilo");//
		// request.getParameter("name")
		// request.setAttribute("user", new User(user));

		String redirectURL = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + "/" + "PeerPen/Login";
		response.sendRedirect(redirectURL);
	}

}
