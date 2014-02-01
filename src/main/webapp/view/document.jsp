<%@ include file="/view/includes/static/header.jsp" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="com.peerpen.model.Comment" %>
<%@ page import="com.peerpen.model.Changeset" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.TagDescriptor" %>
<script src="/assets/js/custom/tag_autocomplete_caller.js"></script>

<%--Declare all request variables here, easy to debug!!!--%>
<% Peer sessionUser = (Peer) request.getAttribute("sessionUser"); %>
<% Peer urlUser; %>
<% if (request.getAttribute("urlUser") != null){urlUser= (Peer) request.getAttribute("urlUser");}else{urlUser=sessionUser;} %>
<% Document document =(Document) request.getAttribute("document");%>
<% List<Object> objectList = document.getCommentAndChangeset();%>



<div class="container">
  <h1><%= document.getDocName()%> <%= (sessionUser.getId() == urlUser.getId()) ? "" : " (View-only mode)" %></h1>

    <!-- this section is for tags -->
    <div>
        <% List<TagDescriptor> tds = document.getTagDescriptors(); %>
        <form action="/tag.do" method="post" class="form-horizontal" role="form">
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
    <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
      <li class="active"><a href="#commentSection" data-toggle="tab">Comments</a></li>
    </ul>

    <div id="my-tab-content" class="tab-content">
      <div class="tab-pane active" id="commentSection">
        <h2>Comments</h2>
        <p>
        <div class="caption">
          <div class="card">
            <h3 class="card-heading simple"><%= sessionUser.getFirstName() %> <%= sessionUser.getLastName() %></h3>
            <div class="card-body">
              <form method="POST" action="/peer/<%= sessionUser.getId()%>/document/<%= document.getId()%>/comment">

                <textarea name="comment" style="width:100%"></textarea>
                <input type="hidden" name="docId" value="<%= document.getId()%>"/>
                <input type="hidden" name="peerId" value="<%= sessionUser.getId()%>"/>
                <input type="hidden" name="_method" value="POST">
                <button type="submit" class="btn btn-success ">Post</button>

              </form>
            </div>
          </div>


          <%for(Object o : objectList){
            if(o instanceof Comment){
              Comment c = (Comment) o;%>

              <div class="card">
              <h3 class="card-heading simple"><%= c.getPeer().getFirstName() %> <%= c.getPeer().getLastName() %></h3>
              <div class="card-body">
                <%= c.getMessage() %>
              </div>
              <div class="card-actions">
                <form method="POST" action="/vote"  class="AjaxSubmit3">

                  <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                  <input type="hidden" name="commentid" value="<%= c.getId()%>"/>
                  <input type="hidden" name="upvote" value="<%= c.getUpVote()%>"/>
                  <input type="hidden" name="downvote" value="<%= c.getDownVote()%>"/>


                  <button class="btn" onclick="upVote1();" >
                    <div class="point" id="up-<%= c.getId()%>" name="point"><%= c.getUpVote()%></div>&nbsp;<i class="fa fa-thumbs-up"></i></button>&nbsp;


                </form>
                <form method="POST" action="/vote"  id="downvotecomment" class="AjaxSubmit4">

                  <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                  <input type="hidden" name="commentid" value="<%= c.getId()%>"/>
                  <input type="hidden" name="upvote" value="<%= c.getUpVote()%>"/>
                  <input type="hidden" name="downvote" value="<%= c.getDownVote()%>"/>
                  <input type="hidden" name="_method" value="put"/>


                  <button  class="btn" onclick="downVote1();" >
                    <div class="point" id="down-<%= c.getId()+1%>" name="point"><%= c.getDownVote()%></div>&nbsp; <i class="fa fa-thumbs-down"></i></button>&nbsp;

                </form>

                <% if(sessionUser.getId() == document.getPeerId() || sessionUser.getId() == c.getPeerId()){%>
                <a data-toggle="modal" data-id="<%= c.getId()%>" class="confirmDeleteCommentDialog"
                   href="#deleteDialog">delete</a>
                <% } %>
              </div>
            </div>
           <%}if(o instanceof Changeset) {
            Changeset ch = (Changeset) o;
            %>
          <div class="card">
              <h3 class="card-heading simple"> <%= ch.getContent() %> </h3>
              <%--<div class="card-body">--%>
              <%--<p>Change format of Education</p>--%>
              <%--</div>--%>
              <div class="card-comments">
                  <div class="comments-collapse-toggle">
                      <a data-toggle="collapse" href="#<%= ch.getId()%>-comments"><%= ch.getComments().size()%> Comments<i class="icon-angle-down"></i></a>
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


                                  <button class="btn" onclick="upVote2();" >
                                      <div class="point" id="<%= c.getId()%>" name="point"><%= c.getUpVote()%></div>&nbsp;<i class="fa fa-thumbs-up"></i></button>&nbsp;

                              </form>

                              <form data-id="<%= c.getId()%>" id="downvotechangesetcomment" method="POST" action="/vote"  class="AjaxSubmit2" >

                                  <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                                  <input type="hidden" name="commentid" value="<%= c.getId()%>"/>
                                  <input type="hidden" name="upvote" value="<%= c.getUpVote()%>"/>
                                  <input type="hidden" name="downvote" value="<%= c.getDownVote()%>"/>
                                  <input type="hidden" name="_method" value="put"/>

                                  <button  class="btn" onclick="downVote2();">
                                      <div class="point" id="<%= c.getId()+1%>" name="point"><%= c.getDownVote()%></div>&nbsp; <i class="fa fa-thumbs-down"></i></button>&nbsp;
                              </form>

                              <% if(sessionUser.getId() == ch.getPeerId() || sessionUser.getId() == c.getPeerId()){%>
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
                              <form method="POST" action="/peer/<%= sessionUser.getId()%>/document/<%= document.getId()%>/comment">

                                  <textarea name="comment" style="width:100%"></textarea>
                                  <input type="hidden" name="docid" value="<%= document.getId()%>"/>
                                  <input type="hidden" name="changesetid" value="<%= ch.getId()%>"/>
                                  <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>"/>
                                  <input type="hidden" name="_method" value="put"/>

                                  <%--<br />--%>
                                  <button type="submit" class="btn btn-success ">Post</button>

                              </form>
                          </div>
                      </div>
                  </div>
              </div>
          </div>


           <%} }%>

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
    <form id="deleteComment" method="POST" action="/peer/<%= sessionUser.getId()%>/document/<%= document.getId()%>/comment">
      <input type="hidden" name="_method" value="_delete"/>
      <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>"/>
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
