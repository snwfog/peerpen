<%@ include file="/view/includes/static/header.jsp" %>
<div class="container">
    <% String errorCode = String.valueOf( request.getAttribute( "errorCode" ) ); %>
    <h1>Oh Snap! - <%= (errorCode != null ? errorCode :
            String.valueOf( request.getAttribute( "javax.servlet.error.status_code" ) )) %>
    </h1>

    <div class="alert alert-error">
        <% String reason = (String) request.getAttribute( "reason" ); %>
        <% if (request.getAttribute( "exception" ) == null
                && request.getAttribute( "javax.servlet.error.exception" ) == null && reason != null ) { %>
            <h4><%= StringEscapeUtils.escapeHtml4( reason ) %></h4>
        <% } else { %>
            <% Throwable e = null; %>
            <% if ( (e = (Throwable) request.getAttribute( "exception" )) != null ||
                    (e = (Throwable) request.getAttribute( "javax.servlet.error.exception" )) != null ) { %>
            <% if (reason != null) { %>
                <h4><%= reason %></h4>
            <% } else { %>
                <h4><%= e.getClass().toString() %></h4>
            <% } %>
            <% if (e.getMessage() != null && !e.getMessage().equalsIgnoreCase( "null" )) { %>
                <pre><%= StringEscapeUtils.escapeHtml4( e.getMessage() ) %></pre>
            <% } %>
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Trace</th>
                </tr>
                </thead>
                <tbody>
                <% int index = 1; %>
                <% for ( StackTraceElement el : e.getStackTrace() ) { %>
                <tr>
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
    </div>

    <i class="fa fa-bug fa-5x" id="bug"></i>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>