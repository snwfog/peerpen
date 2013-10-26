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

<%
        out.println(peer.getFirstName().toString());
        out.println(peer.getLastName().toString());
        out.println(peer.getUserName().toString());
        out.println(peer.getEmail().toString());
        out.println(Integer.parseInt(peer.getPoint().toString()));
        out.println(peer.getPersonalWebsite().toString());

    } else {
        response.sendRedirect("/login");
    }

%>


<%@ include file="/view/includes/static/footer.jsp" %>