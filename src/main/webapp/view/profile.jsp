<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>

<%
    Peer peer = (Peer) session.getAttribute("user");
    if (peer != null) {

%>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">PeerPen</a>
        </div>

    </div>
</div>

<br><br>

<p align="center"> (<a href="/logout.do">logout</a>)</p>
<table>
    <tr><td>First</td><td><%= peer.getFirstName().toString() %></td></tr>
    <tr><td>Last</td><td><%= peer.getLastName().toString() %></td></tr>
    <tr><td>Username</td><td><%= peer.getUserName().toString() %></td></tr>
    <tr><td>Email</td><td><%= peer.getEmail().toString() %></td></tr>
    <tr><td>Point</td><td><%= Integer.parseInt(peer.getPoint().toString()) %></td></tr>
    <tr><td>Website</td><td><%= peer.getPersonalWebsite().toString() %></td></tr>
</table>

<%
    } else {
        response.sendRedirect("/login");
    }
%>


<%@ include file="/view/includes/static/footer.jsp" %>