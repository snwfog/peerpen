<%
    Peer sessionUser = (Peer) request.getAttribute("sessionUser");
%>
<% if(request.getAttribute("sessionUser") != null){%>
    <%@ include file="navbar_profile.jsp" %>
<% }else {%>
    <%@ include file="navbar_index.jsp" %>
<% } %>