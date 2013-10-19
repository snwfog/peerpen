package com.peerpen.manager;

import com.peerpen.controller.FrontCommand;
import com.peerpen.model.User;

import javax.servlet.ServletException;
import java.io.IOException;


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
