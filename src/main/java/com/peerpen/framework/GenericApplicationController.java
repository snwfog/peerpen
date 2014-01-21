package com.peerpen.framework;

import com.google.common.base.Throwables;
import com.peerpen.framework.exception.ServletPathDontExistException;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

import javax.mail.MethodNotSupportedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by snw on 1/20/2014.
 */
@Deprecated
public class GenericApplicationController extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger( GenericApplicationController.class );
    protected String SERVLET_PATH = "/";

    @Override
    public void init() throws ServletException {
        //super.init();
        //
        //Annotation[] annotations = this.getClass().getAnnotations();
        //// TODO: Fix these throw clauses
        //if ( annotations.length > 1 ) {
        //    try {
        //        throw new Throwable( "Class cannot have more than 1 resource path" );
        //    } catch ( Throwable throwable ) {
        //        throwable.printStackTrace();
        //    }
        //} else if ( annotations.length == 0 ) {
        //    try {
        //        throw new Throwable(
        //                "Accessing a reserved resource, yet no path have been declared. Might want to add the" +
        //                        " ServletPath annotation" );
        //    } catch ( Throwable throwable ) {
        //        throwable.printStackTrace();
        //    }
        //}
        //
        //String path = ((ServletPath)annotations[0]).value();
        //
        //try {
        //    if ( path.equals( "/" ) ) {
        //        throw new MethodNotSupportedException();
        //    } else {
        //        Map<String, String> parameters = (Map<String, String>) this.getServletContext().request.getAttribute(
        //                "parameters" );
        //        if ( parameters == null ) {
        //            throw new ServletException(
        //                    " Request parameters attribute does not exists, " + "perhaps not an internal request?" );
        //        }
        //        String path = StringUtils.join( parameters.keySet(), "/" );
        //        Set<String> allRoutes = (Set<String>) request.getAttribute( "allRoutes" );
        //        if ( !allRoutes.contains( path ) ) {
        //            throw new ServletPathDontExistException( path );
        //        }
        //    }
        //
        //} catch ( MethodNotSupportedException e ) {
        //    logger.error(
        //            "Servlet path is not properly defined, the default path of " + SERVLET_PATH + " is reserved" );
        //    logger.error( "You must override the 'verifyPath' controller method" );
        //    Throwables.propagate( e );
        //} catch ( ServletException e ) {
        //    logger.error( e.toString() );
        //    Throwables.propagate( e );
        //} catch ( ServletPathDontExistException e ) {
        //    logger.error( "Path " + e.toString() + " is not valid, verify from resources.yml that is properly " +
        //            "declared" );
        //    Throwables.propagate( e );
        //}
        //

    }
}
