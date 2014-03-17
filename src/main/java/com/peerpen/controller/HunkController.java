package com.peerpen.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.peerpen.model.Changeset;
import com.peerpen.model.Document;
import com.peerpen.model.Hunk;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class HunkController extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // hand
        String raw = request.getParameter("json_ajax");
        Boolean isOwner = Boolean.parseBoolean( request.getParameter( "isOwner" ) );   //  check if session user is the document owner?

        //handling JSON from Ajax .. the old way using chrome console
        //Scanner scanner = new Scanner( request.getReader() );
        //StringBuffer sb = new StringBuffer();
        //while ( scanner.hasNextLine() ) {
        //    sb.append( scanner.nextLine() );
        //}
        //String raw = sb.toString();

        // Parsing JSON ... getting the creation, modification, delete list
        JsonParser parser = new JsonParser();
        JsonObject rootObj = parser.parse( raw ).getAsJsonObject();


        //JsonObject createdList = rootObj.get( "created" ).getAsJsonObject();
        JsonArray createdList = rootObj.getAsJsonArray( "created" );
        JsonArray modifiedList = rootObj.getAsJsonArray( "modified" );
        JsonArray deletedList = rootObj.getAsJsonArray( "removed" );
        String etagObj = rootObj.get("etag").getAsString();




        //String etag = rootObj.getAsJsonObject().getAsJsonObject().get("etag").toString();

        Iterator<JsonElement> createdIterator = createdList.iterator();
        Iterator<JsonElement> modifiedIterator = modifiedList.iterator();
        Iterator<JsonElement> deletedIterator = deletedList.iterator();






        // todo
        // for all: check etag first before save()

        // Create .. this will insert a new hunk into db
        Document newDoc = new Document();
        newDoc.setDocName( "newDocasdadsa" );
        newDoc.setPeerId( 1 );
        newDoc.save();
        while ( createdIterator.hasNext() ) {
            JsonObject ob = createdIterator.next().getAsJsonObject().getAsJsonObject();
            String receivedIdView = ob.get( "id" ).toString();
            String receivedHtml = ob.get( "html" ).toString();
            receivedHtml = receivedHtml.replace( "'", "\\'" );
            if(isOwner){
                // create and save new hunk directly
                Hunk hunk = new Hunk();
                hunk.setDocumentId( newDoc.getId() );

                hunk.setIdView( receivedIdView );
                hunk.setContent( receivedHtml );

                if (!hunk.save())
                     throw new RuntimeException("Could not save properly.");
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
            String receivedIdView = ob.get( "id" ).toString();
            String receivedHtml = ob.get( "html" ).toString();
            receivedHtml = receivedHtml.replace("'", "\\'");
            System.out.println( "MODIFICATION(received):" + receivedIdView + " " + receivedHtml );

            // finding the old hunk (need a better way to do this)
            HashMap<String, Object> existingHunkData = new HashMap<String, Object>();
            existingHunkData.put( "idView", receivedIdView );
            List<Hunk> existingHunks = new Hunk().findAll( existingHunkData );
            Hunk existingHunk =existingHunks.get( 0 );
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
            String receivedIdView = ob.get( "id" ).toString();
            String receivedHtml = ob.get( "html" ).toString();
            receivedHtml = receivedHtml.replace("'", "\\'");
            System.out.println( "DELETE(received):" + receivedIdView + " " + receivedHtml );

            // finding the old hunk (need a better way to do this)
            HashMap<String, Object> existingHunkData = new HashMap<String, Object>();
            existingHunkData.put( "idView", receivedIdView );
            List<Hunk> existingHunks = new Hunk().findAll( existingHunkData );
            Hunk existingHunk = existingHunks.get( 0 );
            int existingHunkId = existingHunk.getId();

            if (isOwner){
                // Destroy Hunk & Changesets
                existingHunk.destroy();
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
        int id = Integer.parseInt( (String) request.getParameter( "id" ) );
        Document document = new Document().find(id);
        List<Hunk> hunks = document.getHunks();
        StringBuffer sb = new StringBuffer();
        sb.append("{").append( StringUtils.join( hunks, "," ) ).append("}");

        response.addHeader( "Access-Control-Allow-Origin", "*" );
        response.setContentType( "application/json" );
        response.setCharacterEncoding( "UTF-8" );
        response.setStatus( 200 );


        PrintWriter out = response.getWriter();
        out.write(sb.toString());


    }
}
