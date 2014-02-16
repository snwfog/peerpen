package com.peerpen.ajax;

import com.google.common.collect.Maps;
import com.peerpen.framework.GenericApplicationServlet;
import com.peerpen.framework.InternalRequestDispatcher;
import com.peerpen.framework.exception.ServletPathDontExistException;
import com.peerpen.model.Group;
import com.peerpen.model.Joingroup;
import com.peerpen.model.Peer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class GroupControllerAjax extends GenericApplicationServlet
{
  // join
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
    Group group = new Group().find(Integer.parseInt(parameters.get("groupid")));
    Joingroup jg = new Joingroup();
    jg.setPeerId(peer.getId());
    jg.setGroupId(group.getId());
    jg.save();

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(String.format(getPendingForm(), group.getId(), group.getId(), peer.getId(), group.getId()));
  }

  // leave
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

  // cancel
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
    Group group = new Group().find(Integer.parseInt(parameters.get("groupid")));

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("peerId", peer.getId());
    hm.put("groupId", group.getId());
    Joingroup joingroup = new Joingroup().find(hm);
    joingroup.destroy();

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

  private String getPendingForm()
  {
    return "<form action=\"\" class=\"form-horizontal\" id=\"%s\" role=\"form\">\n" +
        "              <input type=\"hidden\" name=\"groupid\" value=\"%s\">\n" +
        "              <input type=\"hidden\" name=\"peerid\" value=\"%s\">\n" +
        "              <input type=\"hidden\" name=\"_method\" value=\"put\"/>\n" +
        "              <button type=\"button\" class=\"btn btn-default\" onclick=\"cancel(%s);\" >Pending request</button>\n" +
        "            </form>";
  }

}
