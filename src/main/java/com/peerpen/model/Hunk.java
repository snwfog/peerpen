package com.peerpen.model;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Mike
 * Date: 11/1/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hunk extends Base implements IModel {

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

    public void setHunkName(String hunkName) {
        this.hunkName = hunkName;
        setUpdateFlag(true);
    }

    public boolean spawnChangeset( Changeset.ChangesetState state ) {
        return Changeset.getInstanceFromHunk( this, state ).save();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder( getContent() );
        sb.replace( 0, 1, "" );
        sb.replace( sb.length() - 1, sb.length(), "" );
        return getIdView() + ":\"" + StringEscapeUtils.escapeJava( sb.toString() ) + "\"";
    }

    public static class HunkSerializer implements JsonSerializer<Hunk> {

        @Override
        public JsonElement serialize( Hunk src, Type typeOfSrc, JsonSerializationContext context ) {
            JsonObject hunkJson = new JsonObject();
            hunkJson.addProperty( "id", src.getIdView() );
            hunkJson.addProperty( "html", src.getContent() );

            return hunkJson;
        }
    }

    public static class HunkDeserializer implements JsonDeserializer<Hunk> {

        @Override
        public Hunk deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context )
                throws JsonParseException {
            String viewId = json.getAsJsonObject().get( "id" ).getAsString();
            String content = json.getAsJsonObject().get( "html" ).getAsString();
            String hunkName = json.getAsJsonObject().get( "name" ).getAsString();

            Hunk hunk = new Hunk();
            hunk.setIdView( viewId );
            hunk.setContent( content );
            hunk.setHunkName( hunkName );

            return hunk;
        }
    }

}
