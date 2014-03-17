package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/18/2014
 * Time: 7:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tag extends Base implements IModel {

    public static final String tableName = "tags";

    @ActiveRelationHasMany
    private List<TagDescriptor> tagDescriptors;

    @ActiveRelationHasMany
    private List<Taggable> taggables;

    @ActiveRecordField
    private Integer tagDescriptorId;

    @ActiveRecordField
    private Integer taggableId;

    private TagDescriptor tagDescriptor;
    private Taggable taggable;



    public Tag() {
        super();
    }

    public Tag( Map<String, Object> HM ) {
        super( HM );
    }

    public Integer getTagDescriptorId() {
        return this.tagDescriptorId;
    }

    public void setTagDescriptorId( Integer id ) {
        this.tagDescriptorId = id;
    }

    public Integer getTaggableId() {
        return this.taggableId;
    }

    public void setTaggableId( Integer id ) {
        this.taggableId = id;
    }

    public TagDescriptor getTagDescriptor() {
        return tagDescriptor;
    }

    public void setTagDescriptor( TagDescriptor tagDescriptor ) {
        this.tagDescriptorId = tagDescriptor.getId();
        this.tagDescriptor = tagDescriptor;
    }

    public void setTaggable( Taggable taggable ) {
        this.taggableId = taggable.getTaggableId();
        this.taggable = taggable;
    }
}
