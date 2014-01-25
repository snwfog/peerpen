package com.peerpen.ajax;

import com.peerpen.helper.Email;
import com.peerpen.model.Peer;
import org.apache.commons.lang3.RandomStringUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: bobbyyit
 * Date: 2013-11-15
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */

public class ResetControllerAjax extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    String email = request.getParameter("email");
    String message;

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("email", email);
    List<Peer> matches = new Peer().findAll(map);

    if(matches.size() == 1)
    {
      String password = getRandomPassword();
      Peer pear = matches.get(0);

      pear.setPassword(password);
      pear.update();

      boolean status = Email.email(pear.getEmail(), pear.getFirstName(), password);

      message = "True";
    }
    else{
      message = "False";
    }

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
  }

  private String getRandomPassword() {
    return RandomStringUtils.randomAlphabetic(2) + RandomStringUtils.randomAlphanumeric(7);
  }
}
