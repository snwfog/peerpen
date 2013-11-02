package com.peerpen.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.gson.*;
import com.peerpen.model.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 11/2/2013
 * Time: 3:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class HunkTest extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    String raw = request.getParameter("json_hunks");
    //System.out.println("raw" + raw);

//        Hunk a = new Hunk();
//        a.setIdView("box3");
//        a.setContent("div");
//        Document d = new Document();
//        a.setDocument(d);
//        System.out.println(a.getContent().toString());

    JsonParser parser = new JsonParser();
    JsonObject rootObj = parser.parse(raw).getAsJsonObject();
    JsonArray mylist = rootObj.getAsJsonArray("created");
    Iterator<JsonElement> it = mylist.iterator();
    //HashMap<String, String> createdBoxes = new HashMap<String, String>();
    ArrayList<Hunk> list = new ArrayList<Hunk>();
    Document newDoc = new Document();
    newDoc.setPeerId(1);
    newDoc.save();
    while (it.hasNext())
    {
      JsonObject ob = it.next().getAsJsonObject().getAsJsonObject();
      String boxid = ob.get("id").toString();
      String html = ob.get("html").toString();
            Hunk h = new Hunk();
            h.setDocument(newDoc);
            h.setIdView(boxid);
            h.setContent(html);
            list.add(h);
    }

    for (Hunk h : list)
    {
      h.save();
      //System.out.println("here" + h.getContent().toString());
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

  }
}
