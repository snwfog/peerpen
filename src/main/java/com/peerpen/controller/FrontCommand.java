package com.peerpen.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand
{
  protected ServletContext context;
  protected HttpServletRequest request;
  protected HttpServletResponse response;
  protected String localhostURL;

  public void init(ServletContext servletContext, HttpServletRequest request,
      HttpServletResponse response)
  {
    this.context = servletContext;
    this.request = request;
    this.response = response;
    this.localhostURL = "http://" + request.getServerName() + ":"
        + request.getServerPort() + "/PeerPen";

  }

  abstract public void processForward() throws ServletException, IOException;

  abstract public void processRedirect() throws ServletException, IOException;

  protected void forward(String target) throws ServletException, IOException
  {
    RequestDispatcher dispatcher = context.getRequestDispatcher(target);
    dispatcher.forward(request, response);
  }

  protected void redirect(String target) throws ServletException, IOException
  {
    response.sendRedirect(localhostURL + target);
  }

}