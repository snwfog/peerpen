package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/18/2014
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Taggable extends Base {

    @ActiveRecordField
    private String type;

    @ActiveRecordField
    private Integer childId;

    private Taggable trueSelf;

    private String modelPath = "com.peerpen.model.";

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

    public void setType(String type) {
        this.type = type;
        setUpdateFlag(true);
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
        setUpdateFlag(true);
    }


    private boolean saveTaggable(){
        return super.save();
    }

    public boolean addTag(TagDescriptor td){
        Tag tag = new Tag();
        tag.setTagDescriptorId( td );
        tag.setTaggableId( this );
        return tag.save();
    }

    public boolean removeTag(TagDescriptor td){
        Tag t = new Tag().find( td.getId() );
        return t.destroy();
    }
}
