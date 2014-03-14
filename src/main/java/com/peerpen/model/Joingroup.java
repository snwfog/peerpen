package com.peerpen.model;

import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 14/02/14
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Joingroup extends Feedable implements IModel {

    public static final String tableName = "joingroups";

    @ActiveRecordField
    private Integer peerId;
    @ActiveRelationHasOne
    private Peer peer;
    @ActiveRecordField
    private Integer groupId;
    @ActiveRelationHasOne
    private Group group;

    public Joingroup()
    {
        super();
    }

    public Joingroup(Map<String, Object> HM)
    {
        super(HM);
    }

    public Integer getPeerId() {
        return peerId;
    }

    public void setPeerId(Integer peerId) {
        this.peerId = peerId;
        setUpdateFlag(true);
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
        setUpdateFlag(true);
    }
    public Peer getPeer()
    {
        initRelation("peer");
        return peer;
    }

    public void setPeer(Peer p)
    {
        peerId = p.getId();
        peer = p;
    }
    public Group getGroup()
    {
        initRelation("group");
        return group;
    }


    public void setGroup(Group g)
    {
        this.setGroupId(g.getId());
        this.group = g;
        this.setUpdateFlag(true);
    }


//    public List<Joingroup> getRequests()
//    {
//        List<Joingroup> joingroups = new Joingroup().queryAll("SELECT * FROM `joingroups` WHERE group_id= "+ this.getGroupId() +" ORDER BY last_modified_date DESC");
//        return joingroups;
//    }

}

