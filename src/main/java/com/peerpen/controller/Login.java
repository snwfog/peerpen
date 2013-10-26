package com.peerpen.controller;

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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 10/25/2013
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet(request, response);
        System.out.println("gets here");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userName", request.getParameter("username"));
        map.put("password", request.getParameter("password"));

        String redirect = "/error";
        try {
            ArrayList<HashMap<String, Object>> matches = Manager.findAll("peers", map);
            if (matches.size() == 1){ // means found exactly 1 user with that username and password
                HttpSession session = request.getSession();
                session.setAttribute("userSession", request.getParameter("username"));
                session.setMaxInactiveInterval(259200); // 3 days in secs
                redirect = "/profile";
            }

            response.sendRedirect(redirect);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // forward to login jsp
//        ServletContext context = getServletContext();
//        RequestDispatcher dispatcher = context.getRequestDispatcher("/view/login.jsp");
//        dispatcher.forward(request, response);
    }

}
