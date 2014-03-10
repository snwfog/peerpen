<jsp:include page="/view/includes/static/header.jsp">
    <jsp:param name="title" value="FEED ME"/>
</jsp:include>
<%@ include file="/view/includes/static/navbar.jsp" %>
<% ArrayList<Group> navGroups =(ArrayList<Group>) sessionUser.getGroups();%>

<div class="cover-container"> </div>

<div id="feed-container" class="container-fluid wrap-container">

    <img />
    <div class="avatar avatar-container" style="top:-25px; position:relative;">
		<span class="info user-details-feed">
		        <a href="/peer/<%=sessionUser.getId()%>/profile" style="color:#fff;">
                    <h4><%=sessionUser.getFirstName()%> <%=sessionUser.getLastName()%></h4>
                </a>
		</span>
        <a href="/peer/<%=sessionUser.getId()%>/profile">
            <img class="img-circle" src="<%= sessionUser.getAvatar().getDefaultAvatarSource( request ) %>" width="230px">
        </a>

    </div>

    <br />

    <div class="bottom" style="text-align:right; top:-25px; position:relative;">
        <p>
            <a class="btn btn-warning btn-sm" href="/peer/<%=sessionUser.getId()%>/profile">View Profile</a>
            <a class="btn btn-warning btn-sm" href="/peer/<%=sessionUser.getId()%>/document">View Documents</a>
        </p>
    </div>

    <h1 style="font-family:'Oswald', sans-serif; text-align:center; border-bottom:1px solid #bdc3c7;">Feeds</h1>

    <!-- BEGINNING of _showfeed -->

        <% if ( request.getAttribute( "feedableList" ) != null ) {%>
    <%@ include file="/view/feed/_showfeeds.jsp" %>
        <% } else { %>
    <!-- WHEN NO FEEDS -->

    <div class="row-fluid">
        <div class="card">
            <h2 class="card-heading simple">You Got No Feeds</h2>
        </div>
    </div>
    <%}%>

</div>

</div>

<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="/assets/js/lib/sticky/jquery.sticky.js"></script>
<script>
    $(document).ready(function()
    {
        $("#doc-changes-sticker").width($("#feed-container").width());
        $("#doc-changes-sticker").sticky({topSpacing:0});
    });

<%@ include file="/view/includes/static/footer.jsp" %>
