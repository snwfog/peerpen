package com.sunnyd.peerpen.controller;

import java.io.IOException;

import javax.servlet.ServletException;

public class UnknownCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		forward("/Views/Login.jsp");

	}
}
