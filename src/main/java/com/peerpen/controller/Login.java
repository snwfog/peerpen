package com.peerpen.controller;

import com.peerpen.manager.LoginManager;
import com.peerpen.manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class FrontServlet
 */
public class Login extends SuperBase
{
  final static String LoginURI = "/login";
  final static String LogoutURI = "/logout";
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Login()
  {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException
  {
    FrontCommand command;
    if (!sessionExists(request))
      command = getCommand("login");
    else if ((request.getRequestURI().contentEquals(LogoutURI)))
      command = getCommand("logout");
    else
      command = getCommand("login");
    command.init(getServletContext(), request, response);
    command.processForward();
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException
  {


    try
    {
      LoginManager user = new LoginManager();
      String username = request.getParameter("un");
      String password = request.getParameter("pw");

      //		     user = UserDAO.login(user);

      if (user.validateUser(username, password))
      {

        HttpSession session = request.getSession(true);
//				 session.setMaxInactiveInterval(60*60*24*14);
        session.setMaxInactiveInterval(15);
        System.out.println("default timeout period for sessions : " + session.getMaxInactiveInterval());
        session.setAttribute("user", user.findUser(username, password));
        LoginManager logging = new LoginManager();
        logging.init(getServletContext(), request, response);
        logging.processRedirect();

      }

      else
      {
        UserManager newUser = new UserManager();
        newUser.init(getServletContext(), request, response);
        newUser.processRedirect();

      }
    }


    catch (Throwable theException)
    {
      System.out.println(theException);
    }
  }

}