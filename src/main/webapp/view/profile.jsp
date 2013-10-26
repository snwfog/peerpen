<%@ include file="/view/includes/static/header.jsp" %>

<%
    if (session.getAttribute("userSession") == null)
    {
        response.sendRedirect("/login");
    }
%>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">PeerPen</a>
        </div>

    </div>
</div>

<br><br><p align="center"><%= session.getAttribute("userSession") %>'s Profile Page  (<a href="/logout.do">logout</a>)</p>
<%@ include file="/view/includes/static/footer.jsp" %>