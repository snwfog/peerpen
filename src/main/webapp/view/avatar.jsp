<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">PeerPen</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="#"><img class="profile"
                           src="/assets/images/profile/pic2.jpg"/></a></li>
      <li><a> Peter Trang
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

  <h1>Summary of your profile <button class="pull-right btn btn-primary" type="submit">Next (2/2)</button></h1>

  <div class="dravatar">
    <h4>Drag & Drop picture</h4>
    <div class="dravatar-avatar-wrap">
      <img src="/assets/images/profile/256.jpg" id="profile-avatar" alt="Image for Profile">
    </div>

    <h2>Peter Trang</h2>
    <div class="location">MTL QC, CA</div>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequatur voluptatem accusantium voluptas doloremque porro temporibus aut rerum possimus cum minus.</p>

  </div>

  <h3>Upload a picture through browsing...</h3>
  <input type="file" id="uploader">

</div>

<!-- avatar dragging part-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="/assets/js/custom/resample.js"></script>
<script src="/assets/js/custom/avatar.js"></script>
<%@ include file="/view/includes/static/footer.jsp" %>