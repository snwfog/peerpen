package com.peerpen.model;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.peerpen.framework.InternalHttpServletRequest;
import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
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
    public static final int VIEWPORT_DIMENSION = 512;

    // Avatar size
    public static enum Size {
        SMALL( 32 ),
        MEDIUM( 64 ),
        LARGE( 256 ),
        ORIGINAL( 0 );

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

    public String getDefaultAvatarSource( HttpServletRequest request ) {
        return this.getRelativeServletContextAvatarPathForSize( request, Size.MEDIUM );
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

    public String getAvatarExtension( String path ) {
        ContentInfo info = ContentInfoUtil.findExtensionMatch( path );
        return info.getFileExtensions().length > 0 ? info.getFileExtensions()[0] : AVATAR_DEFAULT_PATH;
    }

    public void cropAvatar( HttpServletRequest request ) {
        this.setCoordinate( request );
        double ratio = Math.max( this.originalHeight, originalWidth ) / this.VIEWPORT_DIMENSION;

        double scaledX1 = this.x1 * ratio;
        double scaledX2 = this.x2 * ratio;
        double scaledY1 = this.y1 * ratio;
        double scaledY2 = this.y2 * ratio;

        try {
            String originalImageRelativePath =
                    this.getRelativeServletContextAvatarPathForSize( request, Size.ORIGINAL );
            File originalAvatarFile = new File(
                    MessageFormat.format( "{0}/{1}", request.getSession().getServletContext().getRealPath( AVATAR_DEFAULT_PATH ),
                            originalImageRelativePath ) );
            BufferedImage outImage = ImageIO.read( originalAvatarFile );
            BufferedImage croppedImageBuffer =
                    outImage.getSubimage( (int) scaledX1, (int) scaledY1, (int) (scaledX2 - scaledX1),
                            (int) (scaledY2 - scaledY1) );

            String fileExtension = this.getAvatarExtension( originalImageRelativePath );
            File croppedImage = new File(
                    MessageFormat.format( "{0}/{1}", request.getSession().getServletContext().getRealPath( AVATAR_DEFAULT_PATH ),
                            this.getRelativeServletContextAvatarPathForSize( request, Size.LARGE ) ) );

            ImageIO.write( croppedImageBuffer, fileExtension, croppedImage );
            logger.info( "Success saving cropped image for size " + "large" + " at " + croppedImage.getPath() );


            // Resize the avatar
            this.resizeAvatar( request, Size.LARGE );
            this.resizeAvatar( request, Size.MEDIUM );
            this.resizeAvatar( request, Size.SMALL );

        } catch ( IOException e ) {
            logger.error( "Error copping avatar for " + this.getPeer() );
        } finally {
            this.update();
        }
    }

    public void resizeAvatar( HttpServletRequest request, Size size ) throws IOException {
        File largeAvatarFile = new File(
                MessageFormat.format( "{0}/{1}", request.getSession().getServletContext().getRealPath( "" ),
                        this.getServletContextAvatarPathForSize( request, Size.LARGE ) ) );
        BufferedImage croppedImage = ImageIO.read( largeAvatarFile );
        ColorModel cm = croppedImage.getColorModel();

        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = croppedImage.copyData( null );
        BufferedImage resizeImageCopy = new BufferedImage( cm, raster, isAlphaPremultiplied, null );
        int type = resizeImageCopy.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : croppedImage.getType();


        String originalImageRelativePath = this.getRelativeServletContextAvatarPath( request );
        String fileExtension = this.getAvatarExtension( originalImageRelativePath );

        BufferedImage scaledImage = new BufferedImage( size.getDimension(), size.getDimension(), type );
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage( resizeImageCopy, 0, 0, size.getDimension(), size.getDimension(), null );
        g.dispose();
        g.setComposite( AlphaComposite.Src );

        g.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
        g.setRenderingHint( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
        g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        String resizeImageAbsolutePath =
                MessageFormat.format( "{0}/{1}", request.getSession().getServletContext().getRealPath( AVATAR_DEFAULT_PATH ),
                        this.getServletContextAvatarPathForSize( request, size ) );
        ImageIO.write( scaledImage, fileExtension, new File( resizeImageAbsolutePath ) );

        logger.warn( "Finish resize image for size " + size );
    }

    private void setCoordinate( HttpServletRequest request ) {
        Map<String, Object> parameters = ((InternalHttpServletRequest) request).getParametersMap();
        if ( parameters.containsKey( "x1" ) ) {
            this.setX1( Double.parseDouble( (String) parameters.get( "x1" ) ) );
            System.out.println( this.getX1() );
        }
        if ( parameters.containsKey( "y1" ) ) {
            this.setY1( Double.parseDouble( (String) parameters.get( "y1" ) ) );
            System.out.println( this.getY1() );
        }
        if ( parameters.containsKey( "x2" ) ) {
            this.setX2( Double.parseDouble( (String) parameters.get( "x2" ) ) );
            System.out.println( this.getX2() );
        }
        if ( parameters.containsKey( "y2" ) ) {
            this.setY2( Double.parseDouble( (String) parameters.get( "y2" ) ) );
            System.out.println( this.getY2() );
        }
    }
}

