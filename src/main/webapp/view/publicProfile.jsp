<%
  Peer viewedUser = (Peer) request.getAttribute("viewedUser");
  String fullName =    viewedUser.getFirstName() + " "+ viewedUser.getLastName();
%>
<jsp:include page="/view/includes/static/header.jsp">
    <jsp:param name="title" value="<%= fullName %>" />
  </jsp:include>
<%@ include file="/view/includes/static/navbar.jsp" %>

<%
    ArrayList<Group> navGroups =(ArrayList<Group>) sessionUser.getGroups();
%>

<div class="cover-container pp-body"> </div>
<div class="container-fluid wrap-container">

    <h1 class="pp-header"><%= viewedUser.getFirstName()== null ? viewedUser.getUserName().toString() : viewedUser.getFirstName().toString() %> <%= viewedUser.getLastName()== null ? "" : viewedUser.getLastName().toString() %>'s Profile</h1>

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

    <table class="col-md-9 profile-about-me-body pp-about-me-pointer">
    <tr>
        <td class="pp-basic-info-body">
        <div class="profile-about-me-pointer pp-basic-info-pointer"></div>
        <span class="pp-basic-info-header"><%= viewedUser.getFirstName()== null ? viewedUser.getUserName().toString() : viewedUser.getFirstName().toString() %> <%= viewedUser.getLastName()== null ? "" : viewedUser.getLastName().toString() %></span>

        <br /><br />

        <span class="pp-basic-info-title">Basic Information</span>
        <br />
        <%=viewedUser.getDateOfBirth() == null ? "" : "Date of Birth: " + viewedUser.getDateOfBirth()%>
        <br />
        <%=viewedUser.getGender() == null ? "" : "Gender: " + viewedUser.getGender()%>
        <br />
        <%=viewedUser.getCountry() == null ? "" : "Country: " + viewedUser.getCountry()%>

        <br /><br />
        <span class="pp-basic-info-title">Specialization</span>
        <br/>
        <div><%=viewedUser.getIndustry() == null ? "" : "Industry: " + viewedUser.getIndustry()%>
            <br />
            <%=viewedUser.getExperience() == null ? "" : "Years of Experience: " + viewedUser.getExperience()%>
            <% if (viewedUser.getExperience() == 0 || viewedUser.getExperience() == 1) { %> Year<% } else {%> Years<%}%>
            <br />
            <%=viewedUser.getPersonalWebsite() == null ? "" : "Personal Website: " + viewedUser.getPersonalWebsite()%>
        </div>

        <br/><br />
        <p><%=viewedUser.getDescription() == null ? "" : "<span class='pp-desc-body'>&#8220;</span>" + viewedUser.getDescription() + "<span class='pp-desc-body'>&#8221;</span>"%></p>
        </td>

        <td class="pp-group-joined-body">
        <span class="pp-basic-info-title">Groups you have joined</span>
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