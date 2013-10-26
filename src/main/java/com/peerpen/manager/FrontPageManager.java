package com.peerpen.manager;

import com.peerpen.controller.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;


public class FrontPageManager extends FrontCommand
{

  @Override
  public void processForward() throws ServletException, IOException
  {
    // TODO Auto-generated method stub

    //forward("/Views/FrontPage.jsp");
  }

  @Override
  public void processRedirect() throws ServletException, IOException
  {
    // TODO Auto-generated method stub

    forward("/Views/login.jsp");
  }

}