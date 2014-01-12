package com.peerpen.controller;

import com.peerpen.model.Document;
import com.peerpen.model.Peer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bobbyyit
 * Date: 2014-01-10
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentsController extends HttpServlet
{
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

    HttpSession session = request.getSession();
    Peer pear = (Peer)session.getAttribute("user");
    //System.out.println("dsfsdfdsfdsfdsfsd"+pear.getId());
    //List<Document> listofDocuments =new  ArrayList < Document >();
    //Document doc1 = new Document();
    //doc1.setDocName("DOCUMENT1");
    //doc1.setPeerId(pear.getId());
    //System.out.println("dsfsdfdsfdsfdsfsd" + pear.getId());
    //doc1.save();

    //System.out.println("dfsddfdsfd"+doc1.getDocName());
    //listofDocuments.add(doc1);
    //pear.setDocuments(listofDocuments);
    //pear.update();

    List<Document>  documents = pear.getDocuments();

    for (Document doc : documents)
    {
        request.setAttribute("document", doc);
    }

    request.setAttribute("documents", documents);
    request.setAttribute("test", "bobby");
    request.getRequestDispatcher("/documents").forward(request, response);

  }
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

  }

}