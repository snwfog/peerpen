package com.peerpen.controller;

import com.peerpen.framework.InternalRequestDispatcher;
import com.peerpen.framework.exception.PermissionDeniedException;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DocumentController extends HttpServlet
{

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
//    Peer owner = (Peer)request.getAttribute("sessionUser");
    Map<String, String> parameters = (Map<String, String>) request.getAttribute("parameters");
    Peer peer = new Peer().find(Integer.parseInt(parameters.get("peer")));
    Document document = null;

    if(parameters.get("document.do") == null)
    {
      request.setAttribute("peer", peer);
      request.getRequestDispatcher("/documents").forward(request, response);
    }
    else
    {
      try
      {
        if((document = peer.getDocument(Integer.parseInt(parameters.get("document.do")))) != null)
        {
          request.setAttribute("document", document);
          request.getRequestDispatcher("/document").forward(request, response);
        }
      }
      catch (PermissionDeniedException e)
      {
        ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError(request, response, e);
      }
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {

  }

}