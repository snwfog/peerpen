package com.peerpen.helper;

import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.TagDescriptor;
import com.peerpen.model.Taggable;
import com.peerpen.model.Peer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 2014-03-09
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Search {

    /*
    ======= BASIC SEARCH =========
     */

    public static List<Peer> getMatchedPeers(String keyword){
        String sql =
                "SELECT * FROM `peers` WHERE `user_name` LIKE '%" + keyword + "%' OR `first_name` LIKE '%" + keyword +
                        "%' OR `last_name` LIKE '%" + keyword + "%'";
        return new Peer().queryAll(sql);
    }

    public static List<Document> getMatchedDocuments(String keyword){
        String sql = "SELECT * FROM `documents` WHERE `doc_name` LIKE '%" + keyword + "%'";
        return new Document().queryAll(sql);
    }

    public static List<Group> getMatchedGroups(String keyword){
        String sql = "SELECT * FROM `groups` WHERE `group_name` LIKE '%" + keyword + "%'";
        return new Group().queryAll(sql);
    }

    public static List<Document> getMatchedDocumentsContents(String keyword){
        String sql = "SELECT * FROM `documents`, `hunks` WHERE `hunks`.`document_id` = `documents`.`id` AND (`hunks`.`content` LIKE '%" + keyword + "%' OR '" + keyword + "' LIKE CONCAT" +
                "('%',`hunks`.`content`,'%'));";
        return new Document(  ).queryAll( sql );
    }

    /*
    ======== ADV. SEARCH (with tags) ===========
     */

    public static List<Document> getMatchedDocuments (List<TagDescriptor> tagDescriptors){
        List<Taggable> taggables = new Taggable(  ).getTaggablesFromTagDescriptors( tagDescriptors );

        List<Document> documents = new ArrayList<>(  );
        // loop through all taggables
        for (Taggable taggable: taggables){
            if (taggable.getType().toLowerCase().equals( "document" )){
                String getEntity = "SELECT * FROM `documents` WHERE id = '" + taggable.getChildId().toString() + "'";
                List<Document> d = new Document().queryAll( getEntity );
                documents.add( d.get( 0 ) ); // assume 1 result could be selected
            }
        }
        return documents;
    }

    public static List<Group> getMatchedGroups (List<TagDescriptor> tagDescriptors){
        List<Taggable> taggables = new Taggable(  ).getTaggablesFromTagDescriptors( tagDescriptors );

        List<Group> groups = new ArrayList<>(  );
        // loop through all taggables
        for (Taggable taggable: taggables){
            if (taggable.getType().toLowerCase().equals( "group" )){
                String getEntity = "SELECT * FROM `groups` WHERE id = '" + taggable.getChildId().toString() + "'";
                List<Group> g = new Group().queryAll( getEntity );
                groups.add( g.get( 0 ) ); // assume 1 result could be selected
            }
        }
        return groups;
    }

    public static void removeDuplicatedResults (List list){
        //remove duplicates if any
        //Set set = new LinkedHashSet(list);
        //list.clear();
        //list.addAll(set);
        List result = new ArrayList(  );
        for(int i=0;i<list.size();i++){
            if (!result.contains( list.get( i ) )){
                 result.add( list.get( i ) );
            }
        }
    }

}
