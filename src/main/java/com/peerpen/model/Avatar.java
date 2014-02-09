package com.peerpen.model;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.peerpen.framework.InternalHttpServletRequest;
import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Avatar extends Base implements IModel {

    public static final String tableName = "avatars";
    static final Logger logger = LoggerFactory.getLogger( Avatar.class );

    public static String DEFAULT_AVATAR_FILENAME = "default-avatar.jpg";
    public static String AVATAR_DEFAULT_PATH = "";

    // Defined inside of JSP
    public static final int VIEWPORT_WIDTH = 512;
    public static final int VIEWPORT_HEIGHT = 512;

    // Avatar size
    public static enum Size {
        SMALL( 16 ),
        MEDIUM( 32 ),
        LARGE( 64 ),
        HUGE( 128 );

        private int dimension;

        Size( int s ) {
            dimension = s;
        }

        public int getDimension() {
            return dimension;
        }

        public String getFolderName() {
            return this.name().toLowerCase();
        }
    }

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

    @ActiveRecordField
    private Double originalWidth;

    @ActiveRecordField
    private Double originalHeight;

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

    public String getFilename() {
        return filename;
    }

    public String getServletContextAvatarPath( HttpServletRequest request ) {
        return this.getServletContextAvatarPathForSize( request, Size.LARGE );
    }

    public String getServletContextAvatarPathForSize( HttpServletRequest request, Avatar.Size size ) {
        String avatarDir = (String) request.getSession().getServletContext().getAttribute( "avatarDir" );
        return MessageFormat.format( "/{0}/{1}/{2}", avatarDir, size.getFolderName(), this.getFilename() );
    }

    public String getRelativeServletContextAvatarPath( HttpServletRequest request ) {
        return this.getServletContextAvatarPath( request );
    }

    public String getRelativeServletContextAvatarPathForSize( HttpServletRequest request, Size size ) {
        return this.getServletContextAvatarPathForSize( request, size );
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

    public void rescaleAvatar( Object parameters ) {

    }

    public Double getOriginalWidth() {
        return originalWidth;
    }

    public void setOriginalWidth( Double original_width ) {
        this.originalWidth = original_width;
        this.setUpdateFlag( true );
    }

    public Double getOriginalHeight() {
        return originalHeight;
    }

    public void setOriginalHeight( Double original_height ) {
        this.originalHeight = original_height;
        this.setUpdateFlag( true );
    }

    public void setOriginalSize( double width, double height ) {
        this.setOriginalWidth( width );
        this.setOriginalHeight( height );
    }

    public void setOriginalSize( Map<String, String> map ) {
        this.setOriginalWidth( Double.parseDouble( map.get( "original-width" ) ) );
        this.setOriginalHeight( Double.parseDouble( map.get( "original-height" ) ) );
    }

    public void cropAvatarFromParameter( HttpServletRequest request ) {

    }

    public String getAvatarExtension( String path ) {
        ContentInfo info = ContentInfoUtil.findExtensionMatch( path );
        return info.getFileExtensions().length > 0 ? info.getFileExtensions()[0] : "";
    }

    public void cropAvatar( HttpServletRequest request ) {
        this.setCoordinate( request );
        double inversedX1 = this.originalWidth / this.VIEWPORT_WIDTH * this.x1;
        double inversedX2 = this.originalWidth / this.VIEWPORT_WIDTH * this.x2;
        double inversedY1 = this.originalWidth / this.VIEWPORT_HEIGHT * this.y1;
        double inversedY2 = this.originalWidth / this.VIEWPORT_HEIGHT * this.y2;

        try {
            // FIXME: Better pathing
            File largeAvatarFile = new File(
                    MessageFormat.format( "{0}/{1}", request.getSession().getServletContext().getRealPath( "" ),
                            this.getServletContextAvatarPath( request ) ) );
            BufferedImage outImage = ImageIO.read( largeAvatarFile );
            BufferedImage croppedImageBuffer =
                    outImage.getSubimage( (int) inversedX1, (int) inversedY1, (int) (inversedX2 - inversedX1),
                            (int) (inversedY2 - inversedY1) );

            String originalImageRelativePath = this.getRelativeServletContextAvatarPath( request );
            String fileExtension = this.getAvatarExtension( originalImageRelativePath );
            File croppedImage = new File( MessageFormat.format( "{0}/{1}"
                    , request.getSession().getServletContext().getRealPath( "" )
                    , this.getRelativeServletContextAvatarPathForSize( request, Size.MEDIUM ) ) );

            ImageIO.write( croppedImageBuffer, fileExtension, croppedImage );
            logger.info( "Success saving cropped image for size " + "large" + " at " + croppedImage.getPath() );

        } catch ( IOException e ) {
            logger.error( "Error copping avatar for " + this.getPeer() );
        } finally {
            this.update();
        }

    }

    private void setCoordinate( HttpServletRequest request ) {
        Map<String, Object> parameters = ((InternalHttpServletRequest) request).getParametersMap();
        if ( parameters.containsKey( "x1" ) ) {
            this.setX1( Double.parseDouble( (String) parameters.get( "x1" ) ) );
        }
        if ( parameters.containsKey( "y1" ) ) {
            this.setY1( Double.parseDouble( (String) parameters.get( "y1" ) ) );
        }
        if ( parameters.containsKey( "x2" ) ) {
            this.setX2( Double.parseDouble( (String) parameters.get( "x2" ) ) );
        }
        if ( parameters.containsKey( "y2" ) ) {
            this.setY2( Double.parseDouble( (String) parameters.get( "y2" ) ) );
        }
    }
}

