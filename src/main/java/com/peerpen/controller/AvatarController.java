/**
 * Created by Zearf on 1/7/2014.
 */
package com.peerpen.controller;

import com.google.common.collect.Maps;
import com.peerpen.model.Avatar;
import com.peerpen.model.Peer;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvatarController extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger( AvatarController.class );

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
//        this.storeAvatar( request, response );

        // get all register input
        //        int id = Integer.parseInt(request.getParameter("id"));
        //        String dateOfBirth = request.getParameter("dob");
        //        String gender = request.getParameter("gender");
        //        String country = request.getParameter("country");
        //        String industry = request.getParameter("industry");
        //        String yearOfExperience = request.getParameter("yoe");
        //        String description = request.getParameter("description");
        //        String website = request.getParameter("personal_website");
        //        int x1 = Integer.parseInt(request.getParameter("x1"));
        //        int y1 = Integer.parseInt(request.getParameter("y1"));
        //        int x2 = Integer.parseInt(request.getParameter("x2"));
        //        int y2 = Integer.parseInt(request.getParameter("y2"));
        //        int yoe = 0;
        //
        //        String absolutePath = request.getSession().getServletContext().getRealPath("/") + "assets/images/profile/";
        //        String croppedImage = cropImage(x1, y1, x2, y2, absolutePath);
        //
        //        if (!(yearOfExperience == null || yearOfExperience == ""))
        //            yoe = Integer.parseInt(yearOfExperience);
        //        StringUtils.split(" ");
        //
        //        Date dob = new Date();
        //
        //        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //        try {
        //            dob = formatter.parse(dateOfBirth);
        //        } catch (Exception e) {
        //            System.out.println("Unable to parse date stamp");
        //        }
        //        dateOfBirth = formatter.format(dob);
        //        // here we should validate the input...
        //
        //        Peer peer = new Peer().find(id);
        //        peer.setDateOfBirth(dob);
        //        peer.setGender(gender);
        //        peer.setCountry(country);
        //        peer.setIndustry(industry);
        //        if (yoe != 0)
        //            peer.setExperience(yoe);
        //        peer.setDescription(description);
        //        peer.setPersonalWebsite(website);
        //        peer.update();
        //
        //
        //        request.setAttribute("user", peer);
        //        request.setAttribute("birth_date", dateOfBirth);
        //        request.setAttribute("croppedImage", croppedImage);
        //        response.sendRedirect("/profile");
    }

//        private void cropImage(Avatar avatar, String absolutePath, String fileName, String fileType, HttpServletResponse response) {
//
//            int x1 = avatar.getX1();
//            int y1 = avatar.getY1();
//            int width = (int) avatar.getViewport().getWidth();
//            int height = (int) avatar.getViewport().getHeight();
//            System.out.println("This is the path:  "+absolutePath+LARGE_FOLDER+System.getProperty("file.separator")+fileName+"."+fileType);
//            System.out.println("This is the path:  "+absolutePath+fileName+"."+fileType);
//            try{
//                BufferedImage outImage = ImageIO.read(new File(absolutePath+LARGE_FOLDER+System.getProperty("file.separator")+fileName+"."+fileType));
//                BufferedImage cropped = outImage.getSubimage(x1, y1, width, height);
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                ImageIO.write(cropped,fileType, out);
//                ImageIO.write(cropped, fileType, new File(absolutePath+SMALL_FOLDER+System.getProperty("file.separator")+fileName+"_cropped."+fileType));
//
//                ServletOutputStream wrt = response.getOutputStream();
//                wrt.write(out.toByteArray());
//                wrt.flush();
//                wrt.close();
//
//            }catch (IOException e) {
//                e.printStackTrace();
//            }

//            try {
//                BufferedImage originalImgage = ImageIO.read(new File(absolutePath + "256.jpg"));
//                System.out.println("Original image dimension: " + originalImgage.getWidth() + "x1" + originalImgage.getHeight());
//
//                BufferedImage SubImage = originalImgage.getSubimage(x1, y1, x2, y2);
//                System.out.println("Cropped image dimension: " + SubImage.getWidth() + "x" + SubImage.getHeight());
//
//                File outputfile = new File(absolutePath + "croppedImage.jpg");
//                ImageIO.write(SubImage, "jpg", outputfile);
//
//                System.out.println("Image cropped successfully: " + outputfile.getPath());
//
//                return outputfile.getName();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "";
//            }
//        }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        request.getRequestDispatcher( "/view/avatar.jsp" ).forward( request, response );
    }

}
