
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

<div class="cover-container document-cover"> </div>
<div class="container-fluid wrap-container">

    <h1 class="document-title"><%= document.getDocName()%> <%= (sessionUser.getId() == urlUser.getId()) ? "" : " (View-only mode)" %></h1>

    <%for(Hunk hunk: hunks){ %>
        <div class="profile document-hunk-container">
            <span class="document-hunk-content"><%= hunk.getContent()%></span>
        </div>
        <br />
      <%}%>

    <img src="<%= sessionUser.getAvatar().getRelativeServletContextAvatarPathForSize( request,
                    Avatar.Size.LARGE ) %>" width="30px">
    <span class="feed-comment-commentor"><%= sessionUser.getFirstName() %> <%= sessionUser.getLastName() %> comments</span>
    <br /><br />
    <div class="feed-comment-pointer"></div>
    <div class="feed-comment-body document-comment-body">
        <span class="feed-comment-body-text document-comment-body-text">
            <form method="POST" id="comment1" action="/peer/<%= sessionUser.getId()%>/document/<%= document.getId()%>/comment" parsley-validate>
                <textarea id="textComment1" class="parsley-validated document-comment-body-text-box" name="comment" parsley-trigger="change keyup"></textarea>
                <input type="hidden" name="docId" value="<%= document.getId()%>"/>
                <input type="hidden" name="peerId" value="<%= sessionUser.getId()%>"/>
                <input type="hidden" name="_method" value="POST">
                <button type="submit" class="btn btn-warning document-comment-body-text-submit">Post</button>
            </form>

            <br />
        </span>

        <div class="document-tag-body">
            <form action="/tag" method="post" class="form-horizontal" role="form">
                <input type="hidden" name="entityType" value="document" />
                <input type="hidden" name="entityId" value="<%=document.getId()%>" />
                <table cellspacing="0" cellpadding="0" style="width:100%;">
                    <tr>
                        <td class="document-tag-body-box">
                            <ul id="entityTags" class="document-tag-body-box-list">
                                <% for (TagDescriptor td : tds){ %>
                                <li><%=td.getTagName() %></li>
                                <% } %>
                            </ul>
                        </td>

                        <td class="document-tag-submit-box">
                            <button type="submit" class="btn btn-info document-tag-submit-box-body" name="submit" />Save Tags</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <br />
    <div class="document-separator"></div>
    <br />

    <%for(Comment comment: comments){ Peer p = new Peer().find(comment.getPosterPeerId()); %>
    <img src="<%= comment.getPosterPeer().getAvatar().getRelativeServletContextAvatarPathForSize(request,
                    Avatar.Size.SMALL) %>" width="30px">
    <span class="feed-comment-commentor"><%= p.getFirstName() %> <%= p.getLastName() %> commented</span>

    <span class="feed-comment-date">
        Published <%= comment.getTimesAgo()%>
    </span>
    <br /><br />
    <div class="feed-comment-pointer"></div>
    <div class="feed-comment-body document-comment2-body">
        <span class="feed-comment-body-text document-comment2-body-text">
            <%= comment.getMessage() %>
            <br />

        </span>

        <div class="document-upvote-body">
            <form method="POST" action="/vote"  class="AjaxSubmit3 child">
                <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                <input type="hidden" name="commentid" value="<%= comment.getId()%>"/>
                <input type="hidden" name="upvote" value="<%= comment.getUpVote()%>"/>
                <input type="hidden" name="downvote" value="<%= comment.getDownVote()%>"/>
                <button class="btn btn-xs document-vote-btn" id="btn1<%= comment.getId()%>" onclick="upVote1();">
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
                <button  class="btn btn-xs document-vote-btn" id="btn2<%= comment.getId()+1%>" onclick="downVote1();">
                    <div class="parent">
                        <div class="child point" id="down-<%= comment.getId()+1%>" name="point"><%= comment.getDownVote()%></div>
                        <div class="child">&nbsp; <i class="fa fa-thumbs-down"></i></div>
                    </div>
                </button>
                &nbsp;
            </form>

            <% if(sessionUser.getId() == document.getPeerId() || sessionUser.getId() == comment.getPosterPeerId()){%> |
            <a data-toggle="modal" data-id="<%= comment.getId()%>" class="confirmDeleteCommentDialog child btn btn-danger"
                href="#deleteDialog">delete</a>
            <% } %>
        </div>
    </div>
    <br />
    <%}%>

    <br /><br />




