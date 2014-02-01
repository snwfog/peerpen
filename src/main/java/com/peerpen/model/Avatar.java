package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.awt.Rectangle;
import java.util.Map;

public class Avatar extends Base implements IModel {

    public static final String tableName = "avatars";

    private static String DEFAULT_AVATAR_FILENAME = "default-avatar.jpg";

    @ActiveRecordField
    private String filename;

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

    public Avatar( Map<String, Object> HM ) {
        super( HM );
    }

    public static Avatar getDefaultAvatar() {
        Avatar av = new Avatar();
        av.setX1( 0 );
        av.setX2( 256 );
        av.setY1( 0 );
        av.setY2( 256 );
        av.setFilename( DEFAULT_AVATAR_FILENAME );

        return av;
    }



    public Peer getPeer() {
        initRelation( "peer" );
        return peer;
    }

    public Integer getPeerId() {
        return peerId;
    }

    public void setPeerId( Integer peerId ) {
        this.peerId = peerId;
        setUpdateFlag( true );
    }

    public Rectangle getViewport() {
        return new Rectangle( x1, y1, x2 - x1, y2 - y1 );
    }

    public void setViewport( Map<String, String> map ) {
        this.setX1( Integer.parseInt( map.get( "x1" ) ) );
        this.setX2( Integer.parseInt( map.get( "x2" ) ) );
        this.setY1( Integer.parseInt( map.get( "y1" ) ) );
        this.setY2( Integer.parseInt( map.get( "y2" ) ) );
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename( String avatarFilename ) {
        this.filename = avatarFilename;
        setUpdateFlag( true );
    }

    public Integer getX1() {
        return x1;
    }

    public void setX1( Integer x1 ) {
        this.x1 = x1;
        setUpdateFlag( true );
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2( Integer x2 ) {
        this.x2 = x2;
        setUpdateFlag( true );
    }

    public Integer getY1() {
        return y1;
    }

    public void setY1( Integer y1 ) {
        this.y1 = y1;
        setUpdateFlag( true );
    }

    public Integer getY2() {
        return y2;
    }

    public void setY2( Integer y2 ) {
        this.y2 = y2;
        setUpdateFlag( true );
    }
}

