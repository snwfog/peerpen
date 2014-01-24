package com.peerpen.controller;

import com.google.common.collect.ImmutableMap;
import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.framework.InternalRequestDispatcher;
import com.peerpen.framework.exception.MissingArgumentException;
import com.peerpen.framework.exception.RegistrationFailedException;
import com.peerpen.model.Peer;

import javax.naming.OperationNotSupportedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Zearf on 1/7/2014.
 */
public class AdditionalController extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            // get all register input
//            int id = Integer.parseInt(request.getParameter("id"));
//            String dateOfBirth = request.getParameter("dob");
//            String gender = request.getParameter("gender");
//            String country = request.getParameter("country");
//            String industry = request.getParameter("industry");
//            String yoe = request.getParameter("yoe");
//            String description = request.getParameter("description");
//            String website = request.getParameter("personal_website");
//            Peer peer = new Peer().find(id);



//            // sending the attributes around to reach AvatarController
//            HttpSession session = request.getSession();
//            session.setAttribute("user", peer);
//            session.setAttribute("id", id);
//            session.setAttribute("dob", dateOfBirth);
//            session.setAttribute("gender", gender);
//            session.setAttribute("country", country);
//            session.setAttribute("industry", industry);
//            session.setAttribute("yoe", yoe);
//            session.setAttribute("description", description);
//            session.setAttribute("personal_website", website);

//            response.sendRedirect("/avatar");

            try {
                InternalHttpServletRequest internalRequest = InternalHttpServletRequest.transform( request );
                internalRequest.expectPresenceOf( "dob", "gender", "country", "industry", "yoe", "description", "personal_website" );
                Map<String, Object> map = (Map<String, Object>) request.getAttribute( "parameters" );
                Peer peer = (new Peer()).find( ImmutableMap.of("userName", map.get("userName")) );
                if ( peer != null ) {
                    throw new RegistrationFailedException( "User already exists " + map.get( "userName" ) );
                }
                Peer p = new Peer( map );
                // TODO: Validate uniqueness either using Java, or database constraint
                // Create the user unquestionably given that this user does not exists already
                if ( (map.get( "password" )).equals( map.get( "confirmPassword" ) ) && p.save() ) {
                    p.setSessionId( request.getSession().getId() );
                    p.update();
                    response.encodeRedirectURL( "/peer/" + p.getId() + "/profile" );
                } else {
                    throw new RegistrationFailedException( map );
                }

            } catch ( MissingArgumentException | OperationNotSupportedException | RegistrationFailedException e ) {
                ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError( request, response, e );
            }


        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String path = request.getSession().getServletContext().getRealPath("/");
            Map<String, Object> map = (Map<String, Object>) request.getAttribute("parameters");
            Peer p = new Peer().find(Integer.parseInt(map.get("peer").toString()));
            request.setAttribute("user", p);
            request.setAttribute("path", path);
            System.out.println(map);
            request.getRequestDispatcher( "/view/registration2.jsp" ).forward( request, response );

        }
}
