<%@ include file="/view/includes/static/header.jsp" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="com.peerpen.model.Comment" %>
<%@ page import="java.util.List" %>

<%--Declare all request variables here, easy to debug!!!--%>
<% Peer peer = (Peer) session.getAttribute("user"); %>
<% Document document =(Document) request.getAttribute("document");%>
<% List<Comment> comments = (List<Comment>) request.getAttribute("comments");%>

<script type="text/javascript">
  jQuery(document).ready(function($) {
    $('#tabs').tab();
  });
  $(document).on("click", ".confirmDeleteCommentDialog", function ()
  {
    var commentId = $(this).data('id');
    $(".modal-footer #commentId").val(commentId);
  });
</script>

<div class="container">
  <h1><%= document.getDocName()%></h1>
  <div id="content">
    <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
      <li class="active"><a href="#commentSection" data-toggle="tab">Comments</a></li>
      <li><a href="#changesetSection" data-toggle="tab">Changsets</a></li>
    </ul>
    <div id="my-tab-content" class="tab-content">
      <div class="tab-pane active" id="commentSection">
        <h2>Comments</h2>
        <p>
        <div class="caption">
          <div class="card">
            <h3 class="card-heading simple"><%= peer.getFirstName() %> <%= peer.getLastName() %></h3>
            <div class="card-body">
              <form method="POST" action="/document.do">

                <textarea name="comment" style="width:100%"></textarea>
                <input type="hidden" name="docId" value="<%= document.getId()%>"/>
                <%--<br />--%>
                <button type="submit" class="btn btn-success ">Post</button>

              </form>
            </div>
          </div>
          <% for (Comment comment : comments)
          {
          %>
          <div class="card">
            <h3 class="card-heading simple"><%= comment.getPeer().getFirstName() %> <%= comment.getPeer().getLastName() %></h3>
            <div class="card-body">
              <%= comment.getMessage() %>
            </div>
            <div class="card-actions">
              <button class="btn">25&nbsp;<i class="fa fa-thumbs-up"></i></button>&nbsp;
              <% if(peer.getId() == document.getPeerId() || peer.getId() == comment.getPeerId()){%>
              <a data-toggle="modal" data-id="<%= comment.getId()%>" class="confirmDeleteCommentDialog"
                 href="#deleteDialog">delete</a>
              <% } %>
            </div>
          </div>
          <% } %>
        </div>
        </p>
      </div>
      <div class="tab-pane" id="changesetSection">
        <h2>Changeset</h2>
        <p>
        <div class="card">
          <h3 class="card-heading simple">Changeset 1</h3>
          <div class="card-body">
            <p>Change format of Education</p>
          </div>
          <div class="card-comments">
            <div class="comments-collapse-toggle">
              <a data-toggle="collapse" data-target="#c1-comments" href="#c1-comments">34 comments <i class="icon-angle-down"></i></a>
            </div>
            <div id="c1-comments" class="comments collapse">
              <div class="media">
                <a class="pull-left" href="#">
                  <img class="media-object" data-src="holder.js/28x28" alt="avatar"/>
                </a>
                <div class="media-body">
                  <h4 class="media-heading">Jane Smith title</h4>
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam...</p>
                </div>
              </div>
              <div class="media">
                <a class="pull-left" href="#">
                  <img class="media-object" data-src="holder.js/28x28" alt="avatar"/>
                </a>
                <div class="media-body">
                  <h4 class="media-heading">John Smith title2</h4>
                  <p>dsafsdfdsod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam...</p>
                </div>
              </div>
              <div class="media">
                <a class="pull-left" href="#">
                  <img class="media-object" data-src="holder.js/28x28" alt="avatar"/>
                </a>
                <div class="media-body">
                  <%--<h4 class="media-heading">Your Name</h4>--%>
                  <textarea style="width:100%"></textarea>
                  <button class="btn btn-success">Post</button>
                </div>
              </div>
            </div>
          </div>
        </div>

        </p>
      </div>
    </div>
  </div>
</div>

<div class="modal hide fade" id="deleteDialog">
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
      <input type="hidden" name="docId" value="<%= document.getId().toString()%>"/>
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
      <button type="submit" class="btn btn-primary">Delete</button>
    </form>
  </div>
</div>

<%@ include file="/view/includes/static/footer.jsp" %>