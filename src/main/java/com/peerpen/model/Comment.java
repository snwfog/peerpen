package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.*;

import java.util.HashMap;

public class Comment extends Base implements IModel {

    public static final String tableName = "comments";

    @ActiveRecordField
    private String message;
    @ActiveRecordField
    private Integer peerId;
    @ActiveRelationHasOne
    private Peer peer;
    @ActiveRecordField
    private Integer documentId;
    @ActiveRelationHasOne
    private Document document;
    @ActiveRecordField
    private Integer changesetId;
    @ActiveRelationHasOne
    private Changeset changeset;


    public Comment() {
        super();
    }

    public Comment(HashMap<String, Object> HM) {
        super(HM);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        setUpdateFlag(true);
    }

  //testing purposes only
  private String name;
  public void setName(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

    public Peer getPeer(){
        initRelation("peer");
        return peer;
    }

    public void setPeerId(Integer peerId){
        this.peerId = peerId;
    }

    public int getPeerId(){
        return this.peerId;
    }

    public Document getDocument(){
        initRelation("document");
        return document;
    }

    public void setDocumentId(Integer documentId){
        this.documentId = documentId;
    }

    public int getDocumentId(){
        return this.documentId;
    }

    public Changeset getChangeset(){
        initRelation("changeset");
        return changeset;
    }

    public void setChangesetId(Integer changesetId){
        this.changesetId = changesetId;
    }

    public int getChangesetId(){
        return this.changesetId;
    }
}
