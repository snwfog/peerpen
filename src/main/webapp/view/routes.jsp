<%@ page import="java.util.Set" %>
<%@ include file="/view/includes/static/header.jsp" %>

<% Set<String> routes = (Set<String>) request.getAttribute( "routes" ); %>

<h1>Routes</h1>
<pre>
    <% for ( String route : routes ) { %>
        <%= route %>
    <% } %>
</pre>
<%@ include file="/view/includes/static/footer.jsp" %>
