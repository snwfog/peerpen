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



<div class="page-wrap">

    <h1><%=viewedUser.getFirstName()%> <%=viewedUser.getLastName()%>'s Profile</h1>
    <h3><%=viewedUser.getFirstName()%> <%=viewedUser.getLastName()%></h3>
    <div class="description"><%=viewedUser.getDateOfBirth() == null ? "" : viewedUser.getDateOfBirth()%><%if(viewedUser.getDateOfBirth() != null && viewedUser.getGender() != null){%>, <%}%>
        <%=viewedUser.getGender() == null ? "" :viewedUser.getGender()%>
    </div>
    <div class="profile-set">

        <div class="public-profile">
            <img src="<%= viewedUser.getAvatar().getRelativeServletContextAvatarPathForSize( request,
             Avatar.Size.LARGE ) %>" >
            <h4>Contacts Information</h4>
            <div class="description"><%=viewedUser.getEmail() == null ? "" : viewedUser.getEmail()%><%if(viewedUser.getPersonalWebsite() != ""){%>, <%}%>
                <%=viewedUser.getPersonalWebsite() == null ? "" : viewedUser.getPersonalWebsite()%>
            </div>
        </div>
        <h4>Specialization</h4>
            <div class="description"><%=viewedUser.getIndustry() == null ? "" : viewedUser.getIndustry()%><%if ((viewedUser.getIndustry() != "") && (viewedUser.getExperience() != null)) {%>, <%}%>
                <%=viewedUser.getExperience() == null ? "" : viewedUser.getExperience()%>
                <% if (viewedUser.getExperience() == 0 || viewedUser.getExperience() == 1) { %> Year<% } else {%> Years<%}%><% if (!(viewedUser.getExperience() == null) && !(viewedUser.getCountry() == "")){%>, <%}%>
                <%=viewedUser.getCountry() == null ? "" : viewedUser.getCountry()%></div>
            <p><%=viewedUser.getDescription() == null ? "" : viewedUser.getDescription()%></p>

    </div>

</div>
<%@ include file="/view/includes/static/footer.jsp" %>