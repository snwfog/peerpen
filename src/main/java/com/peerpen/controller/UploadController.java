package com.peerpen.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
//    http://www.tutorialspoint.com/jsp/jsp_file_uploading.htm
    String avatarFolder = request.getSession().getServletContext().getRealPath("/")+"assets/images/profile/";
    System.out.println("context Path "+request.getSession().getServletContext().getRealPath("/") );
    File file ;
    int maxFileSize = 5000 * 1024;
    int maxMemSize = 5000 * 1024;
    ServletContext context = request.getSession().getServletContext();
    String filePath = null;
    if (System.getProperty("os.name").contains("Mac"))
    {
      filePath = avatarFolder;
    }
    else
    {
//      not tested yet
      filePath = context.getInitParameter("file-upload-win");
    }

    // Verify the content type
    String contentType = request.getContentType();
    if ((contentType.indexOf("multipart/form-data") >= 0)) {

      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("i don't know what to put here"));

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
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();
            // Write the file
            if( fileName.lastIndexOf("\\") >= 0 ){
              file = new File( filePath +
                  fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
              file = new File( filePath +
                  fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }
            fi.write(file) ;
            System.out.println("Uploaded Filename: " + filePath + fileName );
          }
        }
      }catch(Exception ex) {
        System.out.println(ex);
      }
    }else{
      System.out.println("NO file uploaded");
    }
      response.sendRedirect("/avatar");
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

  }
}
