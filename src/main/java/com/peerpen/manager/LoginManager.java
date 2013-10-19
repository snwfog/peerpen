package com.sunnyd.peerpen.Manager;
import com.sunnyd.peerpen.Model.User;
import java.io.IOException;

import javax.servlet.ServletException;

import com.sunnyd.peerpen.Controller.FrontCommand;

public class LoginManager extends FrontCommand {

	// @Override
	public void processForward() throws ServletException, IOException {
		forward("/Views/Login.jsp");

	}

	public void processRedirect() throws ServletException, IOException {
		// response.sendRedirect("/FrontPage");
		redirect("/FrontPage");

	}
	
	public Boolean validateUser(String username, String password){
		if(username.equals("Bobby") && password.equals("Yit")){
			return true;
		}
		else return false;
	}
	public User findUser(String username, String password){
		// TODO change this related to database
		if(username.equals("Bobby") && password.equals("Yit"))
			return new User(username,password);
		else
			return null;
	}
}
