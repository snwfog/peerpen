package com.peerpen.helper;

import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.Peer;
import com.peerpen.model.TagDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 2014-03-09
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Autocomplete {

    public static String getSuggestedDocuments(String keyword, int limit){
        String sql = "SELECT * FROM `documents` WHERE `doc_name` LIKE '%" + keyword + "%' LIMIT " + limit;
        List<Document> list = new Document().queryAll(sql);
        String json = "";
        for (Document d : list){
            String docName = d.getDocName();
            String author = d.getPeer().getUserName();
            json += "{\"value\":\"" + docName + "\",\"desc\":\"Document by " + author + "\"},";
        }
        return json;
    }

    public static String getSuggestedPeers(String keyword, int limit){
        String sql = "SELECT * FROM `peers` WHERE `user_name` LIKE '%" + keyword + "%' LIMIT " + limit;
        List<Peer> list = new Peer().queryAll(sql);
        String json = "";
        for (Peer p : list){
            json += "{\"value\":\"" + p.getUserName() + "\",\"desc\":\"" + p.getFirstName() + " " + p.getLastName() + "\"},";
        }
        return json;
    }

    public static String getSuggestedGroups(String keyword, int limit){
        String sql = "SELECT * FROM `groups` WHERE `group_name` LIKE '%" + keyword + "%' LIMIT " + limit;
        List<Group> list = new Group().queryAll(sql);
        String json = "";
        for (Group g : list){
            json += "{\"value\":\"" + g.getGroupName() + "\",\"desc\":\"Group\"},";
        }
        return json;
    }

    /**
     * Get a string list of TagDescriptor matching keyword
     * @param keyword
     * @param limit
     * @return
     */
    public static List<String> getSuggestedTagDescriptors( String keyword, int limit ) {
        String sql = "SELECT `tag_name` FROM `tag_descriptors` WHERE `tag_name` LIKE '%" + keyword + "%' LIMIT " + limit;
        List<TagDescriptor> tagDesc = new TagDescriptor().queryAll( sql );
        List<String> suggestions = new ArrayList<String>();
        if ( tagDesc.size() > 0 ) {
            for ( int i = 0; i < tagDesc.size(); i++ ) {
                suggestions.add( tagDesc.get( i ).getTagName() );
            }
        }
        return suggestions;
    }
}
