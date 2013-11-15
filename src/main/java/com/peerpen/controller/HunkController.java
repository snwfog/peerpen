package com.peerpen.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.peerpen.model.Changeset;
import com.peerpen.model.Document;
import com.peerpen.model.Hunk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.maven.model.Contributor;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 11/15/2013
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class HunkController extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String raw = request.getParameter("json_ajax");
        Boolean isOwner = Boolean.parseBoolean( request.getParameter( "isOwner" ));   //  check if session user is the document owner?

        // handling JSON from Ajax .. the old way using chrome console
        //Scanner scanner = new Scanner( request.getReader() );
        //StringBuffer sb = new StringBuffer();
        //while ( scanner.hasNextLine() ) {
        //    sb.append( scanner.nextLine() );
        //}
        //String raw = sb.toString();

        // Parsing JSON ... getting the creation, modification, delete list
        JsonParser parser = new JsonParser();
        JsonObject rootObj = parser.parse( raw ).getAsJsonObject();

        JsonArray createdList = rootObj.getAsJsonArray( "created" );
        JsonArray modifiedList = rootObj.getAsJsonArray( "modified" );
        JsonArray deletedList = rootObj.getAsJsonArray( "deleted" );

        Iterator<JsonElement> createdIterator = createdList.iterator();
        Iterator<JsonElement> modifiedIterator = modifiedList.iterator();
        Iterator<JsonElement> deletedIterator = deletedList.iterator();






        // todo
        // for creation: handle case for Contributor
        // for delete: handle case for Contributor
        // for all: check etag first before save()

        // Create .. this will insert a new hunk into db
        Document newDoc = new Document();
        newDoc.setDocName( "newDocasdadsa" );
        newDoc.setPeerId( 1 );
        newDoc.save();
        while ( createdIterator.hasNext() ) {
            JsonObject ob = createdIterator.next().getAsJsonObject().getAsJsonObject();
            String etag = ob.get( "etag" ).toString();
            String receivedIdView = ob.get( "id" ).toString();
            String receivedHtml = ob.get( "html" ).toString();
            if(isOwner){
                // create and save new hunk directly
                Hunk hunk = new Hunk();
                hunk.setDocumentId( newDoc.getId() );
                hunk.setIdView( receivedIdView );
                hunk.setContent( receivedHtml );
                hunk.save();
            }else{
                // create a new changeset
                HashMap<String, Object> changesetData = new HashMap<String, Object>();
                changesetData.put( "content", receivedHtml );
                Changeset changeset = new Changeset( changesetData );
                changeset.save();
            }

        }



        // Modify.. if owner: override the hunk directly; if contributor: insert a changeset
        while ( modifiedIterator.hasNext() ) {
            // received: idView, newhtml content
            JsonObject ob = modifiedIterator.next().getAsJsonObject().getAsJsonObject();
            String etag = ob.get( "etag" ).toString();
            String receivedIdView = ob.get( "id" ).toString();
            String receivedHtml = ob.get( "html" ).toString();
            System.out.println( "MODIFICATION(received):" + receivedIdView + " " + receivedHtml );

            // finding the old hunk (need a better way to do this)
            HashMap<String, Object> existingHunkData = new HashMap<String, Object>();
            existingHunkData.put( "idView", receivedIdView );
            ArrayList<Map<String, Object>> existingHunks = new Hunk().findAll( existingHunkData );
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



        // Deletion .. if owner: rm all related changeset and hunk; if contributor: insert changeset with null content
        while ( deletedIterator.hasNext() ) {
            // received: idView, newhtml content
            JsonObject ob = deletedIterator.next().getAsJsonObject().getAsJsonObject();
            String etag = ob.get( "etag" ).toString();
            String receivedIdView = ob.get( "id" ).toString();
            String receivedHtml = ob.get( "html" ).toString();
            System.out.println( "DELETE(received):" + receivedIdView + " " + receivedHtml );

            // finding the old hunk (need a better way to do this)
            HashMap<String, Object> existingHunkData = new HashMap<String, Object>();
            existingHunkData.put( "idView", receivedIdView );
            ArrayList<Map<String, Object>> existingHunks = new Hunk().findAll( existingHunkData );
            Hunk existingHunk = new Hunk( existingHunks.get( 0 ) );
            int existingHunkId = existingHunk.getId();

            if (isOwner){
                // Destroy Hunk & Changesets
                existingHunk.Destroy();
            }else{
                // create a new changeset with null as content
                HashMap<String, Object> changesetData = new HashMap<String, Object>();
                changesetData.put( "hunkId", existingHunkId );
                changesetData.put( "content", null );
                Changeset changeset = new Changeset( changesetData );
                changeset.save();
            }

        }

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }
}
