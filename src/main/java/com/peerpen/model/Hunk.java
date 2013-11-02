package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.*;
import com.sunnyd.database.Manager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Mike
 * Date: 11/1/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hunk extends Base implements IModel {
    public static final String tableName = "hunks";

    @ActiveRecordField
    private String idView;

    @ActiveRecordField
    private String content;

    @ActiveRecordField
    private Integer documentId;

    @ActiveRelationHasOne
    private Document document;

    @ActiveRelationHasMany
    private Changeset[] changesets;

    public Hunk() {
        super();
    }

    public Hunk(HashMap<String, Object> HM) {
        super(HM);
    }

    public String getIdView() {
        return idView;
    }

    public void setIdView(String idView) {
        this.idView = idView;
        setUpdateFlag(true);
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

    /*public void setDocument(Document document){
        this.document = document;
        setUpdateFlag(true);
    } */

    public Document getDocument(){
        if(document == null){
           HashMap<String, Object> foundDocument = Manager.find(documentId, "documents");
           this.document = new Document(foundDocument);
        }
        return document;
    }

    public Changeset[] getChangesets()
    {
        HashMap<String, Object> condition = new HashMap<String, Object>();
        condition.put("hunkId", this.getId());

        ArrayList<HashMap<String, Object>> foundChangesets = Manager.findAll("changesets", condition);
        int size = foundChangesets.size();
        changesets = new Changeset[size];

        for (int i = 0; i < size; i++)
        {
            Changeset c = new Changeset(foundChangesets.get(i));
            changesets[i] = c;
        }
        return changesets;
    }
    public static void main(String[] args) {
        Hunk h = new Hunk();
        h.setContent("ababaabbbabbababb");
        h.setDocumentId(4);
        System.out.println(h.save());

        Hunk a = Hunk.find(h.getId());
//        Document d = Document.find(4);
        System.out.println("lplplplp"+a.getContent());
        System.out.println(a.getDocument().getDocName());

    }



}
