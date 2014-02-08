<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.*" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar_profile.jsp" %>

<% Group group = (Group) request.getAttribute("group"); %>
<% ArrayList<Peer> peers = (ArrayList<Peer>)group.getPeers();%>
<% ArrayList<Broadcast> broadcasts = (ArrayList<Broadcast>)group.getBroadcasts();%>

<%--
  Created by IntelliJ IDEA.
  User: waisk
  Date: 07/02/14
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%if (group.getIsJoined(sessionUser.getId())){%>
<h1><%= group.getGroupName()%></h1>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <h2> Members of this group </h2>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Username</th>
                    <th>Email</th>

                </tr>
                </thead>
                <tbody>
                <% for (Peer p:peers){%>
                <tr>
                    <td><%=p.getFirstName()%></td>
                    <td><%=p.getLastName()%></td>
                    <td><%=p.getUserName()%></td>
                    <td><%=p.getEmail()%></td>
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
                        <%= broadcast.getMessage() %>
                    </div>

                 </div>
                <%}%>

             </div>
         </div>

    </div>
</div>
<%}else
{%>
    <h1> You are not registered to this group.</h1>
<%}%>
<%@ include file="/view/includes/static/footer.jsp" %>