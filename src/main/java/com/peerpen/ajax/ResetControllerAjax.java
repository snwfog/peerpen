package com.peerpen.ajax;

import com.peerpen.model.Peer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResetControllerAjax extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    String email = request.getParameter("email");
    String message;

    Map<String, Object> hm = new HashMap<>();
    hm.put("email", email);
    Peer pear;

    if ((pear = new Peer().find(hm)) != null)
    {
      pear.resetPassword();
      pear.update();

      message = "True";
    }
    else
    {
      message = "False";
    }

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(message);
  }

}
