package com.peerpen.model;

import com.sunnyd.Base;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/18/2014
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Taggable extends Base {

    public Taggable(){
        super();
    }

    public Taggable(Map<String, Object> HM) {
        super(HM);
    }

    public boolean addTag(TagDescriptor td){
        Tag tag = new Tag();
        tag.setTagDescriptorId( td );
        tag.setTaggableId( this );    // ?
        System.out.println( "before save:" + td.getId() + "&" + this.getId() );
        return tag.save();
    }

    public boolean removeTag(TagDescriptor td){
        // todo
        return false;
    }
}
