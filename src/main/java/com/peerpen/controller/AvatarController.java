/**
 * Created by Zearf on 1/7/2014.
 */
package com.peerpen.controller;

import com.peerpen.model.Peer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

import org.apache.commons.lang3.StringUtils;

public class AvatarController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get all register input
        int id = Integer.parseInt(request.getParameter("id"));
        String dateOfBirth = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String industry = request.getParameter("industry");
        String yearOfExperience = request.getParameter("yoe");
        String description = request.getParameter("description");
        String website = request.getParameter("personal_website");
        int x1 = Integer.parseInt(request.getParameter("x1"));
        int y1 = Integer.parseInt(request.getParameter("y1"));
        int x2 = Integer.parseInt(request.getParameter("x2"));
        int y2 = Integer.parseInt(request.getParameter("y2"));
        int yoe = 0;

        String absolutePath = request.getSession().getServletContext().getRealPath("/") + "assets/images/profile/";
        String croppedImage = cropImage(x1, y1, x2, y2, absolutePath);

        if (!(yearOfExperience == null || yearOfExperience == ""))
            yoe = Integer.parseInt(yearOfExperience);
        StringUtils.split(" ");

        Date dob = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dob = formatter.parse(dateOfBirth);
        } catch (Exception e) {
            System.out.println("Unable to parse date stamp");
        }
        dateOfBirth = formatter.format(dob);
        // here we should validate the input...

        Peer peer = new Peer().find(id);
        peer.setDateOfBirth(dob);
        peer.setGender(gender);
        peer.setCountry(country);
        peer.setIndustry(industry);
        if (yoe != 0)
            peer.setExperience(yoe);
        peer.setDescription(description);
        peer.setPersonalWebsite(website);
        peer.update();


        request.setAttribute("user", peer);
        request.setAttribute("birth_date", dateOfBirth);
        request.setAttribute("croppedImage", croppedImage);
        response.sendRedirect("/profile");
    }

    public String cropImage(int x1, int y1, int x2, int y2, String absolutePath) {

        try {
            BufferedImage originalImgage = ImageIO.read(new File(absolutePath + "256.jpg"));
            System.out.println("Original image dimension: " + originalImgage.getWidth() + "x1" + originalImgage.getHeight());

            BufferedImage SubImage = originalImgage.getSubimage(x1, y1, x2, y2);
            System.out.println("Cropped image dimension: " + SubImage.getWidth() + "x" + SubImage.getHeight());

            File outputfile = new File(absolutePath + "croppedImage.jpg");
            ImageIO.write(SubImage, "jpg", outputfile);

            System.out.println("Image cropped successfully: " + outputfile.getPath());

            return outputfile.getName();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/avatar.jsp").forward(request, response);
    }
}
