package com.peerpen.controller;

import com.peerpen.model.Peer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zearf on 1/7/2014.
 */
public class FullController extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // get all extra inputs relating to completing profile
            Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
            System.out.println(parameters);
            Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                peer.setDateOfBirth(formatter.parse(parameters.get("dob")));
            }catch (Exception e) {
                System.out.println("Unable to parse date stamp");
            }
            peer.setGender(parameters.get("gender"));
            peer.setCountry(parameters.get("country"));
            peer.setExperience(Integer.parseInt(parameters.get("yoe")));
            peer.setIndustry(parameters.get("industry"));
            peer.setPersonalWebsite(parameters.get("personalWebsite"));
            peer.setDescription(parameters.get("description"));
            peer.setCompleteProfile(1);
            peer.update();

            response.sendRedirect("/peer/" + peer.getId() + "/profile");

        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            request.getRequestDispatcher( "/view/full.jsp" ).forward( request, response );

        }
}
