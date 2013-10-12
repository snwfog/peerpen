package com.sunnyd.peerpen.Controller;

import java.io.IOException;

import javax.servlet.ServletException;

public class UnknownCommand extends FrontCommand {

	@Override
	public void processForward() throws ServletException, IOException {
		forward("/Views/missingData.jsp");

	}
	public void processRedirect() throws ServletException, IOException {
		
		
	}
}
