package com.peerpen.controller;

import com.google.common.collect.Maps;
import com.peerpen.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 08/02/14
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class BroadcastController extends HttpServlet
    {

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
            Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
            Group group = new Group().find(Integer.parseInt(parameters.get("groupid")));

            Map<String, Object> map = Maps.newHashMap();
            map.put("message", parameters.get("broadcast"));
            map.put("peerId", peer.getId());
            map.put("groupId", group.getId());

            Broadcast bc = new Broadcast(map);
            bc.save();

            request.setAttribute("group", group);
            response.sendRedirect(request.getHeader("referer"));
        }

        protected void doDelete(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException
        {
            Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");

            Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
            Group group = new Group().find(Integer.parseInt(request.getParameter("groupid")));

            Broadcast broadcast = new Broadcast().find(Integer.parseInt(request.getParameter("broadcastid")));
            broadcast.destroy();

            request.setAttribute("group", group);
            request.setAttribute("user", peer);
            response.sendRedirect(request.getHeader("referer"));
        }

    }
