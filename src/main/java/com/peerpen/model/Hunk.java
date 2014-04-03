package com.peerpen.model;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Mike
 * Date: 11/1/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hunk extends Base {

    public static final String tableName = "hunks";
    @ActiveRecordField
    private String idView;
    @ActiveRecordField
    private String content;
    @ActiveRecordField
    private Integer pageNumber;
    @ActiveRecordField
    private Integer documentId;
    @ActiveRelationHasOne
    private Document document;
    @ActiveRecordField
    private String hunkName;
    @ActiveRelationHasMany
    private ArrayList<Changeset> changesets;

    public Hunk() {
        super();
    }

    public Hunk( Map<String, Object> HM ) {
        super( HM );
    }

    public static void main( String[] args ) {
        String input = "{\n" +
                "    \"modified\": [],\n" +
                "    \"removed\": [],\n" +
                "    \"created\": [\n" +
                "        [\n" +
                "            {\n" +
                "                \"id\": \"1391820471425\",\n" +
                "                \"html\": \"<div class=\\\"ppedit-box\\\" contenteditable=\\\"true\\\" id=\\\"1391820471425\\\" style=\\\"left: 50px; top: 50px; width: 75px; height: 50px; color: black; font-family: 'Times New Roman'; font-size: 12pt; font-weight: normal; text-decoration: none; font-style: normal; z-index: 0; text-align: left; vertical-align: bottom;\\\"></div>\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"1391820471440\",\n" +
                "                \"html\": \"<div class=\\\"ppedit-box\\\" contenteditable=\\\"true\\\" id=\\\"1391820471440\\\" style=\\\"left: 50px; top: 50px; width: 75px; height: 50px; color: black; font-family: 'Times New Roman'; font-size: 12pt; font-weight: normal; text-decoration: none; font-style: normal; z-index: 1; text-align: left; vertical-align: bottom;\\\"></div>\"\n" +
                "            }\n" +
                "        ],\n" +
                "        []\n" +
                "    ],\n" +
                "    \"etag\": \"429a8768235e551541c2538787d4f065c350fdb1c57e0235e45572828cbbe32c\"\n" +
                "}";
        System.out.println( input );


        JsonObject rootObj = new JsonParser().parse( input ).getAsJsonObject();

        JsonArray createdList = rootObj.getAsJsonArray( "created" );
        JsonArray modifiedList = rootObj.getAsJsonArray( "modified" );
        JsonArray deletedList = rootObj.getAsJsonArray( "removed" );
        String etagObj = rootObj.get( "etag" ).getAsString();

        // creation list
        for ( JsonElement j : createdList ) {
            for ( JsonElement e : j.getAsJsonArray() ) {
                String id = e.getAsJsonObject().get( "id" ).getAsString();
                String html = e.getAsJsonObject().get( "html" ).getAsString();
                System.out.println( id + "->" + html );
            }
        }
    }

    public String getIdView() {
        return idView;
    }

    public void setIdView( String idView ) {
        this.idView = idView;
        setUpdateFlag( true );
    }

    public String getContent() {
        return content;
    }

    public Hunk setContent( String content ) {
        this.content = content;
        setUpdateFlag( true );
        return this;
    }

    public Integer getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId( Integer documentId ) {
        this.documentId = documentId;
    }

    public Document getDocument() {
        initRelation( "document" );
        return document;
    }

    public void setDocument( Document document ) {
        this.documentId = document.getId();
        this.document = null;
        setUpdateFlag( true );
    }

    public ArrayList<Changeset> getChangesets() {
        initRelation( "changesets" );
        return this.changesets;
    }

    public List<Hunk> getHunksFromIdView( Integer idView ) {
        String sql = "SELECT * FROM `hunks` WHERE `id_view` = " + idView;
        return new Hunk().queryAll( sql );
    }

    @Deprecated
    public Hunk findByViewId( String viewId ) {
        return (new Hunk()).find( ImmutableMap.of( "idView", (Object) viewId ) );
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber( Integer pageNumber ) {
        this.pageNumber = pageNumber;
        this.setUpdateFlag( true );
    }

    public String getHunkName() {
        return hunkName;
    }

    public void setHunkName( String hunkName ) {
        this.hunkName = hunkName;
        setUpdateFlag( true );
    }

    public boolean spawnChangeset( Changeset.ChangesetState state ) {
        return Changeset.getInstanceFromHunk( this, state ).save();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder( getContent() );
        sb.replace( 0, 1, "" );
        sb.replace( sb.length() - 1, sb.length(), "" );
        String obj = getIdView() + ":\"" + StringEscapeUtils.escapeJava( sb.toString() ) + "\"";
        return obj;
    }

    public static class HunkSerializer implements JsonSerializer<Hunk> {

        @Override
        public JsonElement serialize( Hunk src, Type typeOfSrc, JsonSerializationContext context ) {
            JsonObject hunkJson = new JsonObject();
            hunkJson.addProperty( "html", src.getContent() );
            hunkJson.addProperty( "name", src.getHunkName() );

            return hunkJson;
        }
    }

    public static class HunkDeserializer implements JsonDeserializer<Hunk> {

        @Override
        public Hunk deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context )
                throws JsonParseException {
            String viewId = json.getAsJsonObject().get( "id" ).getAsString();
            String content = json.getAsJsonObject().get( "html" ).getAsString();
            JsonElement hunkNameElement = json.getAsJsonObject().get( "name" );
            String hunkName = "";
            if ( hunkNameElement != null ) {
                hunkName = hunkNameElement.getAsString();
            }

            Hunk hunk = new Hunk();
            hunk.setIdView( viewId );
            hunk.setContent( content );
            hunk.setHunkName( hunkName );

            return hunk;
        }
    }
}
