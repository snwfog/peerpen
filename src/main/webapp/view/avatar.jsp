<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>

<%
    Peer peer = (Peer) session.getAttribute("user");
%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">PeerPen</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="#"><img class="profile"
                           src="/assets/images/profile/pic2.jpg"/></a></li>
      <li><a> <%= peer.getFirstName()%> <%= peer.getLastName()%>
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
          <li><a href="#">Action</a></li>
          <li><a href="#">Another action</a></li>
          <li><a href="#">Something else here</a></li>
          <li class="divider"></li>
          <li><a href="#">Separated link</a></li>
          <li class="divider"></li>
          <li><a href="#">One more separated link</a></li>
        </ul>
      </li>
    </ul>
    <form class="navbar-form navbar-right" role="search">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search">
      </div>
      <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
      &nbsp &nbsp<a href="/logout.do">Logout</a>

    </form>
  </div>
</nav>
<div class="page-wrap">
    <form action="/avatar.do" name="myForm" id="form_description" method="post">
        <input type="hidden" class ="peerDescription well form-control" name ="description" value="<%= session.getAttribute("description") == null ? "" : session.getAttribute("description") %>">
        <input type="hidden" class="form-control peerID" name="id" value="<%= peer.getId().toString()%>"/>
        <input type="hidden" class="form-control peerDoB" name="dob" value="<%=session.getAttribute("dob") %>">
        <input type="hidden" class="form-control peerPersonal" name="gender" value="<%= session.getAttribute("gender") == null ? "" : session.getAttribute("gender") %>" >
        <input type="hidden" class="form-control peerPersonal" name="country" value="<%= session.getAttribute("country") == null ? "" : session.getAttribute("country") %>" >
        <input type="hidden" class="form-control peerPersonal" name="industry" value="<%= session.getAttribute("industry") == null ? "" : session.getAttribute("industry") %>">
        <input type="hidden" class="form-control peerPersonal" name="yoe" value="<%= session.getAttribute("yoe").toString()%>">
        <input type="hidden" class="form-control peerPersonal" name="personal_website" value="<%= session.getAttribute("personal_website") == null ? "" : session.getAttribute("personal_website") %>">
        <input type="hidden" name="x1" value="" />
        <input type="hidden" name="y1" value="" />
        <input type="hidden" name="x2" value="" />
        <input type="hidden" name="y2" value="" />
        <h1>Summary of your profile <button class="pull-right btn btn-primary" type="submit">Next (2/2)</button></h1>
    </form>

  <form action="upload.do" method="post" enctype="multipart/form-data">
  <div  class="pull-right">
          <img id="photo" src="/assets/images/profile/256.jpg" style="float:left;" />
      <h3>Upload a picture through browsing...</h3>

      <input id="uploader" type="file" name="file" size="50" />
      <br />
      <input type="submit" value="Upload File" />
  </div>
  <div class="dravatar pull-left" style=" max-width:500px;">
    <h4>Drag & Drop picture</h4>
    <div class="dravatar-avatar-wrap">
      <img src="/assets/images/profile/256.jpg" name="avatar-image" id="profile-avatar" alt="Image for Profile">
      <div class="description"><%= session.getAttribute("gender") == null ? "" : session.getAttribute("gender") %><%if (session.getAttribute("dob") == null){ %>, <%}%> <%= session.getAttribute("dob") == null ? "" : session.getAttribute("dob") %></div>
    </div>

    <h2><%= peer.getFirstName()%> <%= peer.getLastName()%></h2>
    <div class="field"><%= session.getAttribute("industry") == null ? "" : session.getAttribute("industry")%> <%= session.getAttribute("yoe") == null? "" : (session.getAttribute("yoe"))%></div>
    <div class="location"><%= session.getAttribute("country") == null ? "" : session.getAttribute("country")%></div>
    <p><%= session.getAttribute("description") == null ? "" : session.getAttribute("description")%></p>

  </div>

  </form>
</div>

<!-- avatar dragging part-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="/assets/js/custom/resample.js"></script>
<script src="/assets/js/custom/avatar.js"></script>
<%@ include file="/view/includes/static/footer.jsp" %>