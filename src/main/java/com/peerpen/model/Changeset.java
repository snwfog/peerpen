package com.peerpen.model;

import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.util.List;
import java.util.Map;

//Feedable model will not avoided by base and any parent of Feedable
public class Changeset extends Feedable implements IModel, Commentable {

    public static final String tableName = "changesets";

    public static enum ChangesetState {
        REMOVED( 0 ),
        MODIFIED( 1 ),
        CREATED( 2 );

        private final int state;

        private ChangesetState( int state ) {
            this.state = state;
        }

        public int getValue() {
            return this.state;
        }
    }

    @ActiveRecordField
    private String content;


    /**
     * Author of this changeset
     */
    @ActiveRelationHasOne
    private Peer peer;

    @ActiveRecordField
    private Integer peerId;

    @ActiveRelationHasOne
    private Hunk hunk;

    @ActiveRecordField
    private Integer hunkId;

    @ActiveRecordField
    private Integer state;

    @ActiveRecordField
    private Boolean isApplied;

    @ActiveRelationHasMany
    private List<Comment> comments;

    public Changeset() {
        super();
    }

    public Changeset( Map<String, Object> HM ) {
        super( HM );
    }

    public String getContent() {
        return content;
    }

    public void setContent( String content ) {
        this.content = content;
        setUpdateFlag( true );
    }


    public void setPeerId(Integer peerId)
    {
        this.peerId = peerId;
        setUpdateFlag( true );
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

    public Boolean getIsApplied() {
        return isApplied;
    }

    public void setIsApplied( Boolean isApplied ) {
        this.isApplied = isApplied;
    }

    public void setHunkId( Integer hunkId ) {
        this.hunkId = hunkId;
    }

    public int getHunkId() {
        return this.hunkId;
    }

    public Hunk getHunk() {
        initRelation( "hunk" );
        return hunk;
    }


    public void setHunk( Hunk h ) {
        this.hunk = h;
        setUpdateFlag( true );
    }

    public List<Comment> getComments() {
        initRelation( "comments" );
        return this.comments;
    }

    public Integer getState() {
        return state;
    }

    public void setState( Integer state ) {
        this.state = state;
        setUpdateFlag( true );
    }

    public List<Comment> getOrderedComments() {
        return new Comment().queryAll(
                "SELECT *, `up_vote` - `down_vote` AS `total_vote` FROM `comments` WHERE document_id= " +
                        this.getHunk().getDocument().getId() + " AND changeset_id= " + this.getId() +
                        " ORDER BY total_vote DESC, last_modified_date DESC" );
    }

    public static Changeset getInstanceFromHunk( Hunk h, ChangesetState state ) {
        Changeset changeset = new Changeset();
        changeset.setContent( h.getContent() );
        changeset.setHunk( h );
        changeset.setHunkId( h.getId() );

        changeset.setState( state.getValue() );
        changeset.setIsApplied( false );
        changeset.save();
        return changeset;
    }

    @Override
    public void createComment( String message, Peer peer ) {
        Comment.createComment( this, message, peer );
    }

    @Override
    public void findComments() {
        Comment.findComments( this, this.getId() );
    }
}
