<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ page import="java.util.List" %>
<%@ include file="/view/includes/static/header.jsp" %>

<%--Declare all request variables here, easy to debug!!!--%>
<% Peer sessionUser = (Peer) request.getAttribute("sessionUser"); %>
<% List<Group> groups = (List<Group>) request.getAttribute("groups"); %>

<div class="container">
  <div class="row row-offcanvas row-offcanvas-right">
    <h1> Join a group ! </h1>
    <div >
      <div class="row">
        <%
          for (Group group : groups){
        %>
        <div class="col-6 col-sm-6 col-lg-4">
          <h2><%= group.getGroupName()%> <span style="font-size: medium;"><i class="fa fa-users"></i>&nbsp;<%= group.getMembers().size()%></span></h2>
          <p>hallo, willkommen in kanada, hier sind wir gerne Bier mit Kaffee und Ahornsirup gemischt zu trinken. bitte vorbei tims hortons, es selbst zu versuchen!</p>
          <p>
          <% if(group.getIsJoined(sessionUser.getId())){%>
            <form action="/group" method="post" class="form-horizontal" role="form">
              <input type="hidden" name="groupid" value="<%= group.getId()%>">
              <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>">
              <input type="hidden" name="_method" value="delete">
              <button type="submit" class="btn btn-primary btn-success" name="submit"/><i class="fa fa-check-circle"></i> Joined!</button>
            </form>
          <%}else{%>
            <form action="/group/<%= group.getId()%>" method="post" class="form-horizontal" role="form">
              <input type="hidden" name="groupid" value="<%= group.getId()%>">
              <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>">
              <button type="submit" class="btn btn-primary" name="submit"/>Join!</button>
            </form>
          <%}%>
          </p>
        </div><!--/span-->
        <%}%>
      </div><!--/row-->
    </div><!--/span-->
  </div><!--/.container-->
</div>

<%@ include file="/view/includes/static/footer.jsp" %>