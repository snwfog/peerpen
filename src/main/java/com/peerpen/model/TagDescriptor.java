package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/18/2014
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class TagDescriptor extends Base implements IModel {
    public static final String tableName = "tag_descriptors";

    @ActiveRecordField
    private String tagName;

    public TagDescriptor(){
        super();
    }

    public TagDescriptor( Map<String, Object> HM ) {
        super(HM);
    }

    public String getTagName(){
        return tagName;
    }

    public void setTagName(String tagName){
        this.tagName = tagName;
    }

    /**
     * Save: Returns TagDescriptor based on tagName. If does not already exist, creates it and return it.
     * This is to make sure no duplicate td in db
     * @param tagName
     * @return TagDescriptor
     */
    public TagDescriptor getTagDescriptor(String tagName){
        TagDescriptor td = new TagDescriptor(  ).getTagDescriptorIfExists( tagName );
        if (td != null){
            return td;
        }else{
            td = new TagDescriptor(  );
            td.setTagName( tagName );
            td.save();
            return td;
        }
    }

    /**
     * Returns TagDescriptor based on tagName. If does not already exist returns null
     * @param tagName
     * @return TagDescriptor or null
     */
    public TagDescriptor getTagDescriptorIfExists (String tagName){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tagName", tagName);
        TagDescriptor td = new TagDescriptor(  ).find( map );
        if (td != null){ // tag exists
            return td;
        } else{
            return null;
        }
    }

    /**
     * Returns a list of existing TagDescriptors based on a list of tagNames that might not all exist
     * @param tagNames
     * @return
     */
    public List<TagDescriptor> getTagDescriptors (List<String> tagNames){
        List<TagDescriptor> tagDescriptors = new ArrayList<>(  );
        for (String tagName: tagNames){
            TagDescriptor td = new TagDescriptor(  ).getTagDescriptorIfExists( tagName );
            if (td !=null){
                tagDescriptors.add( td );
            }
        }
        return tagDescriptors;
    }


    //public List<String> getSuggestedTagDescriptors( String keyword, int limit ) {
    //    String sql = "SELECT `tag_name` FROM `tag_descriptors` WHERE `tag_name` LIKE '%" + keyword + "%' LIMIT " + limit;
    //    List<TagDescriptor> tagDesc = new TagDescriptor().queryAll( sql );
    //    List<String> suggestions = new ArrayList<String>();
    //    if ( tagDesc.size() > 0 ) {
    //        for ( int i = 0; i < tagDesc.size(); i++ ) {
    //            suggestions.add( tagDesc.get( i ).getTagName() );
    //        }
    //    }
    //    return suggestions;
    //}

    @Override
    public boolean equals (Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof TagDescriptor))return false;
        TagDescriptor myOther = (TagDescriptor) other;
        if (this.getId() == myOther.getId()) return true;
        if (this.getTagName().equals( myOther.getTagName() )) return true;
        return false;
    }

    /**
     * Get all tagDescriptors
     * @return
     */
    public static List<TagDescriptor> getTagCloud(){
        return new TagDescriptor().findAll( new HashMap<String, Object>() );
    }
}
