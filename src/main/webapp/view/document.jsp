<%@ page import="com.peerpen.model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: waisk
  Date: 10/01/14
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>

<% Peer peer = (Peer) session.getAttribute("user"); %>
<% Document document =(Document) request.getAttribute("document");%>
<html>
<head>
    <title><%= document.getDocName() %></title>
</head>
<body>Document: <%= document.getDocName()%></body>
<div class="row">
    <div class="col-sm-6 col-md-4">
        <div class="thumbnail">
            <div class="caption">
                <% for (Comment c : (List<Comment>) request.getAttribute("comments"))
                {
                %>
                  <div class="card">
                    <h3 class="card-heading simple"><%= c.getPeer().getFirstName() %> <%= c.getPeer().getLastName() %><div style="float:right"><a href="#"><i class="fa fa-thumbs-up"></i></a> / <a href="#"><i class="fa fa-thumbs-down"></i></a></div></h3>
                    <div class="card-body">
                      <%= c.getMessage() %>
                    </div>
                  </div>
                <% } %>
                <div class="card">
                  <h3 class="card-heading simple"><%= peer.getFirstName() %> <%= peer.getLastName() %></h3>
                  <div class="card-body">
                    <form method="POST" action="/document.do">
                      <input type="hidden" name="doc_id" value="<%= document.getId().toString()%>"/>
                      <textarea name="comment" rows="4" cols="45"  resize="none"></textarea>
                      <br /><br />
                      <button type="submit" class="btn btn-success">Post</button>
                    </form>
                  </div>
                </div>
            </div>
        </div>
    </div>
</div>
</html>