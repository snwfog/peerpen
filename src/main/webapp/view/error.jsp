<%@ include file="/view/includes/static/header.jsp" %>
<div class="container">
    <% String errorCode = String.valueOf( request.getAttribute( "errorCode" ) ); %>
    <h1>Oh Snap! - <%= (errorCode != null ? errorCode :
            String.valueOf( request.getAttribute( "javax.servlet.error.status_code" ) )) %>
    </h1>
        <% String reason = (String) request.getAttribute( "reason" ); %>
        <% if (request.getAttribute( "exception" ) == null
                && request.getAttribute( "javax.servlet.error.exception" ) == null && reason != null ) { %>
            <div class="alert alert-error" id="exception-reason">
                <h4><%= StringEscapeUtils.escapeHtml4( reason ) %></h4>
            </div>
        <% } else { %>
            <% Throwable e = null; %>
            <% if ( (e = (Throwable) request.getAttribute( "exception" )) != null ||
                    (e = (Throwable) request.getAttribute( "javax.servlet.error.exception" )) != null ) { %>
                <% if (reason != null) { %>
                    <div class="alert alert-error" id="exception-reason">
                        <h4><%= reason %></h4>
                    </div>
                <% } else { %>
                    <div class="alert alert-error" id="exception-reason">
                        <h4><%= e.getClass().toString() %></h4>
                    </div>
                <% } %>
            <% if (e.getMessage() != null && !e.getMessage().equalsIgnoreCase( "null" )) { %>
                <pre><%= StringEscapeUtils.escapeHtml4( e.getMessage() ) %></pre>
            <% } %>
            <table class="table" id="stacktrace">
                <tbody>
                <% int index = 1; %>
                <% for ( StackTraceElement el : e.getStackTrace() ) { %>
                <tr class="stack-<%= index %>">
                    <td><%= index++ %>
                    </td>
                    <td><%= el.toString() %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <% } %>
        <% } %>
    <i class="fa fa-bug fa-5x" id="bug"></i>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>