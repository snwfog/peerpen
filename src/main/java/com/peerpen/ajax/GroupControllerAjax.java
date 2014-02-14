package com.peerpen.ajax;

import com.peerpen.framework.GenericApplicationServlet;
import com.peerpen.framework.InternalRequestDispatcher;
import com.peerpen.framework.exception.ServletPathDontExistException;
import com.peerpen.model.Group;
import com.peerpen.model.Peer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class GroupControllerAjax extends GenericApplicationServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
    Group group = new Group().find(Integer.parseInt(parameters.get("groupid")));
    group.addPeer(peer);
    group.update();

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(String.format(getLeaveForm(), group.getId(), group.getId(), peer.getId(), group.getId()));
  }

  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
    Group group = new Group().find(Integer.parseInt(parameters.get("groupid")));
    group.removePeer(peer);
    group.update();

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(String.format(getJoinForm(), group.getId(), group.getId(), peer.getId(), group.getId()));
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    try
    {
      throw new ServletPathDontExistException("Path does not exit");
    }
    catch (ServletPathDontExistException e)
    {
      ((InternalRequestDispatcher) request.getRequestDispatcher("/error")).forwardError(request, response, e);
    }
  }

  private String getJoinForm()
  {
    return "<form action=\"\" class=\"form-horizontal\" id=\"%s\" role=\"form\">\n" +
        "              <input type=\"hidden\" name=\"groupid\" value=\"%s\">\n" +
        "              <input type=\"hidden\" name=\"peerid\" value=\"%s\">\n" +
        "              <button type=\"button\" class=\"btn btn-primary\" onclick=\"join(%s);\" >Join!</button>\n" +
        "            </form>";
  }

  private String getLeaveForm()
  {
    return "<form action=\"\" class=\"form-horizontal\" id=\"%s\" role=\"form\">\n" +
        "              <input type=\"hidden\" name=\"groupid\" value=\"%s\">\n" +
        "              <input type=\"hidden\" name=\"peerid\" value=\"%s\">\n" +
        "              <input type=\"hidden\" name=\"_method\" value=\"delete\">\n" +
        "              <button type=\"button\" class=\"btn btn-success\" onclick=\"leave(%s);\" ><i class=\"fa fa-check-circle\"></i> Joined!</button>\n" +
        "            </form>";
  }

}
