package com.sunnyd.peerpen.Manager;

import java.io.IOException;

import javax.servlet.ServletException;

import com.sunnyd.peerpen.Controller.FrontCommand;

public class FrontPageManager extends FrontCommand {

	public void processForward() throws ServletException, IOException {
		// User user = User.findUser("bob", "hilo");//
		// request.getParameter("name")
		// request.setAttribute("user", new User(user));

		String redirectURL = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + "/" + "PeerPen/Login";
		response.sendRedirect(redirectURL);
	}

	public void processRedirect() throws ServletException, IOException {
		redirect("/FrontPage");

	}

}
