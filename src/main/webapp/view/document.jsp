<%@ page import="com.peerpen.model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ include file="/view/includes/static/header.jsp" %>

<script>
  $(document).on("click", ".confirmDeleteCommentDialog", function ()
  {
    var commentId = $(this).data('id');
    $(".modal-footer #commentId").val(commentId);
  });
</script>

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
        <div class="card">
          <h3 class="card-heading simple"><%= peer.getFirstName() %> <%= peer.getLastName() %></h3>
          <div class="card-body">   <p>
            <form method="POST" action="/document.do">
              <input type="hidden" name="doc_id" value="<%= document.getId().toString()%>"/>
              <textarea name="comment" rows="4" cols="45"  resize="none"></textarea>
              <br /><br />
              <button type="submit" class="btn btn-success">Post</button>
            </form></p>
          </div>
        </div>
        <% for (Comment c : (List<Comment>) request.getAttribute("comments"))
        {
        %>
          <div class="card">
            <h3 class="card-heading simple"><%= c.getPeer().getFirstName() %> <%= c.getPeer().getLastName() %><div style="float:right"><a href="#"><i class="fa fa-thumbs-up"></i></a> / <a href="#"><i class="fa fa-thumbs-down"></i></a></div></h3>
            <p>
              <div class="card-body">
                <%= c.getMessage() %>
              </div>
            </p>
            <p>
              <% if(peer.getId()==document.getPeerId() || peer.getId()==c.getPeerId()){%>
                <a data-toggle="modal" data-id="<%= c.getId()%>" class="confirmDeleteCommentDialog"
                 href="#deleteDialog">delete</a>
              <% } %>
            </p>
          </div>
        <% } %>
      </div>
    </div>
  </div>
</div>
<div class="modal hide" id="deleteDialog">
  <div class="modal-header">
    <button class="close" data-dismiss="modal"><i class="fa fa-times"></i></button>
    <h3>Confirm delete</h3>
  </div>
  <div class="modal-body">
    <p>Are you sure you want to delete this comment?</p>
  </div>
  <div class="modal-footer">
    <form id="deleteComment" method="POST" action="/document.do">
      <input type="hidden" name="_method" value="_delete"/>
      <input type="hidden" name="commentId" id="commentId" value=""/>
      <input type="hidden" name="doc_id" value="<%= document.getId().toString()%>"/>
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
      <button type="submit" class="btn btn-primary">Delete</button>
    </form>
  </div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>
</html>