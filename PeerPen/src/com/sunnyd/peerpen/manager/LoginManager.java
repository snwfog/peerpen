package com.sunnyd.peerpen.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import com.sunnyd.peerpen.controller.FrontCommand;

public class LoginManager extends FrontCommand {

//	@Override
	public void processForward() throws ServletException, IOException {
		forward("/Views/Login.jsp");

	}
	public void processRedirect() throws ServletException, IOException {
		
	
	}
}
