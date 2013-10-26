package com.peerpen.controller;

import com.peerpen.model.Peer;
import com.sunnyd.database.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet(request, response);
        // get all register input
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String website = request.getParameter("personal_website");
        String user_name = request.getParameter("user_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // here we should validate the input...

        // check if user already exists
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userName", user_name);
        String redirect = "/error";
        try {
            ArrayList<HashMap<String, Object>> matches = Manager.findAll("peers", map);
            if (matches.size() == 0){ // means user does not exist
                Peer newPeer = new Peer();
                newPeer.setFirstName(first_name);
                newPeer.setLastName(last_name);
                newPeer.setUserName(user_name);
                newPeer.setEmail(email);
                newPeer.setPersonalWebsite(website);
                newPeer.setPassword(password);
                newPeer.save();

                HttpSession session = request.getSession();
                session.setAttribute("userSession", user_name);
                redirect = "/profile";
            }
            response.sendRedirect(redirect);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // forward to registration jsp
//        ServletContext context = getServletContext();
//        RequestDispatcher dispatcher = context.getRequestDispatcher("/view/register.jsp");
//        dispatcher.forward(request, response);

    }
}
