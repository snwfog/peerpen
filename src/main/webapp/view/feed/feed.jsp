<jsp:include page="/view/includes/static/header.jsp">
  <jsp:param name="title" value="Feeds"/>
</jsp:include>
<%@ include file="/view/includes/static/navbar.jsp" %>
<% ArrayList<Group> navGroups =(ArrayList<Group>) sessionUser.getGroups();%>

<div class="cover-container"></div>

<div id="feed-container" class="container-fluid wrap-container">

    <img />
    <div class="avatar avatar-container feed-avatar-body">
		<span class="info user-details-feed">
		        <a href="/peer/<%=sessionUser.getId()%>/profile" style="color:#3A3838;">
                    <h2><%=sessionUser.getFirstName()%> <%=sessionUser.getLastName()%></h2>
                </a>
		</span>
        <a href="/peer/<%=sessionUser.getId()%>/profile">
            <img class="img-circle" src="<%= sessionUser.getAvatar().getDefaultAvatarSource( request ) %>" width="230px">
        </a>

    </div>

    <br />

    <div class="bottom feed-profile-body">
        <p>
            <a class="btn btn-warning btn-sm" href="/peer/<%=sessionUser.getId()%>/profile">View Profile</a>
            <a class="btn btn-warning btn-sm" href="/peer/<%=sessionUser.getId()%>/document">View Documents</a>
        </p>
    </div>

    <h1 class="feed-profile-header">Feeds</h1>

    <!-- BEGINNING of _showfeed -->

        <% if ( ((ArrayList)request.getAttribute( "feedableList" )).size() != 0 ) {%>
    <%@ include file="/view/feed/_showfeeds.jsp" %>
        <% } else { %>
    <!-- WHEN NO FEEDS -->

    <div class="profile feed-no-feeds-body"">
        <h2 class="feed-no-feeds-header">Important Notice</h2>
        <span class="feed-no-feeds-content">Sorry, you have no feeds yet.</span>
    </div>
    <%}%>

</div>

<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="/assets/js/lib/sticky/jquery.sticky.js"></script>
<script>
    $(document).ready(function()
    {
        $("#doc-changes-sticker").width($("#feed-container").width());
        $("#doc-changes-sticker").sticky({topSpacing:0});
    });
</script>
<%@ include file="/view/includes/static/footer.jsp" %>
