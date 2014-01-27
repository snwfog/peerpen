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

    public List<Taggable> getTaggables (TagDescriptor tagDescriptor){
        String sql = "SELECT taggables.* FROM `tags`, `taggables` WHERE tags.`taggable_id` = taggables.`id` AND tags.`tag_descriptor_id` = "+ tagDescriptor.getId() +";";
        List<Taggable> taggables = new Taggable(  ).queryAll( sql );
        return taggables;
    }


    // works
    public Integer getTaggableId(){
        // this.getClass().getSimpleName() // --> Entity Name aka Type
        // this.getId() --> Entity id aka child_id
        String sql = "SELECT * FROM `taggables` WHERE `child_id` = " + this.getId() + " AND `type` = '" + this.getClass().getSimpleName() + "'";
        List<Taggable> taggables = new Taggable().queryAll( sql );
        return taggables.get( 0 ).getId();
    }

    public static void main(String[] args){
    }
}


