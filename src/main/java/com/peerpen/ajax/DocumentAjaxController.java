package com.peerpen.ajax;

import com.peerpen.model.Document;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: MP
 * Date: 07/02/14
 * Time: 7:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentAjaxController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        Map<String, String> parameters = (Map<String, String>) req.getAttribute("parameters");
        Set<String> a = parameters.keySet()   ;
        for(String v : a){
            System.out.println("blub"+v);
        }
        String peer = parameters.get("peer");
        String[] document = parameters.get("doc").split("-");

        Document doc = new Document().find(Integer.parseInt(document[1]));

        req.setAttribute("doc", doc);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher( "/view/ajaxDocument.jsp" ).forward( req, resp );
    }


}
