package com.peerpen.controller;

import com.peerpen.model.Peer;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.mail.MethodNotSupportedException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Created with IntelliJ IDEA.
 * User: bobbyyit
 * Date: 2014-01-11
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */

public class UploadController extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
      boolean isMultipart = ServletFileUpload.isMultipartContent(request);
      Peer peer = (Peer) request.getAttribute("sessionUser");
//    http://www.tutorialspoint.com/jsp/jsp_file_uploading.htm

    String filePath = request.getSession().getServletContext().getRealPath("/")+"assets/images/profile/";
    System.out.println("context Path "+request.getSession().getServletContext().getRealPath("/") );
    File file ;
    int maxFileSize = 5000 * 1024;
    int maxMemSize = 5000 * 1024;
    // Verify the content type
    if (isMultipart) {

      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );
      try{
        // Parse the request to get file items.
        List fileItems = upload.parseRequest(request);
        // Process the uploaded file items
        Iterator i = fileItems.iterator();
        while ( i.hasNext () )
        {
          FileItem fi = (FileItem)i.next();
          if ( !fi.isFormField () )
          {
            // Get the uploaded file parameters
            String fileName = peer.getId().toString();
            // Write the file
            file = new File( filePath + fileName+".PNG") ;
            fi.write(file) ;
            file = new File( filePath + fileName+"_cropped.PNG") ;
            fi.write(file) ;
            System.out.println("Uploaded Filename: " + filePath + fileName+".PNG" );
          }

        }
      } catch ( Exception ex ) {
          System.out.println( ex );
      }
    }
      response.sendRedirect("/peer/" + peer.getId() + "/additional");
  }

      protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        throw new RuntimeException( this.getClass().getName() + " do not have a doGet method" );
    }
}
