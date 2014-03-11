package com.peerpen.model;

import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.util.List;
import java.util.Map;

//Feedable model will not avoided by base and any parent of Feedable
public class Changeset extends Feedable implements IModel, Commentable
{
  public static final String tableName = "changesets";

  @ActiveRecordField
  private String content;
  @ActiveRelationHasOne
  private Peer peer;
  @ActiveRecordField
  private Integer peerId;
  @ActiveRelationHasOne
  private Hunk hunk;
  @ActiveRecordField
  private Integer hunkId;

  public Changeset()
  {
    super();
  }

  public Changeset(Map<String, Object> HM)
  {
    super(HM);
  }

  public String getContent()
  {
    return content;
  }

  public void setContent(String content)
  {
    this.content = content;
    setUpdateFlag(true);
  }

  public void setPeerId(Integer peerId)
  {
    this.peerId = peerId;
  }

  public Integer getPeerId()
  {
    return this.peerId;
  }

  public Peer getPeer()
  {
    initRelation("peer");
    return peer;
  }

  public void setHunkId(Integer hunkId)
  {
    this.hunkId = hunkId;
    setUpdateFlag(true);
  }

  public int getHunkId()
  {
    return this.hunkId;
  }

  public void setHunk(Hunk hunk)
  {
    this.hunk = hunk;
  }

  public Hunk getHunk()
  {
    initRelation("hunk");
    return hunk;
  }

  public static void main(String[] args)
  {

  }

  public List<Comment> getOrderedComments()
  {
    Integer changesetId = this.getId();
    List<Comment> comments = new Comment().queryAll("SELECT *, `up_vote` - `down_vote` AS `total_vote` FROM `comments` WHERE type='Changeset' AND object_id= " + changesetId + " ORDER BY total_vote DESC, last_modified_date DESC");

    return comments;
  }

  @Override
  public void createComment(String message, Peer peer)
  {
    Comment.createComment(this, message, peer);
  }

  @Override
  public List<Comment> findComments()
  {
    return Comment.findComments(this, this.getId());

  }
}
