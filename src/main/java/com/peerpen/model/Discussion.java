package com.peerpen.model;

import com.sunnyd.IModel;
import com.sunnyd.annotations.*;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 08/02/14
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Discussion extends Feedable implements IModel {

    public static final String tableName = "discussions";

    @ActiveRecordField
    private String message;
    @ActiveRecordField
    private Integer peerId;
    @ActiveRelationHasOne
    private Peer peer;
    @ActiveRecordField
    private Integer groupId;
    @ActiveRelationHasOne
    private Group group;

    public Discussion()
    {
        super();
    }

    public Discussion(Map<String, Object> HM)
    {
        super(HM);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        setUpdateFlag(true);
    }

    public Integer getPeerId() {
        return peerId;
    }

    public Peer getPeer()
    {
        initRelation("peer");
        return peer;
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
    public Group getGroup()
    {
        initRelation("group");
        return group;
    }
}
