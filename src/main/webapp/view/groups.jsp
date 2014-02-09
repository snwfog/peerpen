<%@ page import="java.util.List" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar_profile.jsp" %>

<%--Declare all request variables here, easy to debug!!!--%>
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
          <h2><a href="group/<%= group.getId()%>" style="text-decoration: none"><%= group.getGroupName()%></a> <span style="font-size: medium;"><i class="fa fa-users"></i>&nbsp;<%= group.getPeers().size()%></span></h2>
          <p><%= group.getDescription().toString().substring(0, 50)%>...
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