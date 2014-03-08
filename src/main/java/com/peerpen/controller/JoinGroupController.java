package com.peerpen.controller;

import com.peerpen.framework.GenericApplicationServlet;
import com.peerpen.model.Group;
import com.peerpen.model.Joingroup;
import com.peerpen.model.Peer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 14/02/14
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class JoinGroupController extends GenericApplicationServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
        Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
        Group group = new Group().find(Integer.parseInt(parameters.get("groupid")));
        Joingroup jg = new Joingroup().find(Integer.parseInt(parameters.get("jgid")));
        group.addPeer(peer);
        group.update();
        jg.destroy();
        response.sendRedirect(request.getHeader("referer"));
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
        Joingroup jg = new Joingroup().find(Integer.parseInt(parameters.get("jgid")));
        jg.destroy();
        response.sendRedirect(request.getHeader("referer"));
    }
}
