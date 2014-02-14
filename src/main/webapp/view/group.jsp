<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.*" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar_profile.jsp" %>

<% Group group = (Group) request.getAttribute("group"); %>
<% ArrayList<Peer> peers = (ArrayList<Peer>)group.getPeers();%>
<% ArrayList<Broadcast> broadcasts = (ArrayList<Broadcast>)group.getOrderedBroadcast();%>
<% ArrayList<Joingroup> joingroups = (ArrayList<Joingroup>)group.getRequests();%>

<%--
  Created by IntelliJ IDEA.
  User: waisk
  Date: 07/02/14
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%if (group.getIsJoined(sessionUser.getId())){%>
<br>
<h1><%= group.getGroupName()%> Group Created by <%= group.getAdminId()%> </h1>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <h2> Members of this group </h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Industry</th>

                </tr>
                </thead>
                <tbody>
                <% for (Peer p:peers){%>
                <tr>
                    <td><%=p.getFirstName()%></td>
                    <td><%=p.getLastName()%></td>
                    <td><%=p.getUserName()%></td>
                    <td><%=p.getEmail()%></td>
                    <td><%=p.getIndustry()%></td>
                    <td><a href="/peer/<%=p.getId()%>/document"> Documents </a></td>
                </tr>
                <%}%>
                </tbody>
            </table>
         </div>

        <div id="row">
            <div class="col-md-4">
                <h2> Broadcasts from <%= group.getGroupName()%> peers </h2>
                    <div class="caption">
                        <div class="card2">
                            <h3 class="card-heading simple"><%= sessionUser.getFirstName() %> <%= sessionUser.getLastName() %></h3>
                            <div class="card-body">
                                <form method="POST" action="/group/<%= group.getId()%>/broadcast" parsley-validate>
                                    <textarea id="broadcast" class="parsley-validated" name="broadcast" style="width:100%" parsley-trigger="change keyup"></textarea>
                                    <input type="hidden" name="groupid" value="<%= group.getId()%>"/>
                                    <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>"/>
                                    <input type="hidden" name="_method" value="POST">
                                    <button type="submit" class="btn btn-success ">Post</button>
                                </form>
                            </div>
                        </div>
                    </div>


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
         </div>







        <div id="row">
            <div class="col-md-4">
                <h2> User Request to join your <%= group.getGroupName()%> group </h2>
                <div class="caption">
                    <div class="card2">
                        <h3 class="card-heading simple"><%= sessionUser.getFirstName() %> <%= sessionUser.getLastName() %></h3>
                        <div class="card-body">
                            <% if (joingroups!=null){
                                        for (Joingroup jp:joingroups){
                                            %>
                                            <div class="card2">

                                                <div class="card-body">
                                                    <strong> Peer <%= jp.getPeer().getUserName() %> wants to join your group</strong>
                                        <%}
                                    }%>
                                        <%--else--%>
                                        <%--{ %>--%>
                                                    <%--<h2> No user request to join your group  </h2> --%>
                                        <%--<%}--%>




                            <%--<form method="POST" action="/group/<%= group.getId()%>/broadcast" parsley-validate>--%>
                                <%--<textarea id="broadcast" class="parsley-validated" name="broadcast" style="width:100%" parsley-trigger="change keyup"></textarea>--%>
                                <%--<input type="hidden" name="groupid" value="<%= group.getId()%>"/>--%>
                                <%--<input type="hidden" name="peerid" value="<%= sessionUser.getId()%>"/>--%>
                                <%--<input type="hidden" name="_method" value="POST">--%>
                                <%--<button type="submit" class="btn btn-success ">Post</button>--%>
                            <%--</form>--%>
                        </div>
                    </div>
                </div>


            </div>
        </div>







    </div>
</div>
<%}else
{%>
    <br>
    <h1> You are not registered to this group.</h1>
<%}%>

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