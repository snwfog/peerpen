<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar.jsp" %>

<%
    Peer viewedUser = (Peer) request.getAttribute("viewedUser");
    ArrayList<Group> navGroups =(ArrayList<Group>) sessionUser.getGroups();
%>

<div class="cover-container" style="padding:60px;"> </div>
<div class="container-fluid wrap-container">

    <h1 style="font-family:'Oswald', sans-serif; border-bottom:0px solid #bdc3c7;"><%=viewedUser.getFirstName()%> <%=viewedUser.getLastName()%>'s Profile</h1>

    <div class="row">

    <div class="col-md-3">
        <div>
            <img src="<%= viewedUser.getAvatar().getRelativeServletContextAvatarPathForSize( request,
             Avatar.Size.LARGE ) %>" style="width:200px">
            <h4>Contacts Information</h4>
            <div><%=viewedUser.getEmail() == null ? "" : viewedUser.getEmail()%>
            </div>
        </div>
    </div>

    <table class="col-md-9 profile-about-me-body" style="background-color:#fff; position:relative; left:-15px; text-align:left; padding:0px 0px 25px 25px;">
    <tr>
        <td style="width:65%; vertical-align: top; padding:25px;">
        <div class="profile-about-me-pointer" style="left:-55px; top:30px; border-top: 15px solid transparent; border-bottom: 15px solid transparent; border-right:15px solid #fff;"></div>
        <span style="font-family:'Oswald', sans-serif; font-weight:400; font-size:25px;"><%=viewedUser.getFirstName()%> <%=viewedUser.getLastName()%></span>

        <br /><br />

        <span style="font-family:'Oswald', sans-serif; font-weight:400; font-size:18px;">Basic Information</span>
        <br />
        <%=viewedUser.getDateOfBirth() == null ? "" : "Date of Birth: " + viewedUser.getDateOfBirth()%>
        <br />
        <%=viewedUser.getGender() == null ? "" : "Gender: " + viewedUser.getGender()%>
        <br />
        <%=viewedUser.getCountry() == null ? "" : "Country: " + viewedUser.getCountry()%>

        <br /><br />
        <span style="font-family:'Oswald', sans-serif; font-weight:400; font-size:18px;">Specialization</span>
        <br/>
        <div><%=viewedUser.getIndustry() == null ? "" : "Industry: " + viewedUser.getIndustry()%>
            <br />
            <%=viewedUser.getExperience() == null ? "" : "Years of Experience: " + viewedUser.getExperience()%>
            <% if (viewedUser.getExperience() == 0 || viewedUser.getExperience() == 1) { %> Year<% } else {%> Years<%}%>
            <br />
            <%=viewedUser.getPersonalWebsite() == null ? "" : "Personal Website: " + viewedUser.getPersonalWebsite()%>
        </div>

        <br/><br />
        <p><%=viewedUser.getDescription() == null ? "" : "<span style='font-family:\"Times New Roman\", Times, serif; color:#e74c3c; font-size:75px;'>&#8220;</span>" + viewedUser.getDescription() + "<span style='font-family:\"Times New Roman\", Times, serif; color:#e74c3c; font-size:75px;'>&#8221;</span>"%></p>
        </td>

        <td style="width:35%; color:#fff; background-color:#e74c3c; border-radius:0px 25px 25px 0px; vertical-align: top; padding:25px;">
        <span style="font-family:'Oswald', sans-serif; font-weight:400; font-size:18px;">Groups you have joined</span>
            <br /><br />
            <% for(Group g: navGroups){%>
            <a href="/group/<%=g.getId() %>" style="color:#fff;"><%=g.getGroupName()%></a><br />
            <%}%>
        </td>
    </tr>

    </table>

    </div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>