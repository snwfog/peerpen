package com.peerpen.controller;

import com.peerpen.framework.GenericApplicationServlet;
import com.peerpen.framework.ModelHierarchyUtil;
import com.peerpen.model.Peer;
import com.sunnyd.Base;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zearf on 2/16/2014.
 */
public class PublicProfileController extends GenericApplicationServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        Map<String, Object> parameters = internalRequest.getParametersMap();
        Map<String, Base> modelMap = ModelHierarchyUtil.parameterAsMap(parameters);
        Peer viewedUser = (Peer) modelMap.get("peer");
        request.setAttribute("viewedUser", viewedUser);

        request.getRequestDispatcher("/view/publicProfile.jsp").forward(request, response);


    }
}

