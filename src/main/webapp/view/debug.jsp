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
        <h3><%= name %></h3>
        <pre><%= StringEscapeUtils.escapeHtml4( request.getAttribute( name ).toString() ) %></pre>
        <% } %>
    </div>

    <div class="well">
        <h1>HTTP Session Attributes</h1>
        <% if (session != null) { %>
            <h2><%= session.getClass().toString() %></h2>
            <% Enumeration sessionAttributes = request.getSession().getAttributeNames(); %>
            <% String sessionName = null; %>
            <% while ( sessionAttributes.hasMoreElements() &&
                    (sessionName = (String) sessionAttributes.nextElement()) != null ) { %>
            <h3><%= sessionName %></h3>
            <pre><%= StringEscapeUtils.escapeHtml4( request.getSession().getAttribute( sessionName ).toString() ) %></pre>
            <% } %>
        <% } else { %>
            <h2>Empty Session</h2>
        <% } %>
    </div>

    <div class="well">
        <h1>Cookies</h1>
        <% for ( Cookie cookie : request.getCookies() ) { %>
            <h3><%= cookie.getName() %> (<%= cookie.getMaxAge() %>)</h3>
            <pre><%= StringEscapeUtils.escapeHtml4( cookie.getValue() ) %></pre>
        <% } %>
    </div>
</div>
<% } %>


