package com.sunnyd.peerpen.Controller;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {

	@Override
	public void processForward() throws ServletException, IOException {
		forward("/Views/missingData.jsp");

	}

	public void processRedirect() throws ServletException, IOException {

	}
}
