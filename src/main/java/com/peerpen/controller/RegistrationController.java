package com.peerpen.controller;

import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.framework.exception.MissingArgumentException;
import com.peerpen.model.Peer;
import com.sunnyd.database.Manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationController extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        //doGet(request, response);
        // get all register input
        try
        {
            InternalHttpServletRequest internalRequest = InternalHttpServletRequest.transform( request );
            internalRequest.expectPresenceOf( "first_name", "last_name", "user_name", "email", "password" );

//            String first_name = request.getParameter("first_name");
//            String last_name = request.getParameter("last_name");
//            String user_name = request.getParameter("user_name");
//            String email = request.getParameter("email");
//            String password = request.getParameter("password");
            Map<String, Object> map = (Map<String, Object>)request.getAttribute("parameters");
            Peer p = new Peer(map);


            // This to put up for a session here...
            // Save peer and check is valid
            if (p.save())
                internalRequest.getRequestDispatcher("/peer/" + p.getId() + "/profile").forward(request, response);
//            // here we should validate the input...
//
//            // check if user already exists
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put( "userName", user_name );
//            String redirect = "/error";
//            try {
//                ArrayList<Map<String, Object>> matches = Manager.findAll( "peers", map );
//                System.out.println( matches );
//                if ( matches.size() == 0 ) { // means user does not exist
//                    Peer newPeer = new Peer();
//                    newPeer.setFirstName(first_name);
//                    newPeer.setLastName(last_name);
//                    newPeer.setUserName(user_name);
//                    newPeer.setEmail(email);
//                    newPeer.setPassword(password);
//                    newPeer.setPoint(0);
//                    newPeer.save();
//
//                    HttpSession session = request.getSession();
//                    session.setAttribute("user", newPeer);
//                    redirect = "/registration2";
//
//                }
//                response.sendRedirect( redirect );
//
//            } catch ( Exception e ) {
//                e.printStackTrace();
//            }


        } catch (MissingArgumentException e) {
            e.printStackTrace();
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }


    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // Get user first
//        Map<String, Object> user = request.getAttribute("parameters");
//        request.setAttribute("profileUser",
        ((InternalHttpServletRequest)request).getRequestDispatcher("/additional").forward(request, response);
    }
}
