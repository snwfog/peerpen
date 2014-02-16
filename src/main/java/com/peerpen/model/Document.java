package com.peerpen.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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

public class Document extends Taggable implements IModel, Commentable
{
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

  public Document()
  {
    super();
  }

  public Document(Map<String, Object> HM)
  {
    super(HM);
  }

  public String getDocName()
  {
    return docName;
  }

  public Peer getPeer()
  {
    initRelation("peer");
    return peer;
  }

  public void setPeerId(Integer peerId)
  {
    this.peerId = peerId;
  }

  public int getPeerId()
  {
    return this.peerId;
  }

  public void setDocName(String docName)
  {
    this.docName = docName;
    setUpdateFlag(true);
  }

  public String getThumbnailPath()
  {
    return thumbnailPath;
  }

  public void setThumbnailPath(String thumbnailPath)
  {
    this.thumbnailPath = thumbnailPath;
    setUpdateFlag(true);
  }

  public static void main(String[] args)
  {
      String comm = "content of comment";

    Document d = new Document().find(2);
//    System.out.println("doc test");
//    System.out.println("username:" + d.getPeer().getUserName());
//    System.out.println("hunks:" + d.getHunks());
//    System.out.println("changeset:" + d.getChangesets());
//
//     d.createComment("bitch5");
//     d.deleteComment(2);
      d.findComments();

  }

  public Date getLastModifiedDate()
  {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate)
  {
    this.lastModifiedDate = lastModifiedDate;
    setUpdateFlag(true);
  }

  public Date getCreationDate()
  {
    return creationDate;
  }

  public void setCreationDate(Date creationDate)
  {
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

  private static Connection connection;
  static final Logger logger = LoggerFactory.getLogger(Manager.class);

  static
  {
    try
    {
      connection = Connector.getConnection();
    }
    catch (SQLException e)
    {
      logger.error("Failed statically initiate database connection.");
    }
  }


  public List<Object> getCommentAndChangeset()
  {
    Integer docId = this.getId();
    List<Hunk> hunks1 = this.getHunks();
    List<Object> object = new ArrayList();
    List<Integer> tracker = new ArrayList();
    Integer cs = null;


//      SELECT *  FROM `comments` WHERE `type` IN ('Document','Changeset') AND `object_id` IN (2,5)

    for (Hunk hunk:hunks1)
    {
        List<Changeset> changesets1= hunk.getChangesets();
        for (Changeset changeset: changesets1)
        {
            Integer chId = changeset.getId();
            List<Comment> ChaComments = new Comment().queryAll("SELECT *, `up_vote` - `down_vote` AS `total_vote` FROM `comments` WHERE type='Changeset' AND object_id= " + chId + " ORDER BY total_vote DESC, last_modified_date DESC");


            //try this
            //List<Comment> ChaComments = new Comment().queryAll("SELECT *  FROM `comments` WHERE `type` IN ('Document','Changeset') AND `object_id` IN ("+chId+","+docId+") ORDER BY total_vote DESC, last_modified_date DESC");


//            List<Object> object = new ArrayList();
//            List<Integer> tracker = new ArrayList();
//            Integer cs = null;
            for(Comment comment: ChaComments)
            {
//                Document document = new Document().find(comment.getObjectId());
                cs = comment.getObjectId();
                if(!tracker.contains(comment.getObjectId()))
                {
                    object.add(new Changeset().find(cs));
                    tracker.add(cs);
                }

            }

            if (!tracker.contains(chId)){
                object.add(changeset);
                tracker.add(chId);
            }


        }
    }

    List<Comment> docComments = new Comment().queryAll("SELECT *, `up_vote` - `down_vote` AS `total_vote` FROM `comments` WHERE type='Document' AND object_id= " + docId + " ORDER BY total_vote DESC, last_modified_date DESC");

//    List<Comment> ChaComments = new Comment().queryAll("SELECT *, `up_vote` - `down_vote` AS `total_vote` FROM `comments` WHERE type='Changeset' AND object_id= " + chId + " ORDER BY total_vote DESC, last_modified_date DESC");

//    List<Object> object = new ArrayList();
//    List<Integer> tracker = new ArrayList();
//    Integer cs = null;


    for(Comment comment: docComments)
    {
         object.add(comment);
    }





//    //Dirty...
//    List<Changeset> changesetList = this.getChangesets();
//    for(Changeset changeset : changesetList )
//    {
//      if (!tracker.contains(changeset.getId())){
//        object.add(changeset);
//        tracker.add(changeset.getId());
//      }
//    }
//    return new ArrayList<Object>();
      return object;
  }

  public List<Comment> getOrderedComments()
  {
    Integer docId = this.getId();
    Connection connection = null;
    Statement stmt = null;
    ResultSet rs = null;
    List<Comment> comments = new Comment().queryAll("SELECT *, `up_vote` - `down_vote` AS `total_vote` FROM `comments` WHERE document_id= " + docId + " AND changeset_id IS NULL ORDER BY total_vote DESC, last_modified_date DESC");

    return comments;
  }


  // method used by search
  public List<Document> getMatchedDocuments(String keyword)
  {
    String sql = "SELECT * FROM `documents` WHERE `doc_name` LIKE '%" + keyword + "%'";
    List<Document> documents = new Document().queryAll(sql);
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

    public List<Document> getSuggestions(String keyword, int limit)
    {
        String sql = "SELECT * FROM `documents` WHERE `doc_name` LIKE '%" + keyword + "%' LIMIT " + limit;
        return new Document().queryAll(sql);
    }


    @Override
  public boolean equals (Object other)
  {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof Document)) return false;
    Document myOther = (Document) other;
    if (this.getId() == myOther.getId()) return true;
    return false;
  }


  private static void closeConnection(Connection connection)
  {
    try
    {
      if (!connection.isClosed())
      {
        connection.close();
      }
    }
    catch (SQLException e)
    {
    }
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
