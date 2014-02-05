<%@ page import="com.peerpen.model.Peer" %>
<jsp:include page="/view/includes/static/header.jsp">
    <jsp:param name="title" value="FEED ME" />
</jsp:include>


<% Peer peer = (Peer) request.getAttribute( "peerObject" ); %>


<div class="container-fluids greyish">
    <br/>

    <div class="row">
        <div class="col-md-3">
            <div class="card-white hovercard big">
                <img style="background-color:#fb5324;"/>
                <div class="avatar">
                    <img class="img-circle"
                         src=<%= peer.getAvatarId() != null ? peer.getAvatar() : "/assets/images/peers/avatars/lg/default-avatar.jpg" %>>
                </div>

                <div class="info">
                    <h4>User Name</h4>
                </div>

                <div class="bottom">
                    <p>
                        <a class="btn btn-primary" href="/peer/<%=peer.getId()%>/profile">View Profile &raquo;</a>
                        <a class="btn btn-primary" href="/peer/<%=peer.getId()%>/document">View Documents &raquo;</a>
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



        <%--<div class="row-fluid">--%>
            <%--<div class="navbar navbar-inverse">--%>
                <%--<div class="navbar-inner">--%>
                    <%--<div class="container-fluid">--%>
                        <%--<button type="button" class="btn btn-navbar" data-toggle="collapse"--%>
                                <%--data-target=".nav-collapse">--%>
                            <%--<span class="icon-bar"></span>--%>
                            <%--<span class="icon-bar"></span>--%>
                            <%--<span class="icon-bar"></span>--%>
                        <%--</button>--%>
                        <%--<a class="brand" href="#">Feeds</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>


        <%--<div class="row-fluid">--%>
        <%--<div class="card">--%>
        <%--<h2 class="card-heading simple">Heading</h2>--%>
        <%--<div class="card-body">--%>
        <%--<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>--%>
        <%--<p><a class="btn" href="#">View details &raquo;</a></p>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>

        <%--<div class="row-fluid">--%>
        <%--<div class="card">--%>
        <%--<div class="card-heading image">--%>
        <%--<img src="holder.js/46x46" alt=""/>--%>
        <%--<div class="card-heading-header">--%>
        <%--<h3>Simple News Card</h3>--%>
        <%--<span>Published today - 08.34 AM</span>--%>
        <%--</div>--%>
        <%--<div> &nbsp</div>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>

        <!--/row-->

    <hr>

    <%@ include file="/view/includes/static/footer.jsp" %>
