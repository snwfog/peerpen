<% Document document =(Document) request.getAttribute("document");%>
<jsp:include page="/view/includes/static/header.jsp">
  <jsp:param name="title" value="<%=document.getDocName()%>" />
</jsp:include>

<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="com.peerpen.model.Comment" %>
<%@ page import="com.peerpen.model.Changeset" %>
<%@ page import="com.peerpen.model.TagDescriptor" %>
<%@ page import="com.peerpen.model.Hunk" %>
<%@ page import="java.util.List" %>
<%@ include file="/view/includes/static/navbar.jsp" %>

<%--Declare all request variables here, easy to debug!!!--%>
<% Peer urlUser; %>
<% if (request.getAttribute("urlUser") != null){urlUser= (Peer) request.getAttribute("urlUser");}else{urlUser=sessionUser;} %>
<% List<Comment> comments = document.getComments();%>
<% List<Changeset> changesets = document.getChangeset();%>
<% List<TagDescriptor> tds = document.getTagDescriptors(); %>
<% List<Hunk> hunks = document.getHunks(); %>

<div class="container">
  <h1><%= document.getDocName()%> <%= (sessionUser.getId() == urlUser.getId()) ? "" : " (View-only mode)" %></h1>
    <div style="border:1px solid black">
      <%for(Hunk hunk: hunks){ %>
      <textarea name=""><%= hunk.getContent()%></textarea>
      <%}%>
    </div>
    <br /><br />
    <!-- TAG SECTION -->
    <div>
      <form action="/tag" method="post" class="form-horizontal" role="form">
        <input type="hidden" name="entityType" value="document" />
        <input type="hidden" name="entityId" value="<%=document.getId()%>" />
        <ul id="entityTags" style="float:left;width:600px;">
          <% for (TagDescriptor td : tds){ %>
          <li><%=td.getTagName() %></li>
          <% } %>
        </ul>
        <button type="submit" class="btn btn-primary" name="submit" style="margin-left:10px" />Save Tags</button>
      </form>
    </div>
    <br /><br /><br /><br />
  <div id="content">
    <div class="caption">
      <div class="row">
        <div class="col-md-6">
          <!-- COMMENT SECTION -->
          <div class="card2">
            <h3 class="card-heading simple"><%= sessionUser.getFirstName() %> <%= sessionUser.getLastName() %></h3>
            <form method="POST" id="comment1" action="/peer/<%= sessionUser.getId()%>/document/<%= document.getId()%>/comment" parsley-validate>
            <div class="card-body">
                <textarea id="textComment1" class="parsley-validated" name="comment" style="width:100%" parsley-trigger="change keyup"></textarea>
                <input type="hidden" name="docId" value="<%= document.getId()%>"/>
                <input type="hidden" name="peerId" value="<%= sessionUser.getId()%>"/>
                <input type="hidden" name="_method" value="POST">
            </div>
            <div class="card-actions">
              <button type="submit" class=" btn btn-success ">Post</button>
            </div>
            </form>
          </div>
          <%for(Comment comment: comments){ Peer p = new Peer().find(comment.getPosterPeerId()); %>
          <div class="card2">
            <div class="card-heading image">
              <img src="<%= comment.getPosterPeer().getAvatar().getRelativeServletContextAvatarPathForSize(request,
                    Avatar.Size.SMALL) %>" >
              <div class="card-heading-header">
                <h3><%= p.getFirstName() %> <%= p.getLastName() %></h3>
                <span>Published <%= comment.getTimesAgo()%>
                <% if(sessionUser.getId() == document.getPeerId() || sessionUser.getId() == comment.getPosterPeerId()){%> |
                  <a data-toggle="modal" data-id="<%= comment.getId()%>" class="confirmDeleteCommentDialog child"
                   href="#deleteDialog">delete</a>
                <% } %>
                </span>
              </div>
            </div>
            <div class="card-body">
              <%= comment.getMessage() %>
            </div>
            <div class="card-actions parent">
              <form method="POST" action="/vote"  class="AjaxSubmit3 child">
                <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                <input type="hidden" name="commentid" value="<%= comment.getId()%>"/>
                <input type="hidden" name="upvote" value="<%= comment.getUpVote()%>"/>
                <input type="hidden" name="downvote" value="<%= comment.getDownVote()%>"/>
                <button class="btn btn-xs" id="btn1<%= comment.getId()%>" onclick="upVote1();" >
                  <div class="parent">
                    <div class="child point" id="up-<%= comment.getId()%>" name="point"><%= comment.getUpVote()%></div>
                    <div class="child">&nbsp;<i class="fa fa-thumbs-up"></i></div>
                  </div>
                </button>
                &nbsp;
              </form>
              <form method="POST" action="/vote"  id="downvotecomment" class="AjaxSubmit4 child">
                <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                <input type="hidden" name="commentid" value="<%= comment.getId()%>"/>
                <input type="hidden" name="upvote" value="<%= comment.getUpVote()%>"/>
                <input type="hidden" name="downvote" value="<%= comment.getDownVote()%>"/>
                <input type="hidden" name="_method" value="put"/>
                <button  class="btn btn-xs" id="btn2<%= comment.getId()+1%>" onclick="downVote1();" >
                  <div class="parent">
                    <div class="child point" id="down-<%= comment.getId()+1%>" name="point"><%= comment.getDownVote()%></div>
                    <div class="child">&nbsp; <i class="fa fa-thumbs-down"></i></div>
                  </div>
                </button>
                &nbsp;
              </form>
            </div>
          </div>
          <%}%>
        </div>
        <div class="col-md-6">
          <!-- CHANGESET SECTION -->
          <%for(Changeset changeset: changesets){ %>
          <div class="card2">
            <h3 class="card-heading simple"><%= changeset.getContent() %> - <span class="child" style="font-style: italic;font-size: small;"><%= changeset.getTimesAgo()%></span></h3>
            <div class="card-comments">
              <div class="comments-collapse-toggle">
                <a data-toggle="collapse" href="#<%= changeset.getId()%>-comments"> <%=changeset.getOrderedComments().size()%> Comments<i class="icon-angle-down"></i></a>
              </div>
              <div id="<%= changeset.getId()%>-comments" class="comments collapse">
                <% for (Comment comment: changeset.getOrderedComments()){%>
                <div class="media">
                  <a class="pull-left" href="/peer/<%= comment.getPosterPeer().getId()%>">
                    <img class="media-object" title="<%= comment.getPosterPeer().getFirstName()%> <%= comment.getPosterPeer().getLastName()%>" src="<%= comment.getPosterPeer().getAvatar().getRelativeServletContextAvatarPathForSize( request,
                    Avatar.Size.SMALL ) %>" alt="avatar"/>
                  </a>
                  <div class="media-body">
                    <h4 class="media-heading"><%= comment.getPosterPeer().getFirstName()%> <%= comment.getPosterPeer().getLastName()%></h4>
                      <form  id="upvotechangesetcomment" method="POST" action="/vote" class="AjaxSubmit1 child">
                        <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                        <input type="hidden" name="commentid" value="<%= comment.getId()%>"/>
                        <input type="hidden" name="upvote" value="<%= comment.getUpVote()%>"/>
                        <input type="hidden" name="downvote" value="<%= comment.getDownVote()%>"/>
                        <button class="parent btn btn-xs" id="btn3<%= comment.getId()%>" onclick="upVote2();" >
                          <div class="child point" id="<%= comment.getId()%>" name="point"><%= comment.getUpVote()%></div>
                          <div class="child">&nbsp;<i class="fa fa-thumbs-up"></i></div>
                        </button>&nbsp;
                      </form>
                      <form id="downvotechangesetcomment" method="POST" action="/vote" class="AjaxSubmit2 child" >
                        <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                        <input type="hidden" name="commentid" value="<%= comment.getId()%>"/>
                        <input type="hidden" name="upvote" value="<%= comment.getUpVote()%>"/>
                        <input type="hidden" name="downvote" value="<%= comment.getDownVote()%>"/>
                        <input type="hidden" name="_method" value="put"/>
                        <button  class="parent btn btn-xs" id="btn4<%= comment.getId()+1%>" onclick="downVote2();">
                          <div class="child point" id="<%= comment.getId()+1%>" name="point"><%= comment.getDownVote()%></div>
                          <div class="child">&nbsp;<i class="fa fa-thumbs-down"></i></div>
                        </button>
                        &nbsp;
                      </form>
                      <span class="child" style="font-style: italic;font-size: small;"><%= comment.getTimesAgo()%></span>
                      <% if(sessionUser.getId() == changeset.getPeerId() || sessionUser.getId() == comment.getPosterPeerId()){%>|
                      <a data-toggle="modal" data-id="<%= comment.getId()%>" class="confirmDeleteCommentDialog child"
                         href="#deleteDialog">delete</a>
                    <p><%= comment.getMessage()%></p>
                  </div>
                <% } %>
                </div>
                <%}%>
                <div class="media">
                  <form method="POST" id="comment2" action="/peer/<%= sessionUser.getId()%>/document/<%= document.getId()%>/comment" parsley-validate>
                  <div class="media-body">
                    <textarea name="comment" id="textComment2" class="parsely-validated" style="width:100%" parsley-trigger="change keyup"></textarea>
                  </div>
                    <br/>
                  <div class="media-body">
                    <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                    <input type="hidden" name="changesetid" value="<%= changeset.getId()%>"/>
                    <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>"/>
                    <input type="hidden" name="_method" value="put"/>
                    <button type="submit" class="btn btn-success ">Post</button>
                  </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
          <%}%>
      </div>
    </div>
  </div>
</div>

<!-- MODEL SECTION -->
<div class="modal fade" id="deleteDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Confirm delete</h4>
      </div>
      <div class="modal-body">
        <p>Are you sure you want to delete this comment?</p>
      </div>
      <div class="modal-footer">
        <form id="deleteComment" method="POST" action="/peer/<%= sessionUser.getId()%>/document/<%= document.getId()%>/comment">
          <input type="hidden" name="_method" value="delete"/>
          <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>"/>
          <input type="hidden" name="commentid" id="commentid" value=""/>
          <input type="hidden" name="docid" value="<%= document.getId()%>"/>
          <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
          <button type="submit" class="btn btn-primary">Delete</button>
        </form>
      </div>
    </div>
  </div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>
