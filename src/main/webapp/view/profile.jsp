<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar.jsp" %>

<% SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");%>

<div class="container-fluids greyish">
  <div class="row col-md-offset-0">
    <a href="/peer/<%= sessionUser.getId() %>/profile/avatar">
        <img class="img-circle"
             src="<%= sessionUser.getAvatar().getRelativeServletContextAvatarPathForSize( request,
             Avatar.Size.LARGE ) %>">
    </a>
  </div>
  <div class="col-md-12 profile">
    <div class="col-md-10">
      <div class="row">
          <p>Personal Information</p>&nbsp<a id="edit_personal" href="#"><span id="personal_icon" class="glyphicon glyphicon-pencil"></span></a>
      </div>
      <div class="well">
          <form action="/peer/<%=sessionUser.getId()%>/profile" id="form_personal" method="post">
      <div class="row">
          <div class="col-md-6">
            <table>
              <tr>
                <td><h6>First name</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="first_name"
                           value="<%= sessionUser.getFirstName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Last name</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="last_name"
                           value="<%= sessionUser.getLastName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Username</h6></td>
                <td><input type="text" class="form-control peerUsername"
                           name="user_name"
                           value="<%= sessionUser.getUserName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Date of Birth &nbsp</h6></td>
                <td><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerDob" : "peerPersonal"%>"
                           name="dob"
                           value="<%= sessionUser.getDateOfBirth() == null ? "" : formatter.format(sessionUser.getDateOfBirth())%>" readonly>
                </td>
              </tr>
            </table>
          </div>
          <div class="col-md-6">
            <table>
              <tr>
                <td><h6>Gender</h6></td>
                <td><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerGender" : "peerPersonal"%>"
                           name="gender"
                           value="<%= sessionUser.getGender() == null ? "" : sessionUser.getGender().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Country</h6></td>
                <td><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerCountry" : "peerPersonal"%>"
                           name="country"
                           value="<%= sessionUser.getCountry().toString()%>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Industry</h6></td>
                <td><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerIndustry" : "peerPersonal"%>"
                           name="industry"
                           value="<%=sessionUser.getIndustry().toString()%>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Years of Experience &nbsp</h6></td>
                <td><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerYoe" : "peerPersonal"%>"
                           name="yoe"
                           value="<%= sessionUser.getExperience() == null ? 0 : sessionUser.getExperience()%>" readonly>
                </td>
              </tr>
              <input type="hidden" class="form-control peerID" name="peerId"
                     value="<%= sessionUser.getId()%>">
              <input type="hidden" name="form" value="personal">
            </table>
          </div>
          </form>
      </div>
        <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden"
        id="save_personal" value="Save" href="#">Save
          Changes</a>
      </div>
        </div>
        <div class="col-md-10">
            <div class="row">
             <p>About Me</p>&nbsp<a id="edit_description" href="#"><span id="description_icon" class="glyphicon glyphicon-pencil"></span></a>
            </div>
      <div class="well">
        <form action="/peer/<%=sessionUser.getId()%>/profile" id="form_description" method="post">
            <textarea class ="<%= sessionUser.getCompleteProfile() == 0 ? "peerDesc" : "peerDescription"%> well form-control" name ="description" style="resize:none" rows="3" readonly><%= sessionUser.getDescription().toString() %></textarea>
            <input type="hidden" class="form-control peerID" name="peerId" value="<%= sessionUser.getId()%>"/>
            <input type="hidden" name ="personal_website" value ="<%= sessionUser.getPersonalWebsite()%>" >
            <input type="hidden" name="form" value="description">
        </form>
        <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden"
           id="save_description" value="Save" href="#">Save
          Changes</a>
          </div>
      </div>

    <div class="col-md-10">
        <div class="row">
          <p>Contact Information</p>&nbsp<a id="edit_contact" href="#"><span id="contact_icon" class="glyphicon glyphicon-pencil"></span></a>
        </div>
      <div class="well">
        <form action="/peer/<%=sessionUser.getId()%>/profile" id="form_contact" method="post">
          <table>
                <tr>
                    <td><h6>Email</h6></td>
                    <td><input type="text" class="form-control peerContact"
                               name="email"
                               value="<%= sessionUser.getEmail() == null ? "" : sessionUser.getEmail().toString() %>"
                               readonly></td>
                </tr>
                <tr>
                    <td><h6>Website &nbsp</h6></td>
                    <td><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerEmail" : "peerContact"%>"
                               name="personal_website"
                               value="<%= sessionUser.getPersonalWebsite().toString() %>"
                               readonly></td>
                </tr>
                <input type="hidden" class="form-control peerID" name="peerId"
                       value="<%=sessionUser.getId()%>">
                <input type="hidden" class="form-control peerID" name="id" value="<%= sessionUser.getId()%>">
                <input type="hidden" name="form" value="contact">
                <input type="hidden" class="form-control peerPoint" name="point"
                       value=""
                       readonly>
            </table>
        </form>
        <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden"
           id="save_contact" value="Save" href="#">Save
            Changes</a>
      </div>
    </div>
      </div>
    <table class="table table-condensed">
        <tr>
            <td class="active">Member Type: Regular Member</td>
        <tr>
            <td class="success">Peer Points: 325</td>
        </tr>
        <td class="warning">Achievements: 12</td>
        <tr>
            <td class="danger">Friends: 0 friends </td>
        </tr>
    </table>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>