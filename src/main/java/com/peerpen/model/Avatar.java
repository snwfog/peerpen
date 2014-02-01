package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.awt.*;
import java.awt.geom.Rectangle2D;
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
    private String avatarFilename;

    @ActiveRelationHasOne
    private Peer peer;

    @ActiveRecordField
    private Integer peerId;

    @ActiveRecordField
    private Integer x1;

    @ActiveRecordField
    private Integer x2;

    @ActiveRecordField
    private Integer y1;

    @ActiveRecordField
    private Integer y2;

    public Avatar() {
        super();
    }

    public Avatar(Map<String, Object> HM) {
        super(HM);
    }


    public String getAvatarFileName() {
        return avatarFilename;
    }

    public void setAvatarFileName(String avatarFileName) {
        this.avatarFilename = avatarFileName;
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

    public Rectangle getAvatarViewport() { return new Rectangle(x1, y1, x2 - x1, y2 - y1); }

    public String getAvatarFilename() {
        return avatarFilename;
    }

    public void setAvatarFilename(String avatarFilename) {
        this.avatarFilename = avatarFilename;
    }

    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = x1;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    public Integer getY1() {
        return y1;
    }

    public void setY1(Integer y1) {
        this.y1 = y1;
    }

    public Integer getY2() {
        return y2;
    }

    public void setY2(Integer y2) {
        this.y2 = y2;
    }
}

