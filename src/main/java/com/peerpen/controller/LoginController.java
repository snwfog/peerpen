package com.peerpen.controller;

import com.peerpen.model.Peer;
import com.sunnyd.database.Manager;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 10/25/2013
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet(request, response);
        System.out.println("gets here");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", request.getParameter("username"));
        map.put("password", request.getParameter("password"));

        String redirect = "/login?status=error";
        try {
            List<Peer> matches = new Peer().findAll(map);
            System.out.println(matches.size());

            if (matches.size() == 1){ // means found exactly 1 user with that username and password
                Peer peer = matches.get(0);
                Date dob;
                String dateOfBirth = "";
                if (peer.getDateOfBirth() != null)
                {
                    dob = peer.getDateOfBirth();

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    dateOfBirth  = formatter.format(dob);
                }
                // store the peer obj in session
                HttpSession session = request.getSession();
                session.setAttribute("birth_date", dateOfBirth);
                session.setAttribute("user", peer);
                session.setMaxInactiveInterval(259200); // 3 days in secs
                redirect = "/profile";
            }

            response.sendRedirect(redirect);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
