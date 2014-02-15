package com.peerpen.model.serializer;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.peerpen.model.Hunk;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Author: Charles Chao Yang (http://github.com/snwfog)
 * Date:   2/15/2014
 * Time:   10:55 AM
 *
 */
public class Page {

    List<Hunk> hunks;

    public static class PageSerializer implements JsonSerializer<Page> {

        @Override
        public JsonElement serialize( Page src, Type typeOfSrc, JsonSerializationContext context ) {

            return null;
        }
    }

    public static class PageDeserializer implements JsonDeserializer<Page> {

        @Override
        public Page deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context )
                throws JsonParseException {
            Page aPage = new Page();

            JsonArray pageArray = json.getAsJsonArray();
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter( Hunk.class, new Hunk.HunkDeserializer() );
            Type hunkList = new TypeToken<List<Hunk>>() {}.getType();
            Gson gson = builder.create();
            List<Page> pageList = Lists.newArrayList();
            aPage.hunks = gson.fromJson( pageArray.toString(), hunkList );
            return aPage;
        }
    }

    public static void main( String[] args ) {
        String pageString =
                "[{\"id\":\"1392410782094\",\"html\":\"<div class=\\\"ppedit-box\\\" contenteditable=\\\"true\\\" id=\\\"1392410782094\\\" style=\\\"left: 15px; top: 13px; width: 174px; height: 50px; color: black; font-family: Arial; font-size: 20pt; font-weight: normal; text-decoration: none; font-style: normal; z-index: 0; text-align: left; vertical-align: bottom;\\\">Charles Yang</div>\"}]";
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter( Page.class, new PageDeserializer() );
        (builder.create()).fromJson( pageString, Page.class );
    }

}
