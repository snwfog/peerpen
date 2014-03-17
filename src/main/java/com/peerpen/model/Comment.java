package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comment extends Feedable implements IModel {

    public static final String tableName = "comments";

    @ActiveRecordField
    private String message;
    @ActiveRecordField
    private Integer objectId;
    @ActiveRecordField
    private String type;
    @ActiveRecordField
    private Integer upVote;
    @ActiveRecordField
    private Integer downVote;
    @ActiveRecordField
    private Integer posterPeerId;
    @ActiveRelationHasOne(idFieldName = "posterPeerId")
    private Peer posterPeer;

    private Integer totalVote;

    public Integer getPosterPeerId() {
        return posterPeerId;
    }

    public void setPosterPeerId( Integer posterPeerId ) {
        this.posterPeerId = posterPeerId;
        setUpdateFlag( true );
    }

    public Peer getPosterPeer() {
        initRelation( "posterPeer" );
        return posterPeer;
    }

    public Peer setPosterPeer( Peer peer ) {
        posterPeer = peer;
        this.setUpdateFlag( true );
        return posterPeer;
    }


    public Comment() {
        super();
        this.upVote = 0;
        this.downVote = 0;
    }

    public Comment( Map<String, Object> HM ) {
        super( HM );
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
        setUpdateFlag( true );
    }

    //  //testing purposes only
    //  private String name;
    //
    //  public void setName(String name)
    //  {
    //    this.name = name;
    //  }
    //
    //  public String getName()
    //  {
    //    return name;
    //  }


    public Integer getUpVote() {
        return upVote;
    }

    public void setUpVote( Integer upVote ) {
        this.upVote = upVote;
        setUpdateFlag( true );
    }

    public void upVote() {
        this.upVote++;
        setUpdateFlag( true );
    }

    public Integer getDownVote() {
        return downVote;
    }

    public void setDownVote( Integer downVote ) {
        this.downVote = downVote;
        setUpdateFlag( true );
    }

    public void downVote() {
        this.downVote++;
        setUpdateFlag( true );
    }

    @Override
    public boolean save() { //use this method for now, until Mike change it in the BASE
        return super.save();
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
        setUpdateFlag( true );
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId( Integer objectId ) {
        this.objectId = objectId;
        setUpdateFlag( true );
    }

    public static void createComment( Commentable commentable, String message, Peer posterPeer ) {
        Comment comment = new Comment();

        comment.setType( commentable.getClass().getSimpleName() );
        comment.setObjectId( ((Base) commentable).getId() );
        comment.setMessage( message );
        comment.setPosterPeerId( posterPeer.getId() );
        comment.save();
    }


    public static void deleteComment( Integer commentId ) {
        Comment comment = new Comment().find( commentId );
        comment.destroy();
    }

    public static List<Comment> findComments( Object object, Integer objectId ) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put( "type", object.getClass().getSimpleName() );
        map.put( "object_id", objectId );
        List<Comment> comments = new Comment().findAll( map );
        return comments;

    }
}
