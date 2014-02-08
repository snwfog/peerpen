package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 07/02/14
 * Time: 5:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class PeersGroup extends Base implements IModel {
    public static final String tableName = "peers_groups";

    @ActiveRelationHasMany
    private List<Group> groups;

    @ActiveRelationHasMany
    private List<Peer> peers;

    @ActiveRecordField
    private Integer groupId;

    @ActiveRecordField
    private Integer peerId;

    private Group group;
    private Peer peer;

    public PeersGroup() {
        super();
    }

    public PeersGroup(Map<String, Object> HM) {
        super(HM);
    }


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPeerId() {
        return peerId;
    }

    public void setPeerId(Integer peerId) {
        this.peerId = peerId;
    }


    public void setGroup( Group group ) {
        this.groupId = group.getId();
        this.group = group;
    }

    public void setPeer (Peer peer){
        this.peerId = peer.getId(); //!
        this.peer = peer;
    }
}
