package com.peerpen.model;

import com.sunnyd.IModel;
import com.sunnyd.annotations.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;


//Feedable model will not avoided by base and any parent of Feedable
public class Changeset extends Feedable implements IModel, Commentable {
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
        setUpdateFlag(true);
    }

    public int getHunkId(){
        return this.hunkId;
    }

    public void setHunk(Hunk hunk){
        this.hunk = hunk;
    }

    public Hunk getHunk(){
        initRelation("hunk");
        return hunk;
    }

    public static void main(String[] args) {

        Changeset c = new Changeset().find(5);
        c.createComment("bitch3");

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


//            Changeset a = new Changeset();
//            a.setDocumentId(1);
//            a.setPeerId(1);
//            a.setHunkId(1);
//            a.setContent("DONT DO IT");
//            a.save();

//          Comment com = new Comment();
//          com.setMessage("faggot stick");
//          com.setDocumentId(1);
//          com.setPeerId(1);
//          com.save();


//        for( Feedable f : Feedable.getFeed(1)){
//            System.out.println(f.reveal().getLastModifiedDate().toString());
//        }



    }

    public List<Comment> getOrderedComments()
    {
      Integer changesetId = this.getId();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Comment> comments = new Comment().queryAll("SELECT *, `up_vote` - `down_vote` AS `total_vote` FROM `comments` WHERE type='Changeset' AND object_id= "+ changesetId +" ORDER BY total_vote DESC, last_modified_date DESC");

        return comments;
    }


    @Override
    public void createComment(String message) {
        Comment.createComment(this, message);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment.deleteComment(commentId);
    }

    @Override
    public void findComments() {
        Comment.findComments(this,this.getId());
    }
}
