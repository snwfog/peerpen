package com.peerpen.controller;

import com.peerpen.model.Peer;

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

        System.out.println("MIKIIIKEIKEIKaiosdoaijdasoij");
        int id = Integer.parseInt(request.getParameter("id"));
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String website = request.getParameter("personal_website");
        String email = request.getParameter("email");
        String description = request.getParameter("description");
        String country = request.getParameter("country");
        String gender = request.getParameter("gender");
        String industry = request.getParameter("industry");
        int yoe = Integer.parseInt(request.getParameter("yoe"));
        String dateOfBirth = (request.getParameter("dob"));
        System.out.println("THIS IS IT!"+dateOfBirth);

        Date dob = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dob = formatter.parse(dateOfBirth);
            dateOfBirth = formatter.format(dob);
        }catch (Exception e) {
            System.out.println("Unable to parse date stamp");
        }

        // here we should validate the input...

        // check if email already exists
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", email);

        String redirect = "/error";
        try {

            redirect = "/profile";
            Peer peer = new Peer().find(id);
            peer.setFirstName(first_name);
            peer.setLastName(last_name);
            peer.setPersonalWebsite(website);
            peer.setEmail(email);
            peer.setDescription(description);
            peer.setCountry(country);
            peer.setEmail(email);
            peer.setGender(gender);
            peer.setIndustry(industry);
            peer.setExperience(yoe);
            peer.setDateOfBirth(dob);
            peer.update();

            HttpSession session = request.getSession();
            session.setAttribute("user", peer);
            session.setAttribute("birth_date", dateOfBirth);
            response.sendRedirect(redirect);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> map = (Map<String, Object>) request.getAttribute("parameters");
        Peer p = new Peer().find(Integer.parseInt(map.get("peer").toString()));
        System.out.println("been here"+ p.getFirstName());
        request.setAttribute("user", p);
        request.getRequestDispatcher("/view/profile.jsp").forward(request, response);

    }
}
