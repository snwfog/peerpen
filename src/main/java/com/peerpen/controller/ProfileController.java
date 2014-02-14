package com.peerpen.controller;

import com.google.common.collect.ImmutableMap;
import com.peerpen.model.Peer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 10/25/2013
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get all profile input
        final String FORM_PERSONAL = "personal";
        final String FORM_DESCRIPTION = "description";
        final String FORM_CONTACT = "contact";


        Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
        System.out.println(parameters);
        String form = parameters.get("form").toString();
        Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
        if(form.equals(FORM_PERSONAL)){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            peer.setFirstName(parameters.get("firstName"));
            peer.setLastName(parameters.get("lastName"));
            try {
                peer.setDateOfBirth(formatter.parse(parameters.get("dob")));
            }catch (Exception e) {
            System.out.println("Unable to parse date stamp");
            }
            peer.setGender(parameters.get("gender"));
            peer.setCountry(parameters.get("country"));
            peer.setExperience(Integer.parseInt(parameters.get("yoe")));
            peer.setIndustry(parameters.get("industry"));

        }
        else if(form.equals(FORM_DESCRIPTION))
            peer.setDescription(parameters.get("description"));
        else if(form.equals(FORM_CONTACT)){
            peer.setEmail(parameters.get("email"));
            peer.setPersonalWebsite(parameters.get("personalWebsite"));
        }


        peer.update();
        response.sendRedirect("/peer/" + peer.getId() + "/profile");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> map = (Map<String, Object>) request.getAttribute("parameters");
        Peer p = new Peer().find(Integer.parseInt(map.get("peer").toString()));
        System.out.println("been here"+ p.getFirstName());
        request.setAttribute("user", p);
        request.getRequestDispatcher("/view/profile.jsp").forward(request, response);

    }
}
