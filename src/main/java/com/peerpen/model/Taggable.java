package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;

import java.util.ArrayList;
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

    public Taggable() {
        super();
    }

    public Taggable( Map<String, Object> HM ) {
        super( HM );
    }

    @Override
    public boolean save() {
        Taggable t = new Taggable();
        super.save(); //this saves the original entity (ie:document)
        if ( this.getId() != null ) { //handle err case
            t.setType( this.getClass().getSimpleName() );
            t.setChildId( this.getId() );
            return t.saveTaggable();
        } else {
            return false;
        }
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
        //setUpdateFlag(true);
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId( Integer childId ) {
        this.childId = childId;
        //setUpdateFlag(true);
    }

    private boolean saveTaggable() {
        return super.save();
    }

    /**
     * Returns a list of TagDescriptors attached to the calling entity
     * @return
     */
    public List<TagDescriptor> getTagDescriptors() {
        List<TagDescriptor> tagDescriptors = new ArrayList<>();
        String taggableId = this.getTaggableId().toString();
        String sql = "SELECT * FROM `tags` WHERE `taggable_id` = '" + taggableId + "'";
        List<Tag> tags = new Tag().queryAll( sql );
        for ( int i = 0; i < tags.size(); i++ ) {
            Tag tag = tags.get( i );
            String getTag = "SELECT * FROM `tag_descriptors` WHERE id = '" + tag.getTagDescriptorId().toString() + "'";
            List<TagDescriptor> td = new TagDescriptor().queryAll( getTag );
            tagDescriptors.add( td.get( 0 ) );
        }
        return tagDescriptors;
    }

    // List<TagDescriptor> --> List<Taggable>
    public List<Taggable> getTaggablesFromTagDescriptors( List<TagDescriptor> tagDescriptors ) {
        String ids = "";
        for ( TagDescriptor td : tagDescriptors ) {
            ids += td.getId() + ",";
        }
        ids = ids.substring( 0, ids.length() - 1 );
        String sql =
                "SELECT DISTINCT taggables.* FROM `tags`, `taggables` WHERE tags.`taggable_id` = taggables.`id` AND tags.`tag_descriptor_id` IN (" +
                        ids + ")";
        List<Taggable> taggables = new Taggable().queryAll( sql );
        return taggables;
    }


    // taggable entity --> its taggable id
    public Integer getTaggableId() {
        // this.getClass().getSimpleName() // --> Entity Name aka Type
        // this.getId() --> Entity id aka child_id
        String sql = "SELECT `id` FROM `taggables` WHERE `child_id` = " + this.getId() + " AND `type` = '" +
                this.getClass().getSimpleName() + "'";
        List<Taggable> taggables = new Taggable().queryAll( sql );
        return taggables.get( 0 ).getId();
    }

    /**
     * Updates the tags of calling entity. Removes whats missing and add whats extra
     * @param newList
     * @return
     */
    public boolean updateTags( List<TagDescriptor> newList ) {
        List<TagDescriptor> oldList = this.getTagDescriptors();

        // note that the contains uses .equals from TagDescriptor
        // removing tags that don't exist anymore in newlist
        for ( int i = 0; i < oldList.size(); i++ ) {
            if ( !newList.contains( oldList.get( i ) ) ) {
                removeTag( oldList.get( i ) );
            }
        }

        // adding tags that don't exist in oldlist
        for ( int i = 0; i < newList.size(); i++ ) {
            if ( !oldList.contains( newList.get( i ) ) ) {
                addTag( newList.get( i ) );
            }
        }
        return true;
    }

    /*
        ==============  HELPER METHODS ==================
     */

    // attach a td to entity (adding existing tag will be ignored)
    private boolean addTag( TagDescriptor td ) {
        // check if this tag is already associated with the taggable entity
        String sql = "SELECT * FROM `tags` WHERE `tag_descriptor_id` = " + td.getId() + " AND `taggable_id` = " +
                getTaggableId();
        List<Tag> tags = new Tag().queryAll( sql );
        if ( tags.size() == 0 ) { // means entity doesn't have the tag yet, can do add now
            Tag tag = new Tag();
            tag.setTagDescriptor( td );
            tag.setTaggable( this );
            return tag.save();
        }
        return true;
    }

    // detach a td from entity (detaching non-attached td will be ignored)
    private boolean removeTag( TagDescriptor td ) {
        String sql =
                "SELECT * FROM `tags` WHERE tags.`tag_descriptor_id` = " + td.getId() + " AND tags.`taggable_id` = " +
                        getTaggableId();
        List<Tag> tags = new Tag().queryAll( sql );
        boolean success = false;
        for ( int i = 0; i < tags.size(); i++ ) {
            success = tags.get( i ).destroy();
            if ( !success ) {
                return false;
            }
        }
        return true;
    }

}


