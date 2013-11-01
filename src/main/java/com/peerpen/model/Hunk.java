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

    @ActiveRelationHasOne
    private Integer documentId;
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
        return idView;
    }

    public void setContent(String content) {
        this.content = content;
        setUpdateFlag(true);
    }

    public void setDocId(){
        this.documentId = document.getId();
    }

    public Integer getDocId(){
          return this.documentId;
    }

    public void setDocument(Document document){
        this.document = document;
    }

    public Document getDocument(){
        if(document == null){
           document = Document.find(this.getDocId());
        }
        return document;
    }

    public Changeset[] getChangesets()
    {
        HashMap<String, Object> condition = new HashMap<String, Object>();
        condition.put("hunkId", this.getId());

        ArrayList<HashMap<String, Object>> foundChangesets = Manager.findAll("changesets", condition);
        int size = foundChangesets.size();
        changesets = new Changeset[size - 1];

        for (int i = 0; i < size; i++)
        {
            Changeset c = new Changeset(foundChangesets.get(i));
            changesets[i] = c;
        }
        return changesets;
    }


}
