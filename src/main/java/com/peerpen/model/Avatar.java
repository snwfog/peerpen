package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.sql.Blob;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Wais
 * Date: 11/15/13
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Avatar extends Base implements IModel {

    public static final String tableName = "avatars";

    @ActiveRecordField
    private String imgTitle;

    @ActiveRecordField
    private Blob imgData;

    @ActiveRelationHasOne
    private Peer peer;

    @ActiveRecordField
    private Integer peerId;


    public Avatar() {
        super();
    }

    public Avatar(Map<String, Object> HM) {
        super(HM);
    }


    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public Blob getImgData() {
        return imgData;
    }

    public void setImgData(Blob imgData) {
        this.imgData = imgData;
    }

    public Peer getPeer(){
        initRelation("peer");
        return peer;
    }

    public Integer getPeerId() {
        return peerId;
    }

    public void setPeerId(Integer peerId) {
        this.peerId = peerId;
    }
}
