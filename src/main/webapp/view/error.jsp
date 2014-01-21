<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%@ include file="/view/includes/static/header.jsp" %>
<div class="container">
    <h1>Oh Snap! - <%= request.getAttribute( "errorCode" ) %>
    </h1>

    <div class="alert alert-error">
        <h4><%= request.getAttribute( "reason" ) %>
        </h4>
        <% Throwable e = null; %>
        <% if ( (e = (Throwable) request.getAttribute( "exception" )) != null ) { %>
        <pre><%= StringEscapeUtils.escapeHtml4( e.getMessage() ) %></pre>
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
    </div>

    <div class="row-fluid">
        <div class="span6 offset3">
            <i class="fa fa-bug fa-5x" id="bug"></i>
        </div>
    </div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>