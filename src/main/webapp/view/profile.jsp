<%@ page import="java.text.SimpleDateFormat" %>
<jsp:include page="/view/includes/static/header.jsp">
  <jsp:param name="title" value="Profile" />
</jsp:include>

<%@ include file="/view/includes/static/navbar.jsp" %>

<% SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");%>

<div class="cover-container"> </div>
<div id="feed-container" class="wrap-container" >
    <img />
    <div class="avatar avatar-container profile-avatar-body">
        <a href="/peer/<%= sessionUser.getId() %>/profile/avatar">
            <img class="img-circle" src="<%= sessionUser.getAvatar().getRelativeServletContextAvatarPathForSize( request,
                    Avatar.Size.LARGE ) %>" width="230px">
        </a>
    </div>
</div>


<div class="wrap-container container-fluids">

    <div class="profile-about-me-pointer profile-about-me-pointer-left"></div>
    <div class="profile-about-me-body profile-about-me-body-bg">
				<span class=".profile-about-me-text">
					<span class="profile-about-me-content-text">
						ABOUT ME

						<span class="profile-about-me-edit-body">
							<a id="edit_description" href="#" style="color:#000;">
                                Edit

                                <span id="description_icon" class="glyphicon glyphicon-pencil"></span>
                            </a>
						</span>
						</span>

					<br />
					<span class="profile-desc-body">
						<form action="/peer/<%=sessionUser.getId()%>/profile" id="form_description" method="post">
                            <textarea class ="<%= sessionUser.getCompleteProfile() == 0 ? "peerDesc" : "peerDescription"%> well form-control" name ="description" style="resize:none" rows="3" readonly><%= sessionUser.getDescription().toString() %></textarea>
                            <input type="hidden" class="form-control peerID" name="peerId" value="<%= sessionUser.getId()%>"/>
                            <input type="hidden" name ="personal_website" value ="<%= sessionUser.getPersonalWebsite()%>" >
                            <input type="hidden" name="form" value="description">
                        </form>


						<a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden; color:#e74c3c;"
                           id="save_description" value="Save" href="#">Save
                            Changes</a>

				</span>
				</span>

        <div class="profile-extra-body">
            <table class="table table-condensed">
                <tr>
                    <td>Member Type: Regular Member</td>
                    <td>Peer Points: 325</td>
                    <td>Achievements: 12</td>
                    <td>Friends: 0 friends </td>
                </tr>
            </table>
        </div>
    </div>


    <div class="profile" style="position:relative; top:50px; background-color:#fff; padding:35px;">

        <div class="row">

            <div class="col-md-4 profile-perso-info-body">
<span class="profile-perso-info-header">
	Personal Information <a id="edit_personal" href="#"><span id="personal_icon" class="glyphicon glyphicon-pencil" style="color:#000;"></span></a>
	</span>

                <br /><br />

                <form action="/peer/<%=sessionUser.getId()%>/profile" id="form_personal" method="post">

                    <table>
                        <tr>
                            <td><h6>First name</h6></td>
                            <td style="padding-bottom:10px;"><input type="text" class="form-control peerPersonal"
                                                                    name="first_name"
                                                                    value="<%= sessionUser.getFirstName().toString() %>" readonly>
                            </td>
                        </tr>
                        <tr>
                            <td><h6>Last name</h6></td>
                            <td style="padding-bottom:10px;"><input type="text" class="form-control peerPersonal"
                                                                    name="last_name"
                                                                    value="<%= sessionUser.getLastName().toString() %>" readonly>
                            </td>
                        </tr>
                        <tr>
                            <td><h6>Username</h6></td>
                            <td style="padding-bottom:10px;"><input type="text" class="form-control peerUsername"
                                                                    name="user_name"
                                                                    value="<%= sessionUser.getUserName().toString() %>" readonly>
                            </td>
                        </tr>
                        <tr>
                            <td><h6>Date of Birth &nbsp</h6></td>
                            <td style="padding-bottom:10px;"><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerDob" : "peerPersonal"%>"
                                                                    name="dob"
                                                                    value="<%= sessionUser.getDateOfBirth() == null ? "" : formatter.format(sessionUser.getDateOfBirth())%>" readonly>
                            </td>
                        </tr>
                    </table>
            </div>

            <div class="col-md-4 profile-perso-info-body">
                <span class="profile-perso-info-header2"></span>

                <div class="profile-perso-info-body2">
                    <br /><br />

                    <table>
                        <tr>
                            <td><h6>Gender</h6></td>
                            <td class="profile-perso-info-body2-push"><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerGender" : "peerPersonal"%>"
                                                                    name="gender"
                                                                    value="<%= sessionUser.getGender() == null ? "" : sessionUser.getGender().toString() %>" readonly>
                            </td>
                        </tr>
                        <tr>
                            <td><h6>Country</h6></td>
                            <td class="profile-perso-info-body2-push"><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerCountry" : "peerPersonal"%>"
                                                                    name="country"
                                                                    value="<%= sessionUser.getCountry().toString()%>" readonly>
                            </td>
                        </tr>
                        <tr>
                            <td><h6>Industry</h6></td>
                            <td class="profile-perso-info-body2-push"><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerIndustry" : "peerPersonal"%>"
                                                                    name="industry"
                                                                    value="<%=sessionUser.getIndustry().toString()%>" readonly>
                            </td>
                        </tr>
                        <tr>
                            <td><h6>Years <br />of Experience &nbsp</h6></td>
                            <td class="profile-perso-info-body2-push"><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerYoe" : "peerPersonal"%>"
                                                                    name="yoe"
                                                                    value="<%= sessionUser.getExperience() == null ? 0 : sessionUser.getExperience()%>" readonly>
                            </td>
                        </tr>
                        <input type="hidden" class="form-control peerID" name="peerId"
                               value="<%= sessionUser.getId()%>">
                        <input type="hidden" name="form" value="personal">

                        <tr>
                            <td colspan="2">
                                <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden; color:#e74c3c;"
                                   id="save_personal" value="Save" href="#">Save
                                    Changes</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            </form>


            <div class="col-md-4 profile-contact-body">
	<span class="profile-contact-header">
		Contact Information <a id="edit_contact" href="#"><span id="contact_icon" class="glyphicon glyphicon-pencil" style="color:#000;"></span></a>
	</span>

                <br /><br />

                <div class="profile-contact-body-push">
                    <form action="/peer/<%=sessionUser.getId()%>/profile" id="form_contact" method="post">
                        <table>
                            <tr>
                                <td><h6>Email</h6></td>
                                <td class="profile-perso-info-body2-push"><input type="text" class="form-control peerContact"
                                                                        name="email"
                                                                        value="<%= sessionUser.getEmail() == null ? "" : sessionUser.getEmail().toString() %>"
                                                                        readonly></td>
                            </tr>
                            <tr>
                                <td><h6>Website &nbsp</h6></td>
                                <td class="profile-perso-info-body2-push"><input type="text" class="form-control <%= sessionUser.getCompleteProfile() == 0 ? "peerEmail" : "peerContact"%>"
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
                    </form>

                    <tr>
                        <td colspan="2">
                            <a class ="pull-right" type="submit" class="pull-right" style="visibility:hidden; color:#e74c3c;"
                               id="save_contact" value="Save" href="#">Save
                                Changes</a>
                        </td>
                    </tr>
                    </table>

                </div>
            </div>

        </div>
</div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>