package com.sunnyd.peerpen.Manager;

import java.io.IOException;

import javax.servlet.ServletException;

import com.sunnyd.peerpen.Controller.FrontCommand;

public class FrontPageManager extends FrontCommand {

	@Override
	public void processForward() throws ServletException, IOException {
		// TODO Auto-generated method stub

		forward("/Views/FrontPage.jsp");
	}

	@Override
	public void processRedirect() throws ServletException, IOException {
		// TODO Auto-generated method stub

		forward("/Views/Login.jsp");
	}
	
}