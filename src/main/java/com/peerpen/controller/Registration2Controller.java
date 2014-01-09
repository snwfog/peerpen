package com.peerpen.controller;

import com.peerpen.model.Peer;
import com.sunnyd.database.Manager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zearf on 1/7/2014.
 */
public class Registration2Controller extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // get all register input
            int id = Integer.parseInt(request.getParameter("id"));
            String dateOfBirth = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String country = request.getParameter("country");
            String industry = request.getParameter("industry");
            String yoe = request.getParameter("yoe");
            String description = request.getParameter("description");
            String website = request.getParameter("personal_website");
            Peer peer = new Peer().find(id);



            // sending the attributes around to reach AvatarController
            HttpSession session = request.getSession();
            session.setAttribute("user", peer);
            session.setAttribute("id", id);
            session.setAttribute("dob", dateOfBirth);
            session.setAttribute("gender", gender);
            session.setAttribute("country", country);
            session.setAttribute("industry", industry);
            session.setAttribute("yoe", yoe);
            session.setAttribute("description", description);
            session.setAttribute("personal_website", website);

            response.sendRedirect("/avatar");


        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // forward to registration jsp
//        ServletContext context = getServletContext();
//        RequestDispatcher dispatcher = context.getRequestDispatcher("/view/register.jsp");
//        dispatcher.forward(request, response);

        }
}
