package com.peerpen.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
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
public class HunkTest extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        //String raw = request.getParameter("json_hunks");
        Scanner scanner = new Scanner( request.getReader() );
        StringBuffer sb = new StringBuffer();
        while ( scanner.hasNextLine() ) {
            sb.append( scanner.nextLine() );
        }

        String raw = sb.toString();

        JsonParser parser = new JsonParser();
        JsonObject rootObj = parser.parse( raw ).getAsJsonObject();

        JsonArray createdList = rootObj.getAsJsonArray( "created" );
        JsonArray modifiedList = rootObj.getAsJsonArray( "modified" );
        JsonArray deletedList = rootObj.getAsJsonArray( "deleted" );

        Iterator<JsonElement> createdIterator = createdList.iterator();
        Iterator<JsonElement> modifiedIterator = modifiedList.iterator();
        Iterator<JsonElement> deletedIterator = deletedList.iterator();

        //CREATE (this will insert a new hunk into db)
        //Document newDoc = new Document();
        //newDoc.setDocName( "newDocasdadsa" );
        //newDoc.setPeerId( 1 );
        //newDoc.save();
        //while ( createdIterator.hasNext() ) {
        //    JsonObject ob = createdIterator.next().getAsJsonObject().getAsJsonObject();
        //    String receivedIdView = ob.get( "id" ).toString();
        //    String receivedHtml = ob.get( "html" ).toString();
        //    Hunk hunk = new Hunk();
        //    hunk.setDocumentId( newDoc.getId() );
        //    hunk.setIdView( receivedIdView );
        //    hunk.setContent( receivedHtml );
        //    hunk.save();
        //}



        // MODIFY (this will update the hunk if owner, or insert a changeset if peer)
        //    check if session user is the document owner?
        Boolean isOwner = false;
        while ( modifiedIterator.hasNext() ) {
            // received: idView, newhtml content
            JsonObject ob = modifiedIterator.next().getAsJsonObject().getAsJsonObject();
            String receivedIdView = ob.get( "id" ).toString();
            String receivedHtml = ob.get( "html" ).toString();
            System.out.println( "MODIFICATION(received):" + receivedIdView + " " + receivedHtml );

            // finding the old hunk (need a better way to do this)
            HashMap<String, Object> existingHunkData = new HashMap<String, Object>();
            existingHunkData.put( "idView", receivedIdView );
            ArrayList<Map<String, Object>> existingHunks = new Manager().findAll( "hunks", existingHunkData );
            Hunk existingHunk = new Hunk( existingHunks.get( 0 ) );
            int existingHunkId = existingHunk.getId();
            System.out.println( "oldHunkId:" + existingHunkId );

            if ( isOwner ) {
                // update the old hunk directly and save
                existingHunk.setContent( receivedHtml );
                existingHunk.update();
            } else {
                // create a new changeset
                HashMap<String, Object> changesetData = new HashMap<String, Object>();
                changesetData.put( "hunkId", existingHunkId );
                changesetData.put( "content", receivedHtml );
                Changeset changeset = new Changeset( changesetData );
                changeset.save();
            }

        }

            //while(deletedIterator.hasNext())
            //{
            //  // received: idView, newhtml content
            //  JsonObject ob = deletedIterator.next().getAsJsonObject().getAsJsonObject();
            //  String receivedIdView = ob.get("id").toString();
            //  String receivedHtml = ob.get("html").toString();
            //  System.out.println("DELETE(received):" + receivedIdView + " " + receivedHtml);
            //
            //  // finding the old hunk (need a better way to do this)
            //  HashMap<String, Object> existingHunkData = new HashMap<String, Object>();
            //  existingHunkData.put("idView", receivedIdView);
            //  ArrayList<Map<String, Object>> existingHunks = Manager.findAll("hunks", existingHunkData);
            //  Hunk existingHunk = new Hunk(existingHunks.get(0));
            //
            //  // Destroy Hunk & Changesets
            //  existingHunk.Destroy();
            //
            //}
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }
}
