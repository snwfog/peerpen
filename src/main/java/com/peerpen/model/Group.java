package com.peerpen.model;

import com.google.common.collect.Maps;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRelationHasOne;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;
import com.sunnyd.annotations.ActiveRelationManyToMany;

import java.util.List;
import java.util.Map;

public class Group extends Taggable implements IModel
{


  public static final String tableName = "groups";

  @ActiveRecordField
  private String groupName;

  @ActiveRecordField
  private Integer adminId;

  @ActiveRecordField
  private String description;

  @ActiveRelationManyToMany(relationTable = "peers_groups")
  private List<Peer> peers;

  @ActiveRelationHasMany
  private List<Broadcast> broadcasts;

  public Group()
  {
    super();
  }

  public Group(Map<String, Object> HM)
  {
    super(HM);
  }

  public String getGroupName()
  {
    return groupName;
  }

  public void setGroupName(String groupName)
  {
    this.groupName = groupName;
    setUpdateFlag(true);
  }

  public Integer getAdminId()
  {
    return adminId;
  }

  public void setAdminId(Integer adminId)
  {
    this.adminId = adminId;
    setUpdateFlag(true);
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
    setUpdateFlag(true);
  }

  public String getShortDescription()
  {
    if (this.getDescription().length() >= 100)
      return this.getDescription().substring(0, 100) + "...";
    else return this.getDescription();
  }

  public List<Group> getMatchedGroups(String keyword)
  {
    String sql = "SELECT * FROM `groups` WHERE `group_name` LIKE '%" + keyword + "%'";
    List<Group> groups = new Group().queryAll(sql);
    return groups;
  }

  public List<Group> getGroups()
  {
    return new Group().queryAll("SELECT * FROM `groups`");
  }

  public List<Group> getSortedGroups(String sort, Integer userId)
  {
    switch (sort)
    {
    case "az": // sort A - Z
      return new Group().queryAll("SELECT * FROM `groups` ORDER BY group_name ASC;");
    case "za": // sort Z - A
      return new Group().queryAll("SELECT * FROM `groups` ORDER BY group_name DESC;");
    case "fc": // sort first creation date
      return new Group().queryAll("SELECT * FROM `groups` ORDER BY creation_date DESC;");
    case "lc": // sort last creation date
      return new Group().queryAll("SELECT * FROM `groups` ORDER BY creation_date ASC;");
    case "mp": // sort greater number of people
      return new Group().queryAll("SELECT *\n" +
          "FROM (SELECT * FROM groups) g\n" +
          "LEFT JOIN (SELECT group_id, count(*) AS `num` FROM peers_groups GROUP BY group_id) pg\n" +
          "ON g.id = pg.group_id\n" +
          "ORDER BY num DESC");
    case "lp": // sort least number of people
      return new Group().queryAll("SELECT *\n" +
          "FROM (SELECT * FROM groups) g\n" +
          "LEFT JOIN (SELECT group_id, count(*) AS `num` FROM peers_groups GROUP BY group_id) pg\n" +
          "ON g.id = pg.group_id\n" +
          "ORDER BY num ASC");
    case "pd":
      return new Group().queryAll(String.format("SELECT * \n" +
          "FROM groups g \n" +
          "INNER JOIN joingroups jg\n" +
          "ON g.id=jg.group_id AND jg.peer_id = %s", userId));
    default:
      return new Group().queryAll("SELECT * FROM `groups` ORDER BY group_name ASC;");
    }
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

  public List<Group> getSuggestions(String keyword, int limit)
  {
    String sql = "SELECT * FROM `groups` WHERE `group_name` LIKE '%" + keyword + "%' LIMIT " + limit;
    return new Group().queryAll(sql);
  }

  public static void main(String[] args)
  {
  }

  public boolean getIsJoined(Integer sessionUserId)
  {
    return this.getPeers().contains(new Peer().find(sessionUserId));
  }

  public List<Peer> getPeers()
  {
    initRelation("peers");
    return this.peers;
  }

  public Group setPeers(List<Peer> peers)
  {
    this.peers = peers;
    this.setUpdateFlag(true);
    return this;
  }

  public void removePeer(Peer peer)
  {
    this.getPeers().remove(peer);
    setUpdateFlag(true);
  }

  public void addPeer(Peer peer)
  {
    this.getPeers().add(peer);
    setUpdateFlag(true);
  }

  public List<Broadcast> getBroadcasts()
  {
    initRelation("broadcasts");
    return this.broadcasts;
  }

  public List<Broadcast> getOrderedBroadcast()
  {
    List<Broadcast> broadcasts = new Broadcast().queryAll("SELECT * FROM `broadcasts` WHERE group_id= " + this.getId() + " ORDER BY last_modified_date DESC");
    return broadcasts;
  }

  public boolean getPending(Integer sessionUserId)
  {
    Map<String, Object> hm = Maps.newHashMap();
    hm.put("peerId", sessionUserId);
    hm.put("groupId", this.getId());
    Joingroup joingroup = new Joingroup().find(hm);

    return joingroup != null;
  }

  public List<Joingroup> getRequests()
  {
    List<Joingroup> joingroups = new Joingroup().queryAll("SELECT * FROM `joingroups` WHERE group_id= " + this.getId() + " ORDER BY last_modified_date DESC");
    return joingroups;
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

  @Override
  public boolean equals(Object other)
  {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof Group)) return false;
    Group myOther = (Group) other;
    if (this.getId() == myOther.getId()) return true;
    return false;
  }

  @Override
  public int hashCode()
  {
    return new HashCodeBuilder().append(this.getId()).toHashCode();
  }
}
