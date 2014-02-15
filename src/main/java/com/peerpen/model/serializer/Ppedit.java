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

    List<Page> modified;
    List<Page> removed;
    List<Page> created;

    public static Ppedit serializeFromJsonString( String jsonString )
    {
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter( Ppedit.class, new PpeditDeserializer() );
        Gson gson = gBuilder.create();
        return gson.fromJson( jsonString, Ppedit.class );
    }

    public static void main( String[] args ) {
        // FIXME: Write a proper test for this
        String testString =
                "{\"modified\":[],\"removed\":[],\"created\":[[{\"id\":\"1392410782094\",\"html\":\"<div class=\\\"ppedit-box\\\" contenteditable=\\\"true\\\" id=\\\"1392410782094\\\" style=\\\"left: 15px; top: 13px; width: 174px; height: 50px; color: black; font-family: Arial; font-size: 20pt; font-weight: normal; text-decoration: none; font-style: normal; z-index: 0; text-align: left; vertical-align: bottom;\\\">Charles Yang</div>\"},{\"id\":\"1392410840432\",\"html\":\"<div class=\\\"ppedit-box\\\" contenteditable=\\\"true\\\" id=\\\"1392410840432\\\" style=\\\"left: 200px; top: 13px; width: 245px; height: 50px; color: black; font-family: Arial; font-size: 20pt; font-weight: normal; text-decoration: none; font-style: normal; z-index: 1; text-align: left; vertical-align: bottom;\\\">2105 Patricia Apt. 7</div>\"}],[]],\"etag\":\"61bd33a3e2bb88cdab20634e3745baa081ba05253bdb53dde70cfacb3afdf6a1\"}";
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
            Type listType = new TypeToken<List<Page>>() {}.getType();
            Ppedit ppedit = new Ppedit();

            ppedit.modified = gson.fromJson( modified, listType );
            ppedit.created = gson.fromJson( removed, listType );
            ppedit.removed = gson.fromJson( created, listType );
            ppedit.etag = jsonObject.get( "etag" ).getAsString();

            return ppedit;
        }
    }
}
