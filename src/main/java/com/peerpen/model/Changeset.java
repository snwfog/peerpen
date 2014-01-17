package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.*;
import com.sunnyd.database.Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Feedable model will not avoided by base and any parent of Feedable
public class Changeset extends Feedable implements IModel {
    public static final String tableName = "changesets";

    @ActiveRecordField
    private String content;

    @ActiveRelationHasOne
    private Document document;

    @ActiveRelationHasOne
    private Peer peer;

    @ActiveRecordField
    private Integer peerId;

    @ActiveRecordField
    private Integer documentId;

    @ActiveRelationHasOne
    private Hunk hunk;

    @ActiveRecordField
    private Integer hunkId;

    @ActiveRelationHasMany
    private List<Comment> comments;

    public Changeset() {
        super();
    }

    public Changeset(Map<String, Object> HM) {
        super(HM);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        setUpdateFlag(true);
    }

    public void setDocumentId(Integer documentId){
        this.documentId = documentId;
    }

    public Integer getDocumentId(){
        return this.documentId;
    }

    public void setDocument(Document document){
        this.document = document;
    }

    public Document getDocument(){
        initRelation("document");
        return document;
    }

    public void setPeerId(Integer peerId){
        this.peerId = peerId;
    }

    public Integer getPeerId(){
        return this.peerId;
    }

    public Peer getPeer(){
        initRelation("peer");
        return peer;
    }

    public void setHunkId(Integer hunkId){
        this.hunkId = hunkId;
    }

    public int getHunkId(){
        return this.hunkId;
    }

    public Hunk getHunk(){
        initRelation("hunk");
        return hunk;
    }

    public List<Comment> getComments()
    {
        initRelation("comments");
        return this.comments;
    }

    public static void main(String[] args) {
//        //Changeset c = new Changeset();
//        /*c.setContent("aaaaaaaaaaa");
//        c.setHunkId(27);
//        c.setDocumentId(2);
//        c.setPeerId(2);
//        System.out.println(c.save()); */
//
////        Document d = Document.find(4);
//        Changeset c = new Changeset().find(11);
//        System.out.println(c.getContent());
//        System.out.println(c.getHunk().getContent());
//        System.out.println(c.getDocument().getDocName());
//        System.out.println(c.getPeer().getUserName());
//
//
//        //System.out.println(d.getPeer().getCreationDate());

    }

    public List<Comment> getChangesetCommentsByOrder(Integer docId, Integer changesetId)
    {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Comment> comments = new Comment().queryAll("SELECT *, `up_vote` - `down_vote` AS `total_vote` FROM `comments` WHERE document_id= "+ docId +" AND changeset_id= "+ changesetId +" ORDER BY total_vote DESC, last_modified_date DESC");

        return comments;
    }






}
