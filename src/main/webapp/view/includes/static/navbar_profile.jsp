<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Group" %>
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
                <img class="profile" src="<%= sessionUser.getAvatar().getServletContextAvatarPath( request ) %>" /></a></li>
            <li><a href="/peer/<%=sessionUser.getId()%>/profile"><%= sessionUser.getUserName() %></a></li>
            <li><a href="/peer/<%=sessionUser.getId()%>/feed">Feeds</a></li>
            <li><a href="/search">Search</a></li>
            <li><a href="/tagsearch">Tags</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Groups <b class="caret"></b></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/group">Browse All Groups</a></li>
                    <% for(Group g: navGroups){%>
                    <li class="list-group"><a href="/group/<%=g.getId() %>"><%=g.getGroupName()%></a></li>
                    <%}%>
                </ul>
             </li>
            <li><a href="/logout">Logout</a></li>
        </ul>
        <!-- SEARCH FORM -->
        <%--<form action="/search" method="post" class="navbar-form navbar-right" role="form" style="margin:0;padding:10px 0 0 0;">--%>
            <%--<div class="input-group" style="width:300px;">--%>
                <%--<input type="text" class="form-control" placeholder="Search for documents, people and groups" name="search_query" id="search_query" autocomplete="off" style="width:300px;" />--%>
            <%--<span class="input-group-btn">--%>
                <%--<button type="submit" class="btn btn-primary" name="submit" />Search</button>--%>
            <%--</span>--%>
            <%--</div>--%>
        <%--</form>--%>
    </div>
</nav>