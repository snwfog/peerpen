package com.peerpen.model.serializer;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.peerpen.model.Hunk;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * A pure abstract class used to serialize and deserialize the JSON hunks coming from
 * ppeditor. Its inner class are used to hook up with Gson API so that an abstract page
 * can be used combined together.
 *
 * Author: Charles Chao Yang (http://github.com/snwfog)
 * Date:   2/15/2014
 * Time:   10:55 AM
 *
 */
public class Page {

    // A map from viewId to actual hunk object
    Map<Long, Hunk> hunks;
    int pageNumber;

    public Page()
    {
        hunks = new HashMap<>();
    }

    public Map<Long, Hunk> getHunks() {
        return hunks;
    }

    public int getPageNumber() { return pageNumber; }
    public void setPageNumber(int page) {
        this.pageNumber = page;
        for (Hunk h : hunks.values())
            h.setPageNumber( this.pageNumber );
    }

    public static class PageSerializer implements JsonSerializer<Page> {

        @Override
        public JsonElement serialize( Page src, Type typeOfSrc, JsonSerializationContext context ) {
            Gson gson = (new GsonBuilder()).registerTypeAdapter( Hunk.class, new Hunk.HunkSerializer() ).create();
            JsonObject el = new JsonObject();
            for ( Long hViewId : src.getHunks().keySet() ) {
                el.add( (new Long( hViewId )).toString(), gson.toJsonTree( src.getHunks().get( hViewId ) ) );
            }

            return el;
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
            Type hunkListType = new TypeToken<List<Hunk>>() {
            }.getType();
            Gson gson = builder.create();
            List<Hunk> hunkList = gson.fromJson( pageArray.toString(), hunkListType );
            aPage.hunks = Maps.uniqueIndex( hunkList, new Function<Hunk, Long>() {
                @Nullable
                @Override
                public Long apply( @Nullable Hunk input ) {
                    return Long.parseLong( input.getIdView() );
                }
            } );
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
