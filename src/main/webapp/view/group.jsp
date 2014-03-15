<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.*" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar.jsp" %>

<% Group group = (Group) request.getAttribute("group"); %>
<% ArrayList<Peer> peers = (ArrayList<Peer>)group.getPeers();%>
<% ArrayList<Broadcast> broadcasts = (ArrayList<Broadcast>)group.getOrderedBroadcast();%>
<% ArrayList<Joingroup> joingroups = (ArrayList<Joingroup>)group.getRequests();%>
<% List<TagDescriptor> tds = group.getTagDescriptors(); %>

<%--
  Created by IntelliJ IDEA.
  User: waisk
  Date: 07/02/14
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>

<div class="cover-container group-cover"> </div>
<div class="container-fluid wrap-container">

<h1 class="group-header-title"><%= group.getGroupName()%> Group</h1>

<%if (group.getIsJoined(sessionUser.getId())){%>

    <img src="<%= sessionUser.getAvatar().getRelativeServletContextAvatarPathForSize( request,
                    Avatar.Size.LARGE ) %>" width="30px">
    <span class="feed-comment-commentor"><%= sessionUser.getFirstName() %> <%= sessionUser.getLastName() %> broadcasts from <%= group.getGroupName()%> peers</span>

    <br /><br />
    <div class="feed-comment-pointer"></div>
    <div class="feed-comment-body group-comment-body">
        <span class="feed-comment-body-text group-comment-content">
            <form method="POST" action="/group/<%= group.getId()%>/broadcast" parsley-validate style="text-align:right;">
                <textarea id="broadcast" class="parsley-validated group-comment-text-box" name="broadcast" parsley-trigger="change keyup"></textarea>
                <input type="hidden" name="groupid" value="<%= group.getId()%>"/>
                <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>"/>
                <input type="hidden" name="_method" value="POST">
                <button type="submit" class="btn btn-warning group-comment-btn">Post</button>
            </form>

            <br />
        </span>

        <div class="group-tag-body">
            <form action="/tag" method="post" class="form-horizontal" role="form">
                <input type="hidden" name="entityType" value="group" />
                <input type="hidden" name="entityId" value="<%=group.getId()%>" />
                <table cellspacing="0" cellpadding="0" style="width:100%;">
                    <tr>
                        <td class="group-tag-content">
                            <ul id="entityTags" class="group-tag-content-list">
                                <% for (TagDescriptor td : tds){ %>
                                <li><%=td.getTagName() %></li>
                                <% } %>
                            </ul>
                        </td>

                        <td class="group-tag-submit-box">
                            <button type="submit" class="btn btn-info group-tag-submit" name="submit" />Save Tags</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <br />

    <div class="profile group-profile">

        <%if (group.getAdminId()==sessionUser.getId() && joingroups!=null && joingroups.size()!=0){%>
        <div class="row">
            <div class="col-md-12">
                <h2 class="group-request-header">User Request to join your <%= group.getGroupName()%> group</h2>
                <%
                    for (Joingroup jg:joingroups){
                        if((group.getPeers().contains(jg.getPeer()))==false)
                        {
                %>
                <%--<div class="card2">--%>

                <div class="card-body">
                    <strong> Peer <%= jg.getPeer().getUserName() %> wants to join your group</strong>
                                                <span class="child" style="font-style: italic;font-size: small;">
                                                 <p class="text-right"><%= jg.getTimesAgo()%></p></span>
                    <div class="card-actions parent">
                        <form action="/joingroup" method="POST" class="form-horizontal child">
                            <input type="hidden" name="groupid" value="<%= jg.getGroupId()%>">
                            <input type="hidden" name="jgid" value="<%= jg.getId()%>">
                            <input type="hidden" name="peerid" value="<%= jg.getPeerId()%>">
                            <input type="hidden" name="_method" value="POST">
                            <button type="submit" class="btn btn-success" >Accept!</button>
                        </form>

                        <form action="/joingroup" method="POST" class="form-horizontal child">
                            <input type="hidden" name="groupid" value="<%= jg.getGroupId()%>">
                            <input type="hidden" name="jgid" value="<%= jg.getId()%>">
                            <input type="hidden" name="peerid" value="<%= jg.getPeerId()%>">
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="btn btn-danger" >Refuse!</button>
                        </form>
                    </div>
                </div>
                <br />
                <%}
                }
                %>
            </div>
        </div>
        <%}%>

    <div class="row">
        <div class="col-md-12">
            <h2 class="group-members-header">Members of this group</h2>

            <table class="table table-striped table-condensed">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Industry</th>
                    <th></th>
                    <th></th>

                </tr>
                </thead>
                <tbody>
                <div class="col-md-4 pull-left">
                <% for (Peer p:peers){%>
                <tr>
                    <td><%=p.getUserName()%></td>
                    <td><%=p.getEmail()%></td>
                    <td><%=p.getIndustry()%></td>
                    <td><a href="/peer/<%=p.getId()%>/document"> Documents </a></td>
                    <td>
                        <% if (p.getId()!=group.getAdminId()){ %>
                                <%--<div class="span8 child"><%=p.getUserName()%></div>--%>
                                <form action="/group" method="POST" class="form-horizontal child">
                                    <input type="hidden" name="groupid" value="<%= group.getId()%>">
                                    <input type="hidden" name="peerid" value="<%= p.getId()%>">
                                    <input type="hidden" name="_method" value="delete"/>
                                    <button type="submit" class="btn btn-danger" >Reject!</button>
                                </form>
                        <% } %>
                    </td>
                </tr>
                <%}%>
                    </div>
                </tbody>
            </table>

         </div>

        <%--<div id="row">--%>

                <%for(Broadcast broadcast : broadcasts ){%>
                <div class="card2">
                    <h3 class="card-heading simple"><%= broadcast.getPeer().getFirstName() %> <%= broadcast.getPeer().getLastName() %></h3>
                    <div class="card-body">
                        <strong><%= broadcast.getMessage() %></strong>

                        <span class="child" style="font-style: italic;font-size: small;">
                            <p class="text-right"><%= broadcast.getTimesAgo()%></p></span>
                    </div>

                    <%--<span class="child" style="font-style: italic;font-size: small;"><%= broadcast.getTimesAgo()%></span>--%>
                    &nbsp;&nbsp;
                    <% if(sessionUser.getId() == broadcast.getPeerId() || sessionUser.getId() == broadcast.getPeerId()){%>
                    <a data-toggle="modal" data-id="<%= broadcast.getId()%>" class="confirmDeleteBroadcastDialog child"
                       href="#deleteDialog">delete</a>
                    <% } %>
                 </div>
                <%}%>

             </div>
         <%--</div>--%>

    </div>

<%}else
{%>
    <div class="profile group-no-user-body">
        <h2 class="group-no-user-header">Important Notice</h2>
    <span class="group-no-user-text">You are not registered to this group.</span>
    </div>
<%}%>
</div>

<div class="modal fade" id="deleteDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Confirm delete</h4>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete this broadcast?</p>
            </div>
            <div class="modal-footer">
                <form id="deleteComment" method="POST" action="/group/<%= group.getId()%>/broadcast">
                    <input type="hidden" name="_method" value="delete"/>
                    <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>"/>
                    <input type="hidden" name="broadcastid" id="broadcastid" value=""/>
                    <input type="hidden" name="groupid" value="<%= group.getId()%>"/>
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                    <button type="submit" class="btn btn-primary">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>