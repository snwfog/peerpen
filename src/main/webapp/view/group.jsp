<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.*" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar.jsp" %>

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
        <%if (group.getAdminId()==sessionUser.getId()){%>
        <div id="row">
            <div class="accordion" id="accordion2">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                           <h2> User Request to join your <%= group.getGroupName()%> group </h2>
                        </a>
                    </div>
                    <div id="collapseOne" class="accordion-body collapse in">
                        <div class="accordion-inner">
                            <% if (joingroups!=null){
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
                                                        <button type="submit" class="btn btn-primary" >Accept!</button>
                                                    </form>

                                                    <form action="/joingroup" method="POST" class="form-horizontal child">
                                                        <input type="hidden" name="groupid" value="<%= jg.getGroupId()%>">
                                                        <input type="hidden" name="jgid" value="<%= jg.getId()%>">
                                                        <input type="hidden" name="peerid" value="<%= jg.getPeerId()%>">
                                                        <input type="hidden" name="_method" value="delete"/>
                                                        <button type="submit" class="btn btn-primary" >Refuse!</button>
                                                    </form>
                                                </div>
                                            </div>
                                        <%}
                                    }
                                }%>
                            </div>
                    </div>
                </div>

            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                        <h2>Reject Member from your <%=group.getGroupName()%></h2>
                    </a>
                </div>
                <div id="collapseTwo" class="accordion-body collapse in">
                    <div class="accordion-inner">
                        <% for (Peer p:peers){
                            if (p.getId()!=group.getAdminId()){%>

                          <div class="card-body">
                              <div class="input-append">
                                  <%--<div class="span8 child"><%=p.getUserName()%></div>--%>
                                  <input class="input-medium" placeholder=".input-medium" disabled  value="<%=p.getUserName()%>">
                                  <form action="/group" method="POST" class="form-horizontal child">
                                      <input type="hidden" name="groupid" value="<%= group.getId()%>">
                                      <input type="hidden" name="peerid" value="<%= p.getId()%>">
                                      <input type="hidden" name="_method" value="delete"/>
                                      <button type="submit" class="btn btn-primary" >Reject!</button>
                                  </form>
                               </div>
                           </div>
                        <%}}%>
                    </div>
                </div>
               </div>
            </div>
         </div>
        <%}%>
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