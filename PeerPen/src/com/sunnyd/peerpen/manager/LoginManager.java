package com.sunnyd.peerpen.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import com.sunnyd.peerpen.controller.FrontCommand;
import com.sunnyd.peerpen.domain.User;

public class LoginManager extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		User user = User.findUser("bob", "hilo");// request.getParameter("name")
		request.setAttribute("user", new User(user));
		forward("/Views/Login.jsp");

	}
}
