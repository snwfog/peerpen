package com.peerpen.controller;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand
{

  @Override
  public void processForward() throws ServletException, IOException
  {
    forward("/views/error.jsp");

  }

  public void processRedirect() throws ServletException, IOException
  {

  }
}
