package com.sunnyd.peerpen.Manager;

import java.io.IOException;

import javax.servlet.ServletException;

import com.sunnyd.peerpen.Controller.FrontCommand;

public class LogoutManager extends FrontCommand {

	@Override
	public void processForward() throws ServletException, IOException {
		forward("/Views/Logout.jsp");

	}

	public void processRedirect() throws ServletException, IOException {

	}
}
