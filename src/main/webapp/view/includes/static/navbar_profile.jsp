<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ page import="com.peerpen.model.Avatar" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="java.util.List" %>
<!-- must set navbar z-index to be <= 100 otherwise search autocomplete box will be hidden under navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="z-index:100 !important;">
  <div class="container">
    <div class="navbar-header">
        <a class="navbar-brand" href="/peer/<%=sessionUser.getId()%>/feed">PeerPen</a>
    </div>

    <ul class="nav navbar-nav">
        <% String jspPageName = request.getServletPath(); %>
        <li   <%= jspPageName.contentEquals("/view/feed/feed.jsp")  ? "class=\"active\"" : "" %>>
            <a href="/peer/<%=sessionUser.getId()%>/feed">Feeds</a>
        </li>
        <li   <%= jspPageName.contentEquals("/view/documents.jsp") ? "class=\"active\"" : "" %>>
            <a href="/peer/<%=sessionUser.getId()%>/document">Documents</a>
        </li>
        <li <%= jspPageName.contentEquals("/view/groups.jsp") ? "class=\"active\"" : "" %>>
            <a href="/group">Groups</a>
        </li>
    </ul>
    <!-- SEARCH FORM -->
    <ul class="nav navbar-nav navbar-right">
      <form action="/search" class="navbar-form navbar-left" method="post" id="search_form" role="form" style="margin:0 10px;padding:10px 0 0 0;">
             <input type="text" class="form-control" placeholder="Search for documents, people and groups" name="search_query" id="nav_search" autocomplete="off" style="width:300px;z-index:1111 !important" />
      </form>
        <li class="dropdown">
            <a href="/peer/<%=sessionUser.getId()%>/profile" class="dropdown-toggle" data-toggle="dropdown">
                <%= sessionUser.getUserName() %> <img style="margin:0 10px;" class="profile"
                     src="<%= sessionUser.getAvatar().getRelativeServletContextAvatarPathForSize( request, Avatar.Size.SMALL ) %>"/><b class="caret"></b></a>
            <ul class="dropdown-menu" role="menu">
                <li><a href="/peer/<%=sessionUser.getId()%>/profile">Profile</a></li>
                <li class="divider"></li>
                <li><a href="/logout">Logout</a></li>
            </ul>

    </ul>
  </div>
</nav>
<div id="enableNotification"></div>