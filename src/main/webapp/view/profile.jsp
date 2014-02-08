<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar_profile.jsp" %>

<%--Cropped image :<%= session.getAttribute("croppedImage") %>--%>
<%--<br />--%>
<%--<img src="/assets/images/profile/<%= session.getAttribute("croppedImage") %>" />--%>



<br>

  <div class="row">
    <div class="span3">
      <a href="#"><img class="profile2"
                       src="/assets/images/profile/pic.jpg"/></a>
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
  <div class="row profile">
    <div class="span4">
      <div class="">
        <div class="span4">
          <p>Personal Information</p>&nbsp<a id="edit_personal" href="#"><span id="personal_icon" class="glyphicon glyphicon-pencil"></span></a>
        </div>
      </div>
      <div class="well">
        <div class="">
          <form action="/profile" id="form_personal" method="post">
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
                <td><input type="text" class="form-control peerPersonal"
                           name="dob"
                           value="<%= sessionUser.getDateOfBirth() == null ? "" : sessionUser.getDateOfBirth().toString()%>" readonly>
                </td>
              </tr>
              <input type="hidden" class="form-control peerID" name="id"
                     value="<%= sessionUser.getId().toString()%>">
            </table>
          </div>
          <div class="col-md-6">
            <table>
              <tr>
                <td><h6>Gender</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="gender"
                           value="<%= sessionUser.getGender() == null ? "" : sessionUser.getGender().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Country</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="country"
                           value="<%= sessionUser.getCountry().toString()%>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Industry</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="industry"
                           value="<%=sessionUser.getIndustry().toString()%>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Years of Experience &nbsp</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="yoe"
                           value="<%= sessionUser.getExperience() == 0 ? "" : sessionUser.getExperience().toString() %>" readonly>
                </td>
              </tr>
              <input type="hidden" class="form-control peerID" name="id"
                     value="<%= Integer.parseInt(sessionUser.getId().toString())%>">
              <input type="hidden" name ="description" value ="<%= sessionUser.getDescription()%>" >
              <input type="hidden" name ="email" value ="<%= sessionUser.getEmail()%>" >
              <input type="hidden" name ="personal_website" value ="<%= sessionUser.getPersonalWebsite()%>" >
            </table>
          </div>
          </form>
        </div>
        <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden"
        id="save_personal" value="Save" href="#">Save
          Changes</a>
      </div>
    </div>

    <div class="span3">
      <div class="">
        <div class="col-md-5">
          <p>About Me</p>&nbsp<a id="edit_description" href="#"><span id="description_icon" class="glyphicon glyphicon-pencil"></span></a>
        </div>
      </div>
      <div class="well">
        <form action="/profile" id="form_description" method="post">
            <textarea class ="peerDescription well form-control" name ="description" style="resize:none" rows="3" readonly><%= sessionUser.getDescription().toString() %></textarea>
            <input type="hidden" class="form-control peerID" name="id" value="<%= Integer.parseInt(sessionUser.getId().toString())%>"/>
            <input type="hidden" name ="email" value ="<%= sessionUser.getEmail()%>" >
            <input type="hidden" name ="personal_website" value ="<%= sessionUser.getPersonalWebsite()%>" >
            <input type="hidden" class="form-control peerPersonal" name="first_name" value="<%= sessionUser.getFirstName().toString() %>">
            <input type="hidden" class="form-control peerPersonal" name="last_name" value="<%= sessionUser.getLastName().toString() %>" >
            <input type="hidden" class="form-control peerUsername" name="user_name" value="<%= sessionUser.getUserName().toString() %>" >
            <input type="hidden" class="form-control peerDoB" name="dob" value="<%= sessionUser.getDateOfBirth() == null ? "" : sessionUser.getDateOfBirth().toString() %>">
            <input type="hidden" class="form-control peerID" name="id" value="<%= sessionUser.getId().toString()%>">
            <input type="hidden" class="form-control peerPersonal" name="gender" value="<%= sessionUser.getGender() == null ? "" : sessionUser.getGender().toString() %>" >
            <input type="hidden" class="form-control peerPersonal" name="country" value="<%= sessionUser.getCountry().toString()%>" >
            <input type="hidden" class="form-control peerPersonal" name="industry" value="<%=sessionUser.getIndustry().toString()%>">
            <input type="hidden" class="form-control peerPersonal" name="yoe" value="<%=sessionUser.getExperience().toString()%>">

        </form>
        <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden"x
           id="save_description" value="Save" href="#">Save
          Changes</a>
      </div>

    </div>
    <div class="span4">
      <div class="">
        <div class="col-md-5">
          <p>Contact Information</p>&nbsp<a id="edit_contact" href="#"><span id="contact_icon" class="glyphicon glyphicon-pencil"></span></a>
        </div>
      </div>
      <div class="well">
        <form action="/profile" id="form_contact" method="post">
          <table>
            <tr>
              <td><h6>Email</h6></td>
              <td><input type="text" class="form-control peerContact"
                         name="email" value="<%= sessionUser.getEmail() == null ? "" : sessionUser.getEmail().toString() %>"
                         readonly></td>
            </tr>
            <input type="hidden" class="form-control peerPoint" name="point"
                   value=""
                   readonly>
            </tr>
            <tr>
              <td><h6>Website &nbsp</h6></td>
              <td><input type="text" class="form-control peerContact"
                         name="personal_website"
                         value="<%= sessionUser.getPersonalWebsite().toString() %>"
                         readonly></td>
            </tr>
            <input type="hidden" class="form-control peerID" name="id"
                   value="<%= Integer.parseInt(sessionUser.getId().toString())%>">
          </table>
            <input type="hidden" class="form-control peerPersonal" name="first_name" value="<%= sessionUser.getFirstName().toString() %>">
            <input type="hidden" class="form-control peerPersonal" name="last_name" value="<%= sessionUser.getLastName().toString() %>" >
            <input type="hidden" class="form-control peerUsername" name="user_name" value="<%= sessionUser.getUserName().toString() %>" >
            <input type="hidden" class="form-control peerPersonal" name="dob" value="<%= sessionUser.getDateOfBirth() == null ? "" : sessionUser.getDateOfBirth().toString() %>">
            <input type="hidden" class="form-control peerID" name="id" value="<%= sessionUser.getId().toString()%>">
            <input type="hidden" class="form-control peerPersonal" name="gender" value="<%= sessionUser.getGender() == null ? "" : sessionUser.getGender().toString() %>\" >
            <input type="hidden" class="form-control peerPersonal" name="country" value="<%= sessionUser.getCountry().toString()%>" >
            <input type="hidden" class="form-control peerPersonal" name="industry" value="<%=sessionUser.getIndustry().toString()%>">
            <input type="hidden" class="form-control peerPersonal" name="yoe" value="<%=sessionUser.getExperience().toString()%>">
            <input type="hidden" name ="description" value ="<%= sessionUser.getDescription()%>" >
        </form>
        <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden"
           id="save_contact" value="Save" href="#">Save
          Changes</a>
      </div>
    </div>
  </div>





<%@ include file="/view/includes/static/footer.jsp" %>