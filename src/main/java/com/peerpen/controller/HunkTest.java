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
    // check if session user is the document owner?
    Boolean isOwner = false;
    while (modifiedIterator.hasNext())
    {
      // received: idView, newhtml content
      JsonObject ob = modifiedIterator.next().getAsJsonObject().getAsJsonObject();
      String receivedIdView = ob.get("id").toString();
      String receivedHtml = ob.get("html").toString();
      System.out.println("MODIFICATION(received):" + receivedIdView + " " + receivedHtml);

      // finding the old hunk (need a better way to do this)
      HashMap<String, Object> existingHunkData = new HashMap<String, Object>();
      existingHunkData.put("idView", receivedIdView);
      ArrayList<HashMap<String, Object>> existingHunks = Manager.findAll("hunks", existingHunkData);
      Hunk existingHunk = new Hunk(existingHunks.get(0));
      int existingHunkId = existingHunk.getId();
      System.out.println("oldHunkId:" + existingHunkId);

      if (isOwner)
      {
        // update the hunk content and save
        existingHunk.setContent(receivedHtml);
        existingHunk.update();
      }
      else
      {
        // get the uid based on idView given
        HashMap<String, Object> changesetData = new HashMap<String, Object>();
        changesetData.put("hunkId", existingHunkId);
        changesetData.put("content", receivedHtml);
        Changeset changeset = new Changeset(changesetData);
        changeset.save();
      }

    }

    while(deletedIterator.hasNext())
    {
      // received: idView, newhtml content
      JsonObject ob = deletedIterator.next().getAsJsonObject().getAsJsonObject();
      String receivedIdView = ob.get("id").toString();
      String receivedHtml = ob.get("html").toString();
      System.out.println("DELETE(received):" + receivedIdView + " " + receivedHtml);

      // finding the old hunk (need a better way to do this)
      HashMap<String, Object> existingHunkData = new HashMap<String, Object>();
      existingHunkData.put("idView", receivedIdView);
      ArrayList<HashMap<String, Object>> existingHunks = Manager.findAll("hunks", existingHunkData);
      Hunk existingHunk = new Hunk(existingHunks.get(0));

      // Destroy Hunk & Changesets
      existingHunk.Destroy();

    }

//      Hunk h = new Hunk();
//
//      h.setDocumentId(newDoc.getId());
//      h.setIdView(boxid);
//      h.setContent(html);
//      list.add(h);


  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

  }
}
