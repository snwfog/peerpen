package com.peerpen.manager;

import com.peerpen.controller.FrontCommand;

import java.io.IOException;

import javax.servlet.ServletException;

public class LogoutManager extends FrontCommand
{

	@Override
	public void processForward() throws ServletException, IOException {
		forward("/Views/Logout.jsp");

	}

	public void processRedirect() throws ServletException, IOException {

	}
}
