<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ page import="com.peerpen.model.Avatar" %>
<%
    Peer sessionUser = (Peer) request.getAttribute("sessionUser");
    ArrayList<Group> navGroups =(ArrayList<Group>) sessionUser.getGroups();
%>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">PeerPen</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/peer/<%=sessionUser.getId()%>/profile">
                <img class="profile"
                     src="<%= sessionUser.getAvatar().getRelativeServletContextAvatarPathForSize( request, Avatar.Size.SMALL ) %>" /></a></li>
            <li><a href="/peer/<%=sessionUser.getId()%>/profile"><%= sessionUser.getUserName() %></a></li>
            <li><a href="/peer/<%=sessionUser.getId()%>/feed">Feeds</a></li>
            <li><a href="/tagsearch">Tags</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Groups <b class="caret"></b></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/group">Browse All Groups</a></li>
                    <% for(Group g: navGroups){%>
                    <li><a href="/group/<%=g.getId() %>"><%=g.getGroupName()%></a></li>
                    <%}%>
                </ul>
             </li>
            <li><a href="/logout">Logout</a></li>
        </ul>
        <!-- SEARCH FORM -->
        <form action="/search" method="post" class="navbar-form navbar-right" id="search_form" role="form" style="margin:0;padding:10px 0 0 0;">
            <input type="text" class="form-control" placeholder="Search for documents, people and groups" name="search_query" id="nav_search" autocomplete="off" style="width:300px;z-index: 999
            !important" />
        </form>
    </div>
</nav>