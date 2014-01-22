package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;

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

    @Override
    public boolean save(){
        if(!isTagNameExists( this.getTagName())){
            return super.save();
        }
        return true;
    }

    private static boolean isTagNameExists(String tagName){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tagName", tagName);
        TagDescriptor td = new TagDescriptor(  ).find( map );
        return td != null;
    }


}