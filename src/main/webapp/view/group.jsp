<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: waisk
  Date: 07/02/14
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<% Peer sessionUser = (Peer) request.getAttribute("sessionUser"); %>
<% ArrayList<Group> groups = (ArrayList<Group>) request.getAttribute("groups"); %>
<div class="container">
    <h1> Join a group ! </h1>
    <!-- this section is for tags -->
    <div>
        <form action="/group" method="post" class="form-horizontal" role="form">
            <select class="form-control">
                <%for (Group g:groups) {
                    %><option><%= g.getGroupName()%></option>
                <%}%>
            </select>
            <button type="submit" class="btn btn-primary" name="submit" />Save Tags</button>
        </form>
    </div>

<%@ include file="/view/includes/static/footer.jsp" %>