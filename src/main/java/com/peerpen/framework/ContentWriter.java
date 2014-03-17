package com.peerpen.framework;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ContentWriter {

    static final Logger logger = LoggerFactory.getLogger( ContentWriter.class );

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ContentWriter( ServletRequest req, ServletResponse resp ) {
        request = (HttpServletRequest) req;
        response = (HttpServletResponse) resp;
    }

    public void write() throws IOException {
        String mimeType = this.getMimeContentInfo().getMimeType();
        String requestURI = request.getRequestURI();
        InputStream is = request.getSession().getServletContext().getResourceAsStream( requestURI );
        response.setContentType( this.getMimeContentInfo().getMimeType() );

        if ( mimeType.contains( "image" ) ) {
            OutputStream out = response.getOutputStream();
            IOUtils.copy( is, out );
            out.close();
        } else {
            PrintWriter pw = response.getWriter();
            byte[] bytes = this.getFileContentAsByte();
            response.setContentLength( bytes.length );
            pw.print( new String( bytes ) );
            pw.flush();
            pw.close();
        }
    }

    private String getRequestFileExtension() {
        String requestURI = request.getRequestURI();
        int idx = requestURI.lastIndexOf( "." );
        if ( idx == -1 ) {
            return "";
        }
        return requestURI.substring( idx + 1, requestURI.length() );

    }

    private ContentInfo getMimeContentInfo() throws FileNotFoundException {
        String URI = request.getRequestURI();
        ContentInfo info = ContentInfoUtil.findExtensionMatch( URI );
        if ( info == null ) {
            throw new FileNotFoundException( "Could not locate the file " + URI + " perhaps is root?" );
        }
        logger.info( "Determine content type of " + URI + " to be " + info.getMimeType() );
        logger.info( "The actual type is " + info.toString() );
        return info;

    }

    private byte[] getFileContentAsByte() throws IOException {
        String URI = request.getRequestURI();
        InputStream fis = request.getSession().getServletContext().getResourceAsStream( URI );
        logger.info( "Preparing to write the requested resource into output stream" );
        if ( fis == null ) {
            logger.error( "Failed to find requested resource" );
            throw new FileNotFoundException( "Could not locate file " + URI );
        }

        byte[] bytes = new byte[fis.available()];
        fis.read( bytes );

        return bytes;
    }
}
