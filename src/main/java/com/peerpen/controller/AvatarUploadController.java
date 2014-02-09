package com.peerpen.controller;

import com.google.common.collect.Maps;
import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.model.Avatar;
import com.peerpen.model.Peer;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AvatarUploadController extends HttpServlet {
    static final Logger logger = LoggerFactory.getLogger(AvatarUploadController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/upload.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.storeAvatar(request, response);
        Peer sessionPeer = (Peer) request.getAttribute("sessionUser");
        request.getRequestDispatcher("/peer/" + sessionPeer.getId() + "/profile/avatar").forward(request, response);
    }

    private void storeAvatar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        Peer sessionPeer = (Peer) request.getAttribute("sessionUser");
        // http://www.tutorialspoint.com/jsp/jsp_file_uploading.htm
        // http://stackoverflow.com/questions/5096862/jsp-get-mime-type-on-file-upload
        ServletContext ctx = this.getServletContext();
        String avatarDir = ctx.getInitParameter("avatar-dir");

        File tmpDir = (File) ctx.getAttribute("javax.servlet.context.tempdir");
        Map<String, String> parameterMaps = Maps.newHashMap();

        int maxFileSize = 5000 * 1024; // 5 MB file size
        int maxMemSize = 5000 * 1024;
        String fileName = "";
        String fileType = "";
        // Verify the content type
        if (isMultipart) {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);
            factory.setRepository(tmpDir);
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // maximum file size to be uploaded.
            upload.setSizeMax(maxFileSize);
            try {
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);
                // Process the uploaded file items
                Iterator i = fileItems.iterator();
                File largeAvatarFile = null;
                while (i.hasNext()) {
                    // Get the content type of the file
                    FileItem fi = (FileItem) i.next();

                    if (fi.isFormField()) {
                        parameterMaps.put(fi.getFieldName(), fi.getString());
                    } else if (!fi.isFormField()) {
                        String contentType = fi.getContentType();
                        fileType = (contentType.lastIndexOf("/") > -1) ?
                                contentType.substring(contentType.lastIndexOf("/") + 1) : "";
                        // Get the uploaded file parameters
                        fileName = sessionPeer.getId().toString() + "-" +
                                StringUtils.lowerCase(sessionPeer.getFirstName());
                        // Write the file
                        largeAvatarFile = new File(
                                MessageFormat.format("{0}/{1}/{2}/{3}.{4}", ctx.getRealPath(""), avatarDir,
                                        Avatar.Size.LARGE.getFolderName(), fileName, fileType));
                        fi.write(largeAvatarFile);
                    }
                }
                logger.info("Image upload properties " + parameterMaps.toString());
                Avatar avatar = sessionPeer.getAvatar();
//                avatar.setViewport(parameterMaps);
                avatar.setOriginalSize(parameterMaps);
                avatar.setFilename(MessageFormat.format("{0}.{1}", fileName, fileType));
                avatar.update(); // FIXME: Does PPAR update relationship?
                sessionPeer.update();
            } catch (FileUploadException e) {
                logger.error("Something went wrong with image upload ", e);
            } catch (Exception e) {
                logger.error("Something went wrong with writing to the image folder", e);
            }
        }
    }
}
