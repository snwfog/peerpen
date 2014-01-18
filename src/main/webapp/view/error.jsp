<%@ include file="/view/includes/static/header.jsp" %>
<h1>Oh Snap! - <%= request.getAttribute("errorCode") %></h1>
<h4><%= request.getAttribute("reason") %></h4>
<%@ include file="/view/includes/static/footer.jsp" %>