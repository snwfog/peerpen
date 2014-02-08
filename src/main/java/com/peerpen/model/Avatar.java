package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.awt.Rectangle;
import java.text.MessageFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Avatar extends Base implements IModel {

    public static final String tableName = "avatars";

    public static String DEFAULT_AVATAR_FILENAME = "default-avatar.jpg";
    public static String AVATAR_DEFAULT_PATH = "";
    public static final String SMALL_FOLDER = "sm";
    public static final String LARGE_FOLDER = "lg";

    @ActiveRecordField
    private String filename;

    @ActiveRelationHasOne
    private Peer peer;

    @ActiveRecordField
    private Integer peerId;

    @ActiveRecordField
    private Double x1;

    @ActiveRecordField
    private Double x2;

    @ActiveRecordField
    private Double y1;

    @ActiveRecordField
    private Double y2;

    public Avatar() {
        super();
    }

    public Avatar( Map<String, Object> HM ) {
        super( HM );
    }

    public static Avatar getDefaultAvatar() {
        Avatar av = new Avatar();
        av.setX1( 0.0 );
        av.setX2( 256.0 );
        av.setY1( 0.0 );
        av.setY2( 256.0 );
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

    public void setViewport( Map<String, String> map ) {
        this.setX1( Double.parseDouble( map.get( "x1" ) ) );
        this.setX2( Double.parseDouble( map.get( "x2" ) ) );
        this.setY1( Double.parseDouble( map.get( "y1" ) ) );
        this.setY2( Double.parseDouble( map.get( "y2" ) ) );
    }

    public String getFilename() {
        return filename;
    }

    public String getServletContextAvatarPath( HttpServletRequest request ) {
        String avatarDir = (String) request.getSession().getServletContext().getAttribute( "avatarDir" );
        return MessageFormat.format( "/{0}/{1}/{2}", avatarDir, Avatar.LARGE_FOLDER, this.getFilename() );
    }

    public void setFilename( String avatarFilename ) {
        this.filename = avatarFilename;
        setUpdateFlag( true );
    }

    public Double getX1() {
        return x1;
    }

    public void setX1( Double x1 ) {
        this.x1 = x1;
        setUpdateFlag( true );
    }

    public Double getX2() {
        return x2;
    }

    public void setX2( Double x2 ) {
        this.x2 = x2;
        setUpdateFlag( true );
    }

    public Double getY1() {
        return y1;
    }

    public void setY1( Double y1 ) {
        this.y1 = y1;
        setUpdateFlag( true );
    }

    public Double getY2() {
        return y2;
    }

    public void setY2( Double y2 ) {
        this.y2 = y2;
        setUpdateFlag( true );
    }
}

