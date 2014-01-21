<%@ page import="java.util.Enumeration" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>

<% if ( request != null ) { %>
<div class="container code" id="debug">
    <div class="well">
        <h1>Internal Request Extra Parameters</h1>

        <h2><%= request.getClass().toString() %>
        </h2>
        <% Enumeration attributes = request.getAttributeNames(); %>
        <% String name = null; %>
        <% while ( attributes.hasMoreElements() && (name = (String) attributes.nextElement()) != null ) { %>
        <h3><%= name %>
        </h3>
        <pre><%= StringEscapeUtils.escapeHtml4( request.getAttribute( name ).toString() ) %></pre>
        <% } %>
    </div>
</div>
<% } %>


