package com.sunnyd.peerpen.manager;

import java.io.IOException;

import javax.servlet.ServletException;

import com.sunnyd.peerpen.controller.FrontCommand;
import com.sunnyd.peerpen.domain.User;

public class LogoutManager extends FrontCommand {

	@Override
	public void processForward() throws ServletException, IOException {
		forward("/Views/Logout.jsp");

	}
	public void processRedirect() throws ServletException, IOException {
		
		
	}
}
