<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ include file="/view/includes/static/header.jsp" %>
<script src="/assets/js/custom/search_autocomplete_caller.js"></script>
<%
    Peer peer = (Peer) request.getAttribute("user");
    ArrayList<Group> groups =(ArrayList<Group>) peer.getGroups();
%>


<%--Cropped image :<%= session.getAttribute("croppedImage") %>--%>
<%--<br />--%>
<%--<img src="/assets/images/profile/<%= session.getAttribute("croppedImage") %>" />--%>

<%@ include file="/view/includes/static/navbar_profile.jsp" %>

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
                           value="<%= peer.getFirstName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Last name</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="last_name"
                           value="<%= peer.getLastName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Username</h6></td>
                <td><input type="text" class="form-control peerUsername"
                           name="user_name"
                           value="<%= peer.getUserName().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Date of Birth &nbsp</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="dob"
                           value="<%= peer.getDateOfBirth() == null ? "" : peer.getDateOfBirth().toString()%>" readonly>
                </td>
              </tr>
              <input type="hidden" class="form-control peerID" name="id"
                     value="<%= peer.getId().toString()%>">
            </table>
          </div>
          <div class="col-md-6">
            <table>
              <tr>
                <td><h6>Gender</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="gender"
                           value="<%= peer.getGender() == null ? "" : peer.getGender().toString() %>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Country</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="country"
                           value="<%= peer.getCountry().toString()%>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Industry</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="industry"
                           value="<%=peer.getIndustry().toString()%>" readonly>
                </td>
              </tr>
              <tr>
                <td><h6>Years of Experience &nbsp</h6></td>
                <td><input type="text" class="form-control peerPersonal"
                           name="yoe"
                           value="<%= peer.getExperience() == 0 ? "" : peer.getExperience().toString() %>" readonly>
                </td>
              </tr>
              <input type="hidden" class="form-control peerID" name="id"
                     value="<%= Integer.parseInt(peer.getId().toString())%>">
              <input type="hidden" name ="description" value ="<%= peer.getDescription()%>" >
              <input type="hidden" name ="email" value ="<%= peer.getEmail()%>" >
              <input type="hidden" name ="personal_website" value ="<%= peer.getPersonalWebsite()%>" >
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
            <textarea class ="peerDescription well form-control" name ="description" style="resize:none" rows="3" readonly><%= peer.getDescription().toString() %></textarea>
            <input type="hidden" class="form-control peerID" name="id" value="<%= Integer.parseInt(peer.getId().toString())%>"/>
            <input type="hidden" name ="email" value ="<%= peer.getEmail()%>" >
            <input type="hidden" name ="personal_website" value ="<%= peer.getPersonalWebsite()%>" >
            <input type="hidden" class="form-control peerPersonal" name="first_name" value="<%= peer.getFirstName().toString() %>">
            <input type="hidden" class="form-control peerPersonal" name="last_name" value="<%= peer.getLastName().toString() %>" >
            <input type="hidden" class="form-control peerUsername" name="user_name" value="<%= peer.getUserName().toString() %>" >
            <input type="hidden" class="form-control peerDoB" name="dob" value="<%= peer.getDateOfBirth() == null ? "" : peer.getDateOfBirth().toString() %>">
            <input type="hidden" class="form-control peerID" name="id" value="<%= peer.getId().toString()%>">
            <input type="hidden" class="form-control peerPersonal" name="gender" value="<%= peer.getGender() == null ? "" : peer.getGender().toString() %>" >
            <input type="hidden" class="form-control peerPersonal" name="country" value="<%= peer.getCountry().toString()%>" >
            <input type="hidden" class="form-control peerPersonal" name="industry" value="<%=peer.getIndustry().toString()%>">
            <input type="hidden" class="form-control peerPersonal" name="yoe" value="<%=peer.getExperience().toString()%>">

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
                         name="email" value="<%= peer.getEmail() == null ? "" : peer.getEmail().toString() %>"
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
                         value="<%= peer.getPersonalWebsite().toString() %>"
                         readonly></td>
            </tr>
            <input type="hidden" class="form-control peerID" name="id"
                   value="<%= Integer.parseInt(peer.getId().toString())%>">
          </table>
            <input type="hidden" class="form-control peerPersonal" name="first_name" value="<%= peer.getFirstName().toString() %>">
            <input type="hidden" class="form-control peerPersonal" name="last_name" value="<%= peer.getLastName().toString() %>" >
            <input type="hidden" class="form-control peerUsername" name="user_name" value="<%= peer.getUserName().toString() %>" >
            <input type="hidden" class="form-control peerPersonal" name="dob" value="<%= peer.getDateOfBirth() == null ? "" : peer.getDateOfBirth().toString() %>">
            <input type="hidden" class="form-control peerID" name="id" value="<%= peer.getId().toString()%>">
            <input type="hidden" class="form-control peerPersonal" name="gender" value="<%= peer.getGender() == null ? "" : peer.getGender().toString() %>\" >
            <input type="hidden" class="form-control peerPersonal" name="country" value="<%= peer.getCountry().toString()%>" >
            <input type="hidden" class="form-control peerPersonal" name="industry" value="<%=peer.getIndustry().toString()%>">
            <input type="hidden" class="form-control peerPersonal" name="yoe" value="<%=peer.getExperience().toString()%>">
            <input type="hidden" name ="description" value ="<%= peer.getDescription()%>" >
        </form>
        <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden"
           id="save_contact" value="Save" href="#">Save
          Changes</a>
      </div>
    </div>
  </div>





<%@ include file="/view/includes/static/footer.jsp" %>