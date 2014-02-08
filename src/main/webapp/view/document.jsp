<%@ include file="/view/includes/static/header.jsp" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="com.peerpen.model.Comment" %>
<%@ page import="com.peerpen.model.Changeset" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.TagDescriptor" %>

<%--Declare all request variables here, easy to debug!!!--%>
<% Peer sessionUser = (Peer) request.getAttribute("sessionUser"); %>
<% Peer urlUser; %>
<% if (request.getAttribute("urlUser") != null){urlUser= (Peer) request.getAttribute("urlUser");}else{urlUser=sessionUser;} %>
<% Document document =(Document) request.getAttribute("document");%>
<% List<Object> objectList = document.getCommentAndChangeset();%>
<% List<TagDescriptor> tds = document.getTagDescriptors(); %>



<div class="container">
  <h1><%= document.getDocName()%> <%= (sessionUser.getId() == urlUser.getId()) ? "" : " (View-only mode)" %></h1>

    <!-- this section is for tags -->
    <div>
        <form action="/tag" method="post" class="form-horizontal" role="form">
            <input type="hidden" name="entityType" value="document" />
            <input type="hidden" name="entityId" value="<%=document.getId()%>" />
            <ul id="entityTags">
                <% for (TagDescriptor td : tds){ %>
                <li><%=td.getTagName() %></li>
                <% } %>
            </ul>
            <button type="submit" class="btn btn-primary" name="submit" />Save Tags</button>
        </form>
    </div>

  <div id="content">
    <div class="caption">
      <div class="card2">
        <h3 class="card-heading simple"><%= sessionUser.getFirstName() %> <%= sessionUser.getLastName() %></h3>
        <div class="card-body">
          <form method="POST" id="comment1" action="/peer/<%= sessionUser.getId()%>/document/<%= document.getId()%>/comment" parsley-validate>
            <textarea id="textComment1" class="parsley-validated" name="comment" style="width:100%" parsley-trigger="change keyup"></textarea>
            <input type="hidden" name="docId" value="<%= document.getId()%>"/>
            <input type="hidden" name="peerId" value="<%= sessionUser.getId()%>"/>
            <input type="hidden" name="_method" value="POST">
            <button type="submit" class="btn btn-success ">Post</button>
          </form>
        </div>
      </div>

      <%for(Object o : objectList){
        if(o instanceof Comment){
          Comment comment = (Comment) o;%>

          <div class="card2">
          <h3 class="card-heading simple"><%= comment.getPeer().getFirstName() %> <%= comment.getPeer().getLastName() %></h3>
          <div class="card-body">
            <%= comment.getMessage() %>
          </div>
          <div class="card-actions parent">
            <form method="POST" action="/vote"  class="AjaxSubmit3 child">
              <input type="hidden" name="docid" value="<%= document.getId()%>"/>
              <input type="hidden" name="commentid" value="<%= comment.getId()%>"/>
              <input type="hidden" name="upvote" value="<%= comment.getUpVote()%>"/>
              <input type="hidden" name="downvote" value="<%= comment.getDownVote()%>"/>
              <button class="btn" id="btn1<%= comment.getId()%>" onclick="upVote1();" >
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
              <button  class="btn" id="btn2<%= comment.getId()+1%>" onclick="downVote1();" >
                <div class="parent">
                  <div class="child point" id="down-<%= comment.getId()+1%>" name="point"><%= comment.getDownVote()%></div>
                  <div class="child">&nbsp; <i class="fa fa-thumbs-down"></i></div>
                </div>
              </button>
              &nbsp;
            </form>
            <span class="child" style="font-style: italic;font-size: small;"><%= comment.getTimesAgo()%></span>
            &nbsp;&nbsp;
            <% if(sessionUser.getId() == document.getPeerId() || sessionUser.getId() == comment.getPeerId()){%>
            <a data-toggle="modal" data-id="<%= comment.getId()%>" class="confirmDeleteCommentDialog child"
               href="#deleteDialog">delete</a>
            <% } %>
          </div>
        </div>
       <%}if(o instanceof Changeset) {
        Changeset changeset = (Changeset) o;
        %>
      <div class="card2">
        <h3 class="card-heading simple"><%= changeset.getContent() %> - <span class="child" style="font-style: italic;font-size: small;"><%= changeset.getTimesAgo()%></span></h3>
        <div class="card-comments">
          <div class="comments-collapse-toggle">
            <a data-toggle="collapse" href="#<%= changeset.getId()%>-comments"><%= changeset.getComments().size()%> Comments<i class="icon-angle-down"></i></a>
          </div>
          <div id="<%= changeset.getId()%>-comments" class="comments collapse">
            <% for (Comment c: changeset.getOrderedComments()){%>
              <div class="media">
                <a class="pull-left" href="#">
                  <img class="media-object" data-src="holder.js/28x28" alt="avatar"/>
                </a>
                <div class="media-body">
                  <p class=""><%= c.getMessage()%></p>
                  <%--<textarea style="resize: none; width:100%" readonly ><%= c.getMessage()%></textarea>--%>
                </div>
                <div class="parent card-actions">
                  <form  id="upvotechangesetcomment" method="POST" action="/vote" class="AjaxSubmit1 child">
                    <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                    <input type="hidden" name="commentid" value="<%= c.getId()%>"/>
                    <input type="hidden" name="upvote" value="<%= c.getUpVote()%>"/>
                    <input type="hidden" name="downvote" value="<%= c.getDownVote()%>"/>
                    <button class="parent btn" id="btn3<%= c.getId()%>" onclick="upVote2();" >
                      <div class="child point" id="<%= c.getId()%>" name="point"><%= c.getUpVote()%></div>
                      <div class="child">&nbsp;<i class="fa fa-thumbs-up"></i></div>
                    </button>&nbsp;
                  </form>
                  <form id="downvotechangesetcomment" method="POST" action="/vote" class="AjaxSubmit2 child" >
                    <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                    <input type="hidden" name="commentid" value="<%= c.getId()%>"/>
                    <input type="hidden" name="upvote" value="<%= c.getUpVote()%>"/>
                    <input type="hidden" name="downvote" value="<%= c.getDownVote()%>"/>
                    <input type="hidden" name="_method" value="put"/>
                    <button  class="parent btn" id="btn4<%= c.getId()+1%>" onclick="downVote2();">
                      <div class="child point" id="<%= c.getId()+1%>" name="point"><%= c.getDownVote()%></div>
                      <div class="child">&nbsp;<i class="fa fa-thumbs-down"></i></div>
                    </button>
                    &nbsp;
                  </form>
                  <span class="child" style="font-style: italic;font-size: small;"><%= c.getTimesAgo()%></span>
                  &nbsp;&nbsp;
                  <% if(sessionUser.getId() == changeset.getPeerId() || sessionUser.getId() == c.getPeerId()){%>
                  <a data-toggle="modal" data-id="<%= c.getId()%>" class="confirmDeleteCommentDialog child"
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
                  <form method="POST" id="comment2" action="/peer/<%= sessionUser.getId()%>/document/<%= document.getId()%>/comment" parsley-validate>
                    <textarea name="comment" id="textComment2" class="parsely-validated" style="width:100%" parsley-trigger="change keyup"></textarea>
                    <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                    <input type="hidden" name="changesetid" value="<%= changeset.getId()%>"/>
                    <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>"/>
                    <input type="hidden" name="_method" value="put"/>
                    <button type="submit" class="btn btn-success ">Post</button>
                  </form>
                </div>
              </div>
          </div>
        </div>
      </div>
       <%} }%>
    </div>
  </div>

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
