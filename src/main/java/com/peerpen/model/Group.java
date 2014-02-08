package com.peerpen.model;

import com.google.gson.Gson;
import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Group extends Taggable implements IModel {

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


  public List<Group> getGroups()
  {
    return new Group().queryAll("SELECT * FROM `groups`");
  }

    // method used for search autocomplete
    //public List<String> getSuggestedGroups(String keyword, int limit){
    //    String sql = "SELECT `group_name` FROM `groups` WHERE `group_name` LIKE '%" + keyword + "%' LIMIT " + limit;
    //    List<Group> groups = new Group().queryAll(sql);
    //    List<String> suggestions = new ArrayList<String>();
    //
    //    if(groups.size() > 0){
    //        for(int i=0;i<groups.size();i++){
    //            suggestions.add(groups.get(i).getGroupName());
    //        }
    //    }
    //    //
    //    return suggestions;
    //}

    public List<Group> getSuggestions(String keyword, int limit){
        String sql = "SELECT * FROM `groups` WHERE `group_name` LIKE '%" + keyword + "%' LIMIT " + limit;
        return new Group().queryAll(sql);
    }

    @Override
    public boolean equals (Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Group))return false;
        Group myOther = (Group) other;
        if (this.getId() == myOther.getId()) return true;
        return false;
    }


    public static void main (String[] args){

    }

  public boolean getIsJoined(Integer sessionUserId)
  {
    List<PeersGroup> peersGroup = new PeersGroup().queryAll(String.format("SELECT * FROM `peers_groups` WHERE `peer_id` = %s AND `group_id` = %s", sessionUserId, this.getId()));
    return peersGroup.size() == 1;
  }

  public List<Peer> getMembers()
  {
    List<Peer> peerList = new Peer().queryAll(String.format("SELECT * FROM peers p , peers_groups pg where p.id = pg.peer_id and pg.group_id= %s", this.getId()));
    return peerList;  //To change body of created methods use File | Settings | File Templates.
  }


  //public List<Group> removeDuplicates(List<Group> groups){
    //    List<Group> set = new ArrayList<>(  );
    //    for(int i=0;i<groups.size();i++){
    //        if(!set.contains( groups.get( i ) )){
    //            set.add( groups.get( i ) );
    //        }
    //    }
    //    return set;
    //}


}
