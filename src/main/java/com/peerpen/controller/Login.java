package com.peerpen.controller;

import com.sunnyd.database.Manager;

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
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userName", "user");
        map.put("password", "pass");

        String redirect = "view/missingData.jsp";
        try {
            ArrayList<HashMap<String, Object>> matches = Manager.findAll("peers", map);
            if (matches.size() == 1){ // means found exactly 1 user with that username and password
                HttpSession session = request.getSession();
                session.setAttribute("userSession", request.getParameter("username"));
                redirect = "view/ok.jsp";
            }
            response.sendRedirect(redirect);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
