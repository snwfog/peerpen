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

    // returns the td base on the name, if doesnt exist, it creates it
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

    // like above but do not create if not exists
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

    public List<String> getSuggestedTagDescriptors( String keyword, int limit ) {
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

    public static void main (String[] args){
        TagDescriptor td = new TagDescriptor(  ).getTagDescriptorIfExists( "qweqwewqeqwewq" );
        if (td != null){
            System.out.println(td.getTagName());
        }else{
            System.out.println("doesnt exists");
        }
    }

}
