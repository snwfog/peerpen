package com.peerpen.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;
import com.sunnyd.annotations.ActiveRelationHasOne;
import com.sunnyd.database.Connector;
import com.sunnyd.database.Manager;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Document extends Taggable implements IModel {

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
    //@ActiveRecordField
    //private String class;
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

    //
    //public String getDocType() {
    //    return docType;
    //}
    //
    //public void setDocType(String type) {
    //    if(type.toLowerCase().trim().contentEquals("resume") | type.toLowerCase().trim().contentEquals("coverLetter")){
    //        this.docType = type;
    //        setUpdateFlag(true);
    //    }
    //}

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

    private static Connection connection;
    static final Logger logger = LoggerFactory.getLogger( Manager.class );

    static {
        try {
            connection = Connector.getConnection();
        } catch ( SQLException e ) {
            logger.error( "Failed statically initiate database connection." );
        }
    }


    public List<Object> getCommentAndChangeset() {
        Integer docId = this.getId();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Comment> comments = new Comment().queryAll(
                "SELECT *, `up_vote` - `down_vote` AS `total_vote` FROM `comments` WHERE document_id= " + docId +
                        " ORDER BY total_vote DESC, last_modified_date DESC" );
        List<Object> object = new ArrayList();
        List<Integer> tracker = new ArrayList();
        Integer cs = null;
        for ( Comment comment : comments ) {
            cs = comment.getChangesetId();
            if ( (cs != null) && !tracker.contains( comment.getChangesetId() ) ) {
                object.add( new Changeset().find( cs ) );
                tracker.add( cs );
            } else {
                object.add( comment );
            }
        }
        //Dirty...
        List<Changeset> changesetList = this.getChangesets();
        for ( Changeset changeset : changesetList ) {
            if ( !tracker.contains( changeset.getId() ) ) {
                object.add( changeset );
                tracker.add( changeset.getId() );
            }
        }
        return object;
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

    // method used for search autocomplete
    //public List<String> getSuggestedDocuments(String keyword, int limit)
    //{
    //  String sql = "SELECT `doc_name` FROM `documents` WHERE `doc_name` LIKE '%" + keyword + "%' LIMIT " + limit;
    //  List<Document> documents = new Document().queryAll(sql);
    //  // store only doc_name to list
    //  List<String> suggestions = new ArrayList<String>();
    //  for (int i = 0; i < documents.size(); i++)
    //  {
    //    suggestions.add(documents.get(i).getDocName());
    //  }
    //  return suggestions;
    //}

    public List<Document> getSuggestions( String keyword, int limit ) {
        String sql = "SELECT * FROM `documents` WHERE `doc_name` LIKE '%" + keyword + "%' LIMIT " + limit;
        return new Document().queryAll( sql );
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


    public static class DocumentDeserializer implements JsonSerializer<Document> {

        @Override
        public JsonElement serialize( Document src, Type typeOfSrc, JsonSerializationContext context ) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty( "hunks", context.serialize( src.hunks ).toString() );

            return jsonObject;
        }
    }

    public static void main( String[] args ) {
        Document d = new Document().find( 2 );
        d.getHunks();

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter( Document.class, new DocumentDeserializer() );
        builder.registerTypeAdapter( Hunk.class, new Hunk.HunkSerializer() );

        Gson gson = builder.create();

        gson.toJson( d, System.out );

    }
}
