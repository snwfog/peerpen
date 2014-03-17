package com.peerpen.model.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.peerpen.model.Hunk;

import java.lang.reflect.Type;
import java.util.List;

/**
 * This class is a non-AR class used to serialize, and deserialize data
 * coming from the ajax call of the ppeditor
 *
 * {@link Ppedit} and {@link Page} class are extremely coupled with the external interface and object structure of the Ppeditor. Any changes to the structure, and the way the serializer is implemented will directly affect the functioning of the Ppeditor.
 *
 * Author: Charles Chao Yang (http://github.com/snwfog)
 * Date:   2/14/2014
 * Time:   5:22 PM
 *
 */
public class Ppedit {

    String etag;
    int pageNumber;

    List<Page> modified;
    List<Page> removed;
    List<Page> created;

    public List<Page> getCreated() {
        return created;
    }

    public List<Page> getRemoved() {
        return removed;
    }

    public List<Page> getModified() {
        return modified;
    }

    public String getEtag() {
        return etag;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber() {
        for ( int i = 0; i < modified.size(); i++ ) {
            modified.get( i ).setPageNumber( i );
        }
        for ( int i = 0; i < removed.size(); i++ ) {
            removed.get( i ).setPageNumber( i );
        }
        for ( int i = 0; i < created.size(); i++ ) {
            created.get( i ).setPageNumber( i );
        }
    }


    public static Ppedit serializeFromJsonString( String jsonString ) {
        Gson gson = (new GsonBuilder()).registerTypeAdapter( Ppedit.class, new PpeditDeserializer() ).create();
        StringBuffer sb = new StringBuffer();
        for ( int i = 1; i < jsonString.length() - 1; i++ ) {
            if ( jsonString.charAt( i ) == 92 && jsonString.charAt( i + 1 ) == 92 ) {
                sb.append( jsonString.charAt( i++ ) );
            } else if ( !(jsonString.charAt( i ) == 92 && jsonString.charAt( i + 1 ) == 34) ) {
                sb.append( jsonString.charAt( i ) );
            }
        }
        return gson.fromJson( sb.toString(), Ppedit.class );
        //return gson.fromJson( jsonString, Ppedit.class );
    }

    public static void main( String[] args ) {
        // FIXME: Write a proper test for this
        String testString =
                "{\"modified\":[[],[]],\"removed\":[[],[]],\"created\":[[{\"id\":\"1395022685890\",\"html\":\"<div class=\\\"ppedit-box ppedit-box-focus\\\" contenteditable=\\\"true\\\" id=\\\"1395022685890\\\" style=\\\"left: 275px; top: 71px; width: 75px; height: 50px; color: rgb(0, 0, 0); font-family: 'Times New Roman'; font-size: 12pt; font-weight: normal; text-decoration: none; font-style: normal; line-height: 117%; letter-spacing: 0px; padding: 0px; z-index: 0; text-align: left; vertical-align: bottom;\\\">asdfasdfasdfasdfadsfasdfasdfasdf</div>\",\"name\":\"asdfasdfas\"}],[]],\"etag\":\"93e2fc3652cca582b60b301f9263980ac09d66a4cf0b89f377d2279e57dc7445\"}";
        System.out.println( Ppedit.serializeFromJsonString( testString ) );
    }

    public static class PpeditDeserializer implements JsonDeserializer<Ppedit> {

        @Override
        public Ppedit deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context )
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray modified = jsonObject.get( "modified" ).getAsJsonArray();
            JsonArray removed = jsonObject.get( "removed" ).getAsJsonArray();
            JsonArray created = jsonObject.get( "created" ).getAsJsonArray();

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter( Hunk.class, new Hunk.HunkDeserializer() );
            builder.registerTypeAdapter( Page.class, new Page.PageDeserializer() );
            Gson gson = builder.create();
            Type listType = new TypeToken<List<Page>>() {
            }.getType();
            Ppedit ppedit = new Ppedit();

            ppedit.modified = gson.fromJson( modified, listType );
            ppedit.created = gson.fromJson( created, listType );
            ppedit.removed = gson.fromJson( removed, listType );
            ppedit.etag = jsonObject.get( "etag" ).getAsString();
            ppedit.setPageNumber();

            return ppedit;
        }
    }
}
