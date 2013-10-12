package com.sunnyd.peerpen.Manager;

import java.io.IOException;

import javax.servlet.ServletException;

import com.sunnyd.peerpen.Controller.FrontCommand;

public class SessionManager extends FrontCommand {

	@Override
	public void processForward() throws ServletException, IOException {
		// TODO Auto-generated method stub

		forward("/Views/Session.jsp");
	}

	@Override
	public void processRedirect() throws ServletException, IOException {
		// TODO Auto-generated method stub

		forward("/Views/Login.jsp");
	}
}