<%for(Changeset changeset: changesets){ %>
    <div class="profile document-changeset-body">
        <span class="row document-changeset-header">
            <span class="col-lg-9 feed-comment-source"><%= changeset.getContent() %></span>

            <span class="col-lg-3 document-changeset-title">
                Edited <%= changeset.getTimesAgo()%>
                <br />
                <a data-toggle="collapse" href="#<%= changeset.getId()%>-comments" class="document-changeset-size"><%=changeset.getOrderedComments().size()%> Comments<i class="icon-angle-down"></i></a>
            </span>
        </span>
        <br />
        <img src="<%= sessionUser.getAvatar().getRelativeServletContextAvatarPathForSize( request,
                    Avatar.Size.LARGE ) %>" width="30px" class="document-changeset-poster-avatar">
        <span class="feed-comment-commentor"><%= sessionUser.getFirstName() %> <%= sessionUser.getLastName() %> comments</span>
        <br />
        <div class="feed-comment-pointer document-changeset-poster-pointer"></div>
        <span class="document-changeset-poster-body">
            <form method="POST" id="comment2" action="/peer/<%= sessionUser.getId()%>/document/<%= document.getId()%>/comment" parsley-validate>
                <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                <input type="hidden" name="changesetid" value="<%= changeset.getId()%>"/>
                <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>"/>
                <input type="hidden" name="_method" value="put"/>
                <textarea name="comment" id="textComment2" class="parsely-validated document-changeset-body-box" parsley-trigger="change keyup"></textarea>
                <br/>
                <div class="media-body">
                    <button type="submit" class="btn btn-success document-changeset-body-btn">Post</button>
                </div>
            </form>
        </span>

        <div id="<%= changeset.getId()%>-comments">
        <table class="table table-striped table-condensed document-changeset-poster-comment-body">
            <% for (Comment comment: changeset.getOrderedComments()){%>
            <tr>
                <td class="document-changeset-poster-comment-content">
                    <a href="/peer/<%= comment.getPosterPeer().getId()%>">
                        <img title="<%= comment.getPosterPeer().getFirstName()%> <%= comment.getPosterPeer().getLastName()%>" src="<%= comment.getPosterPeer().getAvatar().getRelativeServletContextAvatarPathForSize( request,
                    Avatar.Size.SMALL ) %>" alt="avatar" width="30px">
                    </a>
                    <span class="feed-comment-commentor"><%= comment.getPosterPeer().getFirstName()%> <%= comment.getPosterPeer().getLastName()%> commented</span>

                    <span class="feed-comment-date">
                        Published <%= comment.getTimesAgo()%>
                    </span>

                    <br /><br />

                    <i><%= comment.getMessage()%></i>

                    <br />

                    <div class="document-changeset-poster-comment-vote-body">
                    <form  id="upvotechangesetcomment" method="POST" action="/vote" class="AjaxSubmit1 child">
                        <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                        <input type="hidden" name="commentid" value="<%= comment.getId()%>"/>
                        <input type="hidden" name="upvote" value="<%= comment.getUpVote()%>"/>
                        <input type="hidden" name="downvote" value="<%= comment.getDownVote()%>"/>
                        <button class="parent btn btn-xs document-vote-btn" id="btn3<%= comment.getId()%>" onclick="upVote2();">
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
                        <button  class="parent btn btn-xs document-vote-btn" id="btn4<%= comment.getId()+1%>" onclick="downVote2();">
                            <div class="child point" id="<%= comment.getId()+1%>" name="point"><%= comment.getDownVote()%></div>
                            <div class="child">&nbsp;<i class="fa fa-thumbs-down"></i></div>
                        </button>
                        &nbsp;
                    </form>
                    <% if(sessionUser.getId() == changeset.getPeerId() || sessionUser.getId() == comment.getPosterPeerId()){%>
                    <a data-toggle="modal" data-id="<%= comment.getId()%>" class="confirmDeleteCommentDialog child btn btn-danger"
                       href="#deleteDialog">delete</a>
                    </div>
                    <% } %>
                </td>
            </tr>
            <%}%>
        </table>
        </div>
    </div>
    <br /><br />
<%}%>

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
