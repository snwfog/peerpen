<%@ page import="com.peerpen.model.Peer" %>
<jsp:include page="/view/includes/static/header.jsp">
    <jsp:param name="title" value="FEED ME" />
</jsp:include>
<%@ include file="/view/includes/static/navbar_profile.jsp" %>

<% Peer peer = (Peer) request.getAttribute( "peerObject" ); %>

<div class="container-fluids greyish">
    <br/>

    <div class="row">
        <div class="col-md-3">
            <div class="card-white hovercard big">
                <img style="background-color:#fb5324;"/>
                <div class="avatar">
                    <img class="img-circle" src="<%= peer.getAvatar().getServletContextAvatarPath( request ) %>"></div>
                <div class="info">
                    <h4>User Name</h4>
                </div>

                <div class="bottom">
                    <p>
                        <a class="btn btn-primary" href="/peer/<%=sessionUser.getId()%>/profile">View Profile &raquo;</a>
                        <a class="btn btn-primary" href="/peer/<%=sessionUser.getId()%>/document">View Documents &raquo;</a>
                    </p>
                </div>
            </div>
        </div>


        <div class="col-md-8">
            <% if ( request.getAttribute( "feedableList" ) != null ) {%>
            <%@ include file="/view/feed/_showfeeds.jsp" %>
            <% } else { %>
            <div class="row-fluid">
                <div class="card">
                    <h2 class="card-heading simple">You Got No Feeds</h2>
                </div>
            </div>
            <%}%>
        </div>

        <div class="col-md-1">
        </div>

    </div>
</div>
    <hr>

    <%@ include file="/view/includes/static/footer.jsp" %>
