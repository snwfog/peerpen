package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/18/2014
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Taggable extends Base {
    public static final String tableName = "taggables";

    @ActiveRecordField
    private String type;

    @ActiveRecordField
    private Integer childId;

    public Taggable(){
        super();
    }

    public Taggable(Map<String, Object> HM) {
        super(HM);
    }

    @Override
    public boolean save(){
        Taggable t = new Taggable(  );
        super.save(); //this saves the original entity (ie:document)
        if(this.getId() != null){ //handle err case
            t.setType( this.getClass().getSimpleName() );
            t.setChildId( this.getId() );
            return t.saveTaggable();
        }else{
            return false;
        }
    }

    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
        //setUpdateFlag(true);
    }

    public Integer getChildId(){
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
        //setUpdateFlag(true);
    }

    private boolean saveTaggable(){
        return super.save();
    }


    // business logic

    // adding existing tag will be ignored
    public boolean addTag(TagDescriptor td){
        // check if this tag is already associated with the taggable entity
        String sql = "SELECT * FROM `tags` WHERE `tag_descriptor_id` = " + td.getId() + " AND `taggable_id` = " + getTaggableId();
        List<Tag> tags = new Tag().queryAll( sql );
        if(tags.size() == 0){ // means entity doesnt have the tag yet, can do add now
            Tag tag = new Tag();
            tag.setTagDescriptor( td );
            tag.setTaggable( this );
            return tag.save();
        }
        return true;
    }

    // remove the link between entity and tag, not the td itself
    public boolean removeTag(TagDescriptor td){
        String sql = "SELECT * FROM `tags` WHERE tags.`tag_descriptor_id` = " + td.getId() + " AND tags.`taggable_id` = " + getTaggableId();
        System.out.println("sql:" + sql);
        List<Tag> tags = new Tag().queryAll( sql );
        boolean success = false;
        for(int i=0;i<tags.size();i++){
            success = tags.get( i ).destroy();
            if (!success){
                return false;
            }
        }
        return true;
    }

    // take care of both removing and adding (Remove whats missing, add whats extra)    (!!to be tested)
    public boolean updateTags(List<TagDescriptor> newList){
        List<TagDescriptor> oldList = this.getTagDescriptors();

        // removing tags that dont exist anymore in newlist
        for (int i=0; i<oldList.size();i++){
            if (!newList.contains( oldList.get( i ) )){
                removeTag( oldList.get( i ) );
            }
        }

        // adding tags that dont exist in oldlist
        for (int i=0; i<newList.size();i++){
            if (!oldList.contains( newList.get( i ) )){
                addTag( newList.get( i ) );
            }
        }
        return true;
    }

    // returns all tags attached to a given entity
    public List<TagDescriptor> getTagDescriptors (){
        List<TagDescriptor> tagDescriptors = new ArrayList<>(  );
        String taggableId = this.getTaggableId().toString();
        System.out.println(taggableId);
        String sql = "SELECT * FROM `tags` WHERE `taggable_id` = '" + taggableId + "'";
        List<Tag> tags = new Tag().queryAll( sql );
        for (int i=0; i<tags.size();i++){
            Tag tag = tags.get( i );
            String getTag = "SELECT * FROM `tag_descriptors` WHERE id = '" + tag.getTagDescriptorId().toString() + "'";
            List<TagDescriptor> td = new TagDescriptor(  ).queryAll( getTag );
            tagDescriptors.add( td.get( 0 ) );
        }
        return tagDescriptors;
    }

    // returns all groups that has give tag
    public List<Group> getMatchedGroups (TagDescriptor tagDescriptor){
        List<Taggable> taggables = new Taggable(  ).getTaggablesFromTagDescriptor( tagDescriptor );
        // loop through all taggables
        List<Group> groups = new ArrayList<>(  );
        for(int i=0;i<taggables.size();i++){
            Taggable taggable = taggables.get( i );
            if (taggable.getType().equals( "Group" )){
                String getEntity = "SELECT * FROM `groups` WHERE id = '" + taggable.getChildId().toString() + "'";
                List<Group> g = new Group().queryAll( getEntity );
                groups.add( g.get( 0 ) ); // assume 1 result could be selected
            }
        }
        return groups;
    }

    // return groups that have all given tags (AND query)
    public List<Group> getMatchedGroups (List<TagDescriptor> tagDescriptors){
        List<Group> groups = new ArrayList<>(  );
        // todo
        return groups;
    }

    // return all documents that have a given tag (non-tested yet)
    public List<Document> getMatchedDocuments (TagDescriptor tagDescriptor){
        List<Taggable> taggables = new Taggable(  ).getTaggablesFromTagDescriptor( tagDescriptor );
        // loop through all taggables
        List<Document> documents = new ArrayList<>(  );
        for(int i=0;i<taggables.size();i++){
            Taggable taggable = taggables.get( i );
            if (taggable.getType().equals( "Document" )){
                String getEntity = "SELECT * FROM `documents` WHERE id = '" + taggable.getChildId().toString() + "'";
                List<Document> d = new Document( ).queryAll( getEntity );
                documents.add( d.get( 0 ) );
            }
        }
        return documents;
    }

    // helper works
    private List<Taggable> getTaggablesFromTagDescriptor (TagDescriptor tagDescriptor){
        String sql = "SELECT taggables.* FROM `tags`, `taggables` WHERE tags.`taggable_id` = taggables.`id` AND tags.`tag_descriptor_id` = "+ tagDescriptor.getId() +";";
        List<Taggable> taggables = new Taggable(  ).queryAll( sql );
        return taggables;
    }

    // helper works
    public Integer getTaggableId(){
        // this.getClass().getSimpleName() // --> Entity Name aka Type
        // this.getId() --> Entity id aka child_id
        String sql = "SELECT `id` FROM `taggables` WHERE `child_id` = " + this.getId() + " AND `type` = '" + this.getClass().getSimpleName() + "'";
        List<Taggable> taggables = new Taggable().queryAll( sql );
        return taggables.get( 0 ).getId();
    }

    public static void main(String[] args){
        //TESTING


        // Creating a taggable entity (tested)
        //Group g1 = new Group();
        //g1.setGroupName( "uqam" );
        //g1.setDescription( "this is description of uqam" );
        //g1.save();
        //
        //Group g2 = new Group();
        //g2.setGroupName( "montreal" );
        //g2.setDescription( "this is description of montreal" );
        //g2.save();




        // Attaching a tag to entity entity (tested)
        //Group g = new Group(  ).find( 4 );
        //g.addTag( new TagDescriptor(  ).getTagDescriptor( "montreal" ) );
        ////g.addTag( new TagDescriptor(  ).getTagDescriptor( "university" ) );
        //// Get all tags associated with an entity
        //List<TagDescriptor> tags = g.getTagDescriptors();
        //System.out.println(g.getGroupName() + " has the following tags: ");
        //for (int i=0;i<tags.size();i++){
        //    System.out.println(tags.get( i ).getTagName());
        //}



        // Retrieve a list of taggable items by tag name (tested)
        //TagDescriptor td = new TagDescriptor(  ).getTagDescriptor( "montreal" );
        //List<Group> groups = new Taggable(  ).getMatchedGroups( td );
        //List<Document> documents = new Taggable(  ).getMatchedDocuments( td );
        //System.out.println("the tag " + td.getTagName() + " is attached to the following entities: ");
        //for(int i =0;i<groups.size();i++){
        //    System.out.println("group: " + groups.get( i ).getGroupName());
        //}
        //for(int i =0;i<documents.size();i++){
        //    System.out.println("document: " + documents.get( i ).getDocName());
        //}


        // Retrieve a list of tags by entity (tested)
        //Group g = new Group(  ).find( 3 );
        //List<TagDescriptor> tagDescriptors = g.getTagDescriptors();
        //System.out.println("Group " + g.getGroupName() + " has the following tags:");
        //for (int i=0;i<tagDescriptors.size();i++){
        //    System.out.println(tagDescriptors.get( i ).getTagName());
        //}

        // Delete a tag from entity
        //Group g = new Group(  ).find( 3 );
        //g.removeTag( td );

    }
}


