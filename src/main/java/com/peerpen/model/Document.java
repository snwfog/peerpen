package com.peerpen.model;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mysql.jdbc.Statement;
import com.peerpen.model.serializer.Page;
import com.peerpen.model.serializer.Ppedit;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

public class Document extends Taggable implements IModel, Commentable {

    public static final String tableName = "documents";

    @ActiveRecordField
    private String docName;
    @ActiveRecordField
    private String thumbnailPath;
    @ActiveRecordField
    private Date lastModifiedDate;
    @ActiveRecordField
    private Date creationDate;

    @ActiveRelationHasOne
    private Peer peer;
    @ActiveRecordField
    private Integer peerId;
    @ActiveRelationHasMany
    private List<Hunk> hunks;

    @Deprecated
    @ActiveRelationHasMany
    private List<Changeset> changesets;
    @ActiveRelationHasMany
    private List<Comment> comments;

    public Document() {
        super();
    }

    public Document( Map<String, Object> HM ) {
        super( HM );
    }

    public String getDocName() {
        return docName;
    }

    public Peer getPeer() {
        initRelation( "peer" );
        return peer;
    }

    public void setPeerId( Integer peerId ) {
        this.peerId = peerId;
    }

    public int getPeerId() {
        return this.peerId;
    }

    public void setDocName( String docName ) {
        this.docName = docName;
        setUpdateFlag( true );
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath( String thumbnailPath ) {
        this.thumbnailPath = thumbnailPath;
        setUpdateFlag( true );
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate( Date lastModifiedDate ) {
        this.lastModifiedDate = lastModifiedDate;
        setUpdateFlag( true );
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate ) {
        this.creationDate = creationDate;
        setUpdateFlag( true );
    }

    public List<Hunk> getHunks() {
        initRelation( "hunks" );
        return this.hunks;
    }

    public List<Changeset> getChangesets() {
        initRelation( "changesets" );
        return this.changesets;
    }

    public List<Comment> getComments() {
        initRelation( "comments" );
        return this.comments;
    }


    /**
     * Helper method that will create a map of viewId long vs. hunk object itself
     *
     * @return
     */
    public Map<Long, Hunk> getHunkAsMap() {
        return Maps.uniqueIndex( getHunks(), new Function<Hunk, Long>() {
            @Nullable
            @Override
            public Long apply( @Nullable Hunk input ) {
                return Long.parseLong( input.getIdView() );
            }
        } );
    }

    /**
     * Given a set of hunk view Id, this method will remove all the hunk from the database
     * without creating a corresponding changeset object
     *
     * @param hunkViewId
     * @return
     */
    private boolean removeHunk( Set<Long> hunkViewId, boolean createChangeset ) {
        Map<Long, Hunk> hunkMap = this.getHunkAsMap();
        boolean successful = true;
        // FIXME: Using set operation for fun, not too efficient...
        for ( Long viewId : Sets.intersection( hunkMap.keySet(), hunkViewId ) ) {
            Hunk h = hunkMap.get( viewId );
            if ( createChangeset ) {
                h.getChangesets().add( Changeset.getInstanceFromHunk( h, Changeset.ChangesetState.REMOVED ) );
            }

            successful = successful && hunkMap.get( viewId ).destroy();
        }

        return successful;
    }

    /**
     * Candy wrapper
     *
     * @param removeHunks
     */
    private boolean removeHunk( Map<Long, Hunk> removeHunks, boolean createChangeset ) {
        return this.removeHunk( removeHunks.keySet(), createChangeset );
    }

    /**
     * Given a map of hunk, this method will create hunks from the database
     *
     * @param newHunks
     */
    private boolean createHunk( Map<Long, Hunk> newHunks, boolean createChangeset ) {
        boolean successful = true;
        for ( Hunk h : newHunks.values() ) {

            h.setDocumentId( this.getId() );
            // h.setDocument(this);

            if ( h.save() ) {
                if ( createChangeset ) {
                    h.getChangesets().add( Changeset.getInstanceFromHunk( h, Changeset.ChangesetState.REMOVED ) );
                }

                successful = successful && h.save();
            }
        }

        return successful;
    }

    /**
     * Given a map of hunks, this method will update previous hunk from the database
     * without creating a corresponding changeset object
     *
     */
    private boolean updateHunk( Map<Long, Hunk> modifiedHunks, boolean createChangeset ) {
        boolean successful = true;
        Map<Long, Hunk> hunkMap = this.getHunkAsMap();
        for ( Hunk h : modifiedHunks.values() ) {
            if ( createChangeset ) {
                h.getChangesets().add( Changeset.getInstanceFromHunk( h, Changeset.ChangesetState.MODIFIED ) );
            }
            successful = successful && (hunkMap.get( h.getIdView() )).setContent( h.getContent() ).update();
        }

        return successful;
    }


    /**
     * Will update the document from the latest information from Ppedit,
     * this will not create changesets, hence why is private.
     * @param jsonString
     * @return
     */
    public boolean commitDocumentFromRawJson( String jsonString ) {
        Ppedit ppedit = Ppedit.serializeFromJsonString( jsonString );

        boolean successful = true;

        // FIXME: Turn off when finish debugging
        boolean createChangeset = true;

        for ( Page page : ppedit.getRemoved() ) {
            successful = successful && this.removeHunk( page.getHunks(), createChangeset );
        }

        for ( Page page : ppedit.getModified() ) {
            successful = successful && this.updateHunk( page.getHunks(), createChangeset );
        }

        for ( Page page : ppedit.getCreated() ) {
            successful = successful && this.createHunk( page.getHunks(), createChangeset );
        }

        // FIXME: This is wrong
        return successful;
    }

    public List<Comment> getOrderedComments() {
        Integer docId = this.getId();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Comment> comments = new Comment().queryAll(
                "SELECT *, `up_vote` - `down_vote` AS `total_vote` FROM `comments` WHERE document_id= " + docId +
                        " AND changeset_id IS NULL ORDER BY total_vote DESC, last_modified_date DESC" );

        return comments;
    }


    // method used by search
    public List<Document> getMatchedDocuments( String keyword ) {
        String sql = "SELECT * FROM `documents` WHERE `doc_name` LIKE '%" + keyword + "%'";
        List<Document> documents = new Document().queryAll( sql );
        return documents;
    }

    public List<Document> getSuggestions( String keyword, int limit ) {
        String sql = "SELECT * FROM `documents` WHERE `doc_name` LIKE '%" + keyword + "%' LIMIT " + limit;
        return new Document().queryAll( sql );
    }

    @Override
    public void createComment( String message, Peer peer ) {
        Comment.createComment( this, message, peer );
    }

    @Override
    public void findComments() {
        Comment.findComments( this, this.getId() );
    }

    /*
      Used for displaying purposes
     */
    public List<Changeset> getChangeset() {
        List<Changeset> changesetList = new ArrayList<>();
        List<Hunk> hunkList = getHunks();

        for ( Hunk hunk : hunkList ) {
            changesetList.addAll( hunk.getChangesets() );
        }

        return changesetList;
    }


    @Override
    public boolean equals( Object other ) {
        if ( other == null ) {
            return false;
        }
        if ( other == this ) {
            return true;
        }
        if ( !(other instanceof Document) ) {
            return false;
        }

        Document myOther = (Document) other;
        if ( this.getId() == myOther.getId() ) {
            return true;
        }
        return false;
    }

    public static class DocumentSerializer implements JsonSerializer<Document> {

        @Override
        public JsonElement serialize( Document src, Type typeOfSrc, JsonSerializationContext context ) {
            List<Page> documentPages = new ArrayList<>();
            for ( Hunk h : src.getHunks() ) {
                Page p = documentPages.get( h.getPageNumber() );
                if ( p == null ) {
                    p = new Page();
                    documentPages.add( h.getPageNumber(), p );
                }

                p.getHunks().put( Long.valueOf( h.getIdView() ), h );
            }

            Gson gson = (new GsonBuilder()).registerTypeAdapter( Page.class, new Page.PageSerializer() ).create();
            JsonArray documentHunks = new JsonArray();
            for (Page p : documentPages)
                documentHunks.add(gson.toJsonTree( p ));

            return documentHunks;
        }
    }
}
