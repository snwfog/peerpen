package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

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
    private ArrayList<Changeset> changesets;

    public Hunk() {
        super();
    }

    public Hunk( Map<String, Object> HM ) {
        super( HM );
    }


    public String getIdView() {
        return idView;
    }

    public void setIdView( String idView ) {
        this.idView = idView;
        setUpdateFlag( true );
    }

    public String getContent() {
        return content;
    }

    public void setContent( String content ) {
        this.content = content;
        setUpdateFlag( true );
    }

    public Integer getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId( Integer documentId ) {
        this.documentId = documentId;
    }

    public Document getDocument() {
        initRelation( "document" );
        return document;
    }

    public void setDocument( Document document ) {
        this.documentId = document.getId();
        this.document = null;
        setUpdateFlag( true );
    }

    public ArrayList<Changeset> getChangesets() {
        initRelation( "changesets" );
        return this.changesets;
    }
    public static void main(String[] args) {
        Hunk h = new Hunk();
        h.setContent("ababaabbbabbababb");
        h.setDocumentId(4);
        System.out.println(h.save());

//        Hunk a = Hunk.find(h.getId());
//        System.out.println("lplplplp"+a.getContent());
//        System.out.println(a.getDocument().getDocName());

    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( getContent());
        sb.replace(0, 1, "");
        sb.replace( sb.length() - 1, sb.length(), "");
        String obj = getIdView() + ":\"" + StringEscapeUtils.escapeJava(sb.toString()) + "\"";
        return obj;
    }
}
