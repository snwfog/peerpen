package com.peerpen.model.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.peerpen.model.Hunk;

import java.lang.reflect.Type;
import java.util.List;

/**
 * This class is a non-AR class used to serialize, and deserialize data
 * coming from the ajax call of the ppeditor
 *
 * Author: Charles Chao Yang (http://github.com/snwfog)
 * Date:   2/14/2014
 * Time:   5:22 PM
 *
 */
public class Ppedit {

    String etag;

    List<Hunk> modified;
    List<Hunk> removed;
    List<Hunk> created;

    public static void main( String[] args ) {
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter( Ppedit.class, new PpeditDeserializer() );

        Gson gson = gBuilder.create();
        gson.toJson( new Ppedit(), System.out );
    }

    public static class PpeditDeserializer implements JsonDeserializer<Ppedit> {
        @Override
        public Ppedit deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context )
                throws JsonParseException {
            return null;
        }
    }
}
