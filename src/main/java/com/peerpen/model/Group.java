package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.*;
import java.util.List;
import java.util.Map;

public class Group extends Base implements IModel {

    public static final String tableName = "groups";

    @ActiveRecordField
    private String groupName;

    @ActiveRecordField
    private String description;

    public Group() {
        super();
    }

    public Group(Map<String, Object> HM) {
        super(HM);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
        setUpdateFlag(true);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        setUpdateFlag(true);
    }

    public List<Group> getMatchedGroups(String keyword){
        String sql = "SELECT * FROM `groups` WHERE `group_name` LIKE '%" + keyword + "%'";
        List<Group> groups = new Group().queryAll(sql);
        return groups;
    }

}
