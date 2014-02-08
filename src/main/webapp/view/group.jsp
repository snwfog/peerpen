<%@ page import="com.peerpen.model.Group" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.PeersGroup" %>
<%@ include file="/view/includes/static/header.jsp" %>
<% Peer sessionUser = (Peer) request.getAttribute("sessionUser"); %>
<% PeersGroup peersGroup = (PeersGroup) request.getAttribute("peersGroup"); %>
<% Group group = (Group) request.getAttribute("group"); %>
<% ArrayList<Peer> peers = (ArrayList<Peer>)group.getMembers();%>

<%--
  Created by IntelliJ IDEA.
  User: waisk
  Date: 07/02/14
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%if (sessionUser.getId()==peersGroup.getPeerId()){%>
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
                </tr>
                <%}%>
                </tbody>
            </table>
         </div>
    </div>
</div>
<%}else
{%>
    <h1> You are not registered to this group.</h1>
<%}%>
<%@ include file="/view/includes/static/footer.jsp" %>