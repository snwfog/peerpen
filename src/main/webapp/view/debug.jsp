<%@ page import="java.util.Enumeration" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>

<div class="container well">
    <h1>Debug Quick Link</h1>
    <form action="/login" method="post"><input type="hidden" name="username" value="qwe" /><input type="hidden" name="password" value="qwe" /><button class="btn btn-primary" type="submit">Sign in</button></form>
    <h5><a href="/peer/2/profile">Profile</a></h5>
    <h5><a href="/peer/2/profile/avatar">Avatar</a></h5>
    <h5><a href="/search">Search</a></h5>
  <h5><a href="/tagsearch">Tagsearch</a></h5>
  <h5><a href="/group">Group</a></h5>
</div>

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


