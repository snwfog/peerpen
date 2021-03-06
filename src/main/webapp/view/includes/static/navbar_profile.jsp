<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ page import="com.peerpen.model.Avatar" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="java.util.List" %>
<!-- must set navbar z-index to be <= 100 otherwise search autocomplete box will be hidden under navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="z-index:100 !important; font-family: 'Oswald', sans-serif; font-size:16px;" >
  <div class="container">
    <div class="navbar-header">
        <a href="/peer/<%=sessionUser.getId()%>/feed" style="padding-top:5px; display:block;"><img src="/assets/images/logo32x32.png"></a>
    </div>

    <ul class="nav navbar-nav">
        <% String jspPageName = request.getServletPath(); %>
        <li   <%= jspPageName.contentEquals("/view/feed/feed.jsp")  ? "class=\"active\"" : "" %>>
            <a href="/peer/<%=sessionUser.getId()%>/feed" href="/about" style="font-weight:300;">Feeds</a>
        </li>
        <li   <%= jspPageName.contentEquals("/view/documents.jsp") ? "class=\"active\"" : "" %>>
            <a href="/peer/<%=sessionUser.getId()%>/document" href="/about" style="font-weight:300;">Documents</a>
        </li>
        <li <%= jspPageName.contentEquals("/view/groups.jsp") ? "class=\"active\"" : "" %>>
            <a href="/group" href="/about" style="font-weight:300;">Groups</a>
        </li>
    </ul>
    <!-- SEARCH FORM -->

    <ul class="nav navbar-nav navbar-right">
        <form action="/search" class="navbar-form navbar-left" method="post" id="search_form" role="form">
            <input type="text" class="form-control" placeholder="Search for documents, people and groups" name="search_query" id="nav_search" autocomplete="off" style="min-width:300px;
            z-index:1111 !important" />
          </form>


        <li class="dropdown">
            <a href="/peer/<%=sessionUser.getId()%>/profile" class="dropdown-toggle" data-toggle="dropdown" style="font-weight:300;">
                <%= sessionUser.getUserName() %> <img style="margin:0 10px;" class="profile"
                     src="<%= sessionUser.getAvatar().getRelativeServletContextAvatarPathForSize( request, Avatar.Size.SMALL ) %>"/>
                <%if(sessionUser.getCompleteProfile()==0){%><span class="glyphicon glyphicon-exclamation-sign"></span><%}%>
            </a>

            <ul class="dropdown-menu" role="menu">
                <li><a href="/peer/<%=sessionUser.getId()%>">Profile</a></li>
                <li><a href="/peer/<%=sessionUser.getId()%>/profile">Edit Profile</a></li>
                <%if(sessionUser.getCompleteProfile()==0){%> <li><a href="/peer/<%=sessionUser.getId()%>/profile/full">Complete Profile <span class="glyphicon glyphicon-arrow-left"></span></a></li><%}%>
                <li class="divider"></li>
                <li><a href="/logout">Logout</a></li>
            </ul>

    </ul>
  </div>
</nav>
<div id="enableNotification"></div>