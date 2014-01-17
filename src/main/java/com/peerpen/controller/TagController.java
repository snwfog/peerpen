package com.peerpen.controller;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/13/2014
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */

public class TagController extends HttpServlet {
    private static ArrayList results = new ArrayList(  );

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGet( request, response );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }

    //private List getMatchedTags(String keyword){
    //    String sql = "SELECT * FROM `tag_descriptors` WHERE `tag_name` LIKE '%" + keyword + "%'";
    //    List <Tag> tags = new Tag().queryAll(sql);
    //    return tags;
    //}
}
