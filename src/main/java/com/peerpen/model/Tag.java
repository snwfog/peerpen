package com.peerpen.model;

import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/18/2014
 * Time: 7:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tag extends Taggable implements IModel {
    public static final String tableName = "tags";

    @ActiveRelationHasMany
    private List<TagDescriptor> tagDescriptors;
    @ActiveRecordField
    private Integer tagDescriptorId;

    @ActiveRelationHasMany
    private List<Taggable> taggables;
    @ActiveRecordField
    private Integer taggableId;

    public Tag() {
        super();
    }

    public Tag(Map<String, Object> HM) {
        super(HM);
    }

    public void setTagDescriptorId(TagDescriptor tagDescriptor){
        this.tagDescriptorId = tagDescriptor.getId();
    }

    public void setTaggableId(Taggable taggable){
        this.taggableId = taggable.getId();
    }


}
