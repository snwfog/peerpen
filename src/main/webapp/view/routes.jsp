<%@ page import="java.util.Set" %>
<%@ include file="/view/includes/static/header.jsp" %>

<div class="container">
    <% Set<String> routes = (Set<String>) request.getAttribute( "routes" ); %>

    <h1>Routes</h1>
    <h5>Configurable resource routes determined via config/resources.yml</h5>
    <pre>
        <% int index = 1; %>
        <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Route</th>
                </tr>
            </thead>
            <tbody>
                <% for ( String route : routes ) { %>
                    <tr>
                        <td><%= index++ %></td>
                        <td><%= route %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </pre>

</div>
<%@ include file="/view/includes/static/footer.jsp" %>
