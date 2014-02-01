package com.peerpen.controller;

import com.peerpen.framework.GenericApplicationServlet;
import com.peerpen.framework.InternalRequestDispatcher;
import com.peerpen.framework.ModelHierarchyUtil;
import com.peerpen.framework.exception.PermissionDeniedException;
import com.peerpen.model.Document;
import com.peerpen.model.Peer;
import com.sunnyd.Base;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DocumentController extends GenericApplicationServlet
{
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    // FIXME: This is bad, but okay for now I guess... - snw
    super.doGet(request, response);
    Map<String, Object> parameters = internalRequest.getParametersMap();
    // FIXME: parameters is not garantee to be clean, need distinguish between parameters and URL parameters
    Map<String, Base> modelMap = ModelHierarchyUtil.parameterAsMap(parameters);
    Peer sessionUser = (Peer) request.getAttribute("sessionUser");
    Peer urlUser = (Peer) modelMap.get("peer");
    Document document;
    if(sessionUser.getId() == urlUser.getId())
    {
      //        View documents
      if ((document = (Document) modelMap.get("document")) == null)
      {
        request.setAttribute("peer", modelMap.get("peer"));
        request.getRequestDispatcher("/view/documents.jsp").forward(request, response);
      }
      //        View document
      else
      {
       if(document.getPeerId() == sessionUser.getId())
       {
        request.setAttribute("urlUser", urlUser);
        request.setAttribute("document", modelMap.get("document"));
        request.getRequestDispatcher("/view/document.jsp").forward(request, response);
       }
       else try
       {
         throw new PermissionDeniedException("You have no permission to view this document :/");
       }
       catch (PermissionDeniedException e)
       {
         ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError(request, response, e);
       }
      }
    }
    else//        if viewing someone elses doc, show 'view only' mode
    {
      if ((document = (Document) modelMap.get("document")) == null)
      {
        request.setAttribute("peer", modelMap.get("peer"));
        request.getRequestDispatcher("/view/documents.jsp").forward(request, response);
      }
      else
      {
        if(document.getPeerId() == urlUser.getId())
        {
          request.setAttribute("document", modelMap.get("document"));
          request.setAttribute("urlUser", urlUser);
          request.getRequestDispatcher("/view/document.jsp").forward(request, response);
        }
        else try
        {
          throw new PermissionDeniedException("You have no permission to view this document :/");
        }
        catch (PermissionDeniedException e)
        {
          ((InternalRequestDispatcher) request.getRequestDispatcher( "/error" )).forwardError(request, response, e);
        }
      }

    }

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {

  }

}