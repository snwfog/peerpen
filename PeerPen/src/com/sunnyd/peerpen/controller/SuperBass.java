package com.sunnyd.peerpen.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public class SuperBass extends HttpServlet{
	
	private String hello(){
		return "teset";
	}
	
	protected boolean sessionExists(HttpSession session){
		if(session.isNew())
			return false;
		else
			return true;
	}
	
}