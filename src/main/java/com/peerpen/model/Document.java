package com.peerpen.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRelationHasMany;
import com.sunnyd.annotations.ActiveRelationHasOne;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.database.Connector;
import com.sunnyd.database.Manager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sunnyd.database.Manager.convertJavaToSql;
import static com.sunnyd.database.Manager.convertSQLToJava;

public class Document extends Base implements IModel {
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
    @ActiveRelationHasMany
    private List<Changeset> changesets;
    @ActiveRelationHasMany
    private List<Comment> comments;
    
    
    public Document() {
        super();
    }

    public Document(Map<String, Object> HM) {
        super(HM);
    }

    public String getDocName() {
        return docName;
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
    
    public void setDocName(String docName) {
        this.docName = docName;
        setUpdateFlag(true);
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
        setUpdateFlag(true);
    }
    
    public static void main(String[] args) {
        //Document d = new Document();
        //d.setDocName("mydoc");
        //d.setPeerId(4);
        //System.out.println(d.save());

        Document d = new Document().find(16);
        System.out.println(d.getPeer());
        System.out.println(d.getPeer().getLastName());
        System.out.println(d.getHunks());
        System.out.println(d.getChangesets());

        //System.out.println(d.getPeer().getCreationDate());
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        setUpdateFlag(true);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        setUpdateFlag(true);
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

    public List<Hunk> getHunks()
    {
        initRelation("hunks");
        return this.hunks;
    }

    public List<Changeset> getChangesets()
    {
        initRelation("changesets");
        return this.changesets;
    }

    public List<Comment> getComments()
    {
        initRelation("comments");
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

    public List<Comment> getDocumentCommentsByOrder()
       {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Comment> comments = new ArrayList<Comment>();
        try {
            connection = Connector.getConnection();
            stmt = connection.createStatement();
            //rs = stmt.executeQuery( "SELECT * FROM " + tableName + " WHERE ID = " + id );
            rs = stmt.executeQuery( "SELECT id FROM `comments` ORDER BY last_modified_date DESC");

            //List<Object> objects = new ArrayList<Object>();

            while (rs.next()) {
//                String coffeeName = rs.getString("COF_NAME");
//                int supplierID = rs.getInt("SUP_ID");
//                float price = rs.getFloat("PRICE");
//                int sales = rs.getInt("SALES");
//                int total = rs.getInt("TOTAL");
//                System.out.println(coffeeName + "\t" + supplierID +
//                        "\t" + price + "\t" + sales +
//                        "\t" + total);
                Map<String, Object> row = new HashMap<String, Object>();
                row = Manager.convertSQLToJava( rs );
                //comments.add(row);
                Comment c = new Comment(row);
                comments.add(c);
            }


        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            closeConnection( connection );  }
           System.out.println("bbbbbbbbbbbbbbb"+comments);
    return comments;
    }

    private static void closeConnection( Connection connection ) {
        try {
            if ( !connection.isClosed() ) {
                connection.close();
            }
        } catch ( SQLException e ) {

        }

    }


}
