package com.peerpen.controller;

import com.peerpen.framework.GenericApplicationServlet;
import com.peerpen.framework.ModelHierarchyUtil;
import com.peerpen.model.Group;
import com.peerpen.model.Peer;
import com.sunnyd.Base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GroupController extends GenericApplicationServlet
{
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
      super.doGet(request, response);
    Map<String, Object> parameters = internalRequest.getParametersMap();
    Map<String, Base> modelMap = ModelHierarchyUtil.parameterAsMap(parameters);
    Peer sessionUser = (Peer) request.getAttribute("sessionUser");
    Group urlGroup = (Group) modelMap.get("group");
    List<Group> group = new Group().getGroups();
    if(urlGroup!=null)
    {

       request.setAttribute("group", urlGroup);
       request.getRequestDispatcher("/view/group.jsp").forward(request, response);
    }else {
        request.setAttribute("groups", group);
        request.getRequestDispatcher("/view/groups.jsp").forward(request, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
      Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
      Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
      Group group = new Group().find(Integer.parseInt(parameters.get("groupid")));
      group.getPeers().add(peer);
      group.setUpdateFlag(true);
      group.update();

      request.setAttribute("peer", peer);
      request.setAttribute("group", group);
      response.sendRedirect(request.getHeader("referer"));
//      request.getRequestDispatcher("/view/group.jsp").forward(request, response);
  }
}
