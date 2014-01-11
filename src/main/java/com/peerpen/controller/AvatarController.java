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

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Zearf on 1/7/2014.
 */
public class AvatarController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get all register input
        int id = Integer.parseInt(request.getParameter("id"));
        String dateOfBirth = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String industry = request.getParameter("industry");
        Integer yoe = Integer.parseInt(request.getParameter("yoe"));
        String description = request.getParameter("description");
        String website = request.getParameter("personal_website");

        StringUtils.split(" ");

        Date dob = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dob = formatter.parse(dateOfBirth);
        }catch (Exception e) {
            System.out.println("Unable to parse date stamp");
        }
        dateOfBirth = formatter.format(dob);
        // here we should validate the input...

            Peer peer = new Peer().find(id) ;
            peer.setDateOfBirth(dob);
            peer.setGender(gender);
            peer.setCountry(country);
            peer.setIndustry(industry);
            peer.setExperience(yoe);
            peer.setDescription(description);
            peer.setPersonalWebsite(website);
            peer.update();

        HttpSession session = request.getSession();
        session.setAttribute("user", peer);
        session.setAttribute("birth_date", dateOfBirth);
        response.sendRedirect("/profile");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // forward to registration jsp
//        ServletContext context = getServletContext();
//        RequestDispatcher dispatcher = context.getRequestDispatcher("/view/register.jsp");
//        dispatcher.forward(request, response);

    }
}
