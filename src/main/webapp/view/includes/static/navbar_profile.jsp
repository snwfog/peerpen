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
            <li><a href="#"><img class="profile"
                                 src="/assets/images/profile/pic2.jpg"/></a></li>
            <li><%= sessionUser.getFirstName().toString() %>
            </a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                        class="glyphicon glyphicon-globe"></span><b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="http://localhost:8080/hunk">Discovery</a></li>
                    <li><a href="#">Feeds</a></li>
                </ul>
            </li>
            <li><a href="#"><span class="glyphicon glyphicon-info-sign"></span></a>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="/peer/<%= sessionUser.getId()%>/document">Documents</a></li>
            <%--<li><a href="/group/<%= %>">Groups</a></li>--%>

            <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                    <li class="divider"></li>
                    <li><a href="#">One more separated link</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Groups
                    <%if (navGroups.size() > 0) {%><b
                        class="caret"></b><%}%></a>

                <%if (navGroups.size() > 0) {%>
                <ul class="dropdown-menu" role="menu">
                    <% for(Group g: navGroups){%>
                    <li class="list-group">
                        <a href="/group/<%=g.getId() %>"><%=g.getGroupName()%></a>
                    </li>
                <%}%>
                </ul>
                <%}%>
             </li>
        </ul>
        <!-- SEARCH FORM -->
        <%--<form action="/search" method="post" class="navbar-form navbar-right" role="form">--%>
            <%--<div class="input-group">--%>
                <%--<input type="text" class="form-control" placeholder="Search for documents, people and groups" name="search_query" id="search_query" autocomplete="off" />--%>
            <%--<span class="input-group-btn">--%>
                <%--<button type="submit" class="btn btn-primary" name="submit" />Search</button>--%>
            <%--</span>--%>
            <%--</div>--%>
        <%--</form>--%>
        <%--&nbsp &nbsp<a href="/logout">Logout</a>--%>
        <%--</form>--%>
    </div>
</nav>

<br /><br /><br/>