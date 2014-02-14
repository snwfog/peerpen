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
    List<Group> groups = new Group().getGroups();
    if (urlGroup != null)
    {
      request.setAttribute("group", urlGroup);
      request.getRequestDispatcher("/view/group.jsp").forward(request, response);
    }
    else
    {
      request.setAttribute("groups", groups);
      request.getRequestDispatcher("/view/groups.jsp").forward(request, response);
    }
  }

  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
// todo: work in progess, dont touch

    String sort = (String) request.getAttribute("sort");
    List<Group> groups = new Group().getSortedGroups(sort);
    request.setAttribute("groups", groups);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    //create a group

    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    Peer sessionUser = (Peer) request.getAttribute("sessionUser");
    Group group = new Group();
    group.setGroupName(parameters.get("name"));
    group.setDescription(parameters.get("description"));
    group.setAdminId(sessionUser.getId());
    group.addPeer(sessionUser);
    group.save();

    request.setAttribute("group", group);
    request.getRequestDispatcher("/view/group.jsp").forward(request, response);
// todo: make it such at it redirects to group/ID
//    response.sendRedirect(request.getHeader("referer"));

  }

}
