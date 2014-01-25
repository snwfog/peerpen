<%@ include file="/view/includes/static/header.jsp" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="com.peerpen.model.Comment" %>
<%@ page import="com.peerpen.model.Changeset" %>
<%@ page import="java.util.List" %>

<%--Declare all request variables here, easy to debug!!!--%>
<% Peer peer = (Peer) request.getAttribute("sessionUser"); %>
<% Document document =(Document) request.getAttribute("document");%>
<% List<Comment> comments = document.getOrderedComments();%>
<% Changeset changeset = (Changeset) request.getAttribute("changeset");%>

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
              <form method="POST" action="/peer/<%= peer.getId()%>/document/<%= document.getId()%>/comment">

                <textarea name="comment" style="width:100%"></textarea>
                <input type="hidden" name="docId" value="<%= document.getId()%>"/>
                <input type="hidden" name="peerId" value="<%= peer.getId()%>"/>
                <input type="hidden" name="_method" value="POST">
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
                <form method="POST" action="/vote"  class="AjaxSubmit3">

                    <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                    <input type="hidden" name="commentid" value="<%= comment.getId()%>"/>
                    <input type="hidden" name="upvote" value="<%= comment.getUpVote()%>"/>
                    <input type="hidden" name="downvote" value="<%= comment.getDownVote()%>"/>
                    <input type="hidden" name="_method" value="POST">

                    <button class="btn" onclick="upVote1();" >
                        <div class="point" id="up-<%= comment.getId()%>" name="point"><%= comment.getUpVote()%></div>&nbsp;<i class="fa fa-thumbs-up"></i></button>&nbsp;


                </form>
                <form method="POST" action="/vote"  id="downvotecomment" class="AjaxSubmit4">

                    <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                    <input type="hidden" name="commentid" value="<%= comment.getId()%>"/>
                    <input type="hidden" name="upvote" value="<%= comment.getUpVote()%>"/>
                    <input type="hidden" name="downvote" value="<%= comment.getDownVote()%>"/>
                    <input type="hidden" name="_method" value="_patch"/>


                    <button  class="btn" onclick="downVote1();" >
                        <div class="point" id="down-<%= comment.getId()+1%>" name="point"><%= comment.getDownVote()%></div>&nbsp; <i class="fa fa-thumbs-down"></i></button>&nbsp;

                </form>

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
        <% for (Changeset ch: document.getChangesets()){%>
        <div class="card">
          <h3 class="card-heading simple"> <%= ch.getContent() %> </h3>
          <%--<div class="card-body">--%>
            <%--<p>Change format of Education</p>--%>
          <%--</div>--%>
          <div class="card-comments">
            <div class="comments-collapse-toggle">
              <a data-toggle="collapse" data-target="#<%= ch.getId()%>-comments" href="#<%= ch.getId()%>-comments"><%= ch.getComments().size()%> Comments<i class="icon-angle-down"></i></a>
            </div>

            <div id="<%= ch.getId()%>-comments" class="comments collapse">
              <% for (Comment c: ch.getChangesetCommentsByOrder(document.getId(),ch.getId())){%>
              <div class="media">
                <a class="pull-left" href="#">
                  <img class="media-object" data-src="holder.js/28x28" alt="avatar"/>
                </a>
                <div class="media-body">
                  <%--<h4 class="media-heading"><%= c.getName()%></h4>--%>
                  <p><%= c.getMessage()%></p>
                </div>
                  <div class="card-actions">
                      <form  id="upvotechangesetcomment" method="POST" action="/vote" class="AjaxSubmit1">

                          <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                          <input type="hidden" name="commentid" value="<%= c.getId()%>"/>
                          <input type="hidden" name="upvote" value="<%= c.getUpVote()%>"/>
                          <input type="hidden" name="downvote" value="<%= c.getDownVote()%>"/>
                          <input type="hidden" name="_method" value="POST"/>

                          <button class="btn" onclick="upVote2();" >
                              <div class="point" id="<%= c.getId()%>" name="point"><%= c.getUpVote()%></div>&nbsp;<i class="fa fa-thumbs-up"></i></button>&nbsp;

                      </form>

                      <form data-id="<%= c.getId()%>" id="downvotechangesetcomment" method="POST" action="/vote"  class="AjaxSubmit2" >

                          <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                          <input type="hidden" name="commentid" value="<%= c.getId()%>"/>
                          <input type="hidden" name="upvote" value="<%= c.getUpVote()%>"/>
                          <input type="hidden" name="downvote" value="<%= c.getDownVote()%>"/>
                          <input type="hidden" name="_method" value="_patch"/>

                          <button  class="btn" onclick="downVote2();">
                              <div class="point" id="<%= c.getId()+1%>" name="point"><%= c.getDownVote()%></div>&nbsp; <i class="fa fa-thumbs-down"></i></button>&nbsp;
                      </form>

                      <% if(peer.getId() == ch.getPeerId() || peer.getId() == c.getPeerId()){%>
                      <a data-toggle="modal" data-id="<%= c.getId()%>" class="confirmDeleteCommentDialog"
                         href="#deleteDialog">delete</a>
                      <% } %>
                  </div>
              </div>
              <%}%>
              <div class="media">
                <a class="pull-left" href="#">
                  <img class="media-object" data-src="holder.js/28x28" alt="avatar"/>
                </a>
                <div class="media-body">
                  <%--<h4 class="media-heading">Your Name</h4>--%>
                  <%--<textarea style="width:100%"></textarea>--%>
                  <%--<button class="btn btn-success">Post</button>--%>
                  <form method="POST" action="/peer/<%= peer.getId()%>/document/<%= document.getId()%>/comment">

                      <textarea name="comment" style="width:100%"></textarea>
                      <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                      <input type="hidden" name="changesetid" value="<%= ch.getId()%>"/>
                      <input type="hidden" name="peerid" value="<%= peer.getId()%>"/>
                      <input type="hidden" name="_method" value="_patch"/>

                      <%--<br />--%>
                      <button type="submit" class="btn btn-success ">Post</button>

                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
        <%}%>

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
    <form id="deleteComment" method="POST" action="/peer/<%= peer.getId()%>/document/<%= document.getId()%>/comment">
      <input type="hidden" name="_method" value="_delete"/>
      <input type="hidden" name="peerid" value="<%= peer.getId()%>"/>
      <input type="hidden" name="commentid" id="commentid" value=""/>
      <input type="hidden" name="docid" value="<%= document.getId()%>"/>
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
      <button type="submit" class="btn btn-primary">Delete</button>
    </form>
  </div>
</div>
<script src="/assets/js/custom/vote.js"></script>
<script src="/assets/js/custom/deleteModal.js"></script>
<%@ include file="/view/includes/static/footer.jsp" %>
