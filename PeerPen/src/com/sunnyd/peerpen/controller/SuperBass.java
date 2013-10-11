package com.sunnyd.peerpen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SuperBass extends HttpServlet{
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
}
	
