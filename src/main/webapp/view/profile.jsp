<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>

<script type="text/javascript">
  $(document).ready(function () {
    $('#edit_personal').click(function () {
      if ($('#personal_icon').attr('class') == 'glyphicon glyphicon-pencil') {
        $("#personal_icon").attr('class', 'glyphicon glyphicon-ok')
        $(".peerPersonal").attr("readonly", false);
        $('#save_personal').css('visibility', '');
      }
      else {
        $('#personal_icon').attr('class', 'glyphicon glyphicon-pencil')
        $(".peerPersonal").attr("readonly", true);
        $('#save_personal').css('visibility', 'hidden');
      }
    });
    $('#edit_description').click(function () {
      if ($('#description_icon').attr('class') == 'glyphicon glyphicon-pencil') {
        $("#description_icon").attr('class', 'glyphicon glyphicon-ok')
        $(".peerDescription").attr("readonly", false);
        $('#save_description').css('visibility', '');
      }
      else {
        $('#description_icon').attr('class', 'glyphicon glyphicon-pencil')
        $(".peerDescription").attr("readonly", true);
        $('#save_description').css('visibility', 'hidden');
      }
    });
    $('#edit_contact').click(function () {
      if ($('#contact_icon').attr('class') == 'glyphicon glyphicon-pencil') {
        $("#contact_icon").attr('class', 'glyphicon glyphicon-ok')
        $(".peerContact").attr("readonly", false);
        $('#save_contact').css('visibility', '');
      }
      else {
        $('#contact_icon').attr('class', 'glyphicon glyphicon-pencil')
        $(".peerContact").attr("readonly", true);
        $('#save_contact').css('visibility', 'hidden');
      }
    });
    $('#save_personal').click(function (){
      document.getElementById("form_personal").submit();
    });
    $('#save_description').click(function (){
      document.getElementById("form_description").submit();
    });
    $('#save_contact').click(function (){
      document.getElementById("form_contact").submit();
    });

  });
</script>


<%
  Peer peer = (Peer) session.getAttribute("user");
  if (peer != null)
  {
%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">PeerPen</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="#"><img class="profile"
                           src="/assets/images/profile/pic2.jpg"/></a></li>
      <li><a><%= peer.getFirstName().toString() %>
      </a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
            class="glyphicon glyphicon-globe"></span><b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="#">Discovery</a></li>
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

<br>

<div class="row">
  <div class="col-md-3">
    <div class="bs-sidebar well">
      <a href="#"><img class="profile2"
                       src="/assets/images/profile/pic.jpg"/></a>
    </div>
  </div>
  <div class="col-md-9 personal-info">
    <div class="col-md-9 col-md-3">
      <h4>Personal Information</h4>
      <div class="well">
        <div class="row">
          <form action="/profile.do" id="form_personal" method="post">
          <div class="col-md-6">
            <table>
              <tr>
                <td>First name </td>
                <td><input type="text" class="form-control peerPersonal"
                           name="first_name"
                           value="<%= peer.getFirstName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td>Last name </td>
                <td><input type="text" class="form-control peerPersonal"
                           name="last_name"
                           value="<%= peer.getLastName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td>Username </td>
                <td><input type="text" class="form-control peerUsername"
                           name="user_name"
                           value="<%= peer.getUserName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td>Date of Birth </td>
                <td><input type="text" class="form-control peerDoB"
                           name="user_dob"
                           value="" readonly>
                </td>
              </tr>
              <input type="hidden" class="form-control peerID" name="id"
                     value="<%= Integer.parseInt(peer.getId().toString())%>">
            </table>
          </div>
          <div class="col-md-6">
            <table>
              <tr>
                <td>First name </td>
                <td><input type="text" class="form-control peerPersonal"
                           name="first_name"
                           value="<%= peer.getFirstName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td>Last name </td>
                <td><input type="text" class="form-control peerPersonal"
                           name="last_name"
                           value="<%= peer.getLastName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td>Username </td>
                <td><input type="text" class="form-control peerUsername"
                           name="user_name"
                           value="<%= peer.getUserName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td>Date of Birth </td>
                <td><input type="text" class="form-control peerDoB"
                           name="user_dob"
                           value="" readonly>
                </td>
              </tr>
              <input type="hidden" class="form-control peerID" name="id"
                     value="<%= Integer.parseInt(peer.getId().toString())%>">
            </table>
          </div>
          </form>
        </div>
        <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden"
        id="save_personal" value="Save" href="#">Save
          Changes</a>
      </div>
      <a class ="pull-right" id="edit_personal" href="#"><span id="personal_icon" class="glyphicon glyphicon-pencil"></span>Edit</a>

    </div>

    <div class="col-md-9">
      <h4>About Me</h4>

      <div class="well">
        <form action="/profile.do" id="form_description" method="post">
        <textarea class ="peerDescription well form-control" name ="description" style="resize:none" rows="3" readonly>I am Blabla...</textarea>
        <input type="hidden" class="form-control peerID" name="id"
                 value="<%= Integer.parseInt(peer.getId().toString())%>">
        </form>
        <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden"
           id="save_description" value="Save" href="#">Save
          Changes</a>
      </div>
      <a class ="pull-right" id="edit_description" href="#"><span id="description_icon" class="glyphicon glyphicon-pencil"></span>Edit</a>

    </div>
    <div class="col-md-9">
      <h4>Contact Information</h4>

      <div class="well">
        <form action="/profile.do" id="form_contact" method="post">
          <table>
            <tr>
              <td>Email </td>
              <td><input type="text" class="form-control peerContact"
                         name="email" value="<%= peer.getEmail().toString() %>"
                         readonly></td>
            </tr>
            <input type="hidden" class="form-control peerPoint" name="point"
                   value="<%= Integer.parseInt(peer.getPoint().toString()) %>"
                   readonly>
            </tr>
            <tr>
              <td>Website </td>
              <td><input type="text" class="form-control peerContact"
                         name="personal_website"
                         value="<%= peer.getPersonalWebsite().toString() %>"
                         readonly></td>
            </tr>
            <input type="hidden" class="form-control peerID" name="id"
                   value="<%= Integer.parseInt(peer.getId().toString())%>">
          </table>
        </form>
        <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden"
           id="save_contact" value="Save" href="#">Save
          Changes</a>
      </div>
      <a class ="pull-right" id="edit_contact" href="#"><span id="contact_icon" class="glyphicon glyphicon-pencil"></span>Edit</a>
    </div>
  </div>
</div>


<%
  }
  else
    response.sendRedirect("/login");
%>


<%@ include file="/view/includes/static/footer.jsp" %>