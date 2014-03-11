<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.*" %>

<% for(Feedable f : (List<Feedable>)request.getAttribute("feedableList")){ %>
<% if (f.getType().equalsIgnoreCase("Changeset")){%>
<div class="feed-change-container">
    <div class="span4">
        <div class="">
            <div class="card-white feed-change-title">
                <h2 id="feed-change-header-bar" class="card-heading simple">
                    <span class="feed-change-source"><b><%=((Changeset)f.getTrueSelf()).getPeer().getFirstName()%></b></span>
                    suggested changes to
                    <span class="feed-change-source"><%=((Changeset)f.getTrueSelf()).getHunk().getDocument().getDocName()%></span>

                    <span class="feed-change-date">14 February 2014</span>
                </h2>

                <div class="card-body">

                    <div class="span6">
                        <div class="bs-example feed-change-body">
                            <div class="bs-example-label feed-change-body-source">From</div>
                            <p class="feed-change-new-line">
                                <%=((Changeset)f.getTrueSelf()).getContent()%>
                            </p>
                        </div>
                    </div>

                    <div class="feed-change-arrow-down">
                        &#8595;
                    </div>

                    <div class="span6">
                        <div class="bs-example feed-change-body">
                            <div class="bs-example-label feed-change-body-source">To</div>
                            <%=((Changeset)f.getTrueSelf()).getHunk().getContent()%>
                        </div>
                    </div>



                    <div class="clearfix"></div>
                    <%Integer documentId = ((Changeset)f.getTrueSelf()).getHunk().getDocumentId();%>
                    <p><a class="btn feed-change-view-doc-btn" href="/peer/<%=sessionUser.getId()%>/document/<%=documentId%>">View Document</a></p>
                </div>
            </div>
        </div>

    </div>
</div>
<br />
<% } %>

<% if (f.getType().equalsIgnoreCase("Comment")){%>
<img src="<%= ((Comment)f.getTrueSelf()).getPosterPeer().getAvatar().getDefaultAvatarSource( request ) %>" width="30px">
<span class="feed-comment-commentor"><%=((Comment)f.getTrueSelf()).getPosterPeer().getFirstName()%> commented</span>

<span class="feed-comment-date">
Published <%= ((Comment)f.getTrueSelf()).getTimesAgo()%>
</span>
<br /><br />
<div class="feed-comment-pointer"></div>
<div class="feed-comment-body">
			<span class="feed-comment-body-text">
				<%=((Comment)f.getTrueSelf()).getMessage()%>
			</span>
    </span>
</div>
</span>
<br />
<% } %>

<% if (f.getType().equalsIgnoreCase("Broadcast")){%>
<img src="<%= ((Broadcast)f.getTrueSelf()).getPeer().getAvatar().getServletContextAvatarPath( request ) %>" width="30px">
<span class="feed-comment-commentor"><%=((Broadcast)f.getTrueSelf()).getPeer().getFirstName()%> broadcasted from <%=((Broadcast)f.getTrueSelf()).getGroup().getGroupName()%></span>

    <span class="feed-comment-date">
        Published <%= ((Broadcast)f.getTrueSelf()).getTimesAgo()%>
    </span>
<br /><br />
<div class="feed-comment-pointer"></div>
<div class="feed-comment-body" style="-webkit-box-shadow: 10px 10px 0px 0px rgba(149, 165, 166, 1);
	-moz-box-shadow: 10px 10px 0px 0px rgba(149, 165, 166, 1);
	box-shadow: 10px 10px 0px 0px rgba(149, 165, 166, 1)">
        <span class="feed-comment-body-text">
            <%=((Broadcast)f.getTrueSelf()).getMessage()%>
        </span>
    </span>
</div>
</span>
<br />
<% } %>

<% if (f.getType().equalsIgnoreCase("Joingroup")){%>
<img src="<%= ((Joingroup)f.getTrueSelf()).getPeer().getAvatar().getServletContextAvatarPath( request ) %>" width="30px">
<span class="feed-comment-commentor"><%=((Joingroup)f.getTrueSelf()).getPeer().getFirstName()%> requests to join your group</span>

    <span class="feed-comment-date">
        Published <%= ((Joingroup)f.getTrueSelf()).getTimesAgo()%>
    </span>
<br /><br />
<div class="feed-comment-pointer"></div>
<div class="feed-comment-body" style="-webkit-box-shadow: 10px 10px 0px 0px rgba(189, 195, 199, 1);
	-moz-box-shadow: 10px 10px 0px 0px rgba(189, 195, 199, 1);
	box-shadow: 10px 10px 0px 0px rgba(189, 195, 199, 1)">
        <span class="feed-comment-body-text">
            Peer <%=((Joingroup)f.getTrueSelf()).getPeer().getUserName()%> wants to join your <%=((Joingroup)f.getTrueSelf()).getGroup().getGroupName()%> group
        </span>
    </span>
</div>
</span>
<br />
<% } %>

<% } %>