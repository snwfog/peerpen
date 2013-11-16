package com.peerpen.controller;

import com.peerpen.model.Peer;
import com.sunnyd.database.Manager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        System.out.println("MIKIIIKEIKEIKaiosdoaijdasoij");
        int id = Integer.parseInt(request.getParameter("id"));
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String website = request.getParameter("personal_website");
        String email = request.getParameter("email");
        String description = request.getParameter("description");
        String country = request.getParameter("country");


        // here we should validate the input...

        // check if user already exists
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("firstName", first_name);
        map.put("lastName", last_name);
        map.put("personalWebsite", website);
        map.put("email", email);
        map.put("description", description);
        map.put("country", country);

        String redirect = "/error";
        try {
              //Quang, i commented out the line below to adjust for the new ppar. make sure it works
//            System.out.println("true or fals?" + Manager.update(id,"peers",map));
            redirect = "/profile";
            Peer peer = new Peer().find(id);
            peer.setFirstName(first_name);
            peer.setLastName(last_name);
            peer.setPersonalWebsite(website);
            peer.setEmail(email);
            peer.setDescription(description);
            peer.setCountry(country);
            peer.update();

//            Peer peer = new Peer(Manager.find(id, "peers"));
            HttpSession session = request.getSession();
            session.setAttribute("user", peer);
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
