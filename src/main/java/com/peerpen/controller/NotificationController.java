package com.peerpen.controller;

import com.google.gson.Gson;
import com.peerpen.model.Feedable;
import com.peerpen.model.Notification;
import com.peerpen.model.Peer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zearf on 2/15/2014.
 */
public class NotificationController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestType = request.getParameter( "format" );
        if (requestType != null && requestType.equals( "json" )){ // request.getAttribute ("applicationJson") doesnt work

            if (request.getAttribute("sessionUser") != null){

                Peer sessionUser = (Peer) request.getAttribute("sessionUser");
                Notification notification = new Notification();
                String json = new Gson().toJson(notification.getNotification(sessionUser));
                notification.updateNotification(sessionUser);


                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
