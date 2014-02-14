package com.peerpen.controller;
import com.peerpen.model.Peer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.text.SimpleDateFormat;
/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 10/25/2013
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String FORM_PERSONAL = "personal";
        final String FORM_DESCRIPTION = "description";
        final String FORM_CONTACT = "contact";


        // get all specific profile form inputs
        Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
        System.out.println(parameters);
        String form = parameters.get("form").toString();
        Peer peer = new Peer().find(Integer.parseInt(parameters.get("peerid")));
        if(form.equals(FORM_PERSONAL)){
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
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
        request.getRequestDispatcher("/view/profile.jsp").forward(request, response);

    }
}
