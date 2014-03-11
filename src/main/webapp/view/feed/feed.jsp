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

        <% if ( ((ArrayList)request.getAttribute( "feedableList" )).size() != 0 ) {%>
    <%@ include file="/view/feed/_showfeeds.jsp" %>
        <% } else { %>
    <!-- WHEN NO FEEDS -->

    <div class="profile" style="position:relative; top:30px; background-color:#fff; padding:35px;
                padding:0px;
                text-align:center;
                -webkit-box-shadow: 10px 10px 0px 0px rgba(149,165,166,1);
				-moz-box-shadow: 10px 10px 0px 0px rgba(149,165,166,1);
				box-shadow: 10px 10px 0px 0px rgba(149,165,166,1);">
        <h2 style="background-color:#e74c3c; color:#fff; margin:0px; padding:15px; font-family:'Oswald', sans-serif; text-align:left; font-size:18px;">Important Notice</h2>
        <span style="display:block; padding:25px;">Sorry, you have no feeds yet.</span>
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
