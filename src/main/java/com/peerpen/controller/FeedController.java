package com.peerpen.controller;

import com.peerpen.model.Feedable;
import com.peerpen.model.Peer;
import com.sunnyd.Base;
import org.jooq.DSLContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 10/25/2013
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class FeedController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> map = (Map<String, Object>)request.getAttribute("parameters");
        List<Feedable> data = Feedable.getFeed(Integer.parseInt((String)map.get("peer")));
        request.setAttribute("FeedableList", data);
        request.getRequestDispatcher("/feed").forward(request, response);
    }

}
