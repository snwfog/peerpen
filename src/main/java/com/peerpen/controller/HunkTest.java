package com.peerpen.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.gson.*;
import com.peerpen.model.*;
import com.sunnyd.database.Manager;

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

    JsonArray createdList = rootObj.getAsJsonArray("created");
    JsonArray modifiedList = rootObj.getAsJsonArray("modified");
    JsonArray deletedList = rootObj.getAsJsonArray("deleted");

    Iterator<JsonElement> createdIterator = createdList.iterator();
    Iterator<JsonElement> modifiedIterator = modifiedList.iterator();
    Iterator<JsonElement> deletedIterator = deletedList.iterator();

    // CREATE
//    ArrayList<Hunk> createdHunkList = new ArrayList<Hunk>();
//    Document newDoc = new Document();
//    newDoc.setDocName("newDoc");
//    newDoc.setPeerId(1);
//    newDoc.save();
//    while (createdIterator.hasNext())
//    {
//      JsonObject ob = createdIterator.next().getAsJsonObject().getAsJsonObject();
//      String boxid = ob.get("id").toString();
//      String html = ob.get("html").toString();
//            Hunk h = new Hunk();
//            h.setDocumentId(newDoc.getId());
//            h.setIdView(boxid);
//            h.setContent(html);
//            createdHunkList.add(h);
//    }
//
//    for (Hunk h : createdHunkList)
//    {
//      h.save();
//    }


    // MODIFY

    // save the hunk to changeset
//    ArrayList<Hunk> list = new ArrayList<Hunk>();
//    Document modDoc = new Document();
//    modDoc.getId();


    while (modifiedIterator.hasNext())
    {
      JsonObject ob = modifiedIterator.next().getAsJsonObject().getAsJsonObject();
      String boxid = ob.get("id").toString();
      String html = ob.get("html").toString();
      HashMap<String, Object> findCriteria = new HashMap<String, Object>();
      //findCriteria.put("idView", "box2");
      int id = -1;
      ArrayList<HashMap<String, Object>> found = Manager.findAll("hunks", findCriteria);
      for (HashMap<String, Object> h : found){
        for (String key : h.keySet()){
          //System.out.println("ppp" + key + ":" + h.get(key));
          if (key.equals("idView")){
            id = Integer.parseInt(h.get("id").toString());
          }
        }
      }

      //System.out.println("is this the id?" + found.get(0).toString());

    HashMap<String, Object> updated = new HashMap<String, Object>();
    updated.put("content", html);
    Manager.update(id, "hunks", updated);

//      Hunk h = new Hunk();
//
//      h.setDocumentId(newDoc.getId());
//      h.setIdView(boxid);
//      h.setContent(html);
//      list.add(h);
    }


    }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

  }
}
