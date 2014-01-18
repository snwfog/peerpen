<%@ include file="/view/includes/static/header.jsp" %>
<div class="container">
    <h1>Oh Snap! - <%= request.getAttribute("errorCode") %></h1>
    <h4><%= request.getAttribute("reason") %></h4>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>