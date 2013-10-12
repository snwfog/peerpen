package com.sunnyd.peerpen.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SuperBase extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7273037356425614164L;
	protected HttpSession session = null;
	private String hello(){
		return "teset";
	}
	
	protected boolean sessionExists(HttpServletRequest request) throws ServletException, IOException{
		session = request.getSession(false);
		if(session == null)
			return false;
		
		else if (session.getAttribute("user") == null)
				return false;
			
		return true;
	}
	protected FrontCommand getCommand(String manager) {
		try {
			return (FrontCommand) getCommandClass(manager).newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Class<?> getCommandClass(String controller) {
		Class<?> result;
		final String commandClassName = "com.sunnyd.peerpen.Manager."
				+ controller + "Manager";
		try {
			result = Class.forName(commandClassName);
		} catch (ClassNotFoundException e) {
			result = UnknownCommand.class;
		}
		return result;
	}
}
	